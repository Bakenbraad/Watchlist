package com.vanderveldt.rens.watchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Resultlistview extends AppCompatActivity {

    ArrayList 
    ArrayList
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultlistview);
    }

    public void backToSearch(View view) {
        finish();
        Intent goToMain = new Intent(this, MainActivity.class);
        startActivity(goToMain);
    }
}
