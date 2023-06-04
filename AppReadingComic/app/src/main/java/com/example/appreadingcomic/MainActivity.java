package com.example.appreadingcomic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.GridView;

import com.example.appreadingcomic.adapter.ComicAdapter;
import com.example.appreadingcomic.object.Comic;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gdvListComic;
    ComicAdapter adapter;
    ArrayList<Comic> comicArrayList;
    EditText edSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClick();
    }

    private void init() {
        comicArrayList = new ArrayList<>();
        comicArrayList.add(new Comic("Thượng Cổ Thần Văn", "Chapter 11", "https://st.nettruyento.com/data/comics/184/thuong-co-than-van.jpg"));
        comicArrayList.add(new Comic("Hỗn Loạn Nhất Lịch Sử", "Chapter 12", "https://st.nettruyento.com/data/comics/104/hon-loan-nhat-lich-su.jpg"));
        comicArrayList.add(new Comic("Dạy Hư Đồ Đệ Phản Diện Rồi Phải Làm Sao Đây ?", "Chapter 26", "https://st.nettruyento.com/data/comics/236/day-hu-do-de-phan-dien-roi-phai-lam-sao-4257.jpg"));
        comicArrayList.add(new Comic("Thiếu Chủ Ma Giáo Có Thủ Cung Sa", "Chapter 33", "https://st.nettruyento.com/data/comics/0/thieu-chu-ma-giao-co-thu-cung-sa.jpg"));
        comicArrayList.add(new Comic("Thượng Cổ Thần Văn", "Chapter 11", "https://st.nettruyento.com/data/comics/184/thuong-co-than-van.jpg"));
        comicArrayList.add(new Comic("Hỗn Loạn Nhất Lịch Sử", "Chapter 12", "https://st.nettruyento.com/data/comics/104/hon-loan-nhat-lich-su.jpg"));
        comicArrayList.add(new Comic("Dạy Hư Đồ Đệ Phản Diện Rồi Phải Làm Sao Đây ?", "Chapter 26", "https://st.nettruyento.com/data/comics/236/day-hu-do-de-phan-dien-roi-phai-lam-sao-4257.jpg"));
        comicArrayList.add(new Comic("Thiếu Chủ Ma Giáo Có Thủ Cung Sa", "Chapter 33", "https://st.nettruyento.com/data/comics/0/thieu-chu-ma-giao-co-thu-cung-sa.jpg"));
        comicArrayList.add(new Comic("Thượng Cổ Thần Văn", "Chapter 11", "https://st.nettruyento.com/data/comics/184/thuong-co-than-van.jpg"));
        comicArrayList.add(new Comic("Hỗn Loạn Nhất Lịch Sử", "Chapter 12", "https://st.nettruyento.com/data/comics/104/hon-loan-nhat-lich-su.jpg"));
        comicArrayList.add(new Comic("Dạy Hư Đồ Đệ Phản Diện Rồi Phải Làm Sao Đây ?", "Chapter 26", "https://st.nettruyento.com/data/comics/236/day-hu-do-de-phan-dien-roi-phai-lam-sao-4257.jpg"));
        comicArrayList.add(new Comic("Thiếu Chủ Ma Giáo Có Thủ Cung Sa", "Chapter 33", "https://st.nettruyento.com/data/comics/0/thieu-chu-ma-giao-co-thu-cung-sa.jpg"));

        adapter = new ComicAdapter(this, 0, comicArrayList);
    }

    private void anhXa() {
        gdvListComic = findViewById(R.id.gdvListComic);
        edSearch = findViewById(R.id.edSearch);
    }

    private void setUp() {
        gdvListComic.setAdapter(adapter);
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
}