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

public class DoiMatKhauActivity extends AppCompatActivity {
    EditText edMatKhauCu,edMatKhauMoi,edNhapLaiMatKhauMoi;
    Button btnDoiMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        edMatKhauCu = findViewById(R.id.edMatKhauCu);
        edMatKhauMoi = findViewById(R.id.edMatKhauMoi);
        edNhapLaiMatKhauMoi = findViewById(R.id.edNhapLaiMatKhauMoi);
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhau);
        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matKhauCu = edMatKhauCu.getText().toString();
                String matKhauMoi = edMatKhauMoi.getText().toString();
                String nhapLaiMatKhauMoi = edNhapLaiMatKhauMoi.getText().toString();
                if(matKhauMoi.equals(nhapLaiMatKhauMoi)){
                    SharedPreferences sharedPreferences = getSharedPreferences("TAIKHOAN",MODE_PRIVATE);
                    String matt = sharedPreferences.getString("matt","");
                    ThuThuDAO thuThuDAO = new ThuThuDAO(DoiMatKhauActivity.this);
                    boolean check = thuThuDAO.doiMatKhau(matt,matKhauCu,matKhauMoi);
                    if(check){
                        Toast.makeText(DoiMatKhauActivity.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DoiMatKhauActivity.this,DangNhapActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else {
                        Toast.makeText(DoiMatKhauActivity.this, "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(DoiMatKhauActivity.this, "Nhập mật khẩu không trùng với nhau", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}