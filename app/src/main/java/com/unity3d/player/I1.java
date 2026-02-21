package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.MediaController;
import java.io.FileInputStream;
import java.io.IOException;
import kotlin.time.DurationKt;

/* JADX INFO: loaded from: classes.dex */
public final class I1 extends FrameLayout implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, MediaController.MediaPlayerControl {
    public static final /* synthetic */ int A = 0;
    private final Context a;
    private final SurfaceView b;
    private final SurfaceHolder c;
    private final String d;
    private final int e;
    private final int f;
    private final boolean g;
    private final long h;
    private final long i;
    private final I1 j;
    private int k;
    private int l;
    private int m;
    private int n;
    private MediaPlayer o;
    private MediaController p;
    private boolean q;
    private boolean r;
    private int s;
    private boolean t;
    private int u;
    private boolean v;
    private G w;
    private G1 x;
    private H1 y;
    private volatile int z;

    protected I1(Context context, UnityPlayer unityPlayer, String str, int i, int i2, int i3, boolean z, long j, long j2, G1 g1) {
        super(context);
        this.q = false;
        this.r = false;
        this.s = 0;
        this.t = false;
        this.u = 0;
        this.v = false;
        this.w = null;
        this.z = 0;
        this.x = g1;
        this.a = context;
        this.j = this;
        SurfaceView surfaceView = new SurfaceView(context);
        this.b = surfaceView;
        SurfaceHolder holder = surfaceView.getHolder();
        this.c = holder;
        holder.addCallback(this);
        setBackgroundColor(i);
        addView(surfaceView);
        this.d = str;
        this.e = i2;
        this.f = i3;
        this.g = z;
        this.h = j;
        this.i = j2;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.w = H.a(unityPlayer.getActivity(), DurationKt.NANOS_IN_MILLIS, new F1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.z = i;
        G1 g1 = this.x;
        if (g1 != null) {
            ((K1) g1).a(this.z);
        }
    }

    final boolean a() {
        return this.t;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return true;
    }

    public void cancelOnPrepare() {
        a(2);
    }

    protected void destroyPlayer() {
        if (!this.t) {
            pause();
        }
        doCleanUp();
        G g = this.w;
        if (g != null) {
            g.unregisterOnBackPressedCallback();
            this.w = null;
        }
    }

    protected void doCleanUp() {
        H1 h1 = this.y;
        if (h1 != null) {
            h1.b = true;
            this.y = null;
        }
        MediaPlayer mediaPlayer = this.o;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.o.release();
            this.o = null;
        }
        this.m = 0;
        this.n = 0;
        this.r = false;
        this.q = false;
    }

