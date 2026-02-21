package com.unity3d.services.ads.configuration;

import android.os.ConditionVariable;
import com.unity3d.services.ads.UnityAdsImplementation;
import com.unity3d.services.ads.adunit.AdUnitOpen;
import com.unity3d.services.ads.adunit.VideoPlayerHandler;
import com.unity3d.services.ads.adunit.WebPlayerHandler;
import com.unity3d.services.ads.adunit.WebViewHandler;
import com.unity3d.services.ads.api.AdUnit;
import com.unity3d.services.ads.api.GMAScar;
import com.unity3d.services.ads.api.Load;
import com.unity3d.services.ads.api.Show;
import com.unity3d.services.ads.api.Token;
import com.unity3d.services.ads.api.VideoPlayer;
import com.unity3d.services.ads.api.WebPlayer;
import com.unity3d.services.ads.token.AsyncTokenStorage;
import com.unity3d.services.ads.token.TokenStorage;
import com.unity3d.services.core.configuration.Configuration;
import com.unity3d.services.core.configuration.ErrorState;
import com.unity3d.services.core.log.DeviceLog;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AdsModuleConfiguration implements IAdsModuleConfiguration {
    private InetAddress _address;

    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    public Class[] getWebAppApiClassList() {
        return new Class[]{AdUnit.class, VideoPlayer.class, WebPlayer.class, Load.class, Show.class, Token.class, GMAScar.class};
    }

    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    public boolean resetState(Configuration configuration) {
        AdUnitOpen.setConfiguration(configuration);
        UnityAdsImplementation.setConfiguration(configuration);
        TokenStorage.deleteTokens();
        AsyncTokenStorage.getInstance().setConfiguration(configuration);
        return true;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [com.unity3d.services.ads.configuration.AdsModuleConfiguration$1] */
    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    public boolean initModuleState(Configuration configuration) {
        final ConditionVariable conditionVariable;
        InetAddress inetAddress;
        DeviceLog.debug("Unity Ads init: checking for ad blockers");
        try {
            final String host = new URL(configuration.getConfigUrl()).getHost();
            conditionVariable = new ConditionVariable();
            new Thread() { // from class: com.unity3d.services.ads.configuration.AdsModuleConfiguration.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        AdsModuleConfiguration.this._address = InetAddress.getByName(host);
                        conditionVariable.open();
                    } catch (Exception e) {
                        DeviceLog.exception("Couldn't get address. Host: " + host, e);
                        conditionVariable.open();
                    }
                }
            }.start();
        } catch (MalformedURLException unused) {
        }
        if (conditionVariable.block(2000L) && (inetAddress = this._address) != null && inetAddress.isLoopbackAddress()) {
            DeviceLog.error("Unity Ads init: halting init because Unity Ads config resolves to loopback address (due to ad blocker?)");
            return false;
        }
        AdUnitOpen.setConfiguration(configuration);
        UnityAdsImplementation.setConfiguration(configuration);
        AsyncTokenStorage.getInstance().setConfiguration(configuration);
        return true;
    }

    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    public boolean initErrorState(Configuration configuration, ErrorState errorState, String str) {
        TokenStorage.setInitToken(null);
        TokenStorage.deleteTokens();
        return true;
    }

    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    public boolean initCompleteState(Configuration configuration) {
        AdUnitOpen.setConfiguration(configuration);
        UnityAdsImplementation.setConfiguration(configuration);
        AsyncTokenStorage.getInstance().setConfiguration(configuration);
        return true;
    }

    @Override // com.unity3d.services.ads.configuration.IAdsModuleConfiguration
    public Map<String, Class> getAdUnitViewHandlers() {
        HashMap map = new HashMap();
        map.put("videoplayer", VideoPlayerHandler.class);
        map.put("webplayer", WebPlayerHandler.class);
        map.put("webview", WebViewHandler.class);
        return map;
    }
}
