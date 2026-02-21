package com.google.android.gms.internal.play_billing;

import java.io.IOException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzfo extends zzfm {
    zzfo() {
    }

    @Override // com.google.android.gms.internal.play_billing.zzfm
    final /* synthetic */ int zza(Object obj) {
        return ((zzfn) obj).zza();
    }

    @Override // com.google.android.gms.internal.play_billing.zzfm
    final /* synthetic */ int zzb(Object obj) {
        return ((zzfn) obj).zzb();
    }

    @Override // com.google.android.gms.internal.play_billing.zzfm
    final /* bridge */ /* synthetic */ Object zzc(Object obj) {
        zzdd zzddVar = (zzdd) obj;
        zzfn zzfnVar = zzddVar.zzc;
        if (zzfnVar != zzfn.zzc()) {
            return zzfnVar;
        }
        zzfn zzfnVarZzf = zzfn.zzf();
        zzddVar.zzc = zzfnVarZzf;
        return zzfnVarZzf;
    }

    @Override // com.google.android.gms.internal.play_billing.zzfm
    final /* synthetic */ Object zzd(Object obj) {
        return ((zzdd) obj).zzc;
    }

    @Override // com.google.android.gms.internal.play_billing.zzfm
    final /* bridge */ /* synthetic */ Object zze(Object obj, Object obj2) {
        if (zzfn.zzc().equals(obj2)) {
            return obj;
        }
        if (zzfn.zzc().equals(obj)) {
            return zzfn.zze((zzfn) obj, (zzfn) obj2);
        }
        ((zzfn) obj).zzd((zzfn) obj2);
        return obj;
    }

    @Override // com.google.android.gms.internal.play_billing.zzfm
    final /* bridge */ /* synthetic */ void zzf(Object obj, int i, long j) {
        ((zzfn) obj).zzj(i << 3, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.play_billing.zzfm
    final void zzg(Object obj) {
        ((zzdd) obj).zzc.zzh();
    }

    @Override // com.google.android.gms.internal.play_billing.zzfm
    final /* synthetic */ void zzh(Object obj, Object obj2) {
        ((zzdd) obj).zzc = (zzfn) obj2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzfm
    final /* synthetic */ void zzi(Object obj, zzge zzgeVar) throws IOException {
        ((zzfn) obj).zzk(zzgeVar);
    }
}
