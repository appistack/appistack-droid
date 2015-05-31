package com.voxxel.voxxel;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.appcompat.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.media.MediaPlayer;
import android.media.AudioManager;

import com.voxxel.voxxel.R;
import com.voxxel.Constants;

public class RecordActivity extends Activity {
    private MediaPlayer mPlayer;
    private AudioManager aManager;
    private Long artistId;
    private Long soundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Intent intent = getIntent();
        artistId = intent.getLongExtra("artistId", -1);
        soundId = intent.getLongExtra("soundId", -1);

//        mPlayer = MediaPlayer.create(this, R.raw.sounds.arnold.get_to_the_choppa);
//        mPlayer = initMediaPlayer();
//        aManager = initAudioManager();
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
}
