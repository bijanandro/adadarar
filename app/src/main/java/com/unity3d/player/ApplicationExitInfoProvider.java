package com.unity3d.player;

import android.app.Activity;
import android.app.ActivityManager;
import bitter.jnibridge.a$$ExternalSyntheticApiModelOutline0;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class ApplicationExitInfoProvider {
    ApplicationExitInfoProvider() {
    }

    public static ApplicationExitInfoBase[] getHistoricalProcessExitReasons(Activity activity, String str, int i, int i2) {
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(ActivityManager.class);
        if (!PlatformSupport.RED_VELVET_CAKE_SUPPORT) {
            D.Log(5, "ApplicationExitInfoProvider: ApplicationExitInfo is only available from api 30 (Android 11)");
            return null;
        }
        List historicalProcessExitReasons = activityManager.getHistoricalProcessExitReasons(str, i, i2);
        int size = historicalProcessExitReasons.size();
        ApplicationExitInfoBase[] applicationExitInfoBaseArr = new ApplicationExitInfoBase[size];
        for (int i3 = 0; i3 < size; i3++) {
            applicationExitInfoBaseArr[i3] = new ApplicationExitInfoWrapper(a$$ExternalSyntheticApiModelOutline0.m(historicalProcessExitReasons.get(i3)));
        }
        return applicationExitInfoBaseArr;
    }

    public static void setProcessStateSummary(Activity activity, byte[] bArr) {
        if (PlatformSupport.RED_VELVET_CAKE_SUPPORT) {
            ((ActivityManager) activity.getSystemService(ActivityManager.class)).setProcessStateSummary(bArr);
        }
    }
}
