package com.example.ass.model;

public class ThanhVien {
    private int maThanhVien;
    private String tenThanhVien;
    private String namSinh;

    public ThanhVien() {
    }

    public ThanhVien(int maThanhVien, String tenThanhVien, String namSinh) {
        this.maThanhVien = maThanhVien;
        this.tenThanhVien = tenThanhVien;
        this.namSinh = namSinh;
    }

    public ThanhVien(String tenThanhVien, String namSinh) {
        this.tenThanhVien = tenThanhVien;
        this.namSinh = namSinh;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(int maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public String getTenThanhVien() {
        return tenThanhVien;
    }

    public void setTenThanhVien(String tenThanhVien) {
        this.tenThanhVien = tenThanhVien;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
