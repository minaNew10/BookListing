package com.example.android.booklisting;

/**
 * Created by M on 08/07/2018.
 */


import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Book} object contains information related to one book
 */

public class Book {

    /**
     * Title of the book
     */
    private String mTitle;

    /**
     *  Subtitle of the book
     */
    private String mSubTitle;

    /**
     * Authors of the book in case the book has more than one author
     */
    private List<String> mAuthors;

    /**
     * Rating of the book
     */
    private String mAverageRating;

    /**
     * No of pages in the book
     */
    private String mPageCount;

    /**
     * Small thumbnail of the book
     */

    private Bitmap mSmallThumbnail;

    /**
     * Large preview for the thumbnail of the book
     */

    private Bitmap imagePreview;
    /**
     * a link to book details
     */
    private String mUrl;

    /**
     * Constructs a new {@link Book} object
     *
     * @param Title  of the book
     * @param Authors name of the book's author
     */
    public Book(String Title, List<String> Authors) {
        this.mTitle = Title;
        this.mAuthors = Authors;
    }

    public Book(String mTitle, String mSubTitle, List<String> mAuthors, String mAverageRating,
                String mPageCount, Bitmap mSmallThumbnail, Bitmap imagePreview) {
        this(mTitle, mAuthors);
        this.mAuthors = mAuthors;
        this.mAverageRating = mAverageRating;
        this.mPageCount = mPageCount;
        this.mSmallThumbnail = mSmallThumbnail;
        this.imagePreview = imagePreview;
    }

    public Book(String mTitle, String mSubTitle, List<String> mAuthors, String mAverageRating,
                String mPageCount, Bitmap mSmallThumbnail) {
        this(mTitle, mAuthors);
        this.mAuthors = mAuthors;
        this.mAverageRating = mAverageRating;
        this.mPageCount = mPageCount;
        this.mSmallThumbnail = mSmallThumbnail;
        this.imagePreview = null;
    }

    /**
     * @return String represents the title of the book
     */

    public String getmTitle() {
        return mTitle;
    }


    @Override
    public String toString() {
        return "Book{" +
                "mTitle='" + mTitle + '\'' +
                ", mSubTitle='" + mSubTitle + '\'' +
                ", mAuthors=" + mAuthors +
                ", mAverageRating='" + mAverageRating + '\'' +
                ", mPageCount='" + mPageCount + '\'' +
                ", mSmallThumbnail=" + mSmallThumbnail +
                ", imagePreview=" + imagePreview +
                ", mUrl='" + mUrl + '\'' +
                '}';
    }

    public String getmSubTitle() {
        return mSubTitle;
    }

    public List<String> getmAuthors() {
        return mAuthors;
    }

    public String getmAverageRating() {
        return mAverageRating;
    }

    public String getmPageCount() {
        return mPageCount;
    }

    public Bitmap getmSmallThumbnail() {
        return mSmallThumbnail;
    }

    public Bitmap getImagePreview() {
        return imagePreview;
    }

    public String getmUrl() {
        return mUrl;
    }

    /**
     * @return String represents the name of the Author
     */

    public List<String> getmAuthor() {
        return mAuthors;
    }
}
