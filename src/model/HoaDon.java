
package model;

public class HoaDon {
    private String maHoaDon;
    private String maThietBi;
    private String tenThietBi;
    private int soLuong;
    private String thanhTien;

    public HoaDon(String maHoaDon, String maThietBi, String tenThietBi, int soLuong, String thanhTien) {
        this.maHoaDon = maHoaDon;
        this.maThietBi = maThietBi;
        this.tenThietBi = tenThietBi;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaThietBi() {
        return maThietBi;
    }

    public void setMaThietBi(String maThietBi) {
        this.maThietBi = maThietBi;
    }

    public String getTenThietBi() {
        return tenThietBi;
    }

    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
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
