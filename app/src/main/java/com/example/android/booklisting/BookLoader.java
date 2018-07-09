package com.example.android.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.nfc.tech.TagTechnology;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M on 09/07/2018.
 */

/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class BookLoader extends AsyncTaskLoader<List<Book>> {

    /**
     * TAG for log messages
     */
    private static final String TAG = "BookLoader";

    /**
     * Query URL
     */
    String mUrl;

    /**
     * Constructs a new {@link BookLoader} to fetch list of books
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public BookLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is a background thread
     */
    @Override
    public List<Book> loadInBackground() {
        if (mUrl == null)
            return null;

        //Perform network request, parse the response extract a list of books
        List<Book> bookList = QueryUtils.fetchBooksList(mUrl);
        return bookList;
    }
}
