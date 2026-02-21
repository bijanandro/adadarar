package bitter.jnibridge;

import android.app.ApplicationExitInfo;
import android.app.job.JobInfo;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.InputContentInfo;
import dalvik.system.DelegateLastClassLoader;
import java.util.Map;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ ApplicationExitInfo m(Object obj) {
        return (ApplicationExitInfo) obj;
    }

    public static /* synthetic */ JobInfo.TriggerContentUri m(Uri uri, int i) {
        return new JobInfo.TriggerContentUri(uri, i);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ ImageDecoder.Source m199m(Object obj) {
        return (ImageDecoder.Source) obj;
    }

    public static /* synthetic */ AccessibilityNodeInfo.TouchDelegateInfo m(Map map) {
        return new AccessibilityNodeInfo.TouchDelegateInfo(map);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ InputContentInfo m203m(Object obj) {
        return (InputContentInfo) obj;
    }

    public static /* synthetic */ DelegateLastClassLoader m(String str, ClassLoader classLoader) {
        return new DelegateLastClassLoader(str, classLoader);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m207m() {
    }

    /* JADX INFO: renamed from: m$1, reason: collision with other method in class */
    public static /* synthetic */ void m211m$1() {
    }
}
