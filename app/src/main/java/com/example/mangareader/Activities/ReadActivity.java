package com.example.mangareader.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mangareader.R;
import com.example.mangareader.Read;
import com.example.mangareader.SourceHandlers.Sources;
import com.example.mangareader.mangakakalot.Mangakakalot;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.Collections;

public class ReadActivity extends AppCompatActivity {

    private float xStart = 0;
    private final Read read = new Read();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        PhotoView photoView = findViewById(R.id.photo_view);
        TextView progress = findViewById(R.id.progress);

        // we set up our temp clickers
        // I'll replace this with swiping once I figure out how to
        // Make it work together with PhotoView
        Button prevClick = findViewById(R.id.prevPage);
        prevClick.setBackgroundColor(Color.TRANSPARENT);
        float x = 0;
        prevClick.setAlpha(x);
        Button nextClick = findViewById(R.id.nextPage);
        nextClick.setBackgroundColor(Color.TRANSPARENT);
        nextClick.setAlpha(x);

        prevClick.setOnClickListener(v -> {
            read.Swipe(-1);
        });

        nextClick.setOnClickListener(v -> {
            read.Swipe(1);
        });


        Intent intent = getIntent();
        String chapterUrl = intent.getStringExtra("url");
        String src = intent.getStringExtra("source");
        Log.d("lol", chapterUrl);


        new Thread(() -> {
            Sources source;
            switch (src) {
                case "mangakakalot":
                    source = new Mangakakalot();
                    break;

                default:
                    source = new Mangakakalot();
                    break;
            }

            ArrayList<String> imgs;
            imgs = source.GetImages(chapterUrl);;
            imgs.removeAll(Collections.singleton(null));
            imgs.removeAll(Collections.singleton(""));

            read.AssignData(this,imgs,photoView,progress); // We assign our context to read

            runOnUiThread(() -> {
                read.ImageShower();
            });


        }).start();

    }

    public Activity ReturnActivity() {
        return this;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }




}