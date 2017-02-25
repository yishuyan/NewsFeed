package com.example.android.newsfeed;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by yishuyan on 9/24/16.
 */

public class NewsListLoader extends AsyncTaskLoader<List<News>> {

    private static final String LOG_TAG = NewsListLoader.class.getSimpleName();

    private String mUrl;

    public NewsListLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {forceLoad();}

    @Override
    public List<News> loadInBackground() {
        if(mUrl == null)
            return null;
        List<News> newsList = QueryUtils.extractFeatureFromJson(mUrl);
        return newsList;
    }
}
