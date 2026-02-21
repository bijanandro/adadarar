package org.fmod;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;

/* JADX INFO: loaded from: classes3.dex */
public class FmodAndroidAudioManager {
    private static FmodAndroidAudioManager f;
    private volatile Activity a = null;
    private volatile AudioManager b = null;
    private BroadcastReceiver c = null;
    private boolean d = false;
    private boolean e = false;

    private FmodAndroidAudioManager() {
    }

    public static FmodAndroidAudioManager getInstance() {
        if (f == null) {
            f = new FmodAndroidAudioManager();
        }
        return f;
    }

    public int getBluetoothInputDeviceId() {
        for (AudioDeviceInfo audioDeviceInfo : this.b.getDevices(1)) {
            if (audioDeviceInfo.getType() == 7) {
                return audioDeviceInfo.getId();
            }
        }
        return -1;
    }

    public boolean isBluetoothInputDeviceAvailable() {
        for (AudioDeviceInfo audioDeviceInfo : this.b.getDevices(1)) {
            if (audioDeviceInfo.getType() == 7) {
                return true;
            }
        }
        return false;
    }

    public boolean isBluetoothScoConnected() {
        return this.e;
    }

    public boolean isBuiltinInputDeviceAvailable() {
        for (AudioDeviceInfo audioDeviceInfo : this.b.getDevices(1)) {
            if (audioDeviceInfo.getType() == 15) {
                return true;
            }
        }
        return false;
    }

    public boolean isBuiltinSpeakerDevice(int i) {
        for (AudioDeviceInfo audioDeviceInfo : this.b.getDevices(2)) {
            if (audioDeviceInfo.getId() == i && audioDeviceInfo.getType() == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isInputSampleRateAvailable(int i) {
        for (AudioDeviceInfo audioDeviceInfo : this.b.getDevices(1)) {
            if ((this.d && audioDeviceInfo.getType() == 7) || (!this.d && audioDeviceInfo.getType() == 15)) {
                for (int i2 : audioDeviceInfo.getSampleRates()) {
                    if (i2 == i) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setActivity(Activity activity) {
        if (this.a != activity) {
            if (this.c != null) {
                this.a.unregisterReceiver(this.c);
                this.c = null;
                this.e = false;
            }
            this.a = activity;
            this.b = this.a != null ? (AudioManager) this.a.getSystemService("audio") : null;
        }
    }

    public void setUseBluetooth(boolean z) {
        this.d = z;
    }

    public void startBluetoothSco() {
        if (this.c == null) {
            this.c = new BroadcastReceiver() { // from class: org.fmod.FmodAndroidAudioManager.1
                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context, Intent intent) {
                    FmodAndroidAudioManager fmodAndroidAudioManager;
                    boolean z;
                    int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
                    if (intExtra != 0) {
                        z = true;
                        if (intExtra != 1) {
                            return;
                        } else {
                            fmodAndroidAudioManager = FmodAndroidAudioManager.this;
                        }
                    } else {
                        fmodAndroidAudioManager = FmodAndroidAudioManager.this;
                        z = false;
                    }
                    fmodAndroidAudioManager.e = z;
                }
            };
            this.a.registerReceiver(this.c, new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED"));
        }
        this.b.startBluetoothSco();
    }

    public void stopBluetoothSco() {
        this.b.stopBluetoothSco();
    }
}
