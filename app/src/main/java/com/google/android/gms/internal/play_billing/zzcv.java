package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public enum zzcv {
    DOUBLE(0, 1, zzdo.DOUBLE),
    FLOAT(1, 1, zzdo.FLOAT),
    INT64(2, 1, zzdo.LONG),
    UINT64(3, 1, zzdo.LONG),
    INT32(4, 1, zzdo.INT),
    FIXED64(5, 1, zzdo.LONG),
    FIXED32(6, 1, zzdo.INT),
    BOOL(7, 1, zzdo.BOOLEAN),
    STRING(8, 1, zzdo.STRING),
    MESSAGE(9, 1, zzdo.MESSAGE),
    BYTES(10, 1, zzdo.BYTE_STRING),
    UINT32(11, 1, zzdo.INT),
    ENUM(12, 1, zzdo.ENUM),
    SFIXED32(13, 1, zzdo.INT),
    SFIXED64(14, 1, zzdo.LONG),
    SINT32(15, 1, zzdo.INT),
    SINT64(16, 1, zzdo.LONG),
    GROUP(17, 1, zzdo.MESSAGE),
    DOUBLE_LIST(18, 2, zzdo.DOUBLE),
    FLOAT_LIST(19, 2, zzdo.FLOAT),
    INT64_LIST(20, 2, zzdo.LONG),
    UINT64_LIST(21, 2, zzdo.LONG),
    INT32_LIST(22, 2, zzdo.INT),
    FIXED64_LIST(23, 2, zzdo.LONG),
    FIXED32_LIST(24, 2, zzdo.INT),
    BOOL_LIST(25, 2, zzdo.BOOLEAN),
    STRING_LIST(26, 2, zzdo.STRING),
    MESSAGE_LIST(27, 2, zzdo.MESSAGE),
    BYTES_LIST(28, 2, zzdo.BYTE_STRING),
    UINT32_LIST(29, 2, zzdo.INT),
    ENUM_LIST(30, 2, zzdo.ENUM),
    SFIXED32_LIST(31, 2, zzdo.INT),
    SFIXED64_LIST(32, 2, zzdo.LONG),
    SINT32_LIST(33, 2, zzdo.INT),
    SINT64_LIST(34, 2, zzdo.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzdo.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzdo.FLOAT),
    INT64_LIST_PACKED(37, 3, zzdo.LONG),
    UINT64_LIST_PACKED(38, 3, zzdo.LONG),
    INT32_LIST_PACKED(39, 3, zzdo.INT),
    FIXED64_LIST_PACKED(40, 3, zzdo.LONG),
    FIXED32_LIST_PACKED(41, 3, zzdo.INT),
    BOOL_LIST_PACKED(42, 3, zzdo.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzdo.INT),
    ENUM_LIST_PACKED(44, 3, zzdo.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzdo.INT),
    SFIXED64_LIST_PACKED(46, 3, zzdo.LONG),
    SINT32_LIST_PACKED(47, 3, zzdo.INT),
    SINT64_LIST_PACKED(48, 3, zzdo.LONG),
    GROUP_LIST(49, 2, zzdo.MESSAGE),
    MAP(50, 4, zzdo.VOID);

    private static final zzcv[] zzZ;
    private final zzdo zzab;
    private final int zzac;
    private final Class zzad;

    static {
        zzcv[] zzcvVarArrValues = values();
        zzZ = new zzcv[zzcvVarArrValues.length];
        for (zzcv zzcvVar : zzcvVarArrValues) {
            zzZ[zzcvVar.zzac] = zzcvVar;
        }
    }

    zzcv(int i, int i2, zzdo zzdoVar) {
        this.zzac = i;
        this.zzab = zzdoVar;
        int i3 = i2 - 1;
        if (i3 == 1 || i3 == 3) {
            this.zzad = zzdoVar.zza();
        } else {
            this.zzad = null;
        }
        if (i2 == 1) {
            zzdo zzdoVar2 = zzdo.VOID;
            zzdoVar.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
