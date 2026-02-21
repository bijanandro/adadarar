package com.unity3d.services.core.cache;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.unity3d.services.core.api.Request;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.request.WebRequest;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class CacheThreadHandler extends Handler {
    private WebRequest _currentRequest = null;
    private boolean _canceled = false;
    private boolean _active = false;

    CacheThreadHandler() {
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) throws Throwable {
        HashMap<String, List<String>> map;
        Bundle data = message.getData();
        String string = data.getString("source");
        data.remove("source");
        String string2 = data.getString("target");
        data.remove("target");
        int i = data.getInt("connectTimeout");
        data.remove("connectTimeout");
        int i2 = data.getInt("readTimeout");
        data.remove("readTimeout");
        int i3 = data.getInt("progressInterval");
        data.remove("progressInterval");
        boolean z = data.getBoolean("append", false);
        data.remove("append");
        if (data.size() > 0) {
            DeviceLog.debug("There are headers left in data, reading them");
            HashMap<String, List<String>> map2 = new HashMap<>();
            for (String str : data.keySet()) {
                map2.put(str, Arrays.asList(data.getStringArray(str)));
            }
            map = map2;
        } else {
            map = null;
        }
        File file = new File(string2);
        if ((z && !file.exists()) || (!z && file.exists())) {
            this._active = false;
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_ERROR, CacheError.FILE_STATE_WRONG, string, string2, Boolean.valueOf(z), Boolean.valueOf(file.exists()));
        } else {
            if (message.what != 1) {
                return;
            }
            downloadFile(string, string2, i, i2, i3, map, z);
        }
    }

    public void setCancelStatus(boolean z) {
        WebRequest webRequest;
        this._canceled = z;
        if (!z || (webRequest = this._currentRequest) == null) {
            return;
        }
        this._active = false;
        webRequest.cancel();
    }

    public boolean isActive() {
        return this._active;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0380 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:155:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10, types: [com.unity3d.services.core.request.WebRequest] */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v62 */
    /* JADX WARN: Type inference failed for: r1v63 */
    /* JADX WARN: Type inference failed for: r1v64 */
    /* JADX WARN: Type inference failed for: r1v65 */
    /* JADX WARN: Type inference failed for: r1v66 */
    /* JADX WARN: Type inference failed for: r1v67 */
    /* JADX WARN: Type inference failed for: r1v68 */
    /* JADX WARN: Type inference failed for: r1v69 */
    /* JADX WARN: Type inference failed for: r1v70 */
    /* JADX WARN: Type inference failed for: r1v71 */
    /* JADX WARN: Type inference failed for: r1v72 */
    /* JADX WARN: Type inference failed for: r1v73 */
    /* JADX WARN: Type inference failed for: r1v74 */
    /* JADX WARN: Type inference failed for: r1v75 */
    /* JADX WARN: Type inference failed for: r1v76 */
    /* JADX WARN: Type inference failed for: r1v77 */
    /* JADX WARN: Type inference failed for: r1v78 */
    /* JADX WARN: Type inference failed for: r1v79 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v85 */
    /* JADX WARN: Type inference failed for: r1v86 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r3v16, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11, types: [int] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v54 */
    /* JADX WARN: Type inference failed for: r4v55 */
    /* JADX WARN: Type inference failed for: r4v57 */
    /* JADX WARN: Type inference failed for: r4v58 */
    /* JADX WARN: Type inference failed for: r4v59 */
    /* JADX WARN: Type inference failed for: r4v60 */
    /* JADX WARN: Type inference failed for: r4v61 */
    /* JADX WARN: Type inference failed for: r4v62 */
    /* JADX WARN: Type inference failed for: r4v63 */
    /* JADX WARN: Type inference failed for: r4v64 */
    /* JADX WARN: Type inference failed for: r4v65 */
    /* JADX WARN: Type inference failed for: r4v66 */
    /* JADX WARN: Type inference failed for: r4v67 */
    /* JADX WARN: Type inference failed for: r4v68 */
    /* JADX WARN: Type inference failed for: r4v69 */
    /* JADX WARN: Type inference failed for: r4v70 */
    /* JADX WARN: Type inference failed for: r4v71 */
    /* JADX WARN: Type inference failed for: r4v72 */
    /* JADX WARN: Type inference failed for: r4v73 */
    /* JADX WARN: Type inference failed for: r4v74 */
    /* JADX WARN: Type inference failed for: r4v80 */
    /* JADX WARN: Type inference failed for: r4v81 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v12, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v13, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v15, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v42, types: [long] */
    /* JADX WARN: Type inference failed for: r6v43 */
    /* JADX WARN: Type inference failed for: r6v44 */
    /* JADX WARN: Type inference failed for: r6v45 */
    /* JADX WARN: Type inference failed for: r6v46 */
    /* JADX WARN: Type inference failed for: r6v47 */
    /* JADX WARN: Type inference failed for: r6v48 */
    /* JADX WARN: Type inference failed for: r6v49 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v50 */
    /* JADX WARN: Type inference failed for: r6v51 */
    /* JADX WARN: Type inference failed for: r6v52 */
    /* JADX WARN: Type inference failed for: r6v53 */
    /* JADX WARN: Type inference failed for: r6v54 */
    /* JADX WARN: Type inference failed for: r6v55 */
    /* JADX WARN: Type inference failed for: r6v56 */
    /* JADX WARN: Type inference failed for: r6v57 */
    /* JADX WARN: Type inference failed for: r6v58 */
    /* JADX WARN: Type inference failed for: r6v59 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v60 */
    /* JADX WARN: Type inference failed for: r6v61 */
    /* JADX WARN: Type inference failed for: r6v62 */
    /* JADX WARN: Type inference failed for: r6v63 */
    /* JADX WARN: Type inference failed for: r6v64 */
    /* JADX WARN: Type inference failed for: r6v65 */
    /* JADX WARN: Type inference failed for: r6v66 */
    /* JADX WARN: Type inference failed for: r6v67 */
    /* JADX WARN: Type inference failed for: r6v68 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void downloadFile(java.lang.String r24, java.lang.String r25, int r26, int r27, final int r28, java.util.HashMap<java.lang.String, java.util.List<java.lang.String>> r29, boolean r30) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 932
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.services.core.cache.CacheThreadHandler.downloadFile(java.lang.String, java.lang.String, int, int, int, java.util.HashMap, boolean):void");
    }

    private void postProcessDownload(long j, String str, File file, long j2, long j3, boolean z, int i, Map<String, List<String>> map) {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - j;
        if (!file.setReadable(true, false)) {
            DeviceLog.debug("Unity Ads cache: could not set file readable!");
        }
        if (!z) {
            DeviceLog.debug("Unity Ads cache: File " + file.getName() + " of " + j2 + " bytes downloaded in " + jElapsedRealtime + "ms");
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_END, str, Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(jElapsedRealtime), Integer.valueOf(i), Request.getResponseHeadersMap(map));
            return;
        }
        DeviceLog.debug("Unity Ads cache: downloading of " + str + " stopped");
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.CACHE, CacheEvent.DOWNLOAD_STOPPED, str, Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(jElapsedRealtime), Integer.valueOf(i), Request.getResponseHeadersMap(map));
    }

    private WebRequest getWebRequest(String str, int i, int i2, HashMap<String, List<String>> map) throws MalformedURLException {
        HashMap map2 = new HashMap();
        if (map != null) {
            map2.putAll(map);
        }
        return new WebRequest(str, "GET", map2, i, i2);
    }
}
