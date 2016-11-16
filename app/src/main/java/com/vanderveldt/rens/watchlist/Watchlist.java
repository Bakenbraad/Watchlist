package com.vanderveldt.rens.watchlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Watchlist extends AppCompatActivity {

    public static final String MyPREFERENCES = "myprefs";
    SharedPreferences prefs;
    String[] watchlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        watchlist = prefs.get
        setContentView(R.layout.activity_watchlist);
    }
}
