package com.voxxel.voxxel;

import android.app.ListActivity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.internal.widget.ActionBarOverlayLayout;
//import android.support.v7.appcompat.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.voxxel.voxxel.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_artist, ARTISTS));

//        setContentView(R.layout.activity_artist_list);

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
}
