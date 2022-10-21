package com.example.ass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ass.dao.ThuThuDAO;

public class DangNhapActivity extends AppCompatActivity {
    EditText edTaiKhoan,edMatKhau;
    Button btnDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edTaiKhoan = findViewById(R.id.edTaiKhoan);
        edMatKhau = findViewById(R.id.edMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        ThuThuDAO thuThuDAO = new ThuThuDAO(DangNhapActivity.this);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tk = edTaiKhoan.getText().toString();
                String mk = edMatKhau.getText().toString();
                if(thuThuDAO.checkDangNhap(tk,mk)){
                    SharedPreferences saSharedPreferences = getSharedPreferences("TAIKHOAN",MODE_PRIVATE);
                    SharedPreferences.Editor editor = saSharedPreferences.edit();
                    editor.putString("matt",tk);
                    editor.commit();
                    Intent intent = new Intent(DangNhapActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(DangNhapActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}