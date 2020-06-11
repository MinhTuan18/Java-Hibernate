/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author HP
 */
public class SinhVien {
    private String maSinhVien;
    private String hoTen;
    private String gioiTinh;
    private String soCMND;
    private String maLop;

    public SinhVien() {
    }

    //getter
    public String getMaSinhVien() {
        return maSinhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public String getMaLop() {
        return maLop;
    }
    
    //setter
    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }
    
    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }
}
