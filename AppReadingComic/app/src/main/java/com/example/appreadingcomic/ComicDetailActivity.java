package com.example.appreadingcomic;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.appreadingcomic.object.Comic;

public class ComicDetailActivity extends AppCompatActivity {
    TextView txtNameComic,txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);

        txtNameComic = findViewById(R.id.tvComicName);
        txtContent = findViewById(R.id.tvContent);

        Intent intent = getIntent();
        String tenTruyen = intent.getStringExtra("nameComic");
        String noidung = intent.getStringExtra("content");

        txtNameComic.setText(tenTruyen);
        txtContent.setText(noidung);

        //Cuộn textview
        txtContent.setMovementMethod(new ScrollingMovementMethod());

    }
}

