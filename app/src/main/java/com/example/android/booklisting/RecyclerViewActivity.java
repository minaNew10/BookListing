package com.example.android.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M on 09/07/2018.
 */

public class RecyclerViewActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    private static final String TAG = "RecyclerViewActivity";
    /**
     * URL for the book data from google books api
     *
     */
    private static final String URL_GOOGLE_BOOKS_API = "https://www.googleapis.com/books/v1/volumes?";

    private static final int BOOK_LOADER_ID = 0;

    RecyclerView recyclerView;

    List<Book> books = new ArrayList<>();
    BookAdapter bookAdapter;

    TextView txtvEmptyState;
    ProgressBar progressBar;


    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this));


        txtvEmptyState = findViewById(R.id.txtvEmptyState);
        progressBar = findViewById(R.id.loading_spinner);

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            progressBar.setVisibility(View.VISIBLE);
            getLoaderManager().initLoader(BOOK_LOADER_ID, null, this);
        } else {
            progressBar.setVisibility(View.GONE);
            txtvEmptyState.setVisibility(View.VISIBLE);
            txtvEmptyState.setText("No internet connection");
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        Bundle b = getIntent().getExtras();


        Uri baseUri = Uri.parse(URL_GOOGLE_BOOKS_API);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("q", b.getString("queryParam"));
        uriBuilder.appendQueryParameter("maxResults", "20");
        Log.i(TAG, "onCreateLoader: " + uriBuilder.toString());
        return new BookLoader(RecyclerViewActivity.this, uriBuilder.toString());
    }


    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        progressBar.setVisibility(View.GONE);
        if (data == null || data.size() < 1) {
            txtvEmptyState.setVisibility(View.VISIBLE);
            txtvEmptyState.setText("No data available");
        } else {
            txtvEmptyState.setVisibility(View.GONE);
            bookAdapter = new BookAdapter(this, data);
            recyclerView.setAdapter(bookAdapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

    }
}
