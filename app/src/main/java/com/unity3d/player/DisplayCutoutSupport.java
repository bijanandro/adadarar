package com.unity3d.player;

import android.app.Activity;
import android.content.pm.ApplicationInfo;

/* JADX INFO: loaded from: classes.dex */
class DisplayCutoutSupport {
    private static final String CMD_ARG_FORCE_RENDER_OUTSIDE_SAFEAREA = "-force-render-outside-safearea=";
    private static final String RENDER_OUTSIDE_SAFEAREA = "unity.render-outside-safearea";
    private static int s_DisplayCutoutSupport = -1;

    DisplayCutoutSupport() {
    }

    static boolean getDisplayCutoutSupport(Activity activity) {
        ApplicationInfo applicationInfo;
        int i = s_DisplayCutoutSupport;
        if (i != -1) {
            return i == 1;
        }
        try {
            String stringExtra = activity.getIntent().getStringExtra("unity");
            if (stringExtra != null) {
                if (stringExtra.contains("-force-render-outside-safearea=true")) {
                    s_DisplayCutoutSupport = 1;
                } else if (stringExtra.contains("-force-render-outside-safearea=false")) {
                    s_DisplayCutoutSupport = 0;
                }
            }
            if (s_DisplayCutoutSupport == -1 && (applicationInfo = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 128)) != null) {
                s_DisplayCutoutSupport = applicationInfo.metaData.getBoolean(RENDER_OUTSIDE_SAFEAREA) ? 1 : 0;
            }
        } catch (Exception unused) {
            s_DisplayCutoutSupport = 0;
        }
        return s_DisplayCutoutSupport == 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void setLayoutCutoutMode(android.app.Activity r1) {
        /*
            if (r1 == 0) goto L2e
            android.view.Window r0 = r1.getWindow()
            if (r0 == 0) goto L2e
            boolean r0 = com.unity3d.player.PlatformSupport.PIE_SUPPORT
            if (r0 == 0) goto L2e
            boolean r0 = com.unity3d.player.PlatformSupport.VANILLA_ICE_CREAM_SUPPORT
            if (r0 == 0) goto L11
            goto L1b
        L11:
            boolean r0 = com.unity3d.player.PlatformSupport.RED_VELVET_CAKE_SUPPORT
            if (r0 == 0) goto L1f
            boolean r0 = getDisplayCutoutSupport(r1)
            if (r0 == 0) goto L1d
        L1b:
            r0 = 3
            goto L23
        L1d:
            r0 = 0
            goto L23
        L1f:
            boolean r0 = getDisplayCutoutSupport(r1)
        L23:
            android.view.Window r1 = r1.getWindow()
            android.view.WindowManager$LayoutParams r1 = r1.getAttributes()
            com.unity3d.player.C$$ExternalSyntheticApiModelOutline0.m(r1, r0)
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.DisplayCutoutSupport.setLayoutCutoutMode(android.app.Activity):void");
    }
}
