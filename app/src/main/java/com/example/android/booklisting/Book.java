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
     * The name of tthe Author of the book
     */
    private List<String> mAuthor;

    /**
     * Constructs a new {@link Book} object
     *
     * @param Title  of the book
     * @param Author name of the book's author
     */
    public Book(String Title, List<String> Author) {
        this.mTitle = Title;
        this.mAuthor = Author;
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

    public List<String> getmAuthor() {
        return mAuthor;
    }
}
