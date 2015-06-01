package com.voxxel.voxxel;

import android.app.Activity;
import android.content.Intent;
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
import com.voxxel.voxxel.R;
import com.voxxel.Constants;

public class RecordActivity extends Activity {
    private MediaPlayer mPlayer;
    private AudioManager aManager;
    private Long artistId;
    private Long soundId;
    private SoundModel sound = new SoundModel();
    private AuthManager authManager = AuthManager.getInstance();
    private AccessTokenModel accessToken = authManager.retrieveToken();
    private SoundService soundService;

    private RequestSoundTask soundTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Intent intent = getIntent();
        artistId = intent.getLongExtra("artistId", -1);
        soundId = intent.getLongExtra("soundId", -1);

        if (!accessToken.isValid()) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        } else {
            soundService = ServiceGenerator.createService(SoundService.class, Constants.BASE_URL, accessToken);
            fetchSound();
        }

//        mPlayer = MediaPlayer.create(this, R.raw.sounds.arnold.get_to_the_choppa);
//        mPlayer = initMediaPlayer();
//        aManager = initAudioManager();
    }

    private void fetchSound() {
        //TODO: remove this if API request is not a result of user interaction?
        if (soundTask != null) {
            return;
        }

        soundTask = new RequestSoundTask(this.soundId);
        soundTask.execute((Void) null);
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
