package com.example.android.newsfeed;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<News>> {

    private static String USGS_REQUEST_URL = "http://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2016-09-24&api-key=test";

    private static final int NEWS_LOADER_ID = 1;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    NewsAdapter adapter;
    ArrayList<News> newses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isConnectedNetwork(this)) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, savedInstanceState, this);
        } else {
            Toast.makeText(MainActivity.this, "No Internet connectivity!", Toast.LENGTH_LONG).show();
        }

        if (savedInstanceState == null || !savedInstanceState.containsKey("Latest News")) {
            newses = new ArrayList<News>();
        } else {
            newses = savedInstanceState.getParcelableArrayList("Latest News");
        }

        ListView listView = (ListView) findViewById(R.id.news_list);
        adapter = new NewsAdapter(this, newses);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News selectedNews = newses.get(i);
                String newsLink = selectedNews.getNewsLink();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsLink));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle keepState) {
        keepState.putParcelableArrayList("Latest News", newses);
        super.onSaveInstanceState(keepState);
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsListLoader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsList) {
        adapter.clear();
        if(newsList != null && !newsList.isEmpty()) {
            newses = new ArrayList<News>();
            adapter.addAll(newsList);
            newses.addAll(newsList);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        adapter.clear();
    }

    public static boolean isConnectedNetwork(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
