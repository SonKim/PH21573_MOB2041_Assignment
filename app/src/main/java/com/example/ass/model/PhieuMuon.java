package com.example.ass.model;

public class PhieuMuon {
    private int maPhieuMuon;
    private String maThuThu;
    private int maThanhVien;
    private int maSach;
    private String ngay;
    private int traSach;
    private int tienThue;

    public PhieuMuon() {
    }
    public PhieuMuon(int maPhieuMuon, String maThuThu, int maThanhVien, int maSach, String ngay, int traSach, int tienThue) {
        this.maPhieuMuon = maPhieuMuon;
        this.maThuThu = maThuThu;
        this.maThanhVien = maThanhVien;
        this.maSach = maSach;
        this.ngay = ngay;
        this.traSach = traSach;
        this.tienThue = tienThue;
    }
    public PhieuMuon(String maThuThu, int maThanhVien, int maSach, String ngay, int traSach, int tienThue) {
        this.maThuThu = maThuThu;
        this.maThanhVien = maThanhVien;
        this.maSach = maSach;
        this.ngay = ngay;
        this.traSach = traSach;
        this.tienThue = tienThue;
    }
    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public String getMaThuThu() {
        return maThuThu;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }
}