    public boolean exitOnKeypress(int i, KeyEvent keyEvent) {
        Runnable runnable;
        if (i != 4 && (this.e != 2 || i == 0 || keyEvent.isSystem())) {
            return false;
        }
        com.unity3d.player.a.e eVar = this.w.a;
        if (eVar == null || (runnable = ((F) eVar).a) == null) {
            return true;
        }
        runnable.run();
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        MediaPlayer mediaPlayer = this.o;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getAudioSessionId();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.g) {
            return this.s;
        }
        return 100;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        MediaPlayer mediaPlayer = this.o;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getCurrentPosition();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        MediaPlayer mediaPlayer = this.o;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getDuration();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        boolean z = this.r && this.q;
        MediaPlayer mediaPlayer = this.o;
        return mediaPlayer == null ? !z : mediaPlayer.isPlaying() || !z;
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.s = i;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        destroyPlayer();
        a(3);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        MediaController mediaController = this.p;
        return mediaController != null ? mediaController.onKeyDown(i, keyEvent) : super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (exitOnKeypress(i, keyEvent)) {
            return true;
        }
        MediaController mediaController = this.p;
        return mediaController != null ? mediaController.onKeyUp(i, keyEvent) : super.onKeyUp(i, keyEvent);
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        H1 h1 = this.y;
        if (h1 != null) {
            h1.b = true;
            this.y = null;
        }
        int i = this.e;
        if (i == 0 || i == 1) {
            MediaController mediaController = new MediaController(this.a);
            this.p = mediaController;
            mediaController.setMediaPlayer(this);
            this.p.setAnchorView(this);
            this.p.setEnabled(true);
            if (!PlatformSupport.RED_VELVET_CAKE_SUPPORT) {
                Context context = this.a;
                if (context instanceof Activity) {
                    this.p.setSystemUiVisibility(((Activity) context).getWindow().getDecorView().getSystemUiVisibility());
                }
            }
            this.p.show();
        }
        this.r = true;
        if (!this.q || isPlaying()) {
            return;
        }
        a(1);
        updateVideoLayout();
        if (this.t) {
            return;
        }
        start();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.e != 2 || action != 0) {
            MediaController mediaController = this.p;
            return mediaController != null ? mediaController.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
        }
        destroyPlayer();
        a(3);
        return true;
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 0 || i2 == 0) {
            return;
        }
        this.q = true;
        this.m = i;
        this.n = i2;
        if (!this.r || isPlaying()) {
            return;
        }
        a(1);
        updateVideoLayout();
        if (this.t) {
            return;
        }
        start();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        MediaPlayer mediaPlayer = this.o;
        if (mediaPlayer == null) {
            return;
        }
        if (this.v) {
            this.u = mediaPlayer.getCurrentPosition();
            this.o.pause();
        }
        this.t = true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i) {
        MediaPlayer mediaPlayer = this.o;
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.seekTo(i);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        MediaPlayer mediaPlayer = this.o;
        if (mediaPlayer == null) {
            return;
        }
        if (this.v) {
            int i = this.u;
            if (i > 0) {
                mediaPlayer.seekTo(i);
            }
            this.o.start();
            this.u = 0;
        }
        this.t = false;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (this.k == i2 && this.l == i3) {
            return;
        }
        this.k = i2;
        this.l = i3;
        if (this.v) {
            updateVideoLayout();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        FileInputStream fileInputStream;
        this.v = true;
        if (this.t) {
            MediaPlayer mediaPlayer = this.o;
            if (mediaPlayer == null || this.u <= 0) {
                return;
            }
            mediaPlayer.setDisplay(this.c);
            this.o.seekTo(this.u);
            return;
        }
        MediaPlayer mediaPlayer2 = this.o;
        if (mediaPlayer2 != null) {
            mediaPlayer2.setDisplay(this.c);
            if (this.t) {
                return;
            }
            int i = this.u;
            if (i > 0) {
                this.o.seekTo(i);
            }
            this.o.start();
            this.u = 0;
            return;
        }
        a(0);
        doCleanUp();
        try {
            MediaPlayer mediaPlayer3 = new MediaPlayer();
            this.o = mediaPlayer3;
            if (this.g) {
                mediaPlayer3.setDataSource(this.a, Uri.parse(this.d));
            } else {
                if (this.i != 0) {
                    fileInputStream = new FileInputStream(this.d);
                    this.o.setDataSource(fileInputStream.getFD(), this.h, this.i);
                } else {
                    try {
                        AssetFileDescriptor assetFileDescriptorOpenFd = getResources().getAssets().openFd(this.d);
                        this.o.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
                        assetFileDescriptorOpenFd.close();
                    } catch (IOException unused) {
                        fileInputStream = new FileInputStream(this.d);
                        this.o.setDataSource(fileInputStream.getFD());
                        fileInputStream.close();
                    }
                }
                fileInputStream.close();
            }
            this.o.setDisplay(this.c);
            this.o.setScreenOnWhilePlaying(true);
            this.o.setOnBufferingUpdateListener(this);
            this.o.setOnCompletionListener(this);
            this.o.setOnPreparedListener(this);
            this.o.setOnVideoSizeChangedListener(this);
            this.o.setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(3).build());
            this.o.prepareAsync();
            this.y = new H1(this);
            new Thread(this.y).start();
        } catch (Exception unused2) {
            a(2);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.v = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
    
        if (r7 <= r3) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0039, code lost:
    
        if (r7 >= r3) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003b, code lost:
    
        r4 = (int) (r5 / r3);
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
    
        r2 = (int) (r6 * r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0042, code lost:
    
        if (r8 == 0) goto L27;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void updateVideoLayout() {
        /*
            r10 = this;
            android.media.MediaPlayer r0 = r10.o
            if (r0 != 0) goto L5
            return
        L5:
            android.content.Context r0 = r10.a
            android.graphics.Point r0 = com.unity3d.player.R1.a(r0)
            int r1 = r0.x
            if (r1 <= 0) goto L17
            int r0 = r0.y
            if (r0 <= 0) goto L17
            r10.k = r1
            r10.l = r0
        L17:
            int r0 = r10.k
            int r1 = r10.l
            boolean r2 = r10.q
            if (r2 == 0) goto L45
            int r2 = r10.m
            float r3 = (float) r2
            int r4 = r10.n
            float r5 = (float) r4
            float r3 = r3 / r5
            float r5 = (float) r0
            float r6 = (float) r1
            float r7 = r5 / r6
            int r8 = r10.f
            r9 = 1
            if (r8 != r9) goto L34
            int r2 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r2 > 0) goto L3f
            goto L3b
        L34:
            r9 = 2
            if (r8 != r9) goto L42
            int r2 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r2 < 0) goto L3f
        L3b:
            float r5 = r5 / r3
            int r4 = (int) r5
            r2 = r0
            goto L47
        L3f:
            float r6 = r6 * r3
            int r2 = (int) r6
            goto L46
        L42:
            if (r8 != 0) goto L45
            goto L47
        L45:
            r2 = r0
        L46:
            r4 = r1
        L47:
            if (r0 != r2) goto L4b
            if (r1 == r4) goto L59
        L4b:
            android.widget.FrameLayout$LayoutParams r0 = new android.widget.FrameLayout$LayoutParams
            r1 = 17
            r0.<init>(r2, r4, r1)
            com.unity3d.player.I1 r1 = r10.j
            android.view.SurfaceView r2 = r10.b
            r1.updateViewLayout(r2, r0)
        L59:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.I1.updateVideoLayout():void");
    }
}
