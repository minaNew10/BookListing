package com.example.android.booklisting;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
     * Constructs a new {@link BookViewHolder} object for the recycler view
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


    }
}
