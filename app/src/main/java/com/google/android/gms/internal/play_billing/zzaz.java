package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzaz implements zzbd {
    zzaz() {
    }

    @Override // com.google.android.gms.internal.play_billing.zzbd
    public final zzbc zza(CharSequence charSequence) {
        int length = charSequence.length();
        int i = length + length;
        if (i < 0) {
            throw new IllegalArgumentException(zzab.zza("expectedInputSize must be >= 0 but was %s", Integer.valueOf(i)));
        }
        zzbe zzbeVarZzb = zzb();
        int length2 = charSequence.length();
        for (int i2 = 0; i2 < length2; i2++) {
            ((zzba) zzbeVarZzb).zza(charSequence.charAt(i2));
        }
        return zzbeVarZzb.zzc();
    }
}
