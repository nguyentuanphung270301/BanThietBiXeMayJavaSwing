
package model;

import java.util.Date;

public class ThongKe {
    private String maHoaDon;
    private String hoTenNhanVien;
    private Date ngayBan;
    private String tongTien;

    public ThongKe(String maHoaDon, String hoTenNhanVien, Date ngayBan, String tongTien) {
        this.maHoaDon = maHoaDon;
        this.hoTenNhanVien = hoTenNhanVien;
        this.ngayBan = ngayBan;
        this.tongTien = tongTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getHoTenNhanVien() {
        return hoTenNhanVien;
    }

    public void setHoTenNhanVien(String hoTenNhanVien) {
        this.hoTenNhanVien = hoTenNhanVien;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
    
    
}
