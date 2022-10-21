package com.example.ass.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ass.database.DBHelper;

public class ThuThuDAO {
    DBHelper dbHelper;

    public ThuThuDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
    public boolean checkDangNhap(String mtt, String matkhau){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM THUTHU WHERE MATT = ? AND MATKHAU = ?",new String[]{mtt,matkhau});
        if(cursor.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }
    public boolean doiMatKhau(String matt, String matKhauCu, String matKhauMoi){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM THUTHU WHERE MATT = ? AND MATKHAU = ?",new String[]{matt,matKhauCu});
        if(cursor.getCount()>0){
            ContentValues values = new ContentValues();
            values.put("MATKHAU",matKhauMoi);
            long check = database.update("THUTHU",values,"MATT = ?",new String[]{matt});
            if(check==-1){
                return false;
            }
            return true;
        }
        return false;
    }
}
