package com.qunar.im.common;

import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AutomaticGainControl;
import android.media.audiofx.NoiseSuppressor;
import android.os.Build.VERSION;

public class NoiseSuppressorHelper {
    public static void setNoiseSuppressor(int audioSession) {
        if (VERSION.SDK_INT >= 16 && NoiseSuppressor.isAvailable()) {
            NoiseSuppressor noiseSuppressor = NoiseSuppressor.create(audioSession);
            if (noiseSuppressor != null) {
                noiseSuppressor.setEnabled(true);
            }
        }
    }

    public static void setAcousticEchoCanceler(int audioSession) {
        if (VERSION.SDK_INT >= 16 && AcousticEchoCanceler.isAvailable()) {
            AcousticEchoCanceler acousticEchoCanceler = AcousticEchoCanceler.create(audioSession);
            if (acousticEchoCanceler != null) {
                acousticEchoCanceler.setEnabled(true);
            }
        }
    }

    public static void setAutomaticGainControl(int audioSession) {
        if (VERSION.SDK_INT >= 16 && AutomaticGainControl.isAvailable()) {
            AutomaticGainControl automaticGainControl = AutomaticGainControl.create(audioSession);
            if (automaticGainControl != null) {
                automaticGainControl.setEnabled(true);
            }
        }
    }
}
