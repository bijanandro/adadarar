package com.unity3d.player;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.InputEvent;
import android.view.OrientationEventListener;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.FrameLayout;
import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.fmod.FmodAndroidAudioManager;

/* JADX INFO: loaded from: classes.dex */
public abstract class UnityPlayer implements IUnityPlayerLifecycleEvents {
    private static final String ARCORE_ENABLE_METADATA_NAME = "unity.arcore-enable";
    private static final String AUTO_REPORT_FULLY_DRAWN_ENABLE_METADATA_NAME = "unity.auto-report-fully-drawn";
    private static final String AUTO_SET_GAME_STATE_ENABLE_METADATA_NAME = "unity.auto-set-game-state";
    private static final String LAUNCH_FULLSCREEN = "unity.launch-fullscreen";
    private static final String SPLASH_ENABLE_METADATA_NAME = "unity.splash-enable";
    private static final String SPLASH_MODE_METADATA_NAME = "unity.splash-mode";
    public static Activity currentActivity;
    public static Context currentContext;
    Activity mActivity;
    Context mContext;
    private A mContextType;
    private int mInitialScreenOrientation;
    int mNaturalOrientation;
    boolean mQuitting;
    private Q1 mVideoPlayerProxy;
    private ClipboardManager m_ClipboardManager;
    private FrameLayout m_FrameLayout;
    private HashMap m_PermissionRequests;
    private com.unity3d.player.a.g m_SplashScreen;
    TelephonyManager m_TelephonyManager;
    protected IUnityPlayerLifecycleEvents m_UnityPlayerLifecycleEvents;
    Window m_Window;
    private Configuration prevConfig;
    Handler mHandler = new Handler();
    private boolean mIsFullscreen = true;
    E1 mState = new E1();
    private OrientationEventListener mOrientationListener = null;
    boolean m_AddPhoneCallListener = false;
    Y0 m_PhoneCallListener = new Y0(this);
    private GoogleARCoreApi m_ARCoreApi = null;
    private V0 m_FakeListener = new V0();
    private Camera2Wrapper m_Camera2Wrapper = null;
    private HFPStatus m_HFPStatus = null;
    private AudioVolumeHandler m_AudioVolumeHandler = null;
    private OrientationLockListener m_OrientationLockListener = null;
    private FmodAndroidAudioManager m_FmodAndroidAudioManager = null;
    private E m_NetworkConnectivity = null;
    private G m_OnBackPressedDispatcher = null;
    private A0 m_Cursor = null;
    private UnityAccessibilityDelegate m_AccessibilityDelegate = null;
    private final ConcurrentLinkedQueue m_MainThreadJobs = new ConcurrentLinkedQueue();
    private Thread m_UIThread = Thread.currentThread();

    static {
        new B0().a();
    }

