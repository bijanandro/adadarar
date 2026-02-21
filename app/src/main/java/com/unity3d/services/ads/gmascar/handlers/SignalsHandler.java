package com.unity3d.services.ads.gmascar.handlers;

import com.unity3d.scar.adapter.common.GMAEvent;
import com.unity3d.scar.adapter.common.signals.ISignalCollectionListener;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;

/* JADX INFO: loaded from: classes.dex */
public class SignalsHandler implements ISignalCollectionListener {
    @Override // com.unity3d.scar.adapter.common.signals.ISignalCollectionListener
    public void onSignalsCollected(String str) {
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.SIGNALS, str);
    }

    @Override // com.unity3d.scar.adapter.common.signals.ISignalCollectionListener
    public void onSignalsCollectionFailed(String str) {
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.SIGNALS_ERROR, str);
    }
}
