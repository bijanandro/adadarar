package com.unity3d.services.core.device;

/* JADX INFO: loaded from: classes.dex */
public interface IVolumeChangeListener {
    int getStreamType();

    void onVolumeChanged(int i);
}
