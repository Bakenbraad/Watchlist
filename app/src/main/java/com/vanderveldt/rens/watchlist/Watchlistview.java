package com.vanderveldt.rens.watchlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Watchlistview extends AppCompatActivity {

    public static final String MyPREFERENCES = "myprefs";
    SharedPreferences prefs;
    Set<String> titles;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlistview);
        context = getApplicationContext();

        // Get listview.
        final ListView lv = (ListView) findViewById(R.id.listView);

        // Get content for listview in titles form.
        prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Set<String> titles = prefs.getStringSet("myStrings", new HashSet<String>());
        // Convert to strings
        String[] titleStrings;
        //if(titles.isEmpty() == false){
            //titleStrings = titles.toArray(new String[titles.size()]);
        //}
        titleStrings = new String[]{"Batman", "Superman", "Spiderman"};

        // Set the adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, titleStrings);
        lv.setAdapter(adapter);

        // Set listener
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) lv.getItemAtPosition(position);

                // Request movie page
                new WatchlistAsync(context, itemValue, 0).execute();
                finish();

            }
        });
    }
}
