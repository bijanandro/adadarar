package com.unity3d.scar.adapter.common;

/* JADX INFO: loaded from: classes.dex */
public interface IScarInterstitialAdListenerWrapper extends IScarAdListenerWrapper {
    void onAdClicked();

    void onAdFailedToShow(int i, String str);

    void onAdImpression();

    void onAdLeftApplication();
}
