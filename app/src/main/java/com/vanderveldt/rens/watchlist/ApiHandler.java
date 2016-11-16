package com.vanderveldt.rens.watchlist;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;

/**
 * Created by Rens on 15-11-2016.
 */

public class ApiHandler {

    private String parameter = "";

    public String querySet(String searchparameter){
        parameter = searchparameter;
        return parameter;
    }

    protected JSONObject Searchquery(){
        String querystring = "http://omdbapi.com/?&s=";
        String movietype = "&type=movie";

        parameter = parameter.replace(" ","+");

        // Create the final adress
        String finalquery;
        finalquery = querystring + parameter + movietype;

        // Convert to URL.
        URL url;
        try {
            url = new URL(finalquery);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Get JSON object from adress
        return new JSONObject(IOUtils.toString(url, Charset.forName("UTF-8")));
    }
}
