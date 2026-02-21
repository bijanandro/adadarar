package com.unity3d.scar.adapter.common;

/* JADX INFO: loaded from: classes.dex */
public interface IScarAdListenerWrapper {
    void onAdClosed();

    void onAdFailedToLoad(int i, String str);

    void onAdLoaded();

    void onAdOpened();

    void onAdSkipped();
}
