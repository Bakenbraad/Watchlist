package com.vanderveldt.rens.watchlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.R.id.edit;
import static android.R.id.list;

public class Watchlistdetail extends AppCompatActivity {

    public static final String MyPREFERENCES = "myprefs";
    SharedPreferences prefs;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlistdetail);
        Bundle extras = getIntent().getExtras();

        prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        // Get all json response variables
        String title = extras.getString("title");
        String year = extras.getString("year");
        String runtime = extras.getString("runtime");
        String link = extras.getString("link");
        String plot = extras.getString("plot");
        String director = extras.getString("director");
        String actors = extras.getString("actors");

        // Cast all variables to view
        TextView titleTV = (TextView) findViewById(R.id.title2);
        titleTV.setText(title);

        TextView yearTV = (TextView) findViewById(R.id.year2);
        yearTV.setText("Released: " + year);

        TextView directorTV = (TextView) findViewById(R.id.director2);
        directorTV.setText("Directed by: " + director);

        TextView runtimeTV = (TextView) findViewById(R.id.runtime2);
        runtimeTV.setText(runtime);

        TextView plotTV = (TextView) findViewById(R.id.plot2);
        plotTV.setMovementMethod(new ScrollingMovementMethod());
        plotTV.setText(plot);

        TextView actorsTV = (TextView) findViewById(R.id.actors2);
        actorsTV.setText("Starring: " + actors);

        final ImageView iv = (ImageView) findViewById(R.id.poster2);
        new DownLoadImageTask(iv).execute(link);
    }
    public void removeFromWatchlist(View view) {

        // Get current list
        SharedPreferences.Editor editor = prefs.edit();

        // Remove current title
        editor.remove(title);
        editor.commit();

        // Let user know you added their movie
        Toast.makeText(this, "Removed", Toast.LENGTH_LONG).show();
    }

    // Back to watchlist
    public void backToWatchlist(View view) {

        Intent goToWatchlist = new Intent(Watchlistdetail.this, Watchlistview.class);
        startActivity(goToWatchlist);
        finish();
    }
    public void onBackPressed(){
        Intent goToWatchlist = new Intent(Watchlistdetail.this, Watchlistview.class);
        startActivity(goToWatchlist);
        finish();
    }

    // Gets image into view
    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap>{
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }

}
