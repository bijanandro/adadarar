package com.unity3d.player;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.assetpacks.AssetPackException;
import com.google.android.play.core.assetpacks.AssetPackState;
import com.google.android.play.core.assetpacks.AssetPackStates;
import java.util.Collections;
import java.util.Map;
import java.util.Vector;

/* JADX INFO: renamed from: com.unity3d.player.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0057k implements OnCompleteListener {
    private IAssetPackManagerDownloadStatusCallback a;
    private UnityPlayer b;
    private String[] c;

    public C0057k(UnityPlayer unityPlayer, IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback, String[] strArr) {
        this.b = unityPlayer;
        this.a = iAssetPackManagerDownloadStatusCallback;
        this.c = strArr;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task) {
        int errorCode;
        try {
            AssetPackStates assetPackStates = (AssetPackStates) task.getResult();
            Map mapPackStates = assetPackStates.packStates();
            if (mapPackStates.size() == 0) {
                return;
            }
            Vector vector = new Vector();
            for (AssetPackState assetPackState : mapPackStates.values()) {
                if (assetPackState.errorCode() != 0 || assetPackState.status() == 4 || assetPackState.status() == 5 || assetPackState.status() == 0) {
                    String strName = assetPackState.name();
                    int iStatus = assetPackState.status();
                    int iErrorCode = assetPackState.errorCode();
                    long j = assetPackStates.totalBytes();
                    this.b.invokeOnMainThread(new RunnableC0045g(Collections.singleton(this.a), strName, iStatus, j, iStatus == 4 ? j : 0L, 0, iErrorCode));
                } else {
                    vector.add(assetPackState.name());
                }
            }
            if (vector.size() > 0) {
                C0066n c0066n = C0066n.e;
                UnityPlayer unityPlayer = this.b;
                IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback = this.a;
                c0066n.getClass();
                synchronized (C0066n.e) {
                    Object obj = c0066n.d;
                    if (obj == null) {
                        C0048h c0048h = new C0048h(c0066n, unityPlayer, iAssetPackManagerDownloadStatusCallback);
                        c0066n.b.registerListener(c0048h);
                        c0066n.d = c0048h;
                    } else {
                        ((C0048h) obj).a(iAssetPackManagerDownloadStatusCallback);
                    }
                    c0066n.c.addAll(vector);
                    c0066n.b.fetch(vector);
                }
            }
        } catch (RuntimeExecutionException e) {
            e = e;
            String[] strArr = this.c;
            if (strArr.length != 1) {
                C0066n c0066n2 = C0066n.e;
                IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback2 = this.a;
                c0066n2.getClass();
                for (String str : strArr) {
                    c0066n2.b.getPackStates(Collections.singletonList(str)).addOnCompleteListener(new C0057k(c0066n2.a, iAssetPackManagerDownloadStatusCallback2, new String[]{str}));
                }
                return;
            }
            String str2 = strArr[0];
            while (true) {
                if (e instanceof AssetPackException) {
                    errorCode = e.getErrorCode();
                    break;
                }
                e = e.getCause();
                if (e == null) {
                    errorCode = -100;
                    break;
                }
            }
            this.b.invokeOnMainThread(new RunnableC0045g(Collections.singleton(this.a), str2, 0, 0L, 0L, 0, errorCode));
        }
    }
}
