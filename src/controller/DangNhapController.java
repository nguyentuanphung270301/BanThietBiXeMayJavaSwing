package controller;

import DAO.ShareData;
import DAO.DbConnection;
import DAO.PasswordSecurity;
import java.sql.ResultSet;
import model.TaiKhoan;

public class DangNhapController {
    public void DangNhap(String tenDangNhap, String matKhau) throws Exception {
        String sql ="EXECUTE SP_DANGNHAP ?, ?";

        ResultSet rs = DbConnection.executeQuery(sql, tenDangNhap, PasswordSecurity.encode(matKhau));

        if (!rs.next())
            throw new Exception("Thông tin đăng nhập không chính xác");

        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(rs.getString("TENDANGNHAP"));
        tk.setHoTen(rs.getString("HOTEN"));
        tk.setMaNhanVien(rs.getString("MANHANVIEN"));
        tk.setLoaiTaiKhoan(rs.getString("LOAITAIKHOAN"));

        ShareData.nguoiDangNhap = tk;
    }
}
