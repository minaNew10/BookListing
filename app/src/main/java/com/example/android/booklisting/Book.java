package com.example.android.booklisting;

/**
 * Created by M on 08/07/2018.
 */


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
     * name of the Authors of the book
     */
    private String mAuthor;

    /**
     * Constructs a new {@link Book} object
     *
     * @param Title  of the book
     * @param Authors name of the book's author
     */
    public Book(String Title, String Authors) {
        this.mTitle = Title;
        this.mAuthor = Authors;
    }

    /**
     * @return String represents the title of the book
     */

    public String getmTitle() {
        return mTitle;
    }

    /**
     * @return String represents the name of the Author
     */

    public String getmAuthor() {
        return mAuthor;
    }
}
