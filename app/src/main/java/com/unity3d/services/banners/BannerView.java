package com.unity3d.services.banners;

import android.app.Activity;
import android.view.ViewManager;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import com.unity3d.services.ads.webplayer.WebPlayerSettingsCache;
import com.unity3d.services.banners.bridge.BannerBridge;
import com.unity3d.services.banners.view.BannerWebPlayerContainer;
import com.unity3d.services.core.configuration.ErrorState;
import com.unity3d.services.core.configuration.IInitializationListener;
import com.unity3d.services.core.configuration.InitializationNotificationCenter;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.misc.ViewUtilities;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.properties.SdkProperties;
import java.util.UUID;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class BannerView extends RelativeLayout {
    private BannerWebPlayerContainer bannerWebPlayerContainer;
    private IInitializationListener initializationListener;
    private IListener listener;
    private String placementId;
    private UnityBannerSize size;
    private String viewId;

    public interface IListener {
        void onBannerClick(BannerView bannerView);

        void onBannerFailedToLoad(BannerView bannerView, BannerErrorInfo bannerErrorInfo);

        void onBannerLeftApplication(BannerView bannerView);

        void onBannerLoaded(BannerView bannerView);
    }

    public static abstract class Listener implements IListener {
        @Override // com.unity3d.services.banners.BannerView.IListener
        public void onBannerClick(BannerView bannerView) {
        }

        @Override // com.unity3d.services.banners.BannerView.IListener
        public void onBannerFailedToLoad(BannerView bannerView, BannerErrorInfo bannerErrorInfo) {
        }

        @Override // com.unity3d.services.banners.BannerView.IListener
        public void onBannerLeftApplication(BannerView bannerView) {
        }

        @Override // com.unity3d.services.banners.BannerView.IListener
        public void onBannerLoaded(BannerView bannerView) {
        }
    }

    public BannerView(Activity activity, String str, UnityBannerSize unityBannerSize) {
        super(activity);
        this.viewId = UUID.randomUUID().toString();
        this.placementId = str;
        this.size = unityBannerSize;
        setupLayoutParams();
        setBackgroundColor(0);
        ClientProperties.setActivity(activity);
        BannerViewCache.getInstance().addBannerView(this);
    }

    public String getPlacementId() {
        return this.placementId;
    }

    public UnityBannerSize getSize() {
        return this.size;
    }

    public void setListener(IListener iListener) {
        this.listener = iListener;
    }

    public IListener getListener() {
        return this.listener;
    }

    public void load() {
        if (SdkProperties.isInitialized()) {
            bridgeLoad();
        } else {
            registerInitializeListener();
        }
    }

    public void destroy() {
        BannerViewCache.getInstance().removeBannerView(this.viewId);
        unregisterInitializeListener();
        BannerBridge.destroy(this.placementId);
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.banners.BannerView.1
            @Override // java.lang.Runnable
            public void run() {
                ViewParent parent = this.getParent();
                if (parent == null || !(parent instanceof ViewManager)) {
                    return;
                }
                ((ViewManager) parent).removeView(this);
            }
        });
        BannerWebPlayerContainer bannerWebPlayerContainer = this.bannerWebPlayerContainer;
        if (bannerWebPlayerContainer != null) {
            bannerWebPlayerContainer.destroy();
        }
        DeviceLog.info("Banner [" + this.placementId + "] was destroyed");
        this.viewId = null;
        this.listener = null;
        this.bannerWebPlayerContainer = null;
    }

    void loadWebPlayer(final UnityBannerSize unityBannerSize) {
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.banners.BannerView.2
            @Override // java.lang.Runnable
            public void run() {
                JSONObject webSettings = WebPlayerSettingsCache.getInstance().getWebSettings(this.viewId);
                JSONObject webPlayerSettings = WebPlayerSettingsCache.getInstance().getWebPlayerSettings(this.viewId);
                JSONObject webPlayerEventSettings = WebPlayerSettingsCache.getInstance().getWebPlayerEventSettings(this.viewId);
                if (this.bannerWebPlayerContainer != null) {
                    this.bannerWebPlayerContainer.setWebPlayerSettings(webSettings, webPlayerSettings);
                    this.bannerWebPlayerContainer.setWebPlayerEventSettings(webPlayerEventSettings);
                } else {
                    this.bannerWebPlayerContainer = new BannerWebPlayerContainer(this.getContext(), this.viewId, webSettings, webPlayerSettings, webPlayerEventSettings, unityBannerSize);
                    BannerView bannerView = this;
                    bannerView.addView(bannerView.bannerWebPlayerContainer);
                }
            }
        });
    }

    String getViewId() {
        return this.viewId;
    }

    private void registerInitializeListener() {
        unregisterInitializeListener();
        this.initializationListener = new IInitializationListener() { // from class: com.unity3d.services.banners.BannerView.3
            @Override // com.unity3d.services.core.configuration.IInitializationListener
            public void onSdkInitialized() {
                this.unregisterInitializeListener();
                this.bridgeLoad();
            }

            @Override // com.unity3d.services.core.configuration.IInitializationListener
            public void onSdkInitializationFailed(String str, ErrorState errorState, int i) {
                this.unregisterInitializeListener();
                if (this.getListener() != null) {
                    this.getListener().onBannerFailedToLoad(this, new BannerErrorInfo("UnityAds sdk initialization failed", BannerErrorCode.NATIVE_ERROR));
                }
            }
        };
        InitializationNotificationCenter.getInstance().addListener(this.initializationListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterInitializeListener() {
        if (this.initializationListener != null) {
            InitializationNotificationCenter.getInstance().removeListener(this.initializationListener);
        }
        this.initializationListener = null;
    }

    private void setupLayoutParams() {
        setLayoutParams(new RelativeLayout.LayoutParams(Math.round(ViewUtilities.pxFromDp(getContext(), this.size.getWidth())), Math.round(ViewUtilities.pxFromDp(getContext(), this.size.getHeight()))));
        setGravity(17);
        requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bridgeLoad() {
        BannerBridge.load(this.placementId, this.viewId, this.size);
    }
}
