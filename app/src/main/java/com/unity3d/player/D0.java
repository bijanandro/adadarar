package com.unity3d.player;

import android.content.Context;
import android.widget.FrameLayout;
import com.unity.androidnotifications.UnityNotificationManager;

/* JADX INFO: loaded from: classes.dex */
final class D0 extends FrameLayout {
    private C0042f a;
    private UnityPlayerForActivityOrService b;
    private O c;

    public D0(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        super(unityPlayerForActivityOrService.getContext());
        Context context = unityPlayerForActivityOrService.getContext();
        this.c = new O(context);
        this.b = unityPlayerForActivityOrService;
        C0042f c0042f = new C0042f(unityPlayerForActivityOrService);
        this.a = c0042f;
        c0042f.setId(context.getResources().getIdentifier("unitySurfaceView", UnityNotificationManager.KEY_ID, context.getPackageName()));
        this.b.applySurfaceViewSettings(this.a);
        this.a.getHolder().addCallback(new C0(this));
        this.a.setFocusable(true);
        this.a.setFocusableInTouchMode(true);
        this.a.setContentDescription(a(context));
        addView(this.a, new FrameLayout.LayoutParams(-1, -1, 17));
    }

    private static String a(Context context) {
        return context.getResources().getString(context.getResources().getIdentifier("game_view_content_description", "string", context.getPackageName()));
    }

    final C0042f a() {
        return this.a;
    }

    final void a(float f) {
        this.a.a(f);
    }

    public final void b() {
        O o = this.c;
        FrameLayout frameLayout = this.b.getFrameLayout();
        N n = o.b;
        if (n != null && n.getParent() != null) {
            frameLayout.removeView(o.b);
        }
        this.c.b = null;
    }

    public final boolean c() {
        C0042f c0042f = this.a;
        return c0042f != null && c0042f.a();
    }
}
