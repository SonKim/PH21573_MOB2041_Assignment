package com.example.ass.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ass.database.DBHelper;
import com.example.ass.model.Sach;

import java.util.ArrayList;

public class SachDAO {
    DBHelper dbHelper;

    public SachDAO(Context context) {
        dbHelper = new DBHelper(context);
    }
    public ArrayList<Sach> getAllSach(){
        ArrayList<Sach> sachs = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT SC.MASACH, SC.TENSACH, SC.GIATHUE, SC.MALOAI, LS.TENLOAI FROM SACH SC, LOAI LS WHERE SC.MALOAI = LS.MALOAI",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                sachs.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4)));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return sachs;
    }
    public boolean them(Sach sach){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH",sach.getTenSach());
        values.put("GIATHUE",sach.getGiaThue());
        values.put("MALOAI",sach.getMaLoai());
        long check = database.insert("SACH",null,values);
        if(check>0){
            return true;
        }
        return false;
    }
    public boolean xoa(int masach){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM PHIEUMUON WHERE MASACH = ?",new String[]{String.valueOf(masach)});
        if(cursor.getCount()>0){
            return false;
        }
        long check = database.delete("SACH","MASACH=?",new String[]{String.valueOf(masach)});
        if(check>0){
            return true;
        }
        return false;
    }
    public boolean sua(Sach sach){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSACH",sach.getTenSach());
        values.put("GIATHUE",sach.getGiaThue());
        values.put("MALOAI",sach.getMaLoai());
        long check = database.update("SACH",values,"MASACH=?",new String[]{String.valueOf(sach.getMaSach())});
        if(check>0){
            return true;
        }
        return false;
    }
}
