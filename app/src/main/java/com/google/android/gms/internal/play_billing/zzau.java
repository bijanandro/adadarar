package com.google.android.gms.internal.play_billing;

import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.CheckForNull;
import kotlin.UByte;

/* JADX INFO: compiled from: com.android.billingclient:billing@@6.2.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzau extends zzal {
    static final zzal zza = new zzau(null, new Object[0], 0);
    final transient Object[] zzb;

    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzau(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v34 */
    /* JADX WARN: Type inference failed for: r3v35 */
    /* JADX WARN: Type inference failed for: r3v36 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r5v2, types: [int[]] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Object[]] */
    static zzau zzg(int i, Object[] objArr, zzak zzakVar) {
        int iHighestOneBit;
        short[] sArr;
        char c;
        char c2;
        ?? r3;
        ?? r32;
        int i2 = i;
        Object[] objArrCopyOf = objArr;
        if (i2 == 0) {
            return (zzau) zza;
        }
        zzaj zzajVar = null;
        ?? r33 = 0;
        zzaj zzajVar2 = null;
        zzaj zzajVar3 = null;
        int i3 = 1;
        if (i2 == 1) {
            zzad.zza(Objects.requireNonNull(objArrCopyOf[0]), Objects.requireNonNull(objArrCopyOf[1]));
            return new zzau(null, objArrCopyOf, 1);
        }
        zzaa.zzb(i2, objArrCopyOf.length >> 1, "index");
        int iMax = Math.max(i2, 2);
        if (iMax < 751619276) {
            iHighestOneBit = Integer.highestOneBit(iMax - 1);
            do {
                iHighestOneBit += iHighestOneBit;
            } while (((double) iHighestOneBit) * 0.7d < iMax);
        } else {
            iHighestOneBit = BasicMeasure.EXACTLY;
            if (iMax >= 1073741824) {
                throw new IllegalArgumentException("collection too large");
            }
        }
        if (i2 == 1) {
            zzad.zza(Objects.requireNonNull(objArrCopyOf[0]), Objects.requireNonNull(objArrCopyOf[1]));
            i2 = 1;
            c = 1;
            c2 = 2;
        } else {
            int i4 = iHighestOneBit - 1;
            byte b = -1;
            if (iHighestOneBit <= 128) {
                byte[] bArr = new byte[iHighestOneBit];
                Arrays.fill(bArr, (byte) -1);
                int i5 = 0;
                int i6 = 0;
                while (i5 < i2) {
                    int i7 = i6 + i6;
                    int i8 = i5 + i5;
                    Object objRequireNonNull = Objects.requireNonNull(objArrCopyOf[i8]);
                    Object objRequireNonNull2 = Objects.requireNonNull(objArrCopyOf[i8 ^ i3]);
                    zzad.zza(objRequireNonNull, objRequireNonNull2);
                    int iZza = zzae.zza(objRequireNonNull.hashCode());
                    while (true) {
                        int i9 = iZza & i4;
                        int i10 = bArr[i9] & UByte.MAX_VALUE;
                        if (i10 == 255) {
                            bArr[i9] = (byte) i7;
                            if (i6 < i5) {
                                objArrCopyOf[i7] = objRequireNonNull;
                                objArrCopyOf[i7 ^ 1] = objRequireNonNull2;
                            }
                            i6++;
                        } else {
                            if (objRequireNonNull.equals(objArrCopyOf[i10 == true ? 1 : 0])) {
                                int i11 = ~i10;
                                zzaj zzajVar4 = new zzaj(objRequireNonNull, objRequireNonNull2, Objects.requireNonNull(objArrCopyOf[i11 == true ? 1 : 0]));
                                objArrCopyOf[i11 == true ? 1 : 0] = objRequireNonNull2;
                                zzajVar2 = zzajVar4;
                                break;
                            }
                            iZza = i9 + 1;
                        }
                    }
                    i5++;
                    i3 = 1;
                }
                if (i6 == i2) {
                    r3 = bArr;
                } else {
                    r32 = new Object[]{bArr, Integer.valueOf(i6), zzajVar2};
                    c2 = 2;
                    c = 1;
                    r33 = r32;
                }
            } else if (iHighestOneBit <= 32768) {
                sArr = new short[iHighestOneBit];
                Arrays.fill(sArr, (short) -1);
                int i12 = 0;
                for (int i13 = 0; i13 < i2; i13++) {
                    int i14 = i12 + i12;
                    int i15 = i13 + i13;
                    Object objRequireNonNull3 = Objects.requireNonNull(objArrCopyOf[i15]);
                    Object objRequireNonNull4 = Objects.requireNonNull(objArrCopyOf[i15 ^ 1]);
                    zzad.zza(objRequireNonNull3, objRequireNonNull4);
                    int iZza2 = zzae.zza(objRequireNonNull3.hashCode());
                    while (true) {
                        int i16 = iZza2 & i4;
                        char c3 = (char) sArr[i16];
                        if (c3 == 65535) {
                            sArr[i16] = (short) i14;
                            if (i12 < i13) {
                                objArrCopyOf[i14] = objRequireNonNull3;
                                objArrCopyOf[i14 ^ 1] = objRequireNonNull4;
                            }
                            i12++;
                        } else {
                            if (objRequireNonNull3.equals(objArrCopyOf[c3])) {
                                int i17 = c3 ^ 1;
                                zzaj zzajVar5 = new zzaj(objRequireNonNull3, objRequireNonNull4, Objects.requireNonNull(objArrCopyOf[i17 == true ? 1 : 0]));
                                objArrCopyOf[i17 == true ? 1 : 0] = objRequireNonNull4;
                                zzajVar3 = zzajVar5;
                                break;
                            }
                            iZza2 = i16 + 1;
                        }
                    }
                }
                if (i12 != i2) {
                    c2 = 2;
                    r33 = new Object[]{sArr, Integer.valueOf(i12), zzajVar3};
                    c = 1;
                }
                r3 = sArr;
            } else {
                int i18 = 1;
                sArr = new int[iHighestOneBit];
                Arrays.fill((int[]) sArr, -1);
                int i19 = 0;
                int i20 = 0;
                while (i19 < i2) {
                    int i21 = i20 + i20;
                    int i22 = i19 + i19;
                    Object objRequireNonNull5 = Objects.requireNonNull(objArrCopyOf[i22]);
                    Object objRequireNonNull6 = Objects.requireNonNull(objArrCopyOf[i22 ^ i18]);
                    zzad.zza(objRequireNonNull5, objRequireNonNull6);
                    int iZza3 = zzae.zza(objRequireNonNull5.hashCode());
                    while (true) {
                        int i23 = iZza3 & i4;
                        ?? r15 = sArr[i23];
                        if (r15 == b) {
                            sArr[i23] = i21;
                            if (i20 < i19) {
                                objArrCopyOf[i21] = objRequireNonNull5;
                                objArrCopyOf[i21 ^ 1] = objRequireNonNull6;
                            }
                            i20++;
                        } else {
                            if (objRequireNonNull5.equals(objArrCopyOf[r15])) {
                                int i24 = r15 ^ 1;
                                zzaj zzajVar6 = new zzaj(objRequireNonNull5, objRequireNonNull6, Objects.requireNonNull(objArrCopyOf[i24 == true ? 1 : 0]));
                                objArrCopyOf[i24 == true ? 1 : 0] = objRequireNonNull6;
                                zzajVar = zzajVar6;
                                break;
                            }
                            iZza3 = i23 + 1;
                            b = -1;
                        }
                    }
                    i19++;
                    i18 = 1;
                    b = -1;
                }
                if (i20 != i2) {
                    c = 1;
                    c2 = 2;
                    r33 = new Object[]{sArr, Integer.valueOf(i20), zzajVar};
                }
                r3 = sArr;
            }
            c2 = 2;
            r32 = r3;
            c = 1;
            r33 = r32;
        }
        boolean z = r33 instanceof Object[];
        ?? r34 = r33;
        if (z) {
            Object[] objArr2 = (Object[]) r33;
            zzaj zzajVar7 = (zzaj) objArr2[c2];
            if (zzakVar == null) {
                throw zzajVar7.zza();
            }
            zzakVar.zzc = zzajVar7;
            Object obj = objArr2[0];
            int iIntValue = ((Integer) objArr2[c]).intValue();
            objArrCopyOf = Arrays.copyOf(objArrCopyOf, iIntValue + iIntValue);
            r34 = obj;
            i2 = iIntValue;
        }
        return new zzau(r34, objArrCopyOf, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0003  */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0003 A[EDGE_INSN: B:44:0x0003->B:4:0x0003 BREAK  A[LOOP:0: B:16:0x003a->B:22:0x0050], EDGE_INSN: B:46:0x0003->B:4:0x0003 BREAK  A[LOOP:1: B:26:0x0065->B:32:0x007c], EDGE_INSN: B:48:0x0003->B:4:0x0003 BREAK  A[LOOP:2: B:34:0x008b->B:43:0x00a2]] */
    @Override // com.google.android.gms.internal.play_billing.zzal, java.util.Map
    @javax.annotation.CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r10) {
        /*
            r9 = this;
            r0 = 0
            if (r10 != 0) goto L6
        L3:
            r10 = r0
            goto L9e
        L6:
            int r1 = r9.zzd
            java.lang.Object[] r2 = r9.zzb
            r3 = 1
            if (r1 != r3) goto L22
            r1 = 0
            r1 = r2[r1]
            java.lang.Object r1 = java.util.Objects.requireNonNull(r1)
            boolean r10 = r1.equals(r10)
            if (r10 == 0) goto L3
            r10 = r2[r3]
            java.lang.Object r10 = java.util.Objects.requireNonNull(r10)
            goto L9e
        L22:
            java.lang.Object r1 = r9.zzc
            if (r1 != 0) goto L27
            goto L3
        L27:
            boolean r4 = r1 instanceof byte[]
            r5 = -1
            if (r4 == 0) goto L53
            r4 = r1
            byte[] r4 = (byte[]) r4
            int r1 = r4.length
            int r6 = r1 + (-1)
            int r1 = r10.hashCode()
            int r1 = com.google.android.gms.internal.play_billing.zzae.zza(r1)
        L3a:
            r1 = r1 & r6
            r5 = r4[r1]
            r7 = 255(0xff, float:3.57E-43)
            r5 = r5 & r7
            if (r5 != r7) goto L43
            goto L3
        L43:
            r7 = r2[r5]
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L50
            r10 = r5 ^ 1
            r10 = r2[r10]
            goto L9e
        L50:
            int r1 = r1 + 1
            goto L3a
        L53:
            boolean r4 = r1 instanceof short[]
            if (r4 == 0) goto L7f
            r4 = r1
            short[] r4 = (short[]) r4
            int r1 = r4.length
            int r6 = r1 + (-1)
            int r1 = r10.hashCode()
            int r1 = com.google.android.gms.internal.play_billing.zzae.zza(r1)
        L65:
            r1 = r1 & r6
            short r5 = r4[r1]
            char r5 = (char) r5
            r7 = 65535(0xffff, float:9.1834E-41)
            if (r5 != r7) goto L6f
            goto L3
        L6f:
            r7 = r2[r5]
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L7c
            r10 = r5 ^ 1
            r10 = r2[r10]
            goto L9e
        L7c:
            int r1 = r1 + 1
            goto L65
        L7f:
            int[] r1 = (int[]) r1
            int r4 = r1.length
            int r4 = r4 + r5
            int r6 = r10.hashCode()
            int r6 = com.google.android.gms.internal.play_billing.zzae.zza(r6)
        L8b:
            r6 = r6 & r4
            r7 = r1[r6]
            if (r7 != r5) goto L92
            goto L3
        L92:
            r8 = r2[r7]
            boolean r8 = r10.equals(r8)
            if (r8 == 0) goto La2
            r10 = r7 ^ 1
            r10 = r2[r10]
        L9e:
            if (r10 != 0) goto La1
            return r0
        La1:
            return r10
        La2:
            int r6 = r6 + 1
            goto L8b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzau.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.play_billing.zzal
    final zzaf zza() {
        return new zzat(this.zzb, 1, this.zzd);
    }

    @Override // com.google.android.gms.internal.play_billing.zzal
    final zzam zzd() {
        return new zzar(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.play_billing.zzal
    final zzam zze() {
        return new zzas(this, new zzat(this.zzb, 0, this.zzd));
    }
}
