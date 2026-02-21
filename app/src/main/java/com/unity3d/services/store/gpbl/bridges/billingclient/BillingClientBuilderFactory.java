package com.unity3d.services.store.gpbl.bridges.billingclient;

import android.content.Context;
import com.unity3d.services.store.gpbl.bridges.billingclient.v4.BillingClientBridge;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public class BillingClientBuilderFactory {
    public static IBillingClientBuilderBridge getBillingClientBuilder(Context context) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        if (BillingClientBridge.isAvailable()) {
            return BillingClientBridge.newBuilder(context);
        }
        return com.unity3d.services.store.gpbl.bridges.billingclient.v3.BillingClientBridge.newBuilder(context);
    }
}
