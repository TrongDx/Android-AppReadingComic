package com.example.appreadingcomic;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.example.appreadingcomic.adapter.ComicAdapter;
import com.example.appreadingcomic.database.DatabaseHelper;
import com.example.appreadingcomic.object.Comic;

import java.util.ArrayList;

public class MainAdmin extends AppCompatActivity {
    ListView listView;
    Button buttonAdd;

    ArrayList<Comic> comicArrayList;
    ComicAdapter comicAdapter;
    DatabaseHelper databaseHelper;
    ImageView btnUser;
    ImageView btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        btnUser = findViewById(R.id.btnUserAdmin);
        listView = findViewById(R.id.listviewAdmin);
        buttonAdd = findViewById(R.id.btnAddComic);
        btnHome = findViewById(R.id.btnHome);
        databaseHelper = new DatabaseHelper(this);
        initList();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogDelete(position);
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy truyện được chọn từ danh sách truyện
                Comic selectedComic = comicArrayList.get(position);

                // Chuyển sang màn hình sửa truyện và truyền thông tin truyện qua Intent
                Intent intent = new Intent(MainAdmin.this, MainSuaBai.class);
                intent.putExtra("comic", selectedComic);
                startActivity(intent);
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                int id = intent.getIntExtra("Id", 0);
                Intent intent1 = new Intent(MainAdmin.this, MainDangBai.class);
                intent.putExtra("Id", id);
                startActivity(intent1);
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainAdmin.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());

                // Xử lý sự kiện khi chọn một item trong menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.menu_option1) {
                            // Xử lý logic khi chọn option 1
                            // Lấy thông tin người dùng đã đăng nhập từ Intent
                            Intent intent = getIntent();
                            String loggedInUsername = intent.getStringExtra("loggedInUsername");

                            // Tạo Intent để chuyển từ MainActivity sang UserInfoActivity
                            Intent userInfoIntent = new Intent(MainAdmin.this, UserInfoActivity.class);
                            userInfoIntent.putExtra("loggedInUsername", loggedInUsername);
                            startActivity(userInfoIntent);
                            return true;
                        } else if (itemId == R.id.menu_option4) {
                            // Xử lý logic khi chọn option 4
                            Intent intent = new Intent(MainAdmin.this, MainLogin.class);
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
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdmin.this, MainAdmin.class);
                startActivity(intent);
            }
        });
    }

    // Dialog Delete
    private void DialogDelete(int position) {

        // Tạo đối tượng cửa sổ dialog
        Dialog dialog = new Dialog(this);

        // Nạp layout vào
        dialog.setContentView(R.layout.dialogdelete);
        // Click No mới thoát, click ngoài không thoát
        dialog.setCanceledOnTouchOutside(false);

        // Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idtruyen = comicArrayList.get(position).getID();
                //Xóa trong SQL
                databaseHelper.Delete(idtruyen);
                //Cập nhật lại listview
                Intent intent = new Intent(MainAdmin.this,MainAdmin.class);
                finish();
                startActivity(intent);
                Toast.makeText(MainAdmin.this,"Xóa truyện thành công",Toast.LENGTH_SHORT).show();
                // Cập nhật lại listview
                initList();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void initList() {
        comicArrayList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);

        Cursor cursor1 = databaseHelper.getData1();

        while (cursor1.moveToNext()) {
            Integer id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);
            comicArrayList.add(new Comic(id, tentruyen, anh, noidung, id_tk));

            comicAdapter = new ComicAdapter(getApplicationContext(), comicArrayList);
            listView.setAdapter(comicAdapter);
        }
        cursor1.moveToFirst();
        cursor1.close();
    }
}
