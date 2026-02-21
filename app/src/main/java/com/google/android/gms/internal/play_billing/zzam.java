package com.google.android.gms.internal.play_billing;

import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzam extends zzaf implements Set {

    @CheckForNull
    private transient zzai zza;

    zzam() {
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzav.zza(this);
    }

    @Override // com.google.android.gms.internal.play_billing.zzaf
    public zzai zzd() {
        zzai zzaiVar = this.zza;
        if (zzaiVar != null) {
            return zzaiVar;
        }
        zzai zzaiVarZzh = zzh();
        this.zza = zzaiVarZzh;
        return zzaiVarZzh;
    }

    @Override // com.google.android.gms.internal.play_billing.zzaf, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* JADX INFO: renamed from: zze */
    public abstract zzaw iterator();

    zzai zzh() {
        Object[] array = toArray();
        int i = zzai.zzd;
        return zzai.zzi(array, array.length);
    }
}
