package com.google.android.gms.internal.play_billing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzdr extends zzbn implements RandomAccess, zzds {

    @Deprecated
    public static final zzds zza;
    private static final zzdr zzb;
    private final List zzc;

    static {
        zzdr zzdrVar = new zzdr(false);
        zzb = zzdrVar;
        zza = zzdrVar;
    }

    public zzdr() {
        this(10);
    }

    private static String zzj(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzcc ? ((zzcc) obj).zzm(zzdl.zzb) : zzdl.zzd((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbn, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbn, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        zza();
        if (collection instanceof zzds) {
            collection = ((zzds) collection).zzh();
        }
        boolean zAddAll = this.zzc.addAll(i, collection);
        this.modCount++;
        return zAddAll;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbn, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zza();
        this.zzc.clear();
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbn, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        Object objRemove = this.zzc.remove(i);
        this.modCount++;
        return zzj(objRemove);
    }

    @Override // com.google.android.gms.internal.play_billing.zzbn, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return zzj(this.zzc.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.gms.internal.play_billing.zzdk
    public final /* bridge */ /* synthetic */ zzdk zzd(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzc);
        return new zzdr(arrayList);
    }

    @Override // com.google.android.gms.internal.play_billing.zzds
    public final zzds zze() {
        return zzc() ? new zzfr(this) : this;
    }

    @Override // com.google.android.gms.internal.play_billing.zzds
    public final Object zzf(int i) {
        return this.zzc.get(i);
    }

    @Override // java.util.AbstractList, java.util.List
    /* JADX INFO: renamed from: zzg, reason: merged with bridge method [inline-methods] */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzcc) {
            zzcc zzccVar = (zzcc) obj;
            String strZzm = zzccVar.zzm(zzdl.zzb);
            if (zzccVar.zzi()) {
                this.zzc.set(i, strZzm);
            }
            return strZzm;
        }
        byte[] bArr = (byte[]) obj;
        String strZzd = zzdl.zzd(bArr);
        if (zzgb.zzd(bArr)) {
            this.zzc.set(i, strZzd);
        }
        return strZzd;
    }

    @Override // com.google.android.gms.internal.play_billing.zzds
    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    @Override // com.google.android.gms.internal.play_billing.zzds
    public final void zzi(zzcc zzccVar) {
        zza();
        this.zzc.add(zzccVar);
        this.modCount++;
    }

    public zzdr(int i) {
        ArrayList arrayList = new ArrayList(i);
        super(true);
        this.zzc = arrayList;
    }

    private zzdr(ArrayList arrayList) {
        super(true);
        this.zzc = arrayList;
    }

    private zzdr(boolean z) {
        super(false);
        this.zzc = Collections.emptyList();
    }

    @Override // com.google.android.gms.internal.play_billing.zzbn, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
