package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Range;
import android.util.Size;
import android.util.SizeF;
import android.view.Surface;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.unity3d.player.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0089w {
    private static CameraManager B;
    private static String[] C;
    private static Semaphore D = new Semaphore(1);
    public static final /* synthetic */ int E = 0;
    private com.unity3d.player.a.b a;
    protected CameraDevice b;
    private HandlerThread c;
    protected Handler d;
    private Rect e;
    private Rect f;
    private int g;
    private int h;
    private int k;
    private int l;
    private Range n;
    private Image p;
    private CaptureRequest.Builder q;
    private int u;
    private SurfaceTexture v;
    private float i = -1.0f;
    private float j = -1.0f;
    private boolean m = false;
    private ImageReader o = null;
    private CameraCaptureSession r = null;
    protected CameraCaptureSession.CaptureCallback s = null;
    private Object t = new Object();
    protected Surface w = null;
    private int A = 3;
    private final CameraDevice.StateCallback x = new C0081s(this);
    private final ImageReader.OnImageAvailableListener y = new C0083t(this);
    private final SurfaceTexture.OnFrameAvailableListener z = new C0085u(this);

    protected C0089w(com.unity3d.player.a.b bVar) {
        this.a = null;
        this.a = bVar;
        e();
    }

    public static int a(Context context, int i) {
        try {
            CameraCharacteristics cameraCharacteristics = getCameraManager(context).getCameraCharacteristics(getCameraIds(context)[i]);
            float[] fArr = (float[]) cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
            SizeF sizeF = (SizeF) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE);
            if (fArr.length > 0) {
                return (int) ((fArr[0] * 36.0f) / sizeF.getWidth());
            }
        } catch (CameraAccessException e) {
            D.Log(6, "Camera2: CameraAccessException " + e);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CameraDevice cameraDevice) {
        synchronized (this.t) {
            this.r = null;
        }
        cameraDevice.close();
        this.b = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object obj) {
        if (obj != "Focus") {
            if (obj == "Cancel focus") {
                synchronized (this.t) {
                    if (this.r != null) {
                        g();
                    }
                }
                return;
            }
            return;
        }
        this.m = false;
        synchronized (this.t) {
            if (this.r != null) {
                try {
                    this.q.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
                    this.q.setTag("Regular");
                    this.r.setRepeatingRequest(this.q.build(), getCaptureCallback(), this.d);
                } catch (CameraAccessException e) {
                    D.Log(6, "Camera2: CameraAccessException " + e);
                }
            }
        }
    }

    private void b() {
        try {
            Semaphore semaphore = D;
            TimeUnit timeUnit = TimeUnit.SECONDS;
            if (!semaphore.tryAcquire(4L, timeUnit)) {
                D.Log(5, "Camera2: Timeout waiting to lock camera for closing.");
                return;
            }
            this.b.close();
            try {
                if (!D.tryAcquire(4L, timeUnit)) {
                    D.Log(5, "Camera2: Timeout waiting to close camera.");
                }
            } catch (InterruptedException e) {
                D.Log(6, "Camera2: Interrupted while waiting to close camera " + e);
            }
            this.b = null;
            D.release();
        } catch (InterruptedException e2) {
            D.Log(6, "Camera2: Interrupted while trying to lock camera for closing " + e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0050 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int[] b(android.content.Context r3, int r4) {
        /*
            r0 = 6
            r1 = 0
            android.hardware.camera2.CameraManager r2 = getCameraManager(r3)     // Catch: android.hardware.camera2.CameraAccessException -> L51
            java.lang.String[] r3 = getCameraIds(r3)     // Catch: android.hardware.camera2.CameraAccessException -> L51
            r3 = r3[r4]     // Catch: android.hardware.camera2.CameraAccessException -> L51
            android.hardware.camera2.CameraCharacteristics r3 = r2.getCameraCharacteristics(r3)     // Catch: android.hardware.camera2.CameraAccessException -> L51
            android.hardware.camera2.CameraCharacteristics$Key r4 = android.hardware.camera2.CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP
            java.lang.Object r3 = r3.get(r4)
            android.hardware.camera2.params.StreamConfigurationMap r3 = (android.hardware.camera2.params.StreamConfigurationMap) r3
            if (r3 != 0) goto L21
            java.lang.String r3 = "Camera2: configuration map is not available."
            com.unity3d.player.D.Log(r0, r3)
        L1f:
            r3 = r1
            goto L2d
        L21:
            r4 = 35
            android.util.Size[] r3 = r3.getOutputSizes(r4)
            if (r3 == 0) goto L1f
            int r4 = r3.length
            if (r4 != 0) goto L2d
            goto L1f
        L2d:
            if (r3 == 0) goto L50
            int r4 = r3.length
            int r4 = r4 * 2
            int[] r4 = new int[r4]
            r0 = 0
        L35:
            int r1 = r3.length
            if (r0 >= r1) goto L4f
            int r1 = r0 * 2
            r2 = r3[r0]
            int r2 = r2.getWidth()
            r4[r1] = r2
            int r1 = r1 + 1
            r2 = r3[r0]
            int r2 = r2.getHeight()
            r4[r1] = r2
            int r0 = r0 + 1
            goto L35
        L4f:
            return r4
        L50:
            return r1
        L51:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r2 = "Camera2: CameraAccessException "
            r4.<init>(r2)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.unity3d.player.D.Log(r0, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.C0089w.b(android.content.Context, int):int[]");
    }

    public static int c(Context context, int i) {
        try {
            return ((Integer) getCameraManager(context).getCameraCharacteristics(getCameraIds(context)[i]).get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        } catch (CameraAccessException e) {
            D.Log(6, "Camera2: CameraAccessException " + e);
            return 0;
        }
    }

    public static boolean d(Context context, int i) {
        try {
            return ((Integer) getCameraManager(context).getCameraCharacteristics(getCameraIds(context)[i]).get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() > 0;
        } catch (CameraAccessException e) {
            D.Log(6, "Camera2: CameraAccessException " + e);
            return false;
        }
    }

    private void e() {
        HandlerThread handlerThread = new HandlerThread("CameraBackground");
        this.c = handlerThread;
        handlerThread.start();
        this.d = new Handler(this.c.getLooper());
    }

    public static boolean e(Context context, int i) {
        try {
            return ((Integer) getCameraManager(context).getCameraCharacteristics(getCameraIds(context)[i]).get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
        } catch (CameraAccessException e) {
            D.Log(6, "Camera2: CameraAccessException " + e);
            return false;
        }
    }

    private void f() {
        try {
            CameraCaptureSession cameraCaptureSession = this.r;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.stopRepeating();
                this.q.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
                this.q.set(CaptureRequest.CONTROL_AF_MODE, 0);
                this.q.setTag("Cancel focus");
                this.r.capture(this.q.build(), getCaptureCallback(), this.d);
            }
        } catch (CameraAccessException e) {
            D.Log(6, "Camera2: CameraAccessException " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00be A[Catch: CameraAccessException -> 0x00e5, TryCatch #0 {CameraAccessException -> 0x00e5, blocks: (B:2:0x0000, B:4:0x0004, B:8:0x0011, B:13:0x001d, B:14:0x00be, B:16:0x00d5), top: B:22:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void g() {
        /*
            Method dump skipped, instruction units count: 249
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.C0089w.g():void");
    }

    protected static String[] getCameraIds(Context context) {
        if (C == null) {
            try {
                if (PlatformSupport.QUINCE_TART_SUPPORT) {
                    C = C0095z.a(context);
                } else {
                    C = getCameraManager(context).getCameraIdList();
                }
            } catch (CameraAccessException e) {
                D.Log(6, "Camera2: CameraAccessException " + e);
                C = new String[0];
            }
        }
        return C;
    }

    protected static CameraManager getCameraManager(Context context) {
        if (B == null) {
            B = (CameraManager) context.getSystemService("camera");
        }
        return B;
    }

    public final void a() {
        if (this.b != null) {
            i();
            b();
            this.s = null;
            this.w = null;
            this.v = null;
            Image image = this.p;
            if (image != null) {
                image.close();
                this.p = null;
            }
            ImageReader imageReader = this.o;
            if (imageReader != null) {
                imageReader.close();
                this.o = null;
            }
        }
        this.c.quit();
        try {
            this.c.join(4000L);
            this.c = null;
            this.d = null;
        } catch (InterruptedException e) {
            this.c.interrupt();
            D.Log(6, "Camera2: Interrupted while waiting for the background thread to finish " + e);
        }
    }

    public final boolean a(float f, float f2) {
        if (this.h <= 0) {
            return false;
        }
        if (this.m) {
            D.Log(5, "Camera2: Setting manual focus point already started.");
            return false;
        }
        this.i = f;
        this.j = f2;
        synchronized (this.t) {
            if (this.r != null && this.A != 2) {
                f();
            }
        }
        return true;
    }

    public final boolean a(Context context, int i, int i2, int i3, int i4, int i5, Surface surface) {
        int i6;
        String str;
        int iIntValue = i4;
        try {
            CameraCharacteristics cameraCharacteristics = B.getCameraCharacteristics(getCameraIds(context)[i]);
            if (((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2) {
                D.Log(5, "Camera2: only LEGACY hardware level is supported.");
            }
            StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            Size[] sizeArr = null;
            if (streamConfigurationMap == null) {
                D.Log(6, "Camera2: configuration map is not available.");
            } else {
                Size[] outputSizes = streamConfigurationMap.getOutputSizes(35);
                if (outputSizes != null && outputSizes.length != 0) {
                    sizeArr = outputSizes;
                }
            }
            if (sizeArr == null || sizeArr.length == 0) {
                return false;
            }
            double d = i2;
            double d2 = i3;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            double d3 = Double.MAX_VALUE;
            while (i7 < sizeArr.length) {
                int width = sizeArr[i7].getWidth();
                int height = sizeArr[i7].getHeight();
                CameraCharacteristics cameraCharacteristics2 = cameraCharacteristics;
                double d4 = d;
                double dAbs = Math.abs(Math.log(d2 / ((double) height))) + Math.abs(Math.log(d / ((double) width)));
                if (dAbs < d3) {
                    i8 = height;
                    d3 = dAbs;
                    i9 = width;
                }
                i7++;
                cameraCharacteristics = cameraCharacteristics2;
                d = d4;
            }
            CameraCharacteristics cameraCharacteristics3 = cameraCharacteristics;
            this.e = new Rect(0, 0, i9, i8);
            Range[] rangeArr = (Range[]) cameraCharacteristics3.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
            if (rangeArr == null || rangeArr.length == 0) {
                D.Log(6, "Camera2: target FPS ranges are not avialable.");
                return false;
            }
            int i10 = -1;
            int i11 = 0;
            double d5 = Double.MAX_VALUE;
            while (true) {
                if (i11 < rangeArr.length) {
                    int iIntValue2 = ((Integer) rangeArr[i11].getLower()).intValue();
                    int iIntValue3 = ((Integer) rangeArr[i11].getUpper()).intValue();
                    float f = iIntValue;
                    if (f + 0.1f > iIntValue2 && f - 0.1f < iIntValue3) {
                        break;
                    }
                    if (dMin < d5) {
                        i10 = i11;
                        d5 = dMin;
                    }
                    i11++;
                } else {
                    iIntValue = ((Integer) (iIntValue > ((Integer) rangeArr[i10].getUpper()).intValue() ? rangeArr[i10].getUpper() : rangeArr[i10].getLower())).intValue();
                }
            }
            this.n = new Range(Integer.valueOf(iIntValue), Integer.valueOf(iIntValue));
            try {
                if (!D.tryAcquire(4L, TimeUnit.SECONDS)) {
                    D.Log(5, "Camera2: Timeout waiting to lock camera for opening.");
                    return false;
                }
                try {
                    B.openCamera(getCameraIdToOpen(context, i), this.x, this.d);
                } catch (CameraAccessException e) {
                    D.Log(6, "Camera2: CameraAccessException " + e);
                    D.release();
                    return false;
                } catch (IllegalArgumentException e2) {
                    str = "Camera2: IllegalArgumentException " + e2;
                    i6 = 6;
                    D.Log(i6, str);
                } catch (SecurityException e3) {
                    i6 = 6;
                    str = "Camera2: SecurityException " + e3;
                    D.Log(i6, str);
                }
                try {
                } catch (InterruptedException e4) {
                    D.Log(6, "Camera2: Interrupted while waiting to open camera " + e4);
                }
                if (!D.tryAcquire(4L, TimeUnit.SECONDS)) {
                    D.Log(5, "Camera2: Timeout waiting to open camera.");
                    return false;
                }
                D.release();
                this.u = i5;
                this.w = surface;
                int iIntValue4 = ((Integer) cameraCharacteristics3.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue();
                this.h = iIntValue4;
                if (iIntValue4 > 0) {
                    this.f = (Rect) cameraCharacteristics3.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
                    float fWidth = this.e.width() / this.e.height();
                    if (fWidth > r0.width() / this.f.height()) {
                        this.k = 0;
                        this.l = (int) ((this.f.height() - (this.f.width() / fWidth)) / 2.0f);
                    } else {
                        this.l = 0;
                        this.k = (int) ((this.f.width() - (this.f.height() * fWidth)) / 2.0f);
                    }
                    this.g = Math.min(this.f.width(), this.f.height()) / 20;
                }
                return this.b != null;
            } catch (InterruptedException e5) {
                D.Log(6, "Camera2: Interrupted while trying to lock camera for opening " + e5);
                return false;
            }
        } catch (CameraAccessException e6) {
            D.Log(6, "Camera2: CameraAccessException " + e6);
            return false;
        }
    }

    public final Rect c() {
        return this.e;
    }

    protected void createCaptureSession(CameraCaptureSession.StateCallback stateCallback) throws CameraAccessException {
        this.b.createCaptureSession(Arrays.asList(this.w), stateCallback, this.d);
    }

    public final void d() {
        synchronized (this.t) {
            CameraCaptureSession cameraCaptureSession = this.r;
            if (cameraCaptureSession != null) {
                try {
                    cameraCaptureSession.stopRepeating();
                    this.A = 2;
                } catch (CameraAccessException e) {
                    D.Log(6, "Camera2: CameraAccessException " + e);
                }
            }
        }
    }

    protected String getCameraIdToOpen(Context context, int i) {
        return getCameraIds(context)[i];
    }

    protected CameraCaptureSession.CaptureCallback getCaptureCallback() {
        if (this.s == null) {
            this.s = new C0087v(this);
        }
        return this.s;
    }

    public final void h() {
        Surface surface;
        if (this.u != 0) {
            if (this.w == null) {
                SurfaceTexture surfaceTexture = new SurfaceTexture(this.u);
                this.v = surfaceTexture;
                surfaceTexture.setDefaultBufferSize(this.e.width(), this.e.height());
                this.v.setOnFrameAvailableListener(this.z, this.d);
                surface = new Surface(this.v);
                this.w = surface;
            }
        } else if (this.w == null && this.o == null) {
            ImageReader imageReaderNewInstance = ImageReader.newInstance(this.e.width(), this.e.height(), 35, 2);
            this.o = imageReaderNewInstance;
            imageReaderNewInstance.setOnImageAvailableListener(this.y, this.d);
            this.p = null;
            surface = this.o.getSurface();
            this.w = surface;
        }
        try {
            CameraCaptureSession cameraCaptureSession = this.r;
            if (cameraCaptureSession == null) {
                createCaptureSession(new C0078r(this));
            } else if (this.A == 2) {
                cameraCaptureSession.setRepeatingRequest(this.q.build(), getCaptureCallback(), this.d);
            }
            this.A = 1;
        } catch (CameraAccessException e) {
            D.Log(6, "Camera2: CameraAccessException " + e);
        }
    }

    public final void i() {
        synchronized (this.t) {
            CameraCaptureSession cameraCaptureSession = this.r;
            if (cameraCaptureSession != null) {
                try {
                    cameraCaptureSession.abortCaptures();
                } catch (CameraAccessException e) {
                    D.Log(6, "Camera2: CameraAccessException " + e);
                }
                this.r.close();
                this.r = null;
                this.A = 3;
            }
        }
    }
}
