package com.unity3d.services.ads.adunit;

import android.os.Bundle;
import android.view.View;
import com.unity3d.services.ads.webplayer.WebPlayerSettingsCache;
import com.unity3d.services.ads.webplayer.WebPlayerView;
import com.unity3d.services.ads.webplayer.WebPlayerViewCache;
import com.unity3d.services.core.misc.ViewUtilities;

/* JADX INFO: loaded from: classes.dex */
public class WebPlayerHandler implements IAdUnitViewHandler {
    private static String webPlayerViewId = "webplayer";
    private WebPlayerView _webPlayerView;

    @Override // com.unity3d.services.ads.adunit.IAdUnitViewHandler
    public void onPause(AdUnitActivity adUnitActivity) {
    }

    @Override // com.unity3d.services.ads.adunit.IAdUnitViewHandler
    public void onResume(AdUnitActivity adUnitActivity) {
    }

    @Override // com.unity3d.services.ads.adunit.IAdUnitViewHandler
    public void onStart(AdUnitActivity adUnitActivity) {
    }

    @Override // com.unity3d.services.ads.adunit.IAdUnitViewHandler
    public void onStop(AdUnitActivity adUnitActivity) {
    }

    @Override // com.unity3d.services.ads.adunit.IAdUnitViewHandler
    public boolean create(AdUnitActivity adUnitActivity) {
        if (this._webPlayerView != null) {
            return true;
        }
        WebPlayerSettingsCache webPlayerSettingsCache = WebPlayerSettingsCache.getInstance();
        String str = webPlayerViewId;
        WebPlayerView webPlayerView = new WebPlayerView(adUnitActivity, str, webPlayerSettingsCache.getWebSettings(str), webPlayerSettingsCache.getWebPlayerSettings(webPlayerViewId));
        this._webPlayerView = webPlayerView;
        webPlayerView.setEventSettings(webPlayerSettingsCache.getWebPlayerEventSettings(webPlayerViewId));
        WebPlayerViewCache.getInstance().addWebPlayer(webPlayerViewId, this._webPlayerView);
        return true;
    }

    @Override // com.unity3d.services.ads.adunit.IAdUnitViewHandler
    public boolean destroy() {
        WebPlayerView webPlayerView = this._webPlayerView;
        if (webPlayerView != null) {
            ViewUtilities.removeViewFromParent(webPlayerView);
            this._webPlayerView.destroy();
        }
        WebPlayerViewCache.getInstance().removeWebPlayer(webPlayerViewId);
        this._webPlayerView = null;
        return true;
    }

    @Override // com.unity3d.services.ads.adunit.IAdUnitViewHandler
    public View getView() {
        return this._webPlayerView;
    }

    @Override // com.unity3d.services.ads.adunit.IAdUnitViewHandler
    public void onCreate(AdUnitActivity adUnitActivity, Bundle bundle) {
        create(adUnitActivity);
    }

    @Override // com.unity3d.services.ads.adunit.IAdUnitViewHandler
    public void onDestroy(AdUnitActivity adUnitActivity) {
        if (adUnitActivity.isFinishing()) {
            destroy();
        }
    }
}
