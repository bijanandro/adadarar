package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.graphics.Insets;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
abstract class R1 {
    static int a;

    static Point a(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (PlatformSupport.RED_VELVET_CAKE_SUPPORT) {
            WindowMetrics currentWindowMetrics = windowManager.getCurrentWindowMetrics();
            currentWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
            return new Point(currentWindowMetrics.getBounds().width(), currentWindowMetrics.getBounds().height());
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    static void a(Activity activity, FrameLayout frameLayout) {
        if (PlatformSupport.RED_VELVET_CAKE_SUPPORT) {
            Insets insets = activity.getWindow().getDecorView().getRootWindowInsets().getInsets(a);
            frameLayout.setPadding(insets.left, insets.top, insets.right, insets.bottom);
        }
    }

    static void a(Activity activity, FrameLayout frameLayout, boolean z, boolean z2) {
        int iNavigationBars;
        int iStatusBars;
        boolean z3;
        if (!PlatformSupport.RED_VELVET_CAKE_SUPPORT) {
            View decorView = activity.getWindow().getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            int i = z ? systemUiVisibility | 5894 : systemUiVisibility & (-5895);
            if (z2) {
                i |= 1024;
            }
            decorView.setSystemUiVisibility(i);
            return;
        }
        WindowInsetsController windowInsetsController = activity.getWindow().getDecorView().getWindowInsetsController();
        if (windowInsetsController != null) {
            if (z) {
                iStatusBars = WindowInsets.Type.navigationBars();
                iNavigationBars = 0;
                z3 = true;
            } else {
                iNavigationBars = WindowInsets.Type.navigationBars();
                iStatusBars = 0;
                z3 = false;
            }
            if (z2) {
                iStatusBars |= WindowInsets.Type.statusBars();
                z3 = true;
            } else {
                iNavigationBars |= WindowInsets.Type.statusBars();
            }
            if (iStatusBars != 0) {
                windowInsetsController.hide(iStatusBars);
            }
            if (iNavigationBars != 0) {
                windowInsetsController.show(iNavigationBars);
            }
            a = iNavigationBars;
            windowInsetsController.setSystemBarsBehavior(z3 ? 2 : 1);
        }
        activity.getWindow().setDecorFitsSystemWindows(false);
    }
}
