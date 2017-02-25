package com.example.android.newsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yishuyan on 9/23/16.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, List<News> newsList){
        super(context, 0, newsList);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.news_list, parent, false);
        }

        News currentNews = getItem(position);

        TextView newsTitle = (TextView) listView.findViewById(R.id.news_title);
        newsTitle.setText(currentNews.getNewsTitle());

        TextView newsType = (TextView) listView.findViewById(R.id.news_type);
        newsType.setText(currentNews.getSectionName());

        TextView newsDate = (TextView) listView.findViewById(R.id.news_date);
        newsDate.setText(currentNews.getPublicationTime());

        return listView;
    }

}
