package com.vanderveldt.rens.watchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Noresults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noresults);
    }

    // Back to search
    public void backToSearch(View view) {
        Intent goToStart = new Intent(Noresults.this, MainActivity.class);
        startActivity(goToStart);
        finish();
    }
    public void onBackPressed(){
        Intent goToStart = new Intent(Noresults.this, MainActivity.class);
        startActivity(goToStart);
        finish();
    }
}
