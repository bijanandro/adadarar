package com.google.android.gms.dynamite;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
/* JADX INFO: loaded from: classes.dex */
public final class DynamiteModule {
    public static final int LOCAL = -1;
    public static final int NONE = 0;
    public static final int NO_SELECTION = 0;
    public static final int REMOTE = 1;
    private static Boolean zzb = null;
    private static String zzc = null;
    private static boolean zzd = false;
    private static int zze = -1;
    private static Boolean zzf;
    private static zzq zzk;
    private static zzr zzl;
    private final Context zzj;
    private static final ThreadLocal zzg = new ThreadLocal();
    private static final ThreadLocal zzh = new zzd();
    private static final VersionPolicy.IVersions zzi = new zze();
    public static final VersionPolicy PREFER_REMOTE = new zzf();
    public static final VersionPolicy PREFER_LOCAL = new zzg();
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzh();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = new zzi();
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzj();
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = new zzk();
    public static final VersionPolicy zza = new zzl();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public static class LoadingException extends Exception {
        /* synthetic */ LoadingException(String str, zzp zzpVar) {
            super(str);
        }

        /* synthetic */ LoadingException(String str, Throwable th, zzp zzpVar) {
            super(str, th);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public interface VersionPolicy {

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
        public interface IVersions {
            int zza(Context context, String str);

            int zzb(Context context, String str, boolean z) throws LoadingException;
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
        public static class SelectionResult {
            public int localVersion = 0;
            public int remoteVersion = 0;
            public int selection = 0;
        }

        SelectionResult selectModule(Context context, String str, IVersions iVersions) throws LoadingException;
    }

    private DynamiteModule(Context context) {
        Preconditions.checkNotNull(context);
        this.zzj = context;
    }

    public static int getLocalVersion(Context context, String str) {
        try {
            Class<?> clsLoadClass = context.getApplicationContext().getClassLoader().loadClass("com.google.android.gms.dynamite.descriptors." + str + ".ModuleDescriptor");
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (Objects.equal(declaredField.get(null), str)) {
                return declaredField2.getInt(null);
            }
            Log.e("DynamiteModule", "Module descriptor id '" + String.valueOf(declaredField.get(null)) + "' didn't match expected id '" + str + "'");
            return 0;
        } catch (ClassNotFoundException unused) {
            Log.w("DynamiteModule", "Local module descriptor class for " + str + " not found.");
            return 0;
        } catch (Exception e) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e.getMessage())));
            return 0;
        }
    }

    public static int getRemoteVersion(Context context, String str) {
        return zza(context, str, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x008e A[Catch: all -> 0x02a7, TRY_LEAVE, TryCatch #1 {all -> 0x02a7, blocks: (B:7:0x0052, B:11:0x0080, B:16:0x0088, B:19:0x008e, B:23:0x0097, B:97:0x01ee, B:98:0x01f9, B:100:0x01fb, B:102:0x01fd, B:103:0x0205, B:105:0x0207, B:107:0x0225, B:109:0x0233), top: B:140:0x0052, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0094  */
    @com.google.errorprone.annotations.ResultIgnorabilityUnspecified
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.gms.dynamite.DynamiteModule load(android.content.Context r22, com.google.android.gms.dynamite.DynamiteModule.VersionPolicy r23, java.lang.String r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 721
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.load(android.content.Context, com.google.android.gms.dynamite.DynamiteModule$VersionPolicy, java.lang.String):com.google.android.gms.dynamite.DynamiteModule");
    }

    /* JADX WARN: Removed duplicated region for block: B:94:0x016a A[Catch: all -> 0x01c6, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x01c6, blocks: (B:3:0x0002, B:61:0x00dd, B:63:0x00e3, B:68:0x0104, B:90:0x015c, B:94:0x016a, B:115:0x01bf, B:116:0x01c2, B:110:0x01b7, B:66:0x00e9, B:119:0x01c5, B:4:0x0003, B:7:0x0009, B:8:0x0025, B:59:0x00da, B:19:0x0046, B:41:0x009c, B:44:0x009f, B:52:0x00b8, B:60:0x00dc, B:58:0x00be), top: B:129:0x0002, inners: #1, #12 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int zza(android.content.Context r10, java.lang.String r11, boolean r12) {
        /*
            Method dump skipped, instruction units count: 459
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zza(android.content.Context, java.lang.String, boolean):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00dc  */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int zzb(android.content.Context r8, java.lang.String r9, boolean r10, boolean r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzb(android.content.Context, java.lang.String, boolean, boolean):int");
    }

    private static DynamiteModule zzc(Context context, String str) {
        Log.i("DynamiteModule", "Selected local version of ".concat(String.valueOf(str)));
        return new DynamiteModule(context);
    }

    private static void zzd(ClassLoader classLoader) throws LoadingException {
        zzr zzrVar;
        zzp zzpVar = null;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(null).newInstance(null);
            if (iBinder == null) {
                zzrVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                zzrVar = iInterfaceQueryLocalInterface instanceof zzr ? (zzr) iInterfaceQueryLocalInterface : new zzr(iBinder);
            }
            zzl = zzrVar;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new LoadingException("Failed to instantiate dynamite loader", e, zzpVar);
        }
    }

    private static boolean zze(Cursor cursor) {
        zzn zznVar = (zzn) zzg.get();
        if (zznVar == null || zznVar.zza != null) {
            return false;
        }
        zznVar.zza = cursor;
        return true;
    }

    private static boolean zzf(Context context) {
        if (Boolean.TRUE.equals(null) || Boolean.TRUE.equals(zzf)) {
            return true;
        }
        boolean z = false;
        if (zzf == null) {
            ProviderInfo providerInfoResolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", 0);
            if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 10000000) == 0 && providerInfoResolveContentProvider != null && "com.google.android.gms".equals(providerInfoResolveContentProvider.packageName)) {
                z = true;
            }
            Boolean boolValueOf = Boolean.valueOf(z);
            zzf = boolValueOf;
            boolValueOf.getClass();
            if (z && providerInfoResolveContentProvider.applicationInfo != null && (providerInfoResolveContentProvider.applicationInfo.flags & 129) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                zzd = true;
            }
        }
        if (!z) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return z;
    }

    private static zzq zzg(Context context) {
        zzq zzqVar;
        synchronized (DynamiteModule.class) {
            zzq zzqVar2 = zzk;
            if (zzqVar2 != null) {
                return zzqVar2;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    zzqVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzqVar = iInterfaceQueryLocalInterface instanceof zzq ? (zzq) iInterfaceQueryLocalInterface : new zzq(iBinder);
                }
                if (zzqVar != null) {
                    zzk = zzqVar;
                    return zzqVar;
                }
            } catch (Exception e) {
                Log.e("DynamiteModule", "Failed to load IDynamiteLoader from GmsCore: " + e.getMessage());
            }
            return null;
        }
    }

    @ResultIgnorabilityUnspecified
    public Context getModuleContext() {
        return this.zzj;
    }

    public IBinder instantiate(String str) throws LoadingException {
        try {
            return (IBinder) this.zzj.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new LoadingException("Failed to instantiate module class: ".concat(String.valueOf(str)), e, null);
        }
    }
}
