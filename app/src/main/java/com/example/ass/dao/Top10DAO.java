package com.example.ass.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ass.database.DBHelper;
import com.example.ass.model.Sach;

import java.util.ArrayList;

public class Top10DAO {
    DBHelper dbHelper;
    public Top10DAO(Context context){
        dbHelper = new DBHelper(context);
    }
    public ArrayList<Sach> getAllTop10(){
        ArrayList<Sach> saches = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT PM.MASACH, SC.TENSACH, COUNT(PM.MASACH) FROM PHIEUMUON PM, SACH SC WHERE PM.MASACH = SC.MASACH GROUP BY PM.MASACH, SC.TENSACH ORDER BY COUNT(PM.MASACH) DESC LIMIT 10",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                saches.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            }
            cursor.close();
        }
        return saches;
    }
    public int getDoanhThu(String batDau, String ketThuc){
        batDau = batDau.replace("/","");
        ketThuc = ketThuc.replace("/","");
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT SUM(TIENTHUE) FROM PHIEUMUON WHERE SUBSTR(NGAY,7)||SUBSTR(NGAY,4,2)||SUBSTR(NGAY,1,2) BETWEEN ? AND ?",new String[]{batDau,ketThuc});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
