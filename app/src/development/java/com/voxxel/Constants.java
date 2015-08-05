package com.voxxel;

import android.media.AudioFormat;
import android.media.MediaRecorder;

public class Constants {
    public final static String BASE_URL = "http://192.168.1.80:3000";
    public final static String ASSETS_URL = "http://vox.xel.io";
    public final static String SIGNUP_CONFIRM_SUCCESS_URL = "http://vox.xel.io";

    public final static int RECORDER_AUDIO_OUTPUT = MediaRecorder.AudioEncoder.DEFAULT; //AMR_NB //AAC
    public final static int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    //    public final static int RECORDER_AUDIO_ENCODING = MediaRecorder.AudioEncoder.DEFAULT;
    public final static int RECORDER_AUDIO_SAMPLE_RATE = 8000;
    public final static int RECORDER_AUDIO_MONO = 1;
}
