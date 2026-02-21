package com.unity3d.player;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

/* JADX INFO: loaded from: classes.dex */
public class E {
    protected ConnectivityManager a;

    public E(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.a = connectivityManager;
        if (connectivityManager == null) {
            D.Log(6, "NetworkConnectivity: ConnectivityManager not found");
        }
    }

    public void a() {
    }

    public int b() {
        Network activeNetwork;
        NetworkCapabilities networkCapabilities;
        ConnectivityManager connectivityManager = this.a;
        if (connectivityManager == null || (activeNetwork = connectivityManager.getActiveNetwork()) == null || (networkCapabilities = this.a.getNetworkCapabilities(activeNetwork)) == null) {
            return 0;
        }
        return networkCapabilities.hasTransport(0) ? 1 : 2;
    }
}
