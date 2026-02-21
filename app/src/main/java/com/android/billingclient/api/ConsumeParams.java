package com.android.billingclient.api;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class ConsumeParams {
    private String zza;

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
    public static final class Builder {
        private String zza;

        private Builder() {
        }

        /* synthetic */ Builder(zzcb zzcbVar) {
        }

        public ConsumeParams build() {
            String str = this.zza;
            if (str == null) {
                throw new IllegalArgumentException("Purchase token must be set");
            }
            ConsumeParams consumeParams = new ConsumeParams(null);
            consumeParams.zza = str;
            return consumeParams;
        }

        public Builder setPurchaseToken(String str) {
            this.zza = str;
            return this;
        }
    }

    private ConsumeParams() {
    }

    /* synthetic */ ConsumeParams(zzcc zzccVar) {
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public String getPurchaseToken() {
        return this.zza;
    }
}
