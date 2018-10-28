package com.example.android.booklisting;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.graphics.BitmapFactory.decodeStream;

/**
 * Created by M on 09/07/2018.
 * <p>
 * Helper methods relating to requesting and receiving book data from Google books Api
 */

class QueryUtils {

    private static final String TAG = "QueryUtils";

    /**
     * private  constructor because no one should ever need to create an object of {@link QueryUtils}
     * this is a class which contains only static variables and methods which can be accessed only through class name
     * and an object of this class is not needed
     */
    private QueryUtils() {
    }

    public static List<Book> fetchBooksList(String url) {

        URL urlObject = createUrl(url);

        String jsonResponse = "";

        try {
            jsonResponse = makeHttpRequeat(urlObject);
        } catch (IOException e) {
            Log.i(TAG, "fetchBooksList: problems in closiing input stream");
        }

        List<Book> booksList = extractBooksFromJson(jsonResponse);
        return booksList;
    }

    /**
     * @return URL object from the given url string*
     */
    private static URL createUrl(String url) {
        URL urlObject = null;
        try {
            urlObject = new URL(url);
        } catch (MalformedURLException e) {
            Log.i(TAG, "createUrl: ");
        }
        return urlObject;
    }

    /**
     * @return String represents the json response after http request
     */
    private static String makeHttpRequeat(URL urlObject) throws IOException {
        String jsonResponse = "";

        if (urlObject == null)
            return jsonResponse;

//        HttpURLConnection urlConnection = null;
//        InputStream inputStream = null;
//        try {
//            urlConnection = (HttpURLConnection) urlObject.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.setConnectTimeout(15000);
//            urlConnection.setReadTimeout(1000);
//            urlConnection.connect();
//            if (urlConnection.getResponseCode() == 200) {
//                inputStream = urlConnection.getInputStream();
//                jsonResponse = readFromStream(inputStream);
//            } else {
//                Log.i(TAG, "makeHttpRequeat: Error in response code" + urlConnection.getResponseCode());
//            }
//        } catch (IOException e) {
//            Log.i(TAG, "makeHttpRequeat: problems retrieving book results");
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (inputStream != null) {
//                inputStream.close();
//            }
//        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlObject).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if(response.code() == 200){
                jsonResponse = response.body().string();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            response.close();
        }
        return jsonResponse;
    }

    /**
     * @return String json response after reading from {@link InputStream}
     */

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder jsonResponse = new StringBuilder();
        if (inputStream != null) {
            //inputStreamReader to read data in form of byew
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                jsonResponse.append(line);
                line = bufferedReader.readLine();
            }
        }
        return jsonResponse.toString();
    }

    /**
     * @return List for the {@link Book} queried after parsing json response
     */

    private static List<Book> extractBooksFromJson(String jsonResponse) {

        if (TextUtils.isEmpty(jsonResponse))
            return null;
        List<Book> books = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray items = root.getJSONArray("items");

            for (int i = 0, n = items.length(); i < n; i++) {
                JSONObject bookInfo = items.getJSONObject(i);
                JSONObject volumeInfo = bookInfo.getJSONObject("volumeInfo");

                String title = null;
                String subtitle = null;
                ArrayList<String> authorsList = new ArrayList<>();
                String pageCount = null;
                String averageRating = null;
                Bitmap smallThumbnail = null;
                Bitmap largeThumbnail = null;

                if (volumeInfo.has("title"))
                    title = volumeInfo.getString("title");
                if (volumeInfo.has("subtitle"))
                    title = volumeInfo.getString("subtitle");
                if (volumeInfo.has("authors")) {
                    JSONArray authorsJsonArray = volumeInfo.getJSONArray("authors");
                    for (int j = 0; j < authorsJsonArray.length(); j++) {
                        String authorName = authorsJsonArray.getString(j);
                        authorsList.add(authorName);
                    }
                }
                //If there is average rating, then only getString
                if (volumeInfo.has("pageCount")) {
                    pageCount = volumeInfo.getString("pageCount");
                }
                if (volumeInfo.has("averageRating")) {
                    averageRating = volumeInfo.getString("averageRating");
                } else {
                    averageRating = "-";
                }
                if (volumeInfo.has("imageLinks")) {
                    JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                    if (imageLinks.has("smallThumbnail")) {
                        String smallThumbnailUrl = imageLinks.getString("smallThumbnail");
                        Log.i(TAG, "extractBooksFromJson: " + smallThumbnailUrl);
                        smallThumbnail = fetchThumbnail(smallThumbnailUrl);
                    }
                    if (imageLinks.has("thumbnail")) {
                        largeThumbnail = fetchThumbnail(imageLinks.getString("thumbnail"));
                    }
                }

                Book book = new Book(title, subtitle, authorsList,
                        averageRating,
                        pageCount,
                        smallThumbnail,
                        largeThumbnail
                );
                books.add(book);
                Log.i(TAG, "extractBooksFromJson: " + book.toString());
            }

        } catch (JSONException e) {
            Log.i(TAG, "extractBooksFromJson: problems in parsing books from json reponse");
        }
        return books;
    }

    private static Bitmap fetchThumbnail(String requestUrl) {
        //Create URL object
        URL url = createUrl(requestUrl);

        //Perform HTTP request to the URL and receive a JSON response back
        Bitmap smallThumbnail = null;
        try {
            smallThumbnail = makeHttpRequestForBitmap(url);
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request.", e);
        }
        Log.v("SmallThumbnail", String.valueOf(smallThumbnail));
        return smallThumbnail;
    }

    private static Bitmap makeHttpRequestForBitmap(URL url) throws IOException {
        Bitmap bitmapResponse = null;

        //If the URL is null, then return early.
        if (url == null) {
            return null;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                bitmapResponse = decodeStream(inputStream);

            } else {
                Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return bitmapResponse;
    }

}
