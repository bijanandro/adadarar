package com.google.android.gms.internal.play_billing;

import com.bumptech.glide.load.Key;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzdl {
    static final Charset zza = Charset.forName("US-ASCII");
    static final Charset zzb = Charset.forName(Key.STRING_CHARSET_NAME);
    static final Charset zzc = Charset.forName("ISO-8859-1");
    public static final byte[] zzd;
    public static final ByteBuffer zze;
    public static final zzcg zzf;

    static {
        byte[] bArr = new byte[0];
        zzd = bArr;
        zze = ByteBuffer.wrap(bArr);
        int i = zzcg.zza;
        zzce zzceVar = new zzce(bArr, 0, 0, false, null);
        try {
            zzceVar.zza(0);
            zzf = zzceVar;
        } catch (zzdn e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zza(boolean z) {
        return z ? 1231 : 1237;
    }

    static int zzb(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    static Object zzc(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("messageType");
    }

    public static String zzd(byte[] bArr) {
        return new String(bArr, zzb);
    }
}
