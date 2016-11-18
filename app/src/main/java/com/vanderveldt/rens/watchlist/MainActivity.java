package com.vanderveldt.rens.watchlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.vanderveldt.rens.watchlist.R.id.querytext;

public class MainActivity extends AppCompatActivity {

    String query;
    EditText searchquery;
    public static final String MyPREFERENCES = "myprefs";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // Asynctask arguments.
        searchquery = (EditText) findViewById(R.id.querytext);
    }

    public void movieSearch(View view) {
        query = searchquery.getText().toString();
        if (query.length() == 0){
            Toast.makeText(this, "Please enter a title.", Toast.LENGTH_LONG).show();
        }
        else {
            new WatchlistAsync(this, query, 1).execute();
            finish();
        }
    }

    public void goToWatchlist(View view) {
        Intent goToWatchlist = new Intent(this, Watchlistview.class);
        startActivity(goToWatchlist);
    }
}
