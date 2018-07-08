package com.example.android.booklisting;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by M on 08/07/2018.
 * class which represents a view holder for one item of the recycler view
 */


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
     * Textview for the name of the author of the book
     */
    TextView txtvAuthor;

    /**
     * Constructs a new {@link BookViewHolder} object for the recycler view
     *
     * @param itemView Layout of the item
     */
    public BookViewHolder(View itemView) {
        super(itemView);
        parent = itemView.findViewById(R.id.parent_of_recycler_item);
        txtvTitle = itemView.findViewById(R.id.txtv_title);
        txtvAuthor = itemView.findViewById(R.id.txtv_author);
    }
}
