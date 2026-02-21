package com.unity3d.services.ads.token;

import com.unity3d.services.core.configuration.ConfigurationReader;
import com.unity3d.services.core.configuration.InitializeEventsMetricSender;
import com.unity3d.services.core.configuration.PrivacyConfigStorage;
import com.unity3d.services.core.device.reader.DeviceInfoReaderBuilder;
import com.unity3d.services.core.device.reader.GameSessionIdReader;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class TokenStorage {
    private static int _accessCounter = 0;
    private static String _initToken = null;
    private static boolean _peekMode = false;
    private static ConcurrentLinkedQueue<String> _queue;
    private static final Object _lock = new Object();
    private static ExecutorService _executorService = Executors.newSingleThreadExecutor();

    public static void createTokens(JSONArray jSONArray) throws JSONException {
        boolean zIsEmpty;
        synchronized (_lock) {
            _queue = new ConcurrentLinkedQueue<>();
            _accessCounter = 0;
            for (int i = 0; i < jSONArray.length(); i++) {
                _queue.add(jSONArray.getString(i));
            }
            zIsEmpty = _queue.isEmpty();
        }
        if (zIsEmpty) {
            return;
        }
        triggerTokenAvailable(false);
        AsyncTokenStorage.getInstance().onTokenAvailable();
    }

    public static void appendTokens(JSONArray jSONArray) throws JSONException {
        boolean zIsEmpty;
        synchronized (_lock) {
            if (_queue == null) {
                _queue = new ConcurrentLinkedQueue<>();
                _accessCounter = 0;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                _queue.add(jSONArray.getString(i));
            }
            zIsEmpty = _queue.isEmpty();
        }
        if (zIsEmpty) {
            return;
        }
        triggerTokenAvailable(false);
        AsyncTokenStorage.getInstance().onTokenAvailable();
    }

    public static void deleteTokens() {
        synchronized (_lock) {
            _queue = null;
            _accessCounter = 0;
        }
    }

    public static String getToken() {
        synchronized (_lock) {
            ConcurrentLinkedQueue<String> concurrentLinkedQueue = _queue;
            if (concurrentLinkedQueue == null) {
                return _initToken;
            }
            if (concurrentLinkedQueue.isEmpty()) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.TOKEN, TokenEvent.QUEUE_EMPTY, new Object[0]);
                return null;
            }
            if (_peekMode) {
                WebViewApp currentApp = WebViewApp.getCurrentApp();
                WebViewEventCategory webViewEventCategory = WebViewEventCategory.TOKEN;
                TokenEvent tokenEvent = TokenEvent.TOKEN_ACCESS;
                int i = _accessCounter;
                _accessCounter = i + 1;
                currentApp.sendEvent(webViewEventCategory, tokenEvent, Integer.valueOf(i));
                return _queue.peek();
            }
            WebViewApp currentApp2 = WebViewApp.getCurrentApp();
            WebViewEventCategory webViewEventCategory2 = WebViewEventCategory.TOKEN;
            TokenEvent tokenEvent2 = TokenEvent.TOKEN_ACCESS;
            int i2 = _accessCounter;
            _accessCounter = i2 + 1;
            currentApp2.sendEvent(webViewEventCategory2, tokenEvent2, Integer.valueOf(i2));
            return _queue.poll();
        }
    }

    public static void setPeekMode(boolean z) {
        synchronized (_lock) {
            _peekMode = z;
        }
    }

    public static void getNativeGeneratedToken() {
        new NativeTokenGenerator(_executorService, new DeviceInfoReaderBuilder(new ConfigurationReader(), PrivacyConfigStorage.getInstance(), GameSessionIdReader.getInstance()), null).generateToken(new INativeTokenGeneratorListener() { // from class: com.unity3d.services.ads.token.TokenStorage.1
            @Override // com.unity3d.services.ads.token.INativeTokenGeneratorListener
            public void onReady(String str) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.TOKEN, TokenEvent.TOKEN_NATIVE_DATA, str);
            }
        });
    }

    public static void setInitToken(String str) {
        boolean z;
        synchronized (_lock) {
            _initToken = str;
            z = str != null;
        }
        if (z) {
            triggerTokenAvailable(true);
            AsyncTokenStorage.getInstance().onTokenAvailable();
        }
    }

    private static void triggerTokenAvailable(Boolean bool) {
        InitializeEventsMetricSender.getInstance().sdkTokenDidBecomeAvailableWithConfig(bool.booleanValue());
    }
}
