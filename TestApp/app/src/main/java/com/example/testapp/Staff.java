package com.example.testapp;

import java.io.Serializable;

public class Staff implements Serializable {
    private String manhanvien;
    private String hoten;
    private String chucvu;
    private String email;
    private String sodienthoai;
    private String madonvi;

    public  Staff(){
    }

    public Staff(String manhanvien, String hoten, String chucvu, String email, String sdt, String madonvi){
        this.manhanvien = manhanvien;
        this.hoten = hoten;
        this.chucvu = chucvu;
        this.email = email;
        this.sodienthoai = sdt;
        this.madonvi = madonvi;
    }

    public String getMaNhanVien() {
        return manhanvien;
    }
    public void setMaNhanVien(String manhanvien) {
        this.manhanvien = manhanvien;
    }

    public String getTenNhanVien() {
        return hoten;
    }
    public void setTenNhanVien(String hoten) {
        this.hoten = hoten;
    }

    public String getChucVu() {
        return chucvu;
    }
    public void setChucVu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSDT() {
        return sodienthoai;
    }
    public void setSDT(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getMaDonVi() {
        return madonvi;
    }
    public void setMaDonVi(String madonvi) {
        this.madonvi = madonvi;
    }
}
