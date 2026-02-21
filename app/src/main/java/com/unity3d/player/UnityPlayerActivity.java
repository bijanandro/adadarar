package com.unity3d.player;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.unity3d.player.UnityPlayerForActivityOrService;

/* JADX INFO: loaded from: classes.dex */
public class UnityPlayerActivity extends Activity implements IUnityPlayerLifecycleEvents, IUnityPermissionRequestSupport, IUnityPlayerSupport {
    protected UnityPlayerForActivityOrService mUnityPlayer;

    @Override // com.unity3d.player.IUnityPlayerLifecycleEvents
    public void onUnityPlayerQuitted() {
    }

    protected String updateUnityCommandLineArguments(String str) {
        return str;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        getIntent().putExtra("unity", updateUnityCommandLineArguments(getIntent().getStringExtra("unity")));
        UnityPlayerForActivityOrService unityPlayerForActivityOrService = new UnityPlayerForActivityOrService(this, this);
        this.mUnityPlayer = unityPlayerForActivityOrService;
        setContentView(unityPlayerForActivityOrService.getFrameLayout());
        this.mUnityPlayer.getFrameLayout().requestFocus();
    }

    @Override // com.unity3d.player.IUnityPlayerSupport
    public UnityPlayerForActivityOrService getUnityPlayerConnection() {
        return this.mUnityPlayer;
    }

    @Override // com.unity3d.player.IUnityPlayerLifecycleEvents
    public void onUnityPlayerUnloaded() {
        moveTaskToBack(true);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        this.mUnityPlayer.newIntent(intent);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.mUnityPlayer.destroy();
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.mUnityPlayer.onStop();
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mUnityPlayer.onStart();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mUnityPlayer.onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mUnityPlayer.onResume();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.mUnityPlayer.onTrimMemory(UnityPlayerForActivityOrService.MemoryUsage.Critical);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (i == 5) {
            this.mUnityPlayer.onTrimMemory(UnityPlayerForActivityOrService.MemoryUsage.Medium);
        } else if (i == 10) {
            this.mUnityPlayer.onTrimMemory(UnityPlayerForActivityOrService.MemoryUsage.High);
        } else {
            if (i != 15) {
                return;
            }
            this.mUnityPlayer.onTrimMemory(UnityPlayerForActivityOrService.MemoryUsage.Critical);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mUnityPlayer.configurationChanged(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.mUnityPlayer.windowFocusChanged(z);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 2) {
            return this.mUnityPlayer.injectEvent(keyEvent);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // com.unity3d.player.IUnityPermissionRequestSupport
    public void requestPermissions(PermissionRequest permissionRequest) {
        this.mUnityPlayer.addPermissionRequest(permissionRequest);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        this.mUnityPlayer.permissionResponse(this, i, strArr, iArr);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.mUnityPlayer.getFrameLayout().onKeyUp(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.mUnityPlayer.getFrameLayout().onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mUnityPlayer.getFrameLayout().onTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return this.mUnityPlayer.getFrameLayout().onGenericMotionEvent(motionEvent);
    }
}
