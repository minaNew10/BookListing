package com.example.android.booklisting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // api key = AIzaSyCNkAmJ9fPmCvC4mPd8YfMpdKGB1cK7um8
    EditText etxtSearch;
    Button btnSearch;
    String queryParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxtSearch = findViewById(R.id.etxt_search);
        btnSearch = findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryParam = etxtSearch.getText().toString();
                String url = "https://www.googleapis.com/books/v1/volumes?q=" + queryParam;
                Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }
}
