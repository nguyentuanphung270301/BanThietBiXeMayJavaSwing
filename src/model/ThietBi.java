
package model;

public class ThietBi {
    private String maThietBi;
    private String tenThietBi;
    private String maLoai;
    private String maNSX;
    private String tgBaoHanh;
    private int soLuong;
    private float Gia;
    private byte[] hinhAnh;

    public ThietBi() {
    }
   
    public ThietBi(String maThietBi, String tenThietBi, String maLoai, String maNSX, String tgBaoHanh, int soLuong, float Gia, byte[] hinhAnh) {
        this.maThietBi = maThietBi;
        this.tenThietBi = tenThietBi;
        this.maLoai = maLoai;
        this.maNSX = maNSX;
        this.tgBaoHanh = tgBaoHanh;
        this.soLuong = soLuong;
        this.Gia = Gia;
        this.hinhAnh = hinhAnh;
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

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getMaNSX() {
        return maNSX;
    }

    public void setMaNSX(String maNSX) {
        this.maNSX = maNSX;
    }

    public String getTgBaoHanh() {
        return tgBaoHanh;
    }

    public void setTgBaoHanh(String tgBaoHanh) {
        this.tgBaoHanh = tgBaoHanh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    
    
}
