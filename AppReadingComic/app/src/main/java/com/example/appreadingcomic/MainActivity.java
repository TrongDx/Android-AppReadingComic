package com.example.appreadingcomic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.example.appreadingcomic.adapter.ComicAdapter;
import com.example.appreadingcomic.database.DatabaseHelper;
import com.example.appreadingcomic.object.Comic;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gdvListComic;
    ComicAdapter adapter;
    ArrayList<Comic> comicArrayList;
    EditText edSearch;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        setUp();
        setClick();

        databaseHelper = new DatabaseHelper(this);

        // hiển thị
        comicArrayList = new ArrayList<>();
        Cursor dataComic = databaseHelper.getData2();
        while (dataComic.moveToNext()) {
            Integer id = dataComic.getInt(0);
            String nameComic = dataComic.getString(1);
            String nameChap = dataComic.getString(2);
            String linkComic = dataComic.getString(4);
            String content = dataComic.getString(3);

            Comic comic = new Comic(id, nameComic, nameChap, linkComic, content);
            comicArrayList.add(comic);
        }
        adapter = new ComicAdapter(this, 0, comicArrayList);
        gdvListComic.setAdapter(adapter);
    }

    private void anhXa() {
        gdvListComic = findViewById(R.id.gdvListComic);
        edSearch = findViewById(R.id.edSearch);
    }

    private void setUp() {
        comicArrayList = new ArrayList<>();
        adapter = new ComicAdapter(this, 0, comicArrayList);
    }

    private void setClick() {
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edSearch.getText().toString();
                adapter.sortComic(s);
            }
        });

        gdvListComic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ComicDetailActivity.class);
                String nameComic = comicArrayList.get(position).getNameComic();
                String content = comicArrayList.get(position).getContent();
                intent.putExtra("nameComic", nameComic);
                intent.putExtra("content", content);
                //Log.e("Tên truyện : ",tent);
                startActivity(intent);
            }
        });
    }

    private void displayComicContent(Comic comic) {
        // Ở đây, bạn có thể thực hiện các hành động như chuyển sang màn hình mới để hiển thị nội dung của truyện,
        // hoặc hiển thị một Dialog chứa nội dung truyện, tuỳ thuộc vào cách bạn xây dựng ứng dụng của mình.
        String nameComic = comic.getNameComic();
        String nameChap = comic.getNameChap();
        String linkComic = comic.getLinkComic();
        // Ví dụ: Chuyển sang màn hình mới và truyền dữ liệu của truyện
        Intent intent = new Intent(MainActivity.this, ComicDetailActivity.class);
        intent.putExtra("nameComic", nameComic); // Truyền tên comic
        intent.putExtra("nameChap", nameChap); // Truyền tên chapter (nếu cần)
        intent.putExtra("linkComic", linkComic); // Truyền URL hình ảnh comic (nếu cần)
        startActivity(intent);
    }

    public void update(View view) {

    }
}

