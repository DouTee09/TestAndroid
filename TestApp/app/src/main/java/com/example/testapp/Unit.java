package com.example.testapp;

import java.io.Serializable;

public class Unit implements Serializable {
    private String madonvi;
    private String tendonvi;
    private String email;
    private String website;
    private String diachi;
    private String sdt;

    public Unit(){
    }

    public Unit(String madonvi, String tendonvi, String email, String website, String diachi, String sdt) {
        this.madonvi = madonvi;
        this.tendonvi = tendonvi;
        this.email = email;
        this.website = website;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    public String getMaDonVi() {
        return madonvi;
    }
    public void setMaDonVi(String madonvi) {
        this.madonvi = madonvi;
    }

    public String getTenDonVi() {
        return tendonvi;
    }
    public void setTenDonVi(String tendonvi) {
        this.tendonvi = tendonvi;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDiaChi() {
        return diachi;
    }
    public void setDiaChi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
