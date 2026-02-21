package com.unity3d.services.ads.token;

import android.os.Handler;
import android.os.Looper;
import com.unity3d.ads.IUnityAdsTokenListener;
import com.unity3d.services.core.configuration.Configuration;
import com.unity3d.services.core.configuration.ConfigurationReader;
import com.unity3d.services.core.configuration.PrivacyConfigStorage;
import com.unity3d.services.core.device.TokenType;
import com.unity3d.services.core.device.reader.DeviceInfoReaderBuilder;
import com.unity3d.services.core.device.reader.GameSessionIdReader;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.properties.InitializationStatusReader;
import com.unity3d.services.core.properties.SdkProperties;
import com.unity3d.services.core.request.metrics.AdOperationMetric;
import com.unity3d.services.core.request.metrics.ISDKMetrics;
import com.unity3d.services.core.request.metrics.SDKMetrics;
import com.unity3d.services.core.request.metrics.TSIMetric;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class AsyncTokenStorage {
    private static AsyncTokenStorage _instance;
    private final Handler _handler;
    private INativeTokenGenerator _nativeTokenGenerator;
    private final ISDKMetrics _sdkMetrics;
    private final List<TokenListenerState> _tokenListeners = new LinkedList();
    private boolean _tokenAvailable = false;
    private boolean _configurationWasSet = false;
    private Configuration _configuration = new Configuration();
    private final InitializationStatusReader _initStatusReader = new InitializationStatusReader();

    public static AsyncTokenStorage getInstance() {
        if (_instance == null) {
            _instance = new AsyncTokenStorage(null, new Handler(Looper.getMainLooper()), SDKMetrics.getInstance());
        }
        return _instance;
    }

    class TokenListenerState {
        public boolean invoked;
        public IUnityAdsTokenListener listener;
        public Runnable runnable;
        public TokenType tokenType;

        TokenListenerState() {
        }
    }

    public AsyncTokenStorage(INativeTokenGenerator iNativeTokenGenerator, Handler handler, ISDKMetrics iSDKMetrics) {
        this._handler = handler;
        this._nativeTokenGenerator = iNativeTokenGenerator;
        this._sdkMetrics = iSDKMetrics;
    }

    public synchronized void setConfiguration(Configuration configuration) {
        this._configuration = configuration;
        boolean zIsValidConfig = isValidConfig(configuration);
        this._configurationWasSet = zIsValidConfig;
        if (zIsValidConfig) {
            if (this._nativeTokenGenerator == null) {
                DeviceInfoReaderBuilder deviceInfoReaderBuilder = new DeviceInfoReaderBuilder(new ConfigurationReader(), PrivacyConfigStorage.getInstance(), GameSessionIdReader.getInstance());
                ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
                this._nativeTokenGenerator = new NativeTokenGenerator(executorServiceNewSingleThreadExecutor, deviceInfoReaderBuilder);
                if (configuration.getExperiments().shouldNativeTokenAwaitPrivacy()) {
                    this._nativeTokenGenerator = new NativeTokenGeneratorWithPrivacyAwait(executorServiceNewSingleThreadExecutor, this._nativeTokenGenerator, configuration.getPrivacyRequestWaitTimeout());
                }
            }
            Iterator it = new ArrayList(this._tokenListeners).iterator();
            while (it.hasNext()) {
                handleTokenInvocation((TokenListenerState) it.next());
            }
        }
    }

    public synchronized void onTokenAvailable() {
        this._tokenAvailable = true;
        if (this._configurationWasSet) {
            notifyListenersTokenReady();
        }
    }

    public synchronized void getToken(IUnityAdsTokenListener iUnityAdsTokenListener) {
        if (SdkProperties.getCurrentInitializationState() == SdkProperties.InitializationState.INITIALIZED_FAILED) {
            iUnityAdsTokenListener.onUnityAdsTokenReady(null);
            sendTokenMetrics(null, TokenType.TOKEN_REMOTE);
        } else if (SdkProperties.getCurrentInitializationState() == SdkProperties.InitializationState.NOT_INITIALIZED) {
            iUnityAdsTokenListener.onUnityAdsTokenReady(null);
            sendTokenMetrics(null, TokenType.TOKEN_REMOTE);
        } else {
            TokenListenerState tokenListenerStateAddTimeoutHandler = addTimeoutHandler(iUnityAdsTokenListener);
            if (this._configurationWasSet) {
                handleTokenInvocation(tokenListenerStateAddTimeoutHandler);
            }
        }
    }

    private synchronized TokenListenerState addTimeoutHandler(IUnityAdsTokenListener iUnityAdsTokenListener) {
        final TokenListenerState tokenListenerState;
        tokenListenerState = new TokenListenerState();
        tokenListenerState.listener = iUnityAdsTokenListener;
        tokenListenerState.tokenType = TokenType.TOKEN_REMOTE;
        tokenListenerState.runnable = new Runnable() { // from class: com.unity3d.services.ads.token.AsyncTokenStorage.1
            @Override // java.lang.Runnable
            public void run() {
                AsyncTokenStorage.this.notifyTokenReady(tokenListenerState, null);
            }
        };
        this._tokenListeners.add(tokenListenerState);
        this._handler.postDelayed(tokenListenerState.runnable, this._configuration.getTokenTimeout());
        return tokenListenerState;
    }

    private synchronized void notifyListenersTokenReady() {
        String token;
        while (!this._tokenListeners.isEmpty() && (token = TokenStorage.getToken()) != null) {
            notifyTokenReady(this._tokenListeners.get(0), token);
        }
    }

    private void handleTokenInvocation(final TokenListenerState tokenListenerState) {
        if (tokenListenerState.invoked) {
            return;
        }
        tokenListenerState.invoked = true;
        if (!this._tokenAvailable && this._configuration.getExperiments().isNativeTokenEnabled()) {
            tokenListenerState.tokenType = TokenType.TOKEN_NATIVE;
            this._nativeTokenGenerator.generateToken(new INativeTokenGeneratorListener() { // from class: com.unity3d.services.ads.token.AsyncTokenStorage.2
                @Override // com.unity3d.services.ads.token.INativeTokenGeneratorListener
                public void onReady(final String str) {
                    AsyncTokenStorage.this._handler.post(new Runnable() { // from class: com.unity3d.services.ads.token.AsyncTokenStorage.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AsyncTokenStorage.this.notifyTokenReady(tokenListenerState, str);
                        }
                    });
                }
            });
            return;
        }
        tokenListenerState.tokenType = TokenType.TOKEN_REMOTE;
        String token = TokenStorage.getToken();
        if (token == null || token.isEmpty()) {
            return;
        }
        notifyTokenReady(tokenListenerState, token);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void notifyTokenReady(TokenListenerState tokenListenerState, String str) {
        if (this._tokenListeners.remove(tokenListenerState)) {
            tokenListenerState.listener.onUnityAdsTokenReady(str);
            try {
                this._handler.removeCallbacks(tokenListenerState.runnable);
            } catch (Exception e) {
                DeviceLog.exception("Failed to remove callback from a handler", e);
            }
            sendTokenMetrics(str, tokenListenerState.tokenType);
        } else {
            sendTokenMetrics(str, tokenListenerState.tokenType);
        }
    }

    /* JADX INFO: renamed from: com.unity3d.services.ads.token.AsyncTokenStorage$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$unity3d$services$core$device$TokenType;

        static {
            int[] iArr = new int[TokenType.values().length];
            $SwitchMap$com$unity3d$services$core$device$TokenType = iArr;
            try {
                iArr[TokenType.TOKEN_NATIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$unity3d$services$core$device$TokenType[TokenType.TOKEN_REMOTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void sendTokenMetrics(String str, TokenType tokenType) {
        int i = AnonymousClass3.$SwitchMap$com$unity3d$services$core$device$TokenType[tokenType.ordinal()];
        if (i == 1) {
            sendNativeTokenMetrics(str);
        } else if (i == 2) {
            sendRemoteTokenMetrics(str);
        } else {
            DeviceLog.error("Unknown token type passed to sendTokenMetrics");
        }
    }

    private void sendNativeTokenMetrics(String str) {
        ISDKMetrics iSDKMetrics = this._sdkMetrics;
        if (iSDKMetrics == null) {
            return;
        }
        if (str == null) {
            iSDKMetrics.sendMetric(TSIMetric.newNativeGeneratedTokenNull(getMetricTags()));
        } else {
            iSDKMetrics.sendMetric(TSIMetric.newNativeGeneratedTokenAvailable(getMetricTags()));
        }
    }

    private void sendRemoteTokenMetrics(String str) {
        if (this._sdkMetrics == null) {
            return;
        }
        if (str == null || str.isEmpty()) {
            this._sdkMetrics.sendMetric(TSIMetric.newAsyncTokenNull(getMetricTags()));
        } else {
            this._sdkMetrics.sendMetric(TSIMetric.newAsyncTokenAvailable(getMetricTags()));
        }
    }

    private Map<String, String> getMetricTags() {
        HashMap map = new HashMap();
        map.put(AdOperationMetric.INIT_STATE, this._initStatusReader.getInitializationStateString(SdkProperties.getCurrentInitializationState()));
        return map;
    }

    private boolean isValidConfig(Configuration configuration) {
        return (configuration == null || configuration.getExperiments() == null) ? false : true;
    }
}
