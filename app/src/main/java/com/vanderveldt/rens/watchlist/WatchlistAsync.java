package com.vanderveldt.rens.watchlist;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rens on 15-11-2016.
 */

class WatchlistAsync extends AsyncTask<Object, Object, Void> {

    // Name tag.
    private String TAG = MainActivity.class.getSimpleName();
    // Initiate list of search results:
    private ArrayList<HashMap<String, String>> searchList;
    // Extract listview.
    private ListView listView;
    // Create query string
    private String query;
    // Create context;
    private static Context context;
    // Store method:
    private static int methodcall;

    String title, year, runtime, director, actors, plot, link;

    WatchlistAsync(Context c, String searchquery, int method) {
        query = searchquery;
        context = c;
        methodcall = method;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Searching", Toast.LENGTH_SHORT).show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Object... arg0) {
        try {
            JSONObject searchResult = ApiHandler.readJsonFromUrl(query);
            title = searchResult.getString("Title");
            year = searchResult.getString("Year");
            runtime = searchResult.getString("Runtime");
            director = searchResult.getString("Director");
            actors = searchResult.getString("Actors");
            plot = searchResult.getString("Plot");
            link = searchResult.getString("Poster");

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void values) {
        super.onPostExecute(values);
        Intent goToResult;

        switch (methodcall) {
            case 1:
                goToResult = new Intent(context, Resultlistview.class);
                break;
            case 0:
                goToResult = new Intent(context, Watchlistdetail.class);
                break;
            default:
                return;
        }

        goToResult.putExtra("title", title);
        goToResult.putExtra("year", year);
        goToResult.putExtra("runtime", runtime);
        goToResult.putExtra("link", link);
        goToResult.putExtra("plot", plot);
        goToResult.putExtra("director", director);
        goToResult.putExtra("actors", actors);

        goToResult.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(goToResult);
    }
}
