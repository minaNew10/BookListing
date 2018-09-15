package com.example.android.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by M on 08/07/2018.
 * <p>
 * Adapter class to populate data into the recycler View
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    /**
     * context of the recycler view
     */
    Context context;

    /**
     * data for the books to populate
     */
    private List<Book> books;

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
        if (book.getmSubTitle() == null)
            holder.txtvSubtitle.setVisibility(View.GONE);
        else
            holder.txtvSubtitle.setText(book.getmSubTitle());


        StringBuilder sbAauthors = new StringBuilder();
        List<String> listAuthors = book.getmAuthors();
        int len = listAuthors.size();
        if (len > 0)
            sbAauthors.append(listAuthors.get(0));
        for (int i = 0; i < len; i++) {
            sbAauthors.append(", " + listAuthors.get(i));
        }
        sbAauthors.append(".");
        holder.txtvAuthor.setText(sbAauthors);

        holder.txtvPageCount.setText(book.getmPageCount());

        String averageRating = book.getmAverageRating();
        if (averageRating == "-")
            holder.txtvReview.setVisibility(View.GONE);
        else
            holder.txtvReview.setText(book.getmAverageRating());

        holder.imgvSmallThumbnail.setImageBitmap(book.getmSmallThumbnail());
    }

    /**
     * @return int determine the size of the data to be populated
     */
    @Override
    public int getItemCount() {
        if (books != null && books.size() > 0)
            return books.size();
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        /**
         * parent of the whole item
         */
        ConstraintLayout parent;

        /**
         * textview for the title of the book
         */
        TextView txtvTitle;

        /**
         * textview for the subtitle of the book
         */
        TextView txtvSubtitle;

        /**
         * Textview for the name of the author of the book
         */
        TextView txtvAuthor;

        TextView txtvReview;

        TextView txtvPageCount;

        ImageView imgvSmallThumbnail;

        /**
         * Constructs a new {@link com.example.android.booklisting.BookAdapter.BookViewHolder} object for the recycler view
         *
         * @param itemView Layout of the item
         */
        public BookViewHolder(View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent_of_recycler_item);
            txtvTitle = itemView.findViewById(R.id.txtv_title);
            txtvSubtitle = itemView.findViewById(R.id.txtv_subtitle);
            txtvReview = itemView.findViewById(R.id.txtv_review);
            txtvPageCount = itemView.findViewById(R.id.txtv_page_count);
            txtvAuthor = itemView.findViewById(R.id.txtv_author);
            imgvSmallThumbnail = itemView.findViewById(R.id.imgv_small_thumbnail);

        }
    }

}
