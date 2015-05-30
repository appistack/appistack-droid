package com.voxxel.voxxel;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.voxxel.Constants;
import com.voxxel.api.AccessTokenModel;
import com.voxxel.api.AuthManager;
import com.voxxel.api.SoundModel;
import com.voxxel.api.SoundService;
import com.voxxel.api.ArtistModel;
import com.voxxel.api.ArtistService;
import com.voxxel.api.ServiceGenerator;

import com.voxxel.voxxel.R;

import java.util.Collections;
import java.util.List;

public class ArtistDetailActivity extends AppCompatActivity {
    private ArtistService artistService;
    private Long artistId;
    private ArtistModel artist = new ArtistModel();
    private AuthManager authManager = AuthManager.getInstance();
    private AccessTokenModel accessToken = authManager.retrieveToken();
    private RequestArtistTask artistTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        artistId = intent.getLongExtra("artistId", -1);
        setContentView(R.layout.activity_artist_detail);

        if (!accessToken.isValid()) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        } else {
            artistService = ServiceGenerator.createService(ArtistService.class, Constants.BASE_URL, accessToken);
            fetchArtist();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_artist_detail, menu);
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

    private void fetchArtist() {
        //TODO: remove this if API request is not a result of user interaction?
        if (artistTask != null) {
            return;
        }

        artistTask = new RequestArtistTask(this.artistId);
        artistTask.execute((Void) null);
    }

    private void updateArtistView(ArtistModel artist) {
        TextView textName = (TextView) findViewById(R.id.name);
        TextView textDescription = (TextView) findViewById(R.id.description);

        textName.setText(artist.getName());
        textDescription.setText(artist.getDescription());
    }

    public class RequestArtistTask extends AsyncTask<Void, Void, Boolean> {
        private Long artistId;

        RequestArtistTask(final Long artistId) {
            this.artistId = artistId;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                artist = artistService.getArtist(artistId);
            } catch (Exception e) {
                artist = new ArtistModel();
                Log.e("API Auth", e.toString());
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                updateArtistView(artist);
            } else {
                Toast.makeText(getApplicationContext(), "Error loading artist", Toast.LENGTH_LONG).show();
//                artistListAdapter.setArtists(artists);
            }
            artistTask = null;
        }

        @Override
        protected void onCancelled() {
            artistTask = null;
        }

    }

    public class ArtistSoundListAdapter extends BaseAdapter {
        private List<SoundModel> sounds = Collections.emptyList();

        public void setSounds(List<SoundModel> sounds) {
            this.sounds = sounds;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return sounds.size();
        }

        @Override
        public Object getItem(int position) {
            return sounds.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout view = (convertView != null)
                    ? (LinearLayout) convertView
                    : (LinearLayout) getLayoutInflater().inflate(R.layout.list_artist_sounds, parent, false);

            TextView textName = (TextView) view.findViewById(R.id.name);
            textName.setText(sounds.get(position).getName());

            TextView textDescription = (TextView) view.findViewById(R.id.description);
            textDescription.setText(sounds.get(position).getDescription());

            return view;
        }
    }
}
