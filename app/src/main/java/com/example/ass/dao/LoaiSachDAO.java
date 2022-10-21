package com.example.ass.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ass.database.DBHelper;
import com.example.ass.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachDAO {
    DBHelper dbHelper;
    public LoaiSachDAO(Context context){
        dbHelper = new DBHelper(context);
    }
    public ArrayList<LoaiSach> getAllLoaiSach(){
        ArrayList<LoaiSach> loaiSaches = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM LOAI",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                loaiSaches.add(new LoaiSach(cursor.getInt(0),cursor.getString(1)));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return loaiSaches;
    }
    public boolean them(String loaiSach){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLOAI",loaiSach);
        long them = database.insert("LOAI",null,values);
        if(them>0){
            return true;
        }
        return false;
    }
    public boolean xoa(int maloai){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM SACH WHERE MALOAI = ?",new String[]{String.valueOf(maloai)});
        if(cursor.getCount()>0){
            return false;
        }
        long check = database.delete("LOAI","MALOAI=?",new String[]{String.valueOf(maloai)});
        if(check>0){
            return true;
        }
        return false;
    }
    public boolean sua(LoaiSach loaiSach){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLOAI",loaiSach.getTenLoai());
        long check = database.update("LOAI",values,"MALOAI=?",new String[]{String.valueOf(loaiSach.getMaLoai())});
        if(check>0){
            return true;
        }
        return false;
    }
}
