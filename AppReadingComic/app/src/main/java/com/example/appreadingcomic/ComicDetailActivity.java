package com.example.appreadingcomic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.appreadingcomic.object.Comic;

public class ComicDetailActivity extends AppCompatActivity {
    TextView txtNameComic,txtContent;
    ImageView imgLinkComic;
    Button btnSubmitRating;
    Button btnSubmitComment;
    EditText edtComment;
    private Context ct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);

        txtNameComic = findViewById(R.id.tvComicName);
        txtContent = findViewById(R.id.tvContent);
        imgLinkComic = findViewById(R.id.imgComic);
        btnSubmitRating = findViewById(R.id.btnSubmitRating);
        btnSubmitComment = findViewById(R.id.btnSubmitComment);
        edtComment = findViewById(R.id.edtComment);

        Intent intent = getIntent();
        String tenTruyen = intent.getStringExtra("nameComic");
        String noidung = intent.getStringExtra("content");
        String anhTruyen = intent.getStringExtra("linkComic");

        txtNameComic.setText(tenTruyen);
        txtContent.setText(noidung);
        Glide.with(this).load(anhTruyen).into(imgLinkComic);

        //Cuộn textview
        txtContent.setMovementMethod(new ScrollingMovementMethod());

        btnSubmitRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý logic đánh giá

                // Hiển thị thông báo Đánh giá thành công
                Toast.makeText(ComicDetailActivity.this, "Đánh giá thành công", Toast.LENGTH_SHORT).show();
            }
        });

        String comment = edtComment.getText().toString().trim();
        btnSubmitComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý logic đánh giá
//                if (comment.isEmpty()) {
//                    // Nếu chuỗi rỗng, hiển thị cảnh báo
//                    Toast.makeText(ComicDetailActivity.this, "Vui lòng nhập bình luận", Toast.LENGTH_SHORT).show();
//                } else {
                    // Ngược lại, xử lý logic bình luận
                    // Hiển thị thông báo Đánh giá thành công
                    Toast.makeText(ComicDetailActivity.this, "Bình luận thành công", Toast.LENGTH_SHORT).show();
                }
//            }
        });
    }
}

