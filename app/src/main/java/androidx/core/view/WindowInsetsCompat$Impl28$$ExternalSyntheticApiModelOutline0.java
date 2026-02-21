package androidx.core.view;

import android.graphics.Insets;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowInsetsController;
import android.view.animation.Interpolator;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class WindowInsetsCompat$Impl28$$ExternalSyntheticApiModelOutline0 {
    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ WindowInsets.Builder m138m() {
        return new WindowInsets.Builder();
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ WindowInsets.Builder m139m(WindowInsets windowInsets) {
        return new WindowInsets.Builder(windowInsets);
    }

    public static /* synthetic */ WindowInsetsAnimation.Bounds m(Insets insets, Insets insets2) {
        return new WindowInsetsAnimation.Bounds(insets, insets2);
    }

    public static /* synthetic */ WindowInsetsAnimation m(int i, Interpolator interpolator, long j) {
        return new WindowInsetsAnimation(i, interpolator, j);
    }

    public static /* bridge */ /* synthetic */ WindowInsetsAnimation m(Object obj) {
        return (WindowInsetsAnimation) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ WindowInsetsController.OnControllableInsetsChangedListener m142m(Object obj) {
        return (WindowInsetsController.OnControllableInsetsChangedListener) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m149m() {
    }
}
