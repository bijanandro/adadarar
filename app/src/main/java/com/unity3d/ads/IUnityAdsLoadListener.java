package com.unity3d.ads;

import com.unity3d.ads.UnityAds;

/* JADX INFO: loaded from: classes.dex */
public interface IUnityAdsLoadListener {
    void onUnityAdsAdLoaded(String str);

    void onUnityAdsFailedToLoad(String str, UnityAds.UnityAdsLoadError unityAdsLoadError, String str2);
}
