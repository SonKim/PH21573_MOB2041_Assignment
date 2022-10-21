package com.example.ass.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.ass.database.DBHelper;
import com.example.ass.model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonDAO {
    DBHelper dbHelper;
    public PhieuMuonDAO(Context context){
        dbHelper = new DBHelper(context);
    }
    public ArrayList<PhieuMuon> getAllPhieuMuon(){
        ArrayList<PhieuMuon> phieuMuons = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT PM.MAPM, PM.MATT, PM.MATV, PM.MASACH, PM.NGAY, PM.TRASACH, PM.TIENTHUE FROM PHIEUMUON PM, THANHVIEN TV, SACH S, THUTHU TT WHERE PM.MATV = TV.MATV AND PM.MATT = TT.MATT AND PM.MASACH = S.MASACH ORDER BY PM.MAPM DESC",null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                phieuMuons.add(new PhieuMuon(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6)));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return phieuMuons;
    }
    public boolean thayDoiTraSach(int mapm){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TRASACH",1);
        long traSach = database.update("PHIEUMUON",values,"MAPM=?",new String[]{String.valueOf(mapm)});
        if(traSach<0){
            return false;
        }else {
            return true;
        }
    }
    public boolean them(PhieuMuon phieuMuon){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MATT",phieuMuon.getMaThuThu());
        values.put("MATV",phieuMuon.getMaThanhVien());
        values.put("MASACH",phieuMuon.getMaSach());
        values.put("NGAY",phieuMuon.getNgay());
        values.put("TRASACH",phieuMuon.getTraSach());
        values.put("TIENTHUE",phieuMuon.getTienThue());
        long them = database.insert("PHIEUMUON",null,values);
        if(them>0){
            return true;
        }else {
            return false;
        }
    }
}
