
package model;

import java.util.Date;

public class DatHang {
    private String maDonHang;
    private String tenKhachHang;
    private String diaChi;
    private String soDienThoai;
    private String maNhanVien;
    private Date ngayDat;
    private int trangThai;

    public DatHang(String maDonHang, String tenKhachHang, String diaChi, String soDienThoai, String maNhanVien, Date ngayDat, int trangThai) {
        this.maDonHang = maDonHang;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.maNhanVien = maNhanVien;
        this.ngayDat = ngayDat;
        this.trangThai = trangThai;
    }
     public DatHang(DatHang datHang) {
        this.maDonHang = datHang.getMaDonHang();
        this.tenKhachHang = datHang.getTenKhachHang();
        this.diaChi = datHang.getDiaChi();
        this.soDienThoai = datHang.getSoDienThoai();
        this.maNhanVien = datHang.getMaNhanVien();
        this.ngayDat = datHang.getNgayDat();
        this.trangThai = datHang.getTrangThai();
    }
    public String getMaDonHang() {
        return maDonHang;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }
    
}
