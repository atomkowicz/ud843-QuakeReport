/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

//import android.app.LoaderManager;
//import android.content.Context;
//import android.content.Intent;
//import android.content.Loader;
//import android.net.Uri;
////import android.os.AsyncTask;
//import android.os.Bundle;
//import android.content.AsyncTaskLoader;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.app.LoaderManager;
//import android.app.LoaderManager.LoaderCallbacks;
//import java.util.ArrayList;
//import java.util.List;


import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<List<Earthquake>> {

    private static final String QUAKE_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";
    //private static final String QUAKE_URL = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";
    //private ArrayList<Earthquake> earthquakes;
    private static final int EARTHQUAKE_LOADER_ID = 1;

    private EarthquakeAdapter mAdapter;



    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        return new EarthquakeLoader(this, QUAKE_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {

        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        //updateUi(new ArrayList<Earthquake>());
        mAdapter.clear();
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        LoaderManager loaderManager = getLoaderManager();

        //new EarthquakeAsyncTask().execute(QUAKE_URL);
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }



//    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {
//
//        @Override
//        protected ArrayList<Earthquake> doInBackground(String... urls) {
//
//            // Don't perform the request if there are no URLs, or the first URL is null.
//            if (urls.length < 1 || urls[0] == null) {
//                return null;
//            }
//            // Perform the HTTP request for earthquake data and process the response.
//            ArrayList<Earthquake> results = QueryUtils.fetchEarthquakeData(urls[0]);
//            return results;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<Earthquake> results) {
//
//            // If there is no result, do nothing.
//            if (results == null) {
//                return;
//            }
//            // Update the information displayed to the user.
//            updateUi(results);
//        }
//
//    }
//
//
    /**
     * Update the UI with the given earthquake information.
     */
    private void updateUi(List<Earthquake> earthquakes) {

        final List<Earthquake> eq = earthquakes;

        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new EarthquakeAdapter(this, earthquakes);

        mAdapter.clear();

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on

                EarthquakeAdapter adapter = new EarthquakeAdapter(getApplicationContext(), eq);

                Earthquake currentEarthquake = (Earthquake) adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }
}
