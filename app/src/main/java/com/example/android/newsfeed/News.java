package com.example.android.newsfeed;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yishuyan on 9/23/16.
 */

public class News implements Parcelable {
    private String newsTitle, publicationTime, sectionName, newsLink;

    public News(String mNewsTitle, String mPublicationTime, String mSectionName, String mNewsLink){
        newsTitle = mNewsTitle;
        publicationTime = mPublicationTime;
        sectionName = mSectionName;
        newsLink = mNewsLink;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getPublicationTime() {
        return publicationTime;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getNewsLink() {
        return newsLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.newsTitle);
        dest.writeString(this.publicationTime);
        dest.writeString(this.sectionName);
        dest.writeString(this.newsLink);
    }

    protected News(Parcel in) {
        this.newsTitle = in.readString();
        this.publicationTime = in.readString();
        this.sectionName = in.readString();
        this.newsLink = in.readString();
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
