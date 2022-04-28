
package model;

import java.util.Date;

public class NhapHang {
    private String maPhieuNhap;
    private Date ngayNhap;
    private String maNhanVien;
    private int trangThai;

    public NhapHang(String maPhieuNhap, Date ngayNhap, String maNhanVien, int trangThai) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.maNhanVien = maNhanVien;
        this.trangThai = trangThai;
    }

    public NhapHang(NhapHang nh) {
        this.maPhieuNhap = nh.getMaPhieuNhap();
        this.ngayNhap = nh.getNgayNhap();
        this.maNhanVien = nh.getMaNhanVien();
        this.trangThai = nh.getTrangThai();
    }
    
    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
