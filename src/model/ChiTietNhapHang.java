/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class ChiTietNhapHang {
    private String maDonHang;
    private String maThietBi;
    private int soLuong;
    private String thanhTien;

    public ChiTietNhapHang(String maDonHang, String maThietBi, int soLuong, String thanhTien) {
        this.maDonHang = maDonHang;
        this.maThietBi = maThietBi;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getMaThietBi() {
        return maThietBi;
    }

    public void setMaThietBi(String maThietBi) {
        this.maThietBi = maThietBi;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    
}
