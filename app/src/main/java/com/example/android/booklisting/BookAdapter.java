package com.example.android.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M on 08/07/2018.
 * <p>
 * Adapter class to populate data into the recycler View
 */

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {
    /**
     * context of the recycler view
     */
    Context context;

    /**
     * data for the books to populate
     */
    List<Book> books;

    /**
     * constructs an object of the adapter which handles populating data into the recycler view
     *
     * @param context of the recycler view
     * @param books   is the data to be populated
     */
    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    /**
     * @return a view holder for the item to be viewed
     */
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        BookViewHolder viewHolder = new BookViewHolder(view);
        return viewHolder;
    }

    /**
     * binds the data in one item of books to the {@link BookViewHolder} which returns from onCreateViewHolder method
     */
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.txtvTitle.setText(book.getmTitle());
        holder.txtvAuthor.setText(book.getmAuthor());

    }

    /**
     * @return int determine the size of the data to be populated
     */
    @Override
    public int getItemCount() {
        return books.size();
    }
}
