package com.unity3d.services.banners;

import android.content.Context;
import android.content.res.Resources;
import com.unity3d.services.core.misc.ViewUtilities;

/* JADX INFO: loaded from: classes.dex */
public class UnityBannerSize {
    private int height;
    private int width;

    public UnityBannerSize(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public static UnityBannerSize getDynamicSize(Context context) {
        return new UnityBannerSize(BannerSize.BANNER_SIZE_DYNAMIC.getWidth(context), BannerSize.BANNER_SIZE_DYNAMIC.getHeight(context));
    }

    private enum BannerSize {
        BANNER_SIZE_STANDARD,
        BANNER_SIZE_LEADERBOARD,
        BANNER_SIZE_IAB_STANDARD,
        BANNER_SIZE_DYNAMIC;

        private static final int IAB_STANDARD_HEIGHT = 60;
        private static final int IAB_STANDARD_WIDTH = 468;
        private static final int LEADERBOARD_HEIGHT = 90;
        private static final int LEADERBOARD_WIDTH = 728;
        private static final int STANDARD_HEIGHT = 50;
        private static final int STANDARD_WIDTH = 320;

        private BannerSize getNonDynamicSize(Context context) {
            if (this != BANNER_SIZE_DYNAMIC) {
                return this;
            }
            int iRound = Math.round(ViewUtilities.dpFromPx(context, Resources.getSystem().getDisplayMetrics().widthPixels));
            if (iRound >= LEADERBOARD_WIDTH) {
                return BANNER_SIZE_LEADERBOARD;
            }
            if (iRound >= IAB_STANDARD_WIDTH) {
                return BANNER_SIZE_IAB_STANDARD;
            }
            return BANNER_SIZE_STANDARD;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getWidth(Context context) {
            int i = AnonymousClass1.$SwitchMap$com$unity3d$services$banners$UnityBannerSize$BannerSize[getNonDynamicSize(context).ordinal()];
            return i != 2 ? i != 3 ? STANDARD_WIDTH : IAB_STANDARD_WIDTH : LEADERBOARD_WIDTH;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getHeight(Context context) {
            int i = AnonymousClass1.$SwitchMap$com$unity3d$services$banners$UnityBannerSize$BannerSize[getNonDynamicSize(context).ordinal()];
            return i != 2 ? i != 3 ? 50 : 60 : LEADERBOARD_HEIGHT;
        }
    }

    /* JADX INFO: renamed from: com.unity3d.services.banners.UnityBannerSize$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$unity3d$services$banners$UnityBannerSize$BannerSize;

        static {
            int[] iArr = new int[BannerSize.values().length];
            $SwitchMap$com$unity3d$services$banners$UnityBannerSize$BannerSize = iArr;
            try {
                iArr[BannerSize.BANNER_SIZE_STANDARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$unity3d$services$banners$UnityBannerSize$BannerSize[BannerSize.BANNER_SIZE_LEADERBOARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$unity3d$services$banners$UnityBannerSize$BannerSize[BannerSize.BANNER_SIZE_IAB_STANDARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
