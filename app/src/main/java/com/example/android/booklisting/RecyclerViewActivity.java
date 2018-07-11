package com.example.android.booklisting;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M on 09/07/2018.
 */

public class RecyclerViewActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    String url = "https://www.googleapis.com/books/v1/volumes?q=";
    RecyclerView recyclerView;
    List<Book> books = new ArrayList<>();
    BookAdapter bookAdapter;
    TextView txtvEmptyState;
    ProgressBar progressBar;
    Bundle b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this));

        bookAdapter = new BookAdapter(RecyclerViewActivity.this, books);

        recyclerView.setAdapter(bookAdapter);
        txtvEmptyState = findViewById(R.id.txtvEmptyState);
        progressBar = findViewById(R.id.loading_spinner);
        b = getIntent().getExtras();
        String queryParam = b.getString("queryParam");
        url += queryParam;
        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {

        return new BookLoader(RecyclerViewActivity.this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        progressBar.setVisibility(View.GONE);
        if (data == null || data.size() < 1) {
            txtvEmptyState.setVisibility(View.VISIBLE);
            txtvEmptyState.setText("No data available");
        } else {
            txtvEmptyState.setVisibility(View.GONE);
            bookAdapter.books = data;
            bookAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

    }
}
