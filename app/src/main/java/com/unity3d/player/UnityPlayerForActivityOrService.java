package com.unity3d.player;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Looper;
import android.provider.Settings;
import android.view.Surface;
import android.view.WindowInsets;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class UnityPlayerForActivityOrService extends UnityPlayer {
    private boolean mMainDisplayOverride;
    private I mOnHandleFocusListener;
    private boolean mProcessKillRequested;
    private W mSoftInput;
    private long mSoftInputTimeoutMilliSeconds;
    private int m_IsNoWindowMode;
    private G0 m_MainThread;

    public enum MemoryUsage {
        Low(1),
        Medium(2),
        High(3),
        Critical(4);

        public final int value;

        MemoryUsage(int i) {
            this.value = i;
        }
    }

    public enum SynchronizationTimeout {
        Pause(0),
        SurfaceDetach(1),
        Destroy(2);

        private int m_TimeoutMilliseconds = 2000;
        final int value;

        SynchronizationTimeout(int i) {
            this.value = i;
        }

        public static void setTimeoutForAll(int i) {
            for (SynchronizationTimeout synchronizationTimeout : (SynchronizationTimeout[]) SynchronizationTimeout.class.getEnumConstants()) {
                synchronizationTimeout.setTimeout(i);
            }
        }

        public int getTimeout() {
            return this.m_TimeoutMilliseconds;
        }

        public void setTimeout(int i) {
            this.m_TimeoutMilliseconds = i;
        }
    }

    public UnityPlayerForActivityOrService(Context context) {
        this(context, null);
    }

    public UnityPlayerForActivityOrService(Context context, IUnityPlayerLifecycleEvents iUnityPlayerLifecycleEvents) {
        super(context, A.b, iUnityPlayerLifecycleEvents);
        this.m_MainThread = new G0(this);
        this.mMainDisplayOverride = false;
        this.mSoftInput = null;
        this.m_IsNoWindowMode = -1;
        this.mProcessKillRequested = true;
        this.mSoftInputTimeoutMilliSeconds = 1000L;
        B b = new B(context, this);
        b.setOnApplyWindowInsetsListener(new ViewOnApplyWindowInsetsListenerC0068n1(this));
        initialize(b);
        this.m_MainThread.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissSoftInput() {
        W w = this.mSoftInput;
        if (w != null) {
            w.b();
            nativeReportKeyboardConfigChanged();
        }
    }

    private long getSoftInputTimeout() {
        return Math.round(this.mSoftInputTimeoutMilliSeconds * ((double) Math.max(1.0f, Settings.System.getFloat(this.mContext.getContentResolver(), "animator_duration_scale", 0.0f))));
    }

    private final native boolean nativeDone();

    private final native boolean nativeGetNoWindowMode();

    private final native void nativeMemoryUsageChanged(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeOnApplyWindowInsets(WindowInsets windowInsets);

    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean nativePause();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeRecreateGfxState(int i, Surface surface);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeReportKeyboardConfigChanged();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeResume();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSendSurfaceChangedEvent();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetInputArea(int i, int i2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetInputSelection(int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetInputString(String str);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSetKeyboardIsVisible(boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSoftInputCanceled();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSoftInputClosed();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void nativeSoftInputLostFocus();

    /* JADX INFO: Access modifiers changed from: private */
    public void queueDestroy() {
        D.Log(4, "Queue Destroy");
        runOnUiThread(new RunnableC0065m1(this));
    }

    private void raiseFocusListener(boolean z) {
        I i = this.mOnHandleFocusListener;
        if (i != null) {
            RunnableC0038d1 runnableC0038d1 = ((C0032b1) i).a;
            runnableC0038d1.b = true;
            if (runnableC0038d1.a) {
                runnableC0038d1.c.release();
            }
        }
    }

    private boolean updateDisplayInternal(int i, Surface surface) {
        if (!isNativeInitialized()) {
            return false;
        }
        Semaphore semaphore = new Semaphore(0);
        s1 s1Var = new s1(this, i, surface, semaphore);
        if (i == 0) {
            G0 g0 = this.m_MainThread;
            if (surface == null) {
                g0.d(s1Var);
            } else {
                g0.b(s1Var);
            }
        } else {
            s1Var.run();
        }
        if (surface != null || i != 0) {
            return true;
        }
        try {
            SynchronizationTimeout synchronizationTimeout = SynchronizationTimeout.SurfaceDetach;
            if (semaphore.tryAcquire(synchronizationTimeout.getTimeout(), TimeUnit.MILLISECONDS)) {
                return true;
            }
            D.Log(5, "Timeout (" + synchronizationTimeout.getTimeout() + " ms) while trying detaching primary window.");
            return true;
        } catch (InterruptedException unused) {
            D.Log(5, "UI thread got interrupted while trying to detach the primary window from the Unity Engine.");
            return true;
        }
    }

    @Override // com.unity3d.player.UnityPlayer
    void cleanupResourcesForDestroy() {
        this.m_MainThread.c();
        try {
            this.m_MainThread.join(SynchronizationTimeout.Destroy.getTimeout());
        } catch (InterruptedException unused) {
            this.m_MainThread.interrupt();
        }
        if (E1.d()) {
            getFrameLayout().removeAllViews();
        }
        if (this.mProcessKillRequested) {
            this.m_UnityPlayerLifecycleEvents.onUnityPlayerQuitted();
            kill();
        }
    }

    @Override // com.unity3d.player.UnityPlayer
    public void configurationChanged(Configuration configuration) {
        super.configurationChanged(configuration);
        if (isNativeInitialized()) {
            invokeOnMainThread(new RunnableC0077q1(this, new Configuration(configuration)));
        }
    }

    public boolean displayChanged(int i, Surface surface) {
        if (i == 0) {
            this.mMainDisplayOverride = surface != null;
            runOnUiThread(new t1(this));
        }
        return updateDisplayInternal(i, surface);
    }

    boolean getHaveAndroidWindowSupport() {
        if (this.m_IsNoWindowMode == -1) {
            this.m_IsNoWindowMode = nativeGetNoWindowMode() ? 1 : 0;
        }
        return this.m_IsNoWindowMode == 1;
    }

    @Override // com.unity3d.player.UnityPlayer
    public C0042f getSurfaceView() {
        return getView().a();
    }

    @Override // com.unity3d.player.UnityPlayer
    public D0 getView() {
        return ((B) getFrameLayout()).a();
    }

    @Override // com.unity3d.player.UnityPlayer
    boolean handleFocus(boolean z) {
        W w;
        if (!this.mState.a() || ((w = this.mSoftInput) != null && !w.c())) {
            raiseFocusListener(z);
            return false;
        }
        G0 g0 = this.m_MainThread;
        if (z) {
            g0.a();
        } else {
            g0.b();
        }
        raiseFocusListener(z);
        return true;
    }

    @Override // com.unity3d.player.UnityPlayer
    void hidePreservedContent() {
        runOnUiThread(new v1(this));
    }

    protected void hideSoftInput() {
        if (this.mSoftInput == null) {
            return;
        }
        reportSoftInputArea(new Rect());
        reportSoftInputIsVisible(false);
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            dismissSoftInput();
            return;
        }
        Semaphore semaphore = new Semaphore(0);
        postOnUiThread(new RunnableC0038d1(this, semaphore, this));
        try {
            try {
                if (!semaphore.tryAcquire(getSoftInputTimeout(), TimeUnit.MILLISECONDS)) {
                    D.Log(6, "Timeout (" + getSoftInputTimeout() + " ms) while waiting softinput hiding operation.");
                }
            } catch (InterruptedException unused) {
                D.Log(6, "UI thread got interrupted while waiting softinput hiding operation.");
            }
        } finally {
            this.mSoftInput = null;
        }
    }

    final native void nativeConfigurationChanged(Configuration configuration);

    final native void nativeFocusChanged(boolean z);

    final native void nativeOrientationChanged(int i, int i2);

    final native boolean nativeRender();

    @Override // com.unity3d.player.UnityPlayer
    void onOrientationChanged(int i, int i2) {
        this.m_MainThread.a(this.mNaturalOrientation, i2);
    }

    public void onTrimMemory(MemoryUsage memoryUsage) {
        if (E1.d()) {
            nativeMemoryUsageChanged(memoryUsage.value);
        }
    }

    @Override // com.unity3d.player.UnityPlayer
    void pauseUnity() {
        super.pauseUnity();
        reportSoftInputStr(null, 1, true);
        this.mState.c(false);
        this.mState.e(true);
        if (E1.d()) {
            Semaphore semaphore = new Semaphore(0);
            this.m_MainThread.a(isFinishing() ? new RunnableC0071o1(this, semaphore) : new RunnableC0074p1(this, semaphore));
            try {
                SynchronizationTimeout synchronizationTimeout = SynchronizationTimeout.Pause;
                if (!semaphore.tryAcquire(synchronizationTimeout.getTimeout(), TimeUnit.MILLISECONDS)) {
                    D.Log(5, "Timeout (" + synchronizationTimeout.getTimeout() + " ms) while trying to pause the Unity Engine.");
                }
            } catch (InterruptedException unused) {
                D.Log(5, "UI thread got interrupted while trying to pause the Unity Engine.");
            }
        }
        if (this.m_AddPhoneCallListener) {
            this.m_TelephonyManager.listen(this.m_PhoneCallListener, 0);
        }
    }

    protected void reportSoftInputArea(Rect rect) {
        invokeOnMainThread((Runnable) new C0059k1(this, rect));
    }

    protected void reportSoftInputIsVisible(boolean z) {
        invokeOnMainThread((Runnable) new C0062l1(this, z));
    }

    protected void reportSoftInputSelection(int i, int i2) {
        invokeOnMainThread((Runnable) new C0056j1(this, i, i2));
    }

    protected void reportSoftInputStr(String str, int i, boolean z) {
        if (i == 1) {
            hideSoftInput();
        }
        invokeOnMainThread((Runnable) new C0053i1(this, z, str, i));
    }

    @Override // com.unity3d.player.UnityPlayer
    void resumeUnity() {
        super.resumeUnity();
        invokeOnMainThread(new u1(this));
        this.m_MainThread.d();
    }

    @Override // com.unity3d.player.UnityPlayer
    public boolean runningOnMainThread() {
        return Thread.currentThread() == this.m_MainThread;
    }

    void sendSurfaceChangedEvent() {
        if (isNativeInitialized()) {
            this.m_MainThread.c(new RunnableC0080r1(this));
        }
    }

    protected void setCharacterLimit(int i) {
        runOnUiThread(new RunnableC0044f1(this, i));
    }

    protected void setHideInputField(boolean z) {
        runOnUiThread(new RunnableC0047g1(this, z));
    }

    @Override // com.unity3d.player.UnityPlayer
    public void setMainSurfaceViewAspectRatio(float f) {
        runOnUiThread(new w1(this, f));
    }

    public void setOnHandleFocusListener(I i) {
        this.mOnHandleFocusListener = i;
    }

    protected void setSelection(int i, int i2) {
        runOnUiThread(new RunnableC0050h1(this, i, i2));
    }

    protected void setSoftInputStr(String str) {
        runOnUiThread(new RunnableC0041e1(this, str));
    }

    protected void showSoftInput(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2, int i2, boolean z5, boolean z6) {
        Semaphore semaphore = new Semaphore(0);
        postOnUiThread(new y1(this, this, str, i, z, z2, z3, z4, str2, i2, z5, z6, semaphore));
        try {
            if (semaphore.tryAcquire(getSoftInputTimeout(), TimeUnit.MILLISECONDS)) {
                return;
            }
            D.Log(6, "Timeout (" + getSoftInputTimeout() + " ms) while waiting softinput showing operation.");
        } catch (InterruptedException unused) {
            D.Log(6, "UI thread got interrupted while waiting softinput showing operation.");
        }
    }

    @Override // com.unity3d.player.UnityPlayer
    void shutdown() {
        this.mProcessKillRequested = nativeDone();
        super.shutdown();
    }

    void updateGLDisplay(int i, Surface surface) {
        if (this.mMainDisplayOverride) {
            return;
        }
        updateDisplayInternal(i, surface);
    }
}
