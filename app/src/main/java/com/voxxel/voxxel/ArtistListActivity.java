package com.voxxel.voxxel;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.voxxel.Constants;
import com.voxxel.api.AccessTokenModel;
import com.voxxel.api.ArtistModel;
import com.voxxel.api.ArtistService;
import com.voxxel.api.AuthManager;
import com.voxxel.api.ServiceGenerator;

import com.voxxel.api.SignupModel;
import com.voxxel.voxxel.R;

import java.util.Collections;
import java.util.List;

public class ArtistListActivity extends Activity {
    static final String[] ARTISTS = new String[]{
            "Dave Chappelle",
            "Dana Carvey",
            "George Bush",
            "Barack Obama",
            "Darrell Hammond",
            "Mike Myers",
            "Will Ferrell",
            "Arnold Schwarzeneggar",
            "David Conner"
    };

    private List<ArtistModel> artists = Collections.emptyList();
    private ArtistService artistService;
    private AuthManager authManager = AuthManager.getInstance();
    private AccessTokenModel accessToken = authManager.retrieveToken();
    private RequestArtistListTask artistListTask = null;
    private ArtistListAdapter artistListAdapter = new ArtistListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Activity thisActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_list);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(artistListAdapter);

        if (!accessToken.isValid()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            artistService = ServiceGenerator.createService(ArtistService.class, Constants.BASE_URL, accessToken);
            fetchArtists();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long artistId) {
                Intent artistIntent = new Intent(thisActivity, ArtistDetailActivity.class);
                artistIntent.putExtra("artistId", artistId);
                startActivity(artistIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_artist_list, menu);
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

    private void fetchArtists() {
        //TODO: remove this if API request is not a result of user interaction?
        if (artistListTask != null) {
            return;
        }

        artistListTask = new RequestArtistListTask();
        artistListTask.execute((Void) null);
    }

    public class ArtistListAdapter extends BaseAdapter {
        private List<ArtistModel> artists = Collections.emptyList();

        public void setArtists(List<ArtistModel> artists) {
            this.artists = artists;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return artists.size();
        }

        @Override
        public Object getItem(int position) {
            return artists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return artists.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout view = (convertView != null)
                    ? (LinearLayout) convertView
                    : (LinearLayout) getLayoutInflater().inflate(R.layout.list_artist, parent, false);

            TextView textName = (TextView) view.findViewById(R.id.name);
            textName.setText(artists.get(position).getName());

            TextView textDescription = (TextView) view.findViewById(R.id.description);
            textDescription.setText(artists.get(position).getDescription());

            return view;
        }
    }

    public class RequestArtistListTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                artists = artistService.getArtists();
            } catch (Exception e) {
                artists = Collections.emptyList();
                Log.e("API Auth", e.toString());
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                artistListAdapter.setArtists(artists);
            } else {
                Toast.makeText(getApplicationContext(), "Error loading artists", Toast.LENGTH_LONG).show();
                artistListAdapter.setArtists(artists);
            }
            artistListTask = null;
        }

        @Override
        protected void onCancelled() {
            artistListTask = null;
        }
    }
}
