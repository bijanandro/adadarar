package com.google.android.gms.internal.play_billing;

import java.util.Iterator;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzan {
    public static Object zza(Iterable iterable, Object obj) {
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }
}
