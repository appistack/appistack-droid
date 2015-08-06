package com.voxxel;

import android.media.MediaRecorder;

public class Constants {
    public final static String BASE_URL = "https://voxxel.herokuapp.com";
    public final static String ASSETS_URL = "http://vox.xel.io";
    public final static String SIGNUP_CONFIRM_SUCCESS_URL = BASE_URL;
    public final static String PASSWORD_RESET_SUCCESS_URL = BASE_URL + "/password/change";

    public final static int RECORDER_AUDIO_OUTPUT = MediaRecorder.AudioEncoder.DEFAULT;
    public final static int RECORDER_AUDIO_ENCODING = MediaRecorder.AudioEncoder.DEFAULT;
    public final static int RECORDER_AUDIO_SAMPLE_RATE = 8000;
    public final static int RECORDER_AUDIO_MONO = 1;

}
