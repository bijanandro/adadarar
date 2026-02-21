package ir.hiup.pushsdk;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeviceInfoProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\u0004H\u0007J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\t\u001a\u00020\u0004H\u0007J\b\u0010\n\u001a\u00020\u0004H\u0007J\b\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0012\u001a\u00020\rH\u0007J\b\u0010\u0013\u001a\u00020\u0004H\u0007J\b\u0010\u0014\u001a\u00020\u0004H\u0007J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u0017"}, d2 = {"Lir/hiup/pushsdk/DeviceInfoProvider;", "", "()V", "getAndroidId", "", "ctx", "Landroid/content/Context;", "getAndroidVersion", "getAppVersion", "getLocale", "getManufacturer", "getModel", "getScreenDensity", "", "getScreenHeight", "getScreenMetrics", "Landroid/util/DisplayMetrics;", "getScreenWidth", "getSdkInt", "getSupportedAbis", "getTimeZone", "getWindowManager", "Landroid/view/WindowManager;", "pushSdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class DeviceInfoProvider {
    public static final DeviceInfoProvider INSTANCE = new DeviceInfoProvider();

    private DeviceInfoProvider() {
    }

    private final WindowManager getWindowManager(Context ctx) {
        Object systemService = ctx.getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        return (WindowManager) systemService;
    }

    private final DisplayMetrics getScreenMetrics(Context ctx) {
        if (Build.VERSION.SDK_INT >= 30) {
            Rect bounds = getWindowManager(ctx).getCurrentWindowMetrics().getBounds();
            Intrinsics.checkNotNullExpressionValue(bounds, "getBounds(...)");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics.widthPixels = bounds.width();
            displayMetrics.heightPixels = bounds.height();
            displayMetrics.densityDpi = ctx.getResources().getDisplayMetrics().densityDpi;
            return displayMetrics;
        }
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        INSTANCE.getWindowManager(ctx).getDefaultDisplay().getRealMetrics(displayMetrics2);
        return displayMetrics2;
    }

    @JvmStatic
    public static final String getAndroidVersion() {
        String str = Build.VERSION.RELEASE;
        return str == null ? "" : str;
    }

    @JvmStatic
    public static final int getSdkInt() {
        return Build.VERSION.SDK_INT;
    }

    @JvmStatic
    public static final String getManufacturer() {
        String str = Build.MANUFACTURER;
        return str == null ? "" : str;
    }

    @JvmStatic
    public static final String getModel() {
        String str = Build.MODEL;
        return str == null ? "" : str;
    }

    @JvmStatic
    public static final String getAndroidId(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        String string = Settings.Secure.getString(ctx.getContentResolver(), "android_id");
        return string == null ? "" : string;
    }

    @JvmStatic
    public static final String getLocale() {
        String languageTag = Locale.getDefault().toLanguageTag();
        Intrinsics.checkNotNullExpressionValue(languageTag, "toLanguageTag(...)");
        return languageTag;
    }

    @JvmStatic
    public static final String getTimeZone() {
        String id = TimeZone.getDefault().getID();
        Intrinsics.checkNotNullExpressionValue(id, "getID(...)");
        return id;
    }

    @JvmStatic
    public static final String getSupportedAbis() {
        String[] SUPPORTED_ABIS = Build.SUPPORTED_ABIS;
        Intrinsics.checkNotNullExpressionValue(SUPPORTED_ABIS, "SUPPORTED_ABIS");
        return ArraysKt.joinToString$default(SUPPORTED_ABIS, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    @JvmStatic
    public static final int getScreenWidth(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        return INSTANCE.getScreenMetrics(ctx).widthPixels;
    }

    @JvmStatic
    public static final int getScreenHeight(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        return INSTANCE.getScreenMetrics(ctx).heightPixels;
    }

    @JvmStatic
    public static final int getScreenDensity(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        return INSTANCE.getScreenMetrics(ctx).densityDpi;
    }

    @JvmStatic
    public static final String getAppVersion(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        try {
            String str = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionName;
            return str == null ? "" : str;
        } catch (Exception unused) {
            return "";
        }
    }
}
