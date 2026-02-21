package com.unity3d.services.banners.api;

import com.unity3d.services.banners.BannerViewCache;
import com.unity3d.services.banners.UnityBannerSize;
import com.unity3d.services.banners.bridge.BannerBridge;
import com.unity3d.services.banners.properties.BannerRefreshInfo;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;

/* JADX INFO: loaded from: classes.dex */
public class Banner {

    private enum BannerViewType {
        WEB_PLAYER,
        UNKNOWN;

        public static BannerViewType fromString(String str) {
            try {
                return valueOf(str);
            } catch (IllegalArgumentException unused) {
                return UNKNOWN;
            }
        }
    }

    /* JADX INFO: renamed from: com.unity3d.services.banners.api.Banner$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$unity3d$services$banners$api$Banner$BannerViewType;

        static {
            int[] iArr = new int[BannerViewType.values().length];
            $SwitchMap$com$unity3d$services$banners$api$Banner$BannerViewType = iArr;
            try {
                iArr[BannerViewType.WEB_PLAYER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$unity3d$services$banners$api$Banner$BannerViewType[BannerViewType.UNKNOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @WebViewExposed
    public static void load(String str, Integer num, Integer num2, String str2, WebViewCallback webViewCallback) {
        if (AnonymousClass1.$SwitchMap$com$unity3d$services$banners$api$Banner$BannerViewType[BannerViewType.fromString(str).ordinal()] == 1 && BannerViewCache.getInstance().loadWebPlayer(str2, new UnityBannerSize(num.intValue(), num2.intValue()))) {
            BannerBridge.didLoad(str2);
        }
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void setRefreshRate(String str, Integer num, WebViewCallback webViewCallback) {
        if (str != null && num != null) {
            BannerRefreshInfo.getInstance().setRefreshRate(str, num);
        }
        webViewCallback.invoke(new Object[0]);
    }
}
