package com.example.ass.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ass.database.DBHelper;
import com.example.ass.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDAO {
    DBHelper dbHelper;

    public ThanhVienDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
    public ArrayList<ThanhVien> getAllThanhVien(){
        ArrayList<ThanhVien> thanhViens = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM THANHVIEN",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                thanhViens.add(new ThanhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return thanhViens;
    }
    public boolean xoa(ThanhVien thanhVien){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM PHIEUMUON WHERE MATV = ?",new String[]{String.valueOf(thanhVien.getMaThanhVien())});
        if(cursor.getCount()>0){
            return false;
        }
        long check = database.delete("THANHVIEN","MATV=?",new String[]{String.valueOf(thanhVien.getMaThanhVien())});
        if(check>0){
            return true;
        }
        return false;
    }
    public boolean them(ThanhVien thanhVien){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HOTEN",thanhVien.getTenThanhVien());
        values.put("NAMSINH",thanhVien.getNamSinh());
        long check = database.insert("THANHVIEN",null,values);
        if(check>0){
            return true;
        }
        return false;
    }
    public boolean sua(ThanhVien thanhVien){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HOTEN",thanhVien.getTenThanhVien());
        values.put("NAMSINH",thanhVien.getNamSinh());
        long check = database.update("THANHVIEN",values,"MATV=?",new String[]{String.valueOf(thanhVien.getMaThanhVien())});
        if(check>0){
            return true;
        }
        return false;
    }
}
