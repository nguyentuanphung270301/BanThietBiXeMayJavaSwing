
package model;

import java.util.Date;

public class TaiKhoan {
    String tenDangNhap, matKhau,maNhanVien, hoTen, loaiTaiKhoan;
    public TaiKhoan() {
    }
    public TaiKhoan(TaiKhoan taiKhoan){
        this.tenDangNhap = taiKhoan.getTenDangNhap();
        this.matKhau = taiKhoan.getMatKhau();
        this.maNhanVien = taiKhoan.getMaNhanVien();
        this.hoTen = taiKhoan.getHoTen();
        this.loaiTaiKhoan = taiKhoan.getLoaiTaiKhoan();
    }
    public TaiKhoan(String tenDangNhap, String matKhau, String maNhanVien, String hoTen, String loaiTaiKhoan) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public TaiKhoan(String tenDangNhap, String maNhanVien,String loaiTaiKhoan) {
        this.tenDangNhap = tenDangNhap;
        this.maNhanVien = maNhanVien;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public TaiKhoan(String tenDangNhap, String matKhau, String maNhanVien, String loaiTaiKhoan) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maNhanVien = maNhanVien;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }
    
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }  
}
