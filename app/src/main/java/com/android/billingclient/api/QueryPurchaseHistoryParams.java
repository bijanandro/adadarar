package com.android.billingclient.api;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public final class QueryPurchaseHistoryParams {
    private final String zza;

    /* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
    public static class Builder {
        private String zza;

        private Builder() {
        }

        /* synthetic */ Builder(zzde zzdeVar) {
        }

        public QueryPurchaseHistoryParams build() {
            if (this.zza != null) {
                return new QueryPurchaseHistoryParams(this, null);
            }
            throw new IllegalArgumentException("Product type must be set");
        }

        public Builder setProductType(String str) {
            this.zza = str;
            return this;
        }
    }

    /* synthetic */ QueryPurchaseHistoryParams(Builder builder, zzdf zzdfVar) {
        this.zza = builder.zza;
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public final String zza() {
        return this.zza;
    }
}
