/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phucn
 */
public class ChucVu {
    String maChucVu;
    String chucVu;
    int luongCoBan;

    public ChucVu(String maChucVu, String chucVu, int Luong) {
        this.maChucVu = maChucVu;
        this.chucVu = chucVu;
        this.luongCoBan = Luong;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public int getLuong() {
        return luongCoBan;
    }

    public void setLuong(int Luong) {
        this.luongCoBan = Luong;
    }
    
}
