package com.unity3d.services.ads.gmascar.finder;

import com.unity3d.scar.adapter.common.GMAEvent;
import com.unity3d.services.ads.gmascar.bridges.MobileAdsBridge;
import com.unity3d.services.ads.gmascar.listeners.IInitializationStatusListener;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;

/* JADX INFO: loaded from: classes.dex */
public class ScarVersionFinder implements IInitializationStatusListener {
    private static MobileAdsBridge _mobileAdsBridge;
    private GMAInitializer _gmaInitializer;
    private long _gmaSdkVersionCode = -1;
    private PresenceDetector _presenceDetector;

    public ScarVersionFinder(MobileAdsBridge mobileAdsBridge, PresenceDetector presenceDetector, GMAInitializer gMAInitializer) {
        _mobileAdsBridge = mobileAdsBridge;
        this._presenceDetector = presenceDetector;
        this._gmaInitializer = gMAInitializer;
        gMAInitializer.getInitializeListenerBridge().setStatusListener(this);
    }

    public void getVersion() {
        try {
            if (!this._presenceDetector.areGMAClassesPresent()) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.INIT_GMA, GMAEvent.VERSION, "0.0.0");
            } else if (!this._gmaInitializer.isInitialized()) {
                this._gmaInitializer.initializeGMA();
            } else {
                findAndSendVersion(true);
            }
        } catch (Exception e) {
            DeviceLog.debug("Got exception finding GMA SDK: %s", e.getLocalizedMessage());
        }
    }

    public void findAndSendVersion(boolean z) {
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.INIT_GMA, GMAEvent.VERSION, z ? _mobileAdsBridge.getVersionString() : "0.0.0");
    }

    public long getGoogleSdkVersionCode() {
        String versionString;
        if (this._gmaSdkVersionCode == -1 && (versionString = _mobileAdsBridge.getVersionString()) != null) {
            String[] strArrSplit = versionString.split("\\.");
            if (strArrSplit.length > 1) {
                this._gmaSdkVersionCode = Long.parseLong(strArrSplit[1]);
            }
        }
        return this._gmaSdkVersionCode;
    }

    @Override // com.unity3d.services.ads.gmascar.listeners.IInitializationStatusListener
    public void onInitializationComplete(Object obj) {
        findAndSendVersion(this._gmaInitializer.initSuccessful(obj));
    }
}
