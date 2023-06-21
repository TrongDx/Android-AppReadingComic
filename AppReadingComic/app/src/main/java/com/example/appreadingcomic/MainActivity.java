package com.example.appreadingcomic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

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
    ImageView btnUser;

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
//            String nameChap = dataComic.getString(2);
            String linkComic = dataComic.getString(3);
            String content = dataComic.getString(2);

            Comic comic = new Comic(id, nameComic, linkComic, content);
            comicArrayList.add(comic);
        }
        adapter = new ComicAdapter(this,  comicArrayList);
        gdvListComic.setAdapter(adapter);
    }

    private void anhXa() {
        gdvListComic = findViewById(R.id.gdvListComic);
        edSearch = findViewById(R.id.edSearch);
        btnUser = findViewById(R.id.btnUser);
    }

    private void setUp() {
        comicArrayList = new ArrayList<>();
        adapter = new ComicAdapter(this,  comicArrayList);
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
                String linkComic = comicArrayList.get(position).getLinkComic();
                intent.putExtra("nameComic", nameComic);
                intent.putExtra("content", content);
                intent.putExtra("linkComic", linkComic);
                //Log.e("Tên truyện : ",tent);
                startActivity(intent);
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());

                // Xử lý sự kiện khi chọn một item trong menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.menu_option1) {
                            // Xử lý logic khi chọn option 1
                            return true;
                        } else if (itemId == R.id.menu_option4) {
                            // Xử lý logic khi chọn option 4
                            Intent intent = new Intent(MainActivity.this, MainLogin.class);
                            startActivity(intent);
                            finish();
                            return true;
                        } else {
                            return false;
                        }
                    }
                });

                popupMenu.show();
            }
        });
    }

    private void displayComicContent(Comic comic) {
        // Ở đây, bạn có thể thực hiện các hành động như chuyển sang màn hình mới để hiển thị nội dung của truyện,
        // hoặc hiển thị một Dialog chứa nội dung truyện, tuỳ thuộc vào cách bạn xây dựng ứng dụng của mình.
        String nameComic = comic.getNameComic();
        String linkComic = comic.getLinkComic();
        // Ví dụ: Chuyển sang màn hình mới và truyền dữ liệu của truyện
        Intent intent = new Intent(MainActivity.this, ComicDetailActivity.class);
        intent.putExtra("nameComic", nameComic); // Truyền tên comic
        intent.putExtra("linkComic", linkComic); // Truyền URL hình ảnh comic (nếu cần)
        startActivity(intent);
    }
}

