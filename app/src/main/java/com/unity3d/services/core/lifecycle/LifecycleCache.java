package com.unity3d.services.core.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class LifecycleCache implements Application.ActivityLifecycleCallbacks {
    private LifecycleEvent _currentState = LifecycleEvent.RESUMED;
    private boolean _appActive = true;
    private Set<IAppActiveListener> _appActiveListeners = new HashSet();

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        this._currentState = LifecycleEvent.CREATED;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        this._currentState = LifecycleEvent.STARTED;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this._currentState = LifecycleEvent.RESUMED;
        this._appActive = true;
        notifyListeners();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        this._currentState = LifecycleEvent.PAUSED;
        this._appActive = false;
        notifyListeners();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        this._currentState = LifecycleEvent.STOPPED;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this._currentState = LifecycleEvent.SAVE_INSTANCE_STATE;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        this._currentState = LifecycleEvent.DESTROYED;
    }

    public LifecycleEvent getCurrentState() {
        return this._currentState;
    }

    public boolean isAppActive() {
        return this._appActive;
    }

    public synchronized void notifyListeners() {
        LifecycleEvent lifecycleEvent = this._appActive ? LifecycleEvent.RESUMED : LifecycleEvent.PAUSED;
        Iterator<IAppActiveListener> it = this._appActiveListeners.iterator();
        while (it.hasNext()) {
            it.next().onAppStateChanged(lifecycleEvent);
        }
    }

    public synchronized void addListener(IAppActiveListener iAppActiveListener) {
        this._appActiveListeners.add(iAppActiveListener);
    }

    public synchronized void removeListener(IAppActiveListener iAppActiveListener) {
        this._appActiveListeners.remove(iAppActiveListener);
    }
}
