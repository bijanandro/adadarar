package com.unity3d.services.ads.gmascar;

import com.unity3d.scar.adapter.common.GMAAdsError;
import com.unity3d.scar.adapter.common.GMAEvent;
import com.unity3d.scar.adapter.common.IScarAdapter;
import com.unity3d.scar.adapter.common.WebViewAdsError;
import com.unity3d.scar.adapter.common.scarads.ScarAdMetadata;
import com.unity3d.services.ads.gmascar.adapters.ScarAdapterFactory;
import com.unity3d.services.ads.gmascar.bridges.AdapterStatusBridge;
import com.unity3d.services.ads.gmascar.bridges.InitializationStatusBridge;
import com.unity3d.services.ads.gmascar.bridges.InitializeListenerBridge;
import com.unity3d.services.ads.gmascar.bridges.MobileAdsBridge;
import com.unity3d.services.ads.gmascar.finder.GMAInitializer;
import com.unity3d.services.ads.gmascar.finder.PresenceDetector;
import com.unity3d.services.ads.gmascar.finder.ScarVersionFinder;
import com.unity3d.services.ads.gmascar.handlers.ScarInterstitialAdHandler;
import com.unity3d.services.ads.gmascar.handlers.ScarRewardedAdHandler;
import com.unity3d.services.ads.gmascar.handlers.SignalsHandler;
import com.unity3d.services.ads.gmascar.handlers.WebViewErrorHandler;
import com.unity3d.services.ads.gmascar.utils.GMAEventSender;
import com.unity3d.services.core.misc.EventSubject;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.timer.DefaultIntervalTimerFactory;
import java.util.ArrayDeque;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class GMAScarAdapterBridge {
    private final AdapterStatusBridge _adapterStatusBridge;
    private final GMAEventSender _gmaEventSender;
    private final GMAInitializer _gmaInitializer;
    private final InitializeListenerBridge _initializationListenerBridge;
    private final InitializationStatusBridge _initializationStatusBridge;
    private final MobileAdsBridge _mobileAdsBridge;
    private final PresenceDetector _presenceDetector;
    private IScarAdapter _scarAdapter;
    private final ScarAdapterFactory _scarAdapterFactory;
    private final ScarVersionFinder _scarVersionFinder;
    private final WebViewErrorHandler _webViewErrorHandler;

    public GMAScarAdapterBridge() {
        MobileAdsBridge mobileAdsBridge = new MobileAdsBridge();
        this._mobileAdsBridge = mobileAdsBridge;
        InitializationStatusBridge initializationStatusBridge = new InitializationStatusBridge();
        this._initializationStatusBridge = initializationStatusBridge;
        InitializeListenerBridge initializeListenerBridge = new InitializeListenerBridge();
        this._initializationListenerBridge = initializeListenerBridge;
        AdapterStatusBridge adapterStatusBridge = new AdapterStatusBridge();
        this._adapterStatusBridge = adapterStatusBridge;
        this._webViewErrorHandler = new WebViewErrorHandler();
        this._scarAdapterFactory = new ScarAdapterFactory();
        PresenceDetector presenceDetector = new PresenceDetector(mobileAdsBridge, initializeListenerBridge, initializationStatusBridge, adapterStatusBridge);
        this._presenceDetector = presenceDetector;
        GMAInitializer gMAInitializer = new GMAInitializer(mobileAdsBridge, initializeListenerBridge, initializationStatusBridge, adapterStatusBridge);
        this._gmaInitializer = gMAInitializer;
        this._scarVersionFinder = new ScarVersionFinder(mobileAdsBridge, presenceDetector, gMAInitializer);
        this._gmaEventSender = new GMAEventSender();
    }

    public void initializeScar() {
        if (this._presenceDetector.areGMAClassesPresent()) {
            this._gmaEventSender.send(GMAEvent.SCAR_PRESENT, new Object[0]);
            this._gmaInitializer.initializeGMA();
        } else {
            this._webViewErrorHandler.handleError((WebViewAdsError) new GMAAdsError(GMAEvent.SCAR_NOT_PRESENT, new Object[0]));
        }
    }

    public boolean isInitialized() {
        return this._gmaInitializer.isInitialized();
    }

    public void getVersion() {
        this._scarVersionFinder.getVersion();
    }

    public void getSCARSignals(String[] strArr, String[] strArr2) {
        this._scarAdapter = getScarAdapterObject();
        SignalsHandler signalsHandler = new SignalsHandler();
        IScarAdapter iScarAdapter = this._scarAdapter;
        if (iScarAdapter != null) {
            iScarAdapter.getSCARSignals(ClientProperties.getApplicationContext(), strArr, strArr2, signalsHandler);
        } else {
            this._webViewErrorHandler.handleError((WebViewAdsError) GMAAdsError.InternalSignalsError("Could not create SCAR adapter object"));
        }
    }

    public void load(boolean z, String str, String str2, String str3, String str4, int i) {
        ScarAdMetadata scarAdMetadata = new ScarAdMetadata(str, str2, str4, str3, Integer.valueOf(i));
        IScarAdapter scarAdapterObject = getScarAdapterObject();
        this._scarAdapter = scarAdapterObject;
        if (scarAdapterObject == null) {
            this._webViewErrorHandler.handleError((WebViewAdsError) GMAAdsError.InternalLoadError(scarAdMetadata, "Scar Adapter object is null"));
        } else if (z) {
            loadInterstitialAd(scarAdMetadata);
        } else {
            loadRewardedAd(scarAdMetadata);
        }
    }

    private void loadInterstitialAd(ScarAdMetadata scarAdMetadata) {
        this._scarAdapter.loadInterstitialAd(ClientProperties.getApplicationContext(), scarAdMetadata, new ScarInterstitialAdHandler(scarAdMetadata, getScarEventSubject(scarAdMetadata.getVideoLengthMs())));
    }

    private void loadRewardedAd(ScarAdMetadata scarAdMetadata) {
        this._scarAdapter.loadRewardedAd(ClientProperties.getApplicationContext(), scarAdMetadata, new ScarRewardedAdHandler(scarAdMetadata, getScarEventSubject(scarAdMetadata.getVideoLengthMs())));
    }

    public void show(String str, String str2, boolean z) {
        ScarAdMetadata scarAdMetadata = new ScarAdMetadata(str, str2);
        IScarAdapter scarAdapterObject = getScarAdapterObject();
        this._scarAdapter = scarAdapterObject;
        if (scarAdapterObject != null) {
            scarAdapterObject.show(ClientProperties.getActivity(), str2, str);
        } else {
            this._webViewErrorHandler.handleError((WebViewAdsError) GMAAdsError.InternalShowError(scarAdMetadata, "Scar Adapter object is null"));
        }
    }

    private EventSubject getScarEventSubject(Integer num) {
        return new EventSubject(new ArrayDeque(Arrays.asList(GMAEvent.FIRST_QUARTILE, GMAEvent.MIDPOINT, GMAEvent.THIRD_QUARTILE, GMAEvent.LAST_QUARTILE)), num, new DefaultIntervalTimerFactory());
    }

    private IScarAdapter getScarAdapterObject() {
        if (this._scarAdapter == null) {
            this._scarAdapter = this._scarAdapterFactory.createScarAdapter(this._scarVersionFinder.getGoogleSdkVersionCode(), this._webViewErrorHandler);
        }
        return this._scarAdapter;
    }
}