    protected UnityPlayer(Context context, A a, IUnityPlayerLifecycleEvents iUnityPlayerLifecycleEvents) {
        this.mInitialScreenOrientation = -1;
        this.m_UnityPlayerLifecycleEvents = null;
        this.mContext = context;
        this.mContextType = a;
        this.m_UnityPlayerLifecycleEvents = iUnityPlayerLifecycleEvents == null ? this : iUnityPlayerLifecycleEvents;
        B0.a(getUnityNativeLibraryPath(context));
        currentContext = context;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            this.mActivity = activity;
            currentActivity = activity;
            this.mInitialScreenOrientation = activity.getRequestedOrientation();
        }
    }

    private void EarlyEnableFullScreenIfEnabled() {
        Activity activity = this.mActivity;
        if (activity != null && activity.getWindow() != null && (getLaunchFullscreen() || this.mActivity.getIntent().getBooleanExtra("android.intent.extra.VR_LAUNCH", false))) {
            applyWindowUIChanges(true);
        }
        DisplayCutoutSupport.setLayoutCutoutMode(this.mActivity);
    }

    private String GetGlViewContentDescription(Context context) {
        return context.getResources().getString(context.getResources().getIdentifier("game_view_content_description", "string", context.getPackageName()));
    }

    public static void UnitySendMessage(String str, String str2, String str3) {
        byte[] bytes;
        if (E1.d()) {
            if (str3 == null) {
                bytes = null;
            } else {
                try {
                    bytes = str3.getBytes(Key.STRING_CHARSET_NAME);
                } catch (UnsupportedEncodingException unused) {
                    return;
                }
            }
            nativeUnitySendMessage(str, str2, bytes);
            return;
        }
        D.Log(5, "Native libraries not loaded - dropping message for " + str + "." + str2);
    }

    private void applyWindowInsets() {
        Activity activity = this.mActivity;
        if (activity == null || activity.getWindow() == null) {
            return;
        }
        runOnUiThread(new J0(this));
    }

    private void applyWindowUIChanges(boolean z) {
        Activity activity = this.mActivity;
        if (activity == null || activity.getWindow() == null) {
            return;
        }
        runOnUiThread(new I0(this, z));
    }

    private void developmentPlayerInitialize() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchFrameLayoutPadding() {
        if (E1.d()) {
            FrameLayout frameLayout = getFrameLayout();
            nativeViewPaddingChanged(0, frameLayout.getPaddingLeft(), frameLayout.getPaddingTop(), frameLayout.getPaddingRight(), frameLayout.getPaddingBottom());
        }
    }

    private boolean getARCoreEnabled() {
        try {
            return getApplicationInfo().metaData.getBoolean(ARCORE_ENABLE_METADATA_NAME);
        } catch (Exception unused) {
            return false;
        }
    }

    private ActivityInfo getActivityInfo() {
        return this.mActivity.getPackageManager().getActivityInfo(this.mActivity.getComponentName(), 128);
    }

    private boolean getLaunchFullscreen() {
        try {
            return getApplicationInfo().metaData.getBoolean(LAUNCH_FULLSCREEN);
        } catch (Exception unused) {
            return false;
        }
    }

    private int getNaturalOrientation(int i) {
        int rotation = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
        if ((rotation == 0 || rotation == 2) && i == 2) {
            return 0;
        }
        return ((rotation == 1 || rotation == 3) && i == 1) ? 0 : 1;
    }

    private String getProcessName() {
        int iMyPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.mContext.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == iMyPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    private float getScreenBrightness() {
        Window window = this.m_Window;
        if (window == null) {
            return 1.0f;
        }
        float f = window.getAttributes().screenBrightness;
        if (f >= 0.0f) {
            return f;
        }
        int i = Settings.System.getInt(getContext().getContentResolver(), "screen_brightness", 255);
        return PlatformSupport.PIE_SUPPORT ? (float) Math.max(0.0d, Math.min(1.0d, ((Math.log(i) * 19.811d) - 9.411d) / 100.0d)) : i / 255.0f;
    }

    private static String getUnityNativeLibraryPath(Context context) {
        return context.getApplicationInfo().nativeLibraryDir;
    }

    private void hideStatusBar() {
        Activity activity = this.mActivity;
        if (activity != null) {
            activity.getWindow().setFlags(1024, 1024);
        }
    }

    private final native void initJni(Context context, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeOnMainThread(Z0 z0) {
        if (isFinishing()) {
            return;
        }
        invokeOnMainThread((Runnable) z0);
    }

    private boolean isWindowTranslucent() {
        Activity activity = this.mActivity;
        if (activity == null) {
            return false;
        }
        TypedArray typedArrayObtainStyledAttributes = activity.getTheme().obtainStyledAttributes(new int[]{android.R.attr.windowIsTranslucent});
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }

    private static String loadNative(String str) {
        String str2 = str + "/libmain.so";
        try {
            try {
                try {
                    System.load(str2);
                } catch (UnsatisfiedLinkError unused) {
                    System.loadLibrary("main");
                }
                if (NativeLoader.load(str)) {
                    E1.e();
                    return "";
                }
                D.Log(6, "NativeLoader.load failure, Unity libraries were not loaded.");
                return "NativeLoader.load failure, Unity libraries were not loaded.";
            } catch (UnsatisfiedLinkError e) {
                return logLoadLibMainError(str2, e.toString());
            }
        } catch (SecurityException e2) {
            return logLoadLibMainError(str2, e2.toString());
        }
    }

    private static String logLoadLibMainError(String str, String str2) {
        String str3 = "Failed to load 'libmain.so'\n\n" + str2;
        D.Log(6, str3);
        return str3;
    }

    private final native void nativeApplicationUnload();

    private final native void nativeHidePreservedContent();

    private final native boolean nativeInjectEvent(InputEvent inputEvent, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean nativeIsAutorotationOn();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeMuteMasterAudio(boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeSetLaunchURL(String str);

    private static native void nativeUnitySendMessage(String str, String str2, byte[] bArr);

    private final native void nativeViewPaddingChanged(int i, int i2, int i3, int i4, int i5);

    private void pauseJavaAndCallUnloadCallback() {
        runOnUiThread(new H0(this));
    }

    static native void permissionResponseToNative(long j, boolean z);

    private static void preloadJavaPlugins() {
        try {
            Class.forName("com.unity3d.JavaPluginPreloader");
        } catch (ClassNotFoundException unused) {
        } catch (LinkageError e) {
            D.Log(6, "Java class preloading failed: " + e.getMessage());
        }
    }

    private void releasePointerCapture() {
        this.m_Cursor.a();
    }

    private void requestPointerCapture() {
        this.m_Cursor.b();
    }

    private void requestUserAuthorization(String str, long j) {
        Activity activity;
        if (str == null || str.isEmpty() || (activity = this.mActivity) == null) {
            return;
        }
        UnityPermissions.requestUserPermissions(activity, new String[]{str}, j != 0 ? new X0(this, j) : null);
    }

    private boolean runningOnUIThread() {
        return Thread.currentThread() == this.m_UIThread;
    }

    private void setBackButtonLeavesApp(boolean z) {
        G g = this.m_OnBackPressedDispatcher;
        if (g != null) {
            if (z) {
                g.unregisterOnBackPressedCallback();
            } else {
                g.registerOnBackPressedCallback();
            }
        }
    }

    private void setScreenBrightness(float f) {
        float fMax = Math.max(0.04f, f);
        if (this.m_Window == null || getScreenBrightness() == fMax) {
            return;
        }
        runOnUiThread(new U0(this, fMax));
    }

    private void startActivityIndicator(int i) {
        postOnUiThread(new K0(this, i));
    }

    private void stopActivityIndicator() {
        postOnUiThread(new L0());
    }

    private boolean supportsWindowInsetController() {
        return PlatformSupport.RED_VELVET_CAKE_SUPPORT;
    }

    private void swapViews(View view, View view2) {
        boolean z;
        ViewParent parent;
        if (this.mState.b()) {
            z = false;
        } else {
            setupUnityToBePaused();
            z = true;
        }
        FrameLayout frameLayout = getFrameLayout();
        if (view != null && (parent = view.getParent()) != frameLayout) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view);
            }
            frameLayout.addView(view);
            frameLayout.bringChildToFront(view);
            view.setVisibility(0);
        }
        if (view2 != null && view2.getParent() == frameLayout) {
            view2.setVisibility(8);
            frameLayout.removeView(view2);
        }
        if (z) {
            setupUnityToBeResumed();
        }
    }

    private static void unloadNative() {
        if (E1.d()) {
            if (!NativeLoader.unload()) {
                throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
            }
            E1.f();
        }
    }

    public synchronized void addPermissionRequest(PermissionRequest permissionRequest) {
        Integer numValueOf;
        if (this.m_PermissionRequests == null) {
            this.m_PermissionRequests = new HashMap();
        }
        int i = 1;
        while (true) {
            numValueOf = Integer.valueOf(i);
            if (!this.m_PermissionRequests.containsKey(numValueOf)) {
                break;
            } else {
                i++;
            }
        }
        this.m_PermissionRequests.put(numValueOf, permissionRequest);
        if (this.m_PermissionRequests.size() == 1) {
            String[] permissionNames = permissionRequest.getPermissionNames();
            numValueOf.getClass();
            requestPermissionsFromActivity(permissionNames, i);
        }
    }

    protected void addPhoneCallListener() {
        this.m_AddPhoneCallListener = true;
        this.m_TelephonyManager.listen(this.m_PhoneCallListener, 32);
    }

    public boolean addViewToPlayer(View view, boolean z) {
        View view2 = getView();
        swapViews(view, z ? view2 : null);
        FrameLayout frameLayout = getFrameLayout();
        boolean z2 = true;
        boolean z3 = view.getParent() == frameLayout;
        boolean z4 = z && view2.getParent() == null;
        boolean z5 = view2.getParent() == frameLayout;
        if (!z3 || (!z4 && !z5)) {
            z2 = false;
        }
        if (!z2) {
            if (!z3) {
                D.Log(6, "addViewToPlayer: Failure adding view to hierarchy");
            }
            if (!z4 && !z5) {
                D.Log(6, "addViewToPlayer: Failure removing old view from hierarchy");
            }
        }
        return z2;
    }

    protected void applySurfaceViewSettings(SurfaceView surfaceView) {
        if (!isWindowTranslucent()) {
            surfaceView.getHolder().setFormat(-1);
        } else {
            surfaceView.getHolder().setFormat(-3);
            surfaceView.setZOrderOnTop(true);
        }
    }

    protected boolean canPauseUnity() {
        return this.mState.c() || !this.mState.b();
    }

    protected boolean canResumeUnity() {
        Activity activity = this.mActivity;
        return this.mState.a(activity != null ? MultiWindowSupport.isInMultiWindowMode(activity) : false);
    }

    abstract void cleanupResourcesForDestroy();

    public void configurationChanged(Configuration configuration) {
        int iDiff = this.prevConfig.diff(configuration);
        if ((iDiff & 256) != 0 || (iDiff & 1024) != 0 || (iDiff & 2048) != 0 || (iDiff & 128) != 0) {
            nativeHidePreservedContent();
        }
        this.prevConfig = new Configuration(configuration);
        Q1 q1 = this.mVideoPlayerProxy;
        if (q1 != null) {
            q1.a();
        }
        UnityAccessibilityDelegate unityAccessibilityDelegate = this.m_AccessibilityDelegate;
        if (unityAccessibilityDelegate != null) {
            unityAccessibilityDelegate.a(configuration);
        }
    }

    public void destroy() {
        Camera2Wrapper camera2Wrapper = this.m_Camera2Wrapper;
        if (camera2Wrapper != null) {
            camera2Wrapper.closeCamera2();
            this.m_Camera2Wrapper = null;
        }
        HFPStatus hFPStatus = this.m_HFPStatus;
        if (hFPStatus != null) {
            hFPStatus.b();
            this.m_HFPStatus = null;
        }
        FmodAndroidAudioManager fmodAndroidAudioManager = this.m_FmodAndroidAudioManager;
        if (fmodAndroidAudioManager != null) {
            fmodAndroidAudioManager.setActivity(null);
        }
        E e = this.m_NetworkConnectivity;
        if (e != null) {
            e.a();
            this.m_NetworkConnectivity = null;
        }
        G g = this.m_OnBackPressedDispatcher;
        if (g != null) {
            g.unregisterOnBackPressedCallback();
            this.m_OnBackPressedDispatcher = null;
        }
        this.mQuitting = true;
        if (!this.mState.b()) {
            setupUnityToBePaused();
        }
        cleanupResourcesForDestroy();
        unloadNative();
    }

    protected void disableLogger() {
        D.a = true;
    }

    void disableStaticSplashScreen() {
        runOnUiThread(new O0(this));
    }

    protected void executeMainThreadJobs() {
        while (true) {
            Runnable runnable = (Runnable) this.m_MainThreadJobs.poll();
            if (runnable == null) {
                return;
            } else {
                runnable.run();
            }
        }
    }

    void finish() {
        Activity activity = this.mActivity;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        this.mActivity.finish();
    }

    Activity getActivity() {
        return this.mActivity;
    }

    ApplicationInfo getApplicationInfo() {
        return this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128);
    }

    boolean getAutoReportFullyDrawnEnabled() {
        try {
            return getApplicationInfo().metaData.getBoolean(AUTO_REPORT_FULLY_DRAWN_ENABLE_METADATA_NAME);
        } catch (Exception unused) {
            return false;
        }
    }

    boolean getAutoSetGameStateEnabled() {
        try {
            return getApplicationInfo().metaData.getBoolean(AUTO_SET_GAME_STATE_ENABLE_METADATA_NAME);
        } catch (Exception unused) {
            return false;
        }
    }

    protected String getClipboardText() {
        ClipData primaryClip = this.m_ClipboardManager.getPrimaryClip();
        return primaryClip != null ? primaryClip.getItemAt(0).coerceToText(this.mContext).toString() : "";
    }

    public Context getContext() {
        return this.mContext;
    }

    A getContextType() {
        return this.mContextType;
    }

    public FrameLayout getFrameLayout() {
        return this.m_FrameLayout;
    }

    protected String getKeyboardLayout() {
        InputMethodSubtype currentInputMethodSubtype = ((InputMethodManager) this.mContext.getSystemService("input_method")).getCurrentInputMethodSubtype();
        if (currentInputMethodSubtype == null) {
            return null;
        }
        String strA = C.a(currentInputMethodSubtype);
        if (strA != null && !strA.equals("")) {
            return strA;
        }
        return currentInputMethodSubtype.getMode() + " " + currentInputMethodSubtype.getExtraValue();
    }

    protected String getLaunchURL() {
        Uri data;
        Activity activity = this.mActivity;
        if (activity == null || (data = activity.getIntent().getData()) == null) {
            return null;
        }
        return data.toString();
    }

    protected int getNetworkConnectivity() {
        E e = this.m_NetworkConnectivity;
        if (e != null) {
            return e.b();
        }
        this.m_NetworkConnectivity = PlatformSupport.NOUGAT_SUPPORT ? new com.unity3d.player.a.d(this.mContext) : new E(this.mContext);
        return this.m_NetworkConnectivity.b();
    }

    public String getNetworkProxySettings(String str) {
        String str2;
        String str3;
        if (!str.startsWith("http:")) {
            if (str.startsWith("https:")) {
                str2 = "https.proxyHost";
                str3 = "https.proxyPort";
            }
            return null;
        }
        str2 = "http.proxyHost";
        str3 = "http.proxyPort";
        String property = System.getProperties().getProperty(str2);
        if (property != null && !"".equals(property)) {
            StringBuilder sb = new StringBuilder(property);
            String property2 = System.getProperties().getProperty(str3);
            if (property2 != null && !"".equals(property2)) {
                sb.append(":");
                sb.append(property2);
            }
            String property3 = System.getProperties().getProperty("http.nonProxyHosts");
            if (property3 != null && !"".equals(property3)) {
                sb.append('\n');
                sb.append(property3);
            }
            return sb.toString();
        }
        return null;
    }

    boolean getSplashEnabled() {
        try {
            return getApplicationInfo().metaData.getBoolean(SPLASH_ENABLE_METADATA_NAME);
        } catch (Exception unused) {
            return false;
        }
    }

    protected int getSplashMode() {
        try {
            return getApplicationInfo().metaData.getInt(SPLASH_MODE_METADATA_NAME);
        } catch (Exception unused) {
            return 0;
        }
    }

    String getState() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mState.toString() + "\n");
        sb.append(String.format("m_AudioVolumeHandler = %b", this.m_AudioVolumeHandler) + "\n");
        sb.append(String.format("m_OrientationLockListener = %b", this.m_OrientationLockListener) + "\n");
        return sb.toString();
    }

    public abstract SurfaceView getSurfaceView();

    protected int getUaaLLaunchProcessType() {
        String processName = getProcessName();
        return (processName == null || processName.equals(this.mContext.getPackageName())) ? 0 : 1;
    }

    public abstract View getView();

    abstract boolean handleFocus(boolean z);

    abstract void hidePreservedContent();

    protected void initialize(FrameLayout frameLayout) {
        this.m_FrameLayout = frameLayout;
        EarlyEnableFullScreenIfEnabled();
        Configuration configuration = getFrameLayout().getResources().getConfiguration();
        this.prevConfig = configuration;
        this.mNaturalOrientation = getNaturalOrientation(configuration.orientation);
        if (this.mActivity != null && getSplashEnabled()) {
            com.unity3d.player.a.g gVar = new com.unity3d.player.a.g(this.mContext, com.unity3d.player.a.a.b(3)[getSplashMode()]);
            this.m_SplashScreen = gVar;
            this.m_FrameLayout.addView(gVar);
            this.m_FrameLayout.bringChildToFront(this.m_SplashScreen);
        }
        preloadJavaPlugins();
        String strLoadNative = loadNative(getUnityNativeLibraryPath(this.mContext));
        if (!E1.d()) {
            D.Log(6, "Your hardware does not support this application.");
            AlertDialog alertDialogCreate = new AlertDialog.Builder(this.mContext).setTitle("Failure to initialize!").setPositiveButton("OK", new M0(this)).setMessage("Your hardware does not support this application.\n\n" + strLoadNative + "\n\n Press OK to quit.").create();
            alertDialogCreate.setCancelable(false);
            alertDialogCreate.show();
            return;
        }
        initJni(this.mContext, this.mContextType.a);
        this.mState.d(true);
        this.mQuitting = false;
        developmentPlayerInitialize();
        hideStatusBar();
        this.m_TelephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        this.m_ClipboardManager = (ClipboardManager) this.mContext.getSystemService("clipboard");
        this.m_Camera2Wrapper = new Camera2Wrapper(this.mContext);
        this.m_HFPStatus = new HFPStatus(this.mContext);
        this.m_Cursor = new A0(this);
        FmodAndroidAudioManager fmodAndroidAudioManager = FmodAndroidAudioManager.getInstance();
        this.m_FmodAndroidAudioManager = fmodAndroidAudioManager;
        fmodAndroidAudioManager.setActivity(this.mActivity);
        this.m_OnBackPressedDispatcher = H.a(getContext(), 1, new N0(this));
        Activity activity = this.mActivity;
        if (activity != null) {
            this.m_Window = activity.getWindow();
        }
    }

    protected boolean initializeGoogleAr() {
        if (this.m_ARCoreApi != null || this.mActivity == null || !getARCoreEnabled()) {
            return false;
        }
        GoogleARCoreApi googleARCoreApi = new GoogleARCoreApi();
        this.m_ARCoreApi = googleARCoreApi;
        googleARCoreApi.initializeARCore(this.mActivity);
        if (this.mState.b()) {
            return false;
        }
        this.m_ARCoreApi.resumeARCore();
        return false;
    }

    public boolean injectEvent(InputEvent inputEvent) {
        return injectEvent(inputEvent, 0);
    }

    public boolean injectEvent(InputEvent inputEvent, int i) {
        if (E1.d()) {
            return nativeInjectEvent(inputEvent, i);
        }
        return false;
    }

    public void invokeOnMainThread(Runnable runnable) {
        if (E1.d()) {
            if (runningOnUIThread() || !runningOnMainThread()) {
                this.m_MainThreadJobs.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    protected boolean isFinishing() {
        if (this.mQuitting) {
            return true;
        }
        Activity activity = this.mActivity;
        if (activity != null) {
            this.mQuitting = activity.isFinishing();
        }
        return this.mQuitting;
    }

    boolean isNativeInitialized() {
        return E1.d() && this.mState.a();
    }

    protected boolean isUaaLUseCase() {
        String callingPackage;
        Activity activity = this.mActivity;
        return (activity == null || (callingPackage = activity.getCallingPackage()) == null || !callingPackage.equals(this.mContext.getPackageName())) ? false : true;
    }

    protected void kill() {
        D.Log(4, "Quitting process");
        Process.killProcess(Process.myPid());
    }

    protected boolean loadLibrary(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (Exception | UnsatisfiedLinkError unused) {
            return false;
        }
    }

    public void newIntent(Intent intent) {
        setLaunchURL(intent.getData());
    }

    abstract void onOrientationChanged(int i, int i2);

    public void onPause() {
        MultiWindowSupport.saveMultiWindowMode(this.mActivity);
        if (MultiWindowSupport.isInMultiWindowMode(this.mActivity)) {
            return;
        }
        setupUnityToBePaused();
    }

    public void onResume() {
        if (!MultiWindowSupport.isInMultiWindowMode(this.mActivity) || MultiWindowSupport.isMultiWindowModeChangedToTrue(this.mActivity)) {
            setupUnityToBeResumed();
        }
    }

    public void onStart() {
        if (MultiWindowSupport.isInMultiWindowMode(this.mActivity)) {
            setupUnityToBeResumed();
        }
    }

    public void onStop() {
        if (MultiWindowSupport.isInMultiWindowMode(this.mActivity)) {
            setupUnityToBePaused();
        }
    }

    @Override // com.unity3d.player.IUnityPlayerLifecycleEvents
    public void onUnityPlayerQuitted() {
    }

    @Override // com.unity3d.player.IUnityPlayerLifecycleEvents
    public void onUnityPlayerUnloaded() {
    }

    public void pause() {
        setupUnityToBePaused();
    }

    void pauseUnity() {
        this.mState.c(false);
        this.mState.e(true);
    }

    public synchronized void permissionResponse(Activity activity, int i, String[] strArr, int[] iArr) {
        if (this.m_PermissionRequests == null) {
            return;
        }
        Integer numValueOf = Integer.valueOf(i);
        PermissionRequest permissionRequest = (PermissionRequest) this.m_PermissionRequests.get(numValueOf);
        if (permissionRequest == null) {
            return;
        }
        this.m_PermissionRequests.remove(numValueOf);
        String[] permissionNames = permissionRequest.getPermissionNames();
        int[] iArr2 = new int[permissionNames.length];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String str = strArr[i2];
            int i3 = 0;
            while (true) {
                if (i3 >= permissionNames.length) {
                    i3 = -1;
                    break;
                } else if (str.equals(permissionNames[i3])) {
                    break;
                } else {
                    i3++;
                }
            }
            if (i3 < 0) {
                D.Log(6, "Permission not found in request: " + str);
            } else {
                iArr2[i3] = iArr[i2] == 0 ? 1 : (Build.VERSION.SDK_INT < 30 && !UnityPermissions.shouldShowRequestPermissionRationale(activity, str)) ? 3 : 2;
            }
        }
        invokeOnMainThread(new P0(permissionRequest, permissionNames, iArr2));
        triggerNextPermissionRequest();
    }

    void postOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public void removeViewFromPlayer(View view) {
        View view2 = getView();
        swapViews(view2, view);
        boolean z = view.getParent() == null;
        boolean z2 = view2.getParent() == getFrameLayout();
        if (z && z2) {
            return;
        }
        if (!z) {
            D.Log(6, "removeViewFromPlayer: Failure removing view from hierarchy");
        }
        if (z2) {
            return;
        }
        D.Log(6, "removeViewFromPlayer: Failure adding old view to hierarchy");
    }

    public void reportError(String str, String str2) {
        D.Log(6, str + ": " + str2);
    }

    void reportFullyDrawn() {
        this.mActivity.reportFullyDrawn();
    }

    public void requestPermissionsFromActivity(String[] strArr, int i) {
        this.mActivity.requestPermissions(strArr, i);
    }

    public void resume() {
        setupUnityToBeResumed();
    }

    void resumeUnity() {
        this.mState.c(true);
    }

    void runOnAnonymousThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    void runOnUiThread(Runnable runnable) {
        Activity activity = this.mActivity;
        if (activity != null) {
            activity.runOnUiThread(runnable);
        } else if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.mHandler.post(runnable);
        } else {
            runnable.run();
        }
    }

    public abstract boolean runningOnMainThread();

    protected void setAccessibilityDelegate(UnityAccessibilityDelegate unityAccessibilityDelegate) {
        this.m_AccessibilityDelegate = unityAccessibilityDelegate;
    }

    protected void setClipboardText(String str) {
        this.m_ClipboardManager.setPrimaryClip(ClipData.newPlainText("Text", str));
    }

    void setLaunchURL(Uri uri) {
        invokeOnMainThread(new Q0(uri != null ? uri.toString() : null));
    }

    public abstract void setMainSurfaceViewAspectRatio(float f);

    protected void setupUnityToBePaused() {
        GoogleARCoreApi googleARCoreApi = this.m_ARCoreApi;
        if (googleARCoreApi != null) {
            googleARCoreApi.pauseARCore();
        }
        Q1 q1 = this.mVideoPlayerProxy;
        if (q1 != null) {
            q1.b();
        }
        AudioVolumeHandler audioVolumeHandler = this.m_AudioVolumeHandler;
        if (audioVolumeHandler != null) {
            audioVolumeHandler.a();
            this.m_AudioVolumeHandler = null;
        }
        OrientationLockListener orientationLockListener = this.m_OrientationLockListener;
        if (orientationLockListener != null) {
            orientationLockListener.a();
            this.m_OrientationLockListener = null;
        }
        G g = this.m_OnBackPressedDispatcher;
        if (g != null) {
            g.c = g.a != null;
            g.unregisterOnBackPressedCallback();
        }
        if (canPauseUnity()) {
            pauseUnity();
        }
    }

    protected void setupUnityToBeResumed() {
        GoogleARCoreApi googleARCoreApi = this.m_ARCoreApi;
        if (googleARCoreApi != null) {
            googleARCoreApi.resumeARCore();
        }
        this.mState.e(false);
        Q1 q1 = this.mVideoPlayerProxy;
        if (q1 != null) {
            q1.c();
        }
        if (canResumeUnity()) {
            resumeUnity();
        }
        if (this.m_AudioVolumeHandler == null) {
            this.m_AudioVolumeHandler = new AudioVolumeHandler(this.mContext);
        }
        if (this.m_OrientationLockListener == null && E1.d()) {
            this.m_OrientationLockListener = new OrientationLockListener(this.mContext);
        }
        G g = this.m_OnBackPressedDispatcher;
        if (g == null || !g.c) {
            return;
        }
        g.registerOnBackPressedCallback();
    }

    boolean shouldReportFullyDrawn() {
        if (this.mActivity == null) {
            return false;
        }
        return getAutoReportFullyDrawnEnabled();
    }

    boolean shouldSetGameState() {
        return PlatformSupport.TIRAMISU_SUPPORT && this.mActivity != null && !isUaaLUseCase() && getAutoSetGameStateEnabled();
    }

    protected boolean showVideoPlayer(String str, int i, int i2, int i3, boolean z, int i4, int i5) {
        if (this.mVideoPlayerProxy == null) {
            this.mVideoPlayerProxy = new Q1(this);
        }
        boolean zA = this.mVideoPlayerProxy.a(this.mContext, str, i, i2, i3, z, i4, i5, new R0(this));
        if (zA) {
            runOnUiThread(new S0(this));
        }
        return zA;
    }

    void shutdown() {
        this.mState.d(false);
    }

    protected boolean skipPermissionsDialog() {
        Activity activity = this.mActivity;
        if (activity != null) {
            return UnityPermissions.skipPermissionsDialog(activity);
        }
        return false;
    }

    public boolean startOrientationListener(int i) {
        String str;
        if (this.mOrientationListener != null) {
            str = "Orientation Listener already started.";
        } else {
            T0 t0 = new T0(this, this.mContext, i);
            this.mOrientationListener = t0;
            if (t0.canDetectOrientation()) {
                this.mOrientationListener.enable();
                return true;
            }
            str = "Orientation Listener cannot detect orientation.";
        }
        D.Log(5, str);
        return false;
    }

    public boolean stopOrientationListener() {
        OrientationEventListener orientationEventListener = this.mOrientationListener;
        if (orientationEventListener == null) {
            D.Log(5, "Orientation Listener was not started.");
            return false;
        }
        orientationEventListener.disable();
        this.mOrientationListener = null;
        return true;
    }

    protected void toggleGyroscopeSensor(boolean z) {
        SensorManager sensorManager = (SensorManager) this.mContext.getSystemService("sensor");
        Sensor defaultSensor = sensorManager.getDefaultSensor(11);
        if (z) {
            sensorManager.registerListener(this.m_FakeListener, defaultSensor, 1);
        } else {
            sensorManager.unregisterListener(this.m_FakeListener);
        }
    }

    public synchronized void triggerNextPermissionRequest() {
        Iterator it;
        HashMap map = this.m_PermissionRequests;
        if (map != null && (it = map.entrySet().iterator()) != null && it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            requestPermissionsFromActivity(((PermissionRequest) entry.getValue()).getPermissionNames(), ((Integer) entry.getKey()).intValue());
        }
    }

    public void unload() {
        nativeApplicationUnload();
    }

    public void windowFocusChanged(boolean z) {
        this.mState.b(z);
        if (handleFocus(z) && canResumeUnity()) {
            resumeUnity();
        }
    }
}
