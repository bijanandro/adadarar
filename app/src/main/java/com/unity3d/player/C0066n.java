package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import com.google.android.play.core.assetpacks.AssetPackLocation;
import com.google.android.play.core.assetpacks.AssetPackManager;
import com.google.android.play.core.assetpacks.AssetPackManagerFactory;
import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: renamed from: com.unity3d.player.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0066n {
    private static C0066n e;
    private UnityPlayer a;
    private AssetPackManager b;
    private HashSet c;
    private Object d;

    private C0066n(UnityPlayer unityPlayer, Context context) {
        if (e != null) {
            throw new RuntimeException("AssetPackManagerWrapper should be created only once. Use getInstance() instead.");
        }
        this.a = unityPlayer;
        this.b = AssetPackManagerFactory.getInstance(context);
        this.c = new HashSet();
    }

    public static C0066n a(UnityPlayer unityPlayer, Context context) {
        if (e == null) {
            e = new C0066n(unityPlayer, context);
        }
        return e;
    }

    public final Object a(IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        C0048h c0048h = new C0048h(this, this.a, iAssetPackManagerDownloadStatusCallback);
        this.b.registerListener(c0048h);
        return c0048h;
    }

    public final String a(String str) {
        AssetPackLocation packLocation = this.b.getPackLocation(str);
        return packLocation == null ? "" : packLocation.assetsPath();
    }

    public final void a(Activity activity, IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback) {
        this.b.showCellularDataConfirmation(activity).addOnSuccessListener(new C0054j(this.a, iAssetPackManagerMobileDataConfirmationCallback));
    }

    public final void a(Object obj) {
        if (obj instanceof C0048h) {
            this.b.unregisterListener((C0048h) obj);
        }
    }

    public final void a(String[] strArr) {
        this.b.cancel(Arrays.asList(strArr));
    }

    public final void a(String[] strArr, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        this.b.getPackStates(Arrays.asList(strArr)).addOnCompleteListener(new C0057k(this.a, iAssetPackManagerDownloadStatusCallback, strArr));
    }

    public final void a(String[] strArr, IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback) {
        this.b.getPackStates(Arrays.asList(strArr)).addOnCompleteListener(new C0063m(this.a, iAssetPackManagerStatusQueryCallback, strArr));
    }

    public final void b(String str) {
        this.b.removePack(str);
    }
}
