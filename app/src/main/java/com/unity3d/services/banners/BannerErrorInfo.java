package com.unity3d.services.banners;

/* JADX INFO: loaded from: classes.dex */
public class BannerErrorInfo {
    public BannerErrorCode errorCode;
    public String errorMessage;

    public BannerErrorInfo(String str, BannerErrorCode bannerErrorCode) {
        this.errorCode = bannerErrorCode;
        this.errorMessage = str;
    }
}
