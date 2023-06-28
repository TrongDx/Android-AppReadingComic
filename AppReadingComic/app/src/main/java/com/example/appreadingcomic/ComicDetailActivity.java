package com.example.appreadingcomic;

import static com.example.appreadingcomic.database.DatabaseHelper.COLUMN_COMMENT;
import static com.example.appreadingcomic.database.DatabaseHelper.COLUMN_REVIEWER_NAME;
import static com.example.appreadingcomic.database.DatabaseHelper.TABLE_REVIEW;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.appreadingcomic.database.DatabaseHelper;
import com.example.appreadingcomic.object.Comic;

public class ComicDetailActivity extends AppCompatActivity {
    TextView txtNameComic,txtContent;
    ImageView imgLinkComic;
    Button btnSubmitRating;
    Button btnSubmitComment;
    EditText edtComment;
    TextView tvComments;
    DatabaseHelper databaseHelper;

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
        tvComments = findViewById(R.id.tvComments);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        String tenTruyen = intent.getStringExtra("nameComic");
        String noidung = intent.getStringExtra("content");
        String anhTruyen = intent.getStringExtra("linkComic");

        txtNameComic.setText(tenTruyen);
        txtContent.setText(noidung);
        Glide.with(this).load(anhTruyen).into(imgLinkComic);

        //Cuộn textview
        txtContent.setMovementMethod(new ScrollingMovementMethod());

        showCommentsFromDatabase();

        btnSubmitRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý logic đánh giá

                // Hiển thị thông báo Đánh giá thành công
                Toast.makeText(ComicDetailActivity.this, "Đánh giá thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnSubmitComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = edtComment.getText().toString().trim(); // Lấy nội dung bình luận từ EditText
                String username = ((MainLogin) getIntent().getSerializableExtra("previousActivity")).loggedInUsername; // Lấy tên người dùng đã đăng nhập
                Log.d("ComicDetailActivity", "Logged in username: " + username);

                if (TextUtils.isEmpty(comment)) {
                    Toast.makeText(ComicDetailActivity.this, "Vui lòng nhập bình luận", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(username)) {
                    Toast.makeText(ComicDetailActivity.this, "Vui lòng đăng nhập trước khi bình luận", Toast.LENGTH_SHORT).show();
                } else {
                    saveCommentToDatabase(comment, username); // Lưu bình luận và tên người dùng vào cơ sở dữ liệu
                }
            }
        });
    }

    private void saveCommentToDatabase(String comment, String username) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase(); // Mở cơ sở dữ liệu ghi

        ContentValues values = new ContentValues();
        values.put(COLUMN_COMMENT, comment);
        values.put(COLUMN_REVIEWER_NAME, username);

        long result = database.insert(TABLE_REVIEW, null, values); // Chèn giá trị vào bảng TABLE_REVIEW

        if (result == -1) {
            // Xử lý lỗi nếu cần
        } else {
            Toast.makeText(ComicDetailActivity.this, "Bình luận đã được lưu", Toast.LENGTH_SHORT).show();
            edtComment.setText(""); // Xóa nội dung bình luận sau khi lưu thành công
            showCommentsFromDatabase(); // Cập nhật hiển thị danh sách bình luận
        }

        database.close(); // Đóng cơ sở dữ liệu sau khi hoàn thành
    }
    private void showCommentsFromDatabase() {
        SQLiteDatabase database = databaseHelper.getReadableDatabase(); // Mở cơ sở dữ liệu đọc

        String[] projection = {COLUMN_REVIEWER_NAME, COLUMN_COMMENT};
        Cursor cursor = database.query(TABLE_REVIEW, projection, null, null, null, null, null);

        StringBuilder comments = new StringBuilder();

        while (cursor.moveToNext()) {
            String comment = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COMMENT));
            String username = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REVIEWER_NAME));

            comments.append(username).append(": ").append(comment).append("\n");
        }

        cursor.close();
        database.close();

        tvComments.setText(comments.toString());
    }
}
