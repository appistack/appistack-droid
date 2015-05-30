package com.voxxel.voxxel;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.voxxel.Constants;
import com.voxxel.api.AccessTokenModel;
import com.voxxel.api.ArtistModel;
import com.voxxel.api.ArtistService;
import com.voxxel.api.AuthManager;
import com.voxxel.api.ServiceGenerator;

import com.voxxel.voxxel.R;

import java.util.Collections;
import java.util.List;

public class ArtistListActivity extends ListActivity {
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

    private List<ArtistModel> artists;
    private ArtistService artistService;
    private AuthManager authManager = AuthManager.getInstance();
    private AccessTokenModel accessToken = authManager.retrieveToken();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_list);

        if (!accessToken.isValid()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            artistService = ServiceGenerator.createService(ArtistService.class, Constants.BASE_URL, accessToken);
            artists = artistService.getArtists();
            setListAdapter(new ArrayAdapter<ArtistModel>(this, R.layout.list_artist, artists));
        }

        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView adapter, View v, int position) {
//                ItemClicked item = adapter.getItem(position);
//                Intent intent = new Intent(Activity.this, destinationActivity.class);
//            }
//        });

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

    public class ArtistListAdapter extends BaseAdapter {
        private List<ArtistModel> artists = Collections.emptyList();
        private Context context;

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
            return position;
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
}
