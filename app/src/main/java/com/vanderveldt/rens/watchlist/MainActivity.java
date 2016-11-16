package com.vanderveldt.rens.watchlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.vanderveldt.rens.watchlist.R.id.querytext;

public class MainActivity extends AppCompatActivity {
    String query;
    EditText searchquery;
    public static final String MyPREFERENCES = "myprefs";
    SharedPreferences sharedpreferences;
    ArrayList<HashMap<String, String>> watchList;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    public void movieSearch(View view) {
        query = searchquery.getText().toString();
        lv = (ListView) findViewById(R.id.listview);
        WatchlistAsync watchlist = new WatchlistAsync(this, query, lv);
    }
}
