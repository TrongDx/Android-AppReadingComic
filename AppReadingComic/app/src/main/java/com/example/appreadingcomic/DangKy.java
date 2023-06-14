package com.example.appreadingcomic;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appreadingcomic.database.DatabaseHelper;
import com.example.appreadingcomic.object.TaiKhoan;
public class DangKy extends AppCompatActivity {

    EditText edtDKTaiKhoan,edtDKMatKhau,edtDKEmail;
    Button btnDKDangKy,btnDKDangNhap;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        AnhXa();

        databaseHelper = new DatabaseHelper(this);

        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String taikhoan = edtDKTaiKhoan.getText().toString();
                String matkhau = edtDKMatKhau.getText().toString();
                String email = edtDKEmail.getText().toString();

                TaiKhoan taikhoan1 = CreateTaiKhoan();
                if(taikhoan.equals("") || matkhau.equals("") || email.equals("")){
                    Toast.makeText(DangKy.this,"Bạn chưa nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                    Log.e("Thông báo : ","Bạn chưa nhập đầy đủ thông tin");
                }
                //Nếu đầy đủ thông tin
                else{
                    //Kiểm tra xem trùng tài khoản không để có thể hiển thị thông báo tài khoản trùng

                    databaseHelper.AddTaiKhoan(taikhoan1);
                    //Toast.makeText(ManDangKy.this,"Đăng ký thành công ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private TaiKhoan CreateTaiKhoan(){
        String taikhoan = edtDKTaiKhoan.getText().toString();
        String matkhau = edtDKMatKhau.getText().toString();
        String email = edtDKEmail.getText().toString();
        int phanquyen = 1;

        TaiKhoan tk = new TaiKhoan(taikhoan,matkhau,email,phanquyen);

        return tk;
    }
    private void AnhXa() {
        edtDKEmail = findViewById(R.id.dkEmail);
        edtDKMatKhau = findViewById(R.id.dkMatKhau);
        edtDKTaiKhoan = findViewById(R.id.dkTaiKhoan);
        btnDKDangKy = findViewById(R.id.dkDangKy);
        btnDKDangNhap = findViewById(R.id.dkDangNhap);

    }
}
