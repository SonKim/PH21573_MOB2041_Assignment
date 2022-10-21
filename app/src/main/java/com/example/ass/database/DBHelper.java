package com.example.ass.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "dangkymonhoc.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dbThuThu = "CREATE TABLE THUTHU(MATT NVARCHAR PRIMARY KEY, HOTEN NVARCHAR, MATKHAU VARCHAR)";
        sqLiteDatabase.execSQL(dbThuThu);

        String dbThanhVien = "CREATE TABLE THANHVIEN(MATV INTEGER PRIMARY KEY AUTOINCREMENT, HOTEN NVARCHAR, NAMSINH NVARCHAR)";
        sqLiteDatabase.execSQL(dbThanhVien);

        String dbLoai = "CREATE TABLE LOAI(MALOAI INTEGER PRIMARY KEY AUTOINCREMENT, TENLOAI NVARCHAR)";
        sqLiteDatabase.execSQL(dbLoai);

        String dbSach = "CREATE TABLE SACH(MASACH INTEGER PRIMARY KEY AUTOINCREMENT, TENSACH NVARCHAR, GIATHUE INTEGER, MALOAI INTEGER REFERENCES LOAI(MALOAI))";
        sqLiteDatabase.execSQL(dbSach);

        String dbPhieuMuon = "CREATE TABLE PHIEUMUON(MAPM INTEGER PRIMARY KEY AUTOINCREMENT, MATT NVARCHAR REFERENCES THUTHU(MATT), MATV INTEGER REFERENCES THANHVIEN(MATV), MASACH INTEGER REFERENCES SACH(MASACH), NGAY NVARCHAR, TRASACH INTEGER, TIENTHUE INTEGER)";
        sqLiteDatabase.execSQL(dbPhieuMuon);

        sqLiteDatabase.execSQL("INSERT INTO THUTHU VALUES ('thuthu01','Thủ Thư 01','123456')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i!=i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THUTHU");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAI");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(sqLiteDatabase);
        }
    }
}
