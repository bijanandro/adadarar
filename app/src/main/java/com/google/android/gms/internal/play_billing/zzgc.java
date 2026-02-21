package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public enum zzgc {
    DOUBLE(zzgd.DOUBLE, 1),
    FLOAT(zzgd.FLOAT, 5),
    INT64(zzgd.LONG, 0),
    UINT64(zzgd.LONG, 0),
    INT32(zzgd.INT, 0),
    FIXED64(zzgd.LONG, 1),
    FIXED32(zzgd.INT, 5),
    BOOL(zzgd.BOOLEAN, 0),
    STRING(zzgd.STRING, 2),
    GROUP(zzgd.MESSAGE, 3),
    MESSAGE(zzgd.MESSAGE, 2),
    BYTES(zzgd.BYTE_STRING, 2),
    UINT32(zzgd.INT, 0),
    ENUM(zzgd.ENUM, 0),
    SFIXED32(zzgd.INT, 5),
    SFIXED64(zzgd.LONG, 1),
    SINT32(zzgd.INT, 0),
    SINT64(zzgd.LONG, 0);

    private final zzgd zzt;

    zzgc(zzgd zzgdVar, int i) {
        this.zzt = zzgdVar;
    }

    public final zzgd zza() {
        return this.zzt;
    }
}
