package com.vanderveldt.rens.watchlist;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rens on 15-11-2016.
 */

class WatchlistAsync extends AsyncTask {

    // Extract context.
    private static Context context;
    // Initiate list of search results:
    private ArrayList<HashMap<String, String>> searchList = new ArrayList<HashMap<String, String>>();
    // extract listview.
    private ListView lv;

    private String searchquery;
    WatchlistAsync(Context c, String searchquery, ListView lv) {
        context = c;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Searching", Toast.LENGTH_LONG).show();
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] params) {

        // Get json from apihandler.
        ApiHandler apihandler = new ApiHandler();
        apihandler.querySet(searchquery);
        JSONObject results = apihandler.Searchquery();

        // Extract json variables
        JSONArray data;
        try {
            data = results.getJSONArray("Search");

            for(int i = 0; i < data.length(); i++){
                JSONObject tmp = data.getJSONObject(i);

                // Extract each movie.
                String title = tmp.getString("Title");
                String year = tmp.getString("Year");
                String link = tmp.getString("Poster");
                String id = tmp.getString("imdbID");

                // Temp storage.
                HashMap<String, String> movie = new HashMap<>();
                movie.put("title", title);
                movie.put("year", year);
                movie.put("link", link);
                movie.put("id", id);

                // Add to searchList.
                searchList.add(movie);
            }
        }
        catch (final JSONException e) {
            Toast.makeText(context,"Json parsing error", Toast.LENGTH_LONG).show();
                }


        // TODO: create Adapter with setList fucntion to fill expandable listview.
        return null;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void result) {

        // After JSON data is processed create an adapter for listview generation.
        ListAdapter adapter = new SimpleAdapter(context,searchList,R.layout.row_layout, new String[]{"title","year","link","id"},
                new int[]{R.id.title,R.id.year, R.id.link, R.id.id});
        lv.setAdapter(adapter);

    }
}
