package com.unity3d.services.core.device;

import com.unity3d.services.core.device.StorageManager;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.JsonStorage;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import java.io.File;
import java.io.FileNotFoundException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Storage extends JsonStorage {
    private String _targetFileName;
    private StorageManager.StorageType _type;

    public Storage(String str, StorageManager.StorageType storageType) {
        this._targetFileName = str;
        this._type = storageType;
    }

    public StorageManager.StorageType getType() {
        return this._type;
    }

    public synchronized boolean readStorage() {
        try {
            try {
                byte[] fileBytes = Utilities.readFileBytes(new File(this._targetFileName));
                if (fileBytes == null) {
                    return false;
                }
                setData(new JSONObject(new String(fileBytes)));
                return true;
            } catch (Exception e) {
                DeviceLog.debug("Failed to read storage JSON file:", e);
                return false;
            }
        } catch (FileNotFoundException e2) {
            DeviceLog.debug("Storage JSON file not found in local cache:", e2);
            return false;
        }
    }

    public synchronized boolean initStorage() {
        readStorage();
        super.initData();
        return true;
    }

    public synchronized boolean writeStorage() {
        File file = new File(this._targetFileName);
        if (getData() == null) {
            return false;
        }
        return Utilities.writeFile(file, getData().toString());
    }

    public synchronized boolean clearStorage() {
        clearData();
        return new File(this._targetFileName).delete();
    }

    public synchronized boolean storageFileExists() {
        return new File(this._targetFileName).exists();
    }

    public synchronized void sendEvent(StorageEvent storageEvent, Object obj) {
        if (!(WebViewApp.getCurrentApp() != null ? WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORAGE, storageEvent, this._type.name(), obj) : false)) {
            DeviceLog.debug("Couldn't send storage event to WebApp");
        }
    }
}
