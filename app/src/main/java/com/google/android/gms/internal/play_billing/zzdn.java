package com.google.android.gms.internal.play_billing;

import java.io.IOException;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public class zzdn extends IOException {
    private zzek zza;

    public zzdn(IOException iOException) {
        super(iOException.getMessage(), iOException);
        this.zza = null;
    }

    static zzdm zza() {
        return new zzdm("Protocol message tag had invalid wire type.");
    }

    static zzdn zzb() {
        return new zzdn("Protocol message contained an invalid tag (zero).");
    }

    static zzdn zzc() {
        return new zzdn("Protocol message had invalid UTF-8.");
    }

    static zzdn zzd() {
        return new zzdn("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzdn zze() {
        return new zzdn("Failed to parse the message.");
    }

    static zzdn zzg() {
        return new zzdn("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzdn zzf(zzek zzekVar) {
        this.zza = zzekVar;
        return this;
    }

    public zzdn(String str) {
        super(str);
        this.zza = null;
    }
}
