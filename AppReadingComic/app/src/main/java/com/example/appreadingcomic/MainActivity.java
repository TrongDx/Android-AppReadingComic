package com.example.appreadingcomic;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

        databaseHelper = new DatabaseHelper(this, null, null, 1);
        // tạo bảng
        databaseHelper.QueryData("CREATE TABLE IF NOT EXISTS Comics(Id INTEGER PRIMARY KEY AUTOINCREMENT, nameComic VARCHAR(300), nameChap VARCHAR(50), linkComic VARCHAR(200))");

        // thêm dữ liệu

        databaseHelper.QueryData("INSERT INTO Comics VALUES(null, 'Thượng Cổ Thần Văn', 'Chapter 11', 'https://st.nettruyento.com/data/comics/184/thuong-co-than-van.jpg')");
        databaseHelper.QueryData("INSERT INTO Comics VALUES(null, 'Hỗn Loạn Nhất Lịch Sử', 'Chapter 12', 'https://st.nettruyento.com/data/comics/104/hon-loan-nhat-lich-su.jpg')");
        databaseHelper.QueryData("INSERT INTO Comics VALUES(null, 'Dạy Hư Đồ Đệ Phản Diện Rồi Phải Làm Sao Đây ?', 'Chapter 26', 'https://st.nettruyento.com/data/comics/236/day-hu-do-de-phan-dien-roi-phai-lam-sao-4257.jpg')");
        databaseHelper.QueryData("INSERT INTO Comics VALUES(null, 'Thiếu Chủ Ma Giáo Có Thủ Cung Sa', 'Chapter 33', 'https://st.nettruyento.com/data/comics/0/thieu-chu-ma-giao-co-thu-cung-sa.jpg')");
        databaseHelper.QueryData("INSERT INTO Comics VALUES(null, 'Thượng Cổ Thần Văn', 'Chapter 11', 'https://st.nettruyento.com/data/comics/184/thuong-co-than-van.jpg')");
        databaseHelper.QueryData("INSERT INTO Comics VALUES(null, 'Hỗn Loạn Nhất Lịch Sử', 'Chapter 12', 'https://st.nettruyento.com/data/comics/104/hon-loan-nhat-lich-su.jpg')");
        databaseHelper.QueryData("INSERT INTO Comics VALUES(null, 'Dạy Hư Đồ Đệ Phản Diện Rồi Phải Làm Sao Đây ?', 'Chapter 26', 'https://st.nettruyento.com/data/comics/236/day-hu-do-de-phan-dien-roi-phai-lam-sao-4257.jpg')");
        databaseHelper.QueryData("INSERT INTO Comics VALUES(null, 'Thiếu Chủ Ma Giáo Có Thủ Cung Sa', 'Chapter 33', 'https://st.nettruyento.com/data/comics/0/thieu-chu-ma-giao-co-thu-cung-sa.jpg')");
        databaseHelper.QueryData("INSERT INTO Comics VALUES(null, 'Thượng Cổ Thần Văn', 'Chapter 11', 'https://st.nettruyento.com/data/comics/184/thuong-co-than-van.jpg')");
        databaseHelper.QueryData("INSERT INTO Comics VALUES(null, 'Hỗn Loạn Nhất Lịch Sử', 'Chapter 12', 'https://st.nettruyento.com/data/comics/104/hon-loan-nhat-lich-su.jpg')");
        databaseHelper.QueryData("INSERT INTO Comics VALUES(null, 'Dạy Hư Đồ Đệ Phản Diện Rồi Phải Làm Sao Đây ?', 'Chapter 26', 'https://st.nettruyento.com/data/comics/236/day-hu-do-de-phan-dien-roi-phai-lam-sao-4257.jpg')");
        databaseHelper.QueryData("INSERT INTO Comics VALUES(null, 'Thiếu Chủ Ma Giáo Có Thủ Cung Sa', 'Chapter 33', 'https://st.nettruyento.com/data/comics/0/thieu-chu-ma-giao-co-thu-cung-sa.jpg')");

        // hiển thị
        comicArrayList = new ArrayList<>();
        Cursor dataComic = databaseHelper.GetData("SELECT * FROM Comics");
        while (dataComic.moveToNext()) {
            String nameComic = dataComic.getString(1);
            String nameChap = dataComic.getString(2);
            String linkComic = dataComic.getString(3);

            Comic comic = new Comic(nameComic, nameChap, linkComic);
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
    }

    public void update(View view) {

    }
}

