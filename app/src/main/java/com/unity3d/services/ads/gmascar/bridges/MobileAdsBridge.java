package com.unity3d.services.ads.gmascar.bridges;

import android.content.Context;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.reflection.GenericBridge;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MobileAdsBridge extends GenericBridge {
    private static final String initializationStatusMethodName = "getInitializationStatus";
    private static final String initializeMethodName = "initialize";
    private static final String versionStringMethodName = "getVersionString";

    public MobileAdsBridge() {
        super(new HashMap<String, Class<?>[]>() { // from class: com.unity3d.services.ads.gmascar.bridges.MobileAdsBridge.1
            {
                try {
                    put(MobileAdsBridge.initializeMethodName, new Class[]{Context.class, Class.forName("com.google.android.gms.ads.initialization.OnInitializationCompleteListener")});
                } catch (ClassNotFoundException e) {
                    DeviceLog.debug("Could not find class \"com.google.android.gms.ads.initialization.OnInitializationCompleteListener\" %s", e.getLocalizedMessage());
                }
                put(MobileAdsBridge.initializationStatusMethodName, new Class[0]);
                put(MobileAdsBridge.versionStringMethodName, new Class[0]);
            }
        });
    }

    @Override // com.unity3d.services.core.reflection.GenericBridge
    public String getClassName() {
        return "com.google.android.gms.ads.MobileAds";
    }

    public void initialize(Context context, Object obj) {
        callVoidMethod(initializeMethodName, null, context, obj);
    }

    public String getVersionString() {
        Object objCallNonVoidMethod = callNonVoidMethod(versionStringMethodName, null, new Object[0]);
        if (objCallNonVoidMethod == null) {
            return "0.0.0";
        }
        return objCallNonVoidMethod.toString();
    }

    public Object getInitializationStatus() {
        return callNonVoidMethod(initializationStatusMethodName, null, new Object[0]);
    }
}
