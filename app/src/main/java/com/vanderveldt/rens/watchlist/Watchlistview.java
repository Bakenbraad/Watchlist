package com.vanderveldt.rens.watchlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.apache.commons.lang3.ArrayUtils.toArray;

public class Watchlistview extends AppCompatActivity {

    public static final String MyPREFERENCES = "myprefs";
    SharedPreferences prefs;
    Context context;
    Map<String, ?> titles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlistview);
        context = getApplicationContext();

        // Get listview. (WOKRS)
        final ListView lv = (ListView) findViewById(R.id.listView);

        // Get content for listview in titles form. (WOKRS)
        //Map<String, String> titlestrings = new HashMap<String, String>();

        //prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //titles = prefs.getAll();

        //for (Map.Entry<String, ?> entry : titles.entrySet()) {
        //    titlestrings.put(entry.getKey().toString(),entry.getValue().toString());
        //}

        // Set the adapter (WOKRS)
        //MyAdapter adapter = new MyAdapter(titlestrings);
        String[] titles = {"Batman", "Superman", "Iron man", "Pirates of the Caribbean", "Harry Potter and the Chamber of secrets"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        lv.setAdapter(adapter);

        // Set listener (WOKRS)
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                // ListView Clicked item value
                String itemValue = (String) lv.getItemAtPosition(position);

                // Request movie page
                itemValue = itemValue.replace(" ", "+");
                new WatchlistAsync(context, itemValue, 1).execute();
            }
        });
    }

    public void onBackPressed(){
        Intent goToStart = new Intent(Watchlistview.this, MainActivity.class);
        startActivity(goToStart);
        finish();
    }

    public String[] getData(){
        Map<String, ?> allEntries = prefs.getAll();
        List<String> titleArray = new ArrayList<String>();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            titleArray.add(prefs.getString(entry.getKey(), "Boop"));
        }
        String[] titleStrings = titleArray.toArray(new String[titleArray.size()]);
        return titleStrings;
    }
}
