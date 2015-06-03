package com.voxxel.voxxel;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.appcompat.R;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.media.MediaPlayer;
import android.media.AudioManager;
import android.widget.TextView;
import android.widget.Toast;

import com.voxxel.api.AccessTokenModel;
import com.voxxel.api.ArtistModel;
import com.voxxel.api.ArtistService;
import com.voxxel.api.AuthManager;
import com.voxxel.api.ServiceGenerator;
import com.voxxel.api.SoundModel;
import com.voxxel.api.SoundService;
import com.voxxel.visualizer.VisualizerView;
import com.voxxel.voxxel.R;
import com.voxxel.Constants;

// https://developer.android.com/guide/topics/media/audio-capture.html
// https://github.com/kmmbvnr/crydetector/blob/master/CryDetector/src/cc/wthr/crydetector/CryDetector.java

// https://stackoverflow.com/questions/29173651/visualize-while-recording-using-audiorecord-and-visualizer-in-android-studio
// https://stackoverflow.com/questions/20968119/i-cant-see-visualizer-while-recording-voice-on-android
// https://github.com/steelkiwi/AndroidRecording
// https://gist.github.com/luisdelarosa/9107008
// https://stackoverflow.com/questions/8499042/android-audiorecord-example
// https://stackoverflow.com/questions/15955958/android-audiorecord-to-server-over-udp-playback-issues
// https://stackoverflow.com/questions/26430647/how-to-enable-audio-recording-in-android-emulator

public class RecordActivity extends Activity {
    private MediaPlayer mPlayer;
    private MediaRecorder mRecorder = null;
    private AudioManager aManager;
    private Long artistId;
    private Long soundId;
    private SoundModel sound = new SoundModel();
//    private AuthManager authManager = AuthManager.getInstance();
//    private AccessTokenModel accessToken = authManager.retrieveToken();
    private SoundService soundService;
    private VisualizerView mVisualizerView;

    private RequestSoundTask soundTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Intent intent = getIntent();
        artistId = intent.getLongExtra("artistId", -1);
        soundId = intent.getLongExtra("soundId", -1);

        mPlayer = MediaPlayer.create(this, R.raw.get_to_the_choppa);
        linkRecorder(mRecorder);

        mVisualizerView = (VisualizerView) findViewById(R.id.visualizerView);
        mVisualizerView.link(mPlayer);

        mPlayer.setLooping(true);
        mPlayer.start();

//        if (!accessToken.isValid()) {
//            Intent loginIntent = new Intent(this, LoginActivity.class);
//            startActivity(loginIntent);
//        } else {
//            soundService = ServiceGenerator.createService(SoundService.class, Constants.BASE_URL, accessToken);
//            fetchSound();
//        }
    }

    private void linkRecorder(MediaRecorder recorder) {
        unlinkRecorder(recorder);
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(Constants.RECORDER_AUDIO_OUTPUT);
        recorder.setAudioEncoder(Constants.RECORDER_AUDIO_ENCODING);
        recorder.setAudioChannels(1);
        recorder.setAudioSamplingRate(Constants.RECORDER_AUDIO_SAMPLE_RATE);
        recorder.setOutputFile("/dev/null");

        try {
            recorder.prepare();
            recorder.start();
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

    private void unlinkRecorder(MediaRecorder recorder) {
        //TODO: when to call this method?
        if (recorder != null) {
            recorder.stop();
            recorder.release();
        }
    }

    private void fetchSound() {
        //TODO: remove this if API request is not a result of user interaction?
        if (soundTask != null) {
            return;
        }

        soundTask = new RequestSoundTask(this.soundId);
        soundTask.execute((Void) null);
    }

    private void cleanUp() {
        mVisualizerView.release();
        mPlayer.release();
        mRecorder.release();
//        mRecorder = null;
//        mPlayer = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // re-init media player
    }

    @Override
    protected void onPause() {
        cleanUp();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        cleanUp();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void playPressed(View view) {

    }

    public void recordPressed(View view) {

    }

    public void scorePressed(View view) {

    }

    public void sharePressed(View view) {

    }

    private void updateSoundView(SoundModel sound) {
        TextView textName = (TextView) findViewById(R.id.name);
        textName.setText(sound.getName());
    }

    public class RequestSoundTask extends AsyncTask<Void, Void, Boolean> {
        private Long soundId;

        RequestSoundTask(final Long soundId) {
            this.soundId = soundId;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                sound = soundService.getSound(soundId);
            } catch (Exception e) {
                sound = new SoundModel();
                Log.e("API Auth", e.toString());
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                updateSoundView(sound);
            } else {
                Toast.makeText(getApplicationContext(), "Error loading artist", Toast.LENGTH_LONG).show();
            }
            soundTask = null;
        }

        @Override
        protected void onCancelled() {
            soundTask = null;
        }
    }
}
