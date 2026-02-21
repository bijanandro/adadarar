package com.google.android.gms.internal.play_billing;

import com.google.android.gms.internal.play_billing.zzcz;
import com.google.android.gms.internal.play_billing.zzdd;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
public class zzcz<MessageType extends zzdd<MessageType, BuilderType>, BuilderType extends zzcz<MessageType, BuilderType>> extends zzbl<MessageType, BuilderType> {
    protected zzdd zza;
    private final zzdd zzb;

    protected zzcz(MessageType messagetype) {
        this.zzb = messagetype;
        if (messagetype.zzx()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.zza = messagetype.zzj();
    }

    @Override // com.google.android.gms.internal.play_billing.zzbl
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzcz clone() {
        zzcz zzczVar = (zzcz) this.zzb.zzy(5, null, null);
        zzczVar.zza = zze();
        return zzczVar;
    }

    public final MessageType zzc() {
        MessageType messagetype = (MessageType) zze();
        if (messagetype.zzw()) {
            return messagetype;
        }
        throw new zzfl(messagetype);
    }

    @Override // com.google.android.gms.internal.play_billing.zzej
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public MessageType zze() {
        if (!this.zza.zzx()) {
            return (MessageType) this.zza;
        }
        this.zza.zzr();
        return (MessageType) this.zza;
    }

    @Override // com.google.android.gms.internal.play_billing.zzel
    public final /* bridge */ /* synthetic */ zzek zzf() {
        throw null;
    }

    protected final void zzg() {
        if (this.zza.zzx()) {
            return;
        }
        zzh();
    }

    protected void zzh() {
        zzdd zzddVarZzj = this.zzb.zzj();
        zzes.zza().zzb(zzddVarZzj.getClass()).zzg(zzddVarZzj, this.zza);
        this.zza = zzddVarZzj;
    }
}
