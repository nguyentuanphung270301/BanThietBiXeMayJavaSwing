
package controller;

import DAO.KetNoiCoSoDuLieu;
import DAO.ShareData;
import java.math.BigInteger;
import java.net.PasswordAuthentication;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.function.Consumer;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.ChiTietDatHang;
import model.ChiTietNhapHang;
import model.ChucVu;
import model.DatHang;
import model.HoaDon;
import model.LoaiThietBi;
import model.NhaSanXuat;
import model.NhanVien;
import model.NhapHang;
import model.TaiKhoan;
import model.ThietBi;
import model.ThongKe;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;
import view.FormTrangChu_NhanVien;
import view.FormTrangChu_QuanLy;

public class XuLy {
    private TaiKhoan taiKhoan;
    private ChucVu chucVu;
    private NhanVien nhanvien;
    private DatHang datHang;
    private NhapHang nhapHang;
    private LoaiThietBi loaiThietBi;
    private NhaSanXuat nhaSanXuat;
    private ThietBi thietBi;
    private  ChiTietDatHang chiTietDatHang;
    private ChiTietNhapHang chiTietNhapHang;
    private HoaDon hoaDon;
    private ThongKe thongKe;
    private Random generator = new Random();
     public boolean DangNhap(String tenDangNhap, String matKhau, JFrame jFrame) throws SQLException{
        boolean ketQua = false;
        String sql ="EXECUTE SP_DANGNHAP ?,?";
        
        try{
            Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                TaiKhoan tk = new TaiKhoan();
                tk.setTenDangNhap(rs.getString("TENDANGNHAP"));
                tk.setHoTen(rs.getString("HOTEN"));
                tk.setMaNhanVien(rs.getString("MANHANVIEN"));
                tk.setLoaiTaiKhoan(rs.getString("LOAITAIKHOAN"));
                ShareData.nguoiDangNhap = tk;
                if(rs.getString("LOAITAIKHOAN").equals("NHÂN VIÊN")){
                    JOptionPane.showMessageDialog(jFrame, "Đăng nhập thành công !","Thông báo",1);
                    FormTrangChu_NhanVien trangChu_NhanVien = new FormTrangChu_NhanVien(taiKhoan);
                    jFrame.dispose();
                    trangChu_NhanVien.setVisible(true);
                }
                else if(rs.getString("LOAITAIKHOAN").equals("QUẢN LÝ")){
                    JOptionPane.showMessageDialog(jFrame, "Đăng nhập thành công !","Thông báo",1);
                    FormTrangChu_QuanLy trangChu_QuanLy = new FormTrangChu_QuanLy();
                    jFrame.dispose();
                    trangChu_QuanLy.setVisible(true);
                }
            ketQua = true;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return ketQua;
    }
     public int soNgauNhien(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
     public String matKhauNgauNhien(int n){
         String chuThuong = "abcdefghijklmnopqrstuvwxyz";
         String chuHoa = chuThuong.toUpperCase();
         String chuSo = "0123456789";
         String chuoiNgauNhien = chuThuong + chuHoa + chuSo  ;
         StringBuilder sb = new StringBuilder();
         List<String> kq = new ArrayList<>();
         Consumer<String> appendChar = s -> {
             int num = soNgauNhien(0,s.length()-1);
             kq.add(""+s.charAt(num));
         };
         appendChar.accept(chuSo);
         while(kq.size() < n){
             appendChar.accept(chuoiNgauNhien);
         }
         Collections.shuffle(kq,generator);
         return String.join("", kq);
     }
     public ArrayList layTaiKhoan(){
        ArrayList ar = new ArrayList();
        String sql = "EXEC SP_LAYTAIKHOAN";
        try{
                Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
                PreparedStatement ps = ketNoi.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    ar.add(taiKhoan = new TaiKhoan(rs.getString(1), rs.getString(3),rs.getString(4)));
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ar;
    }
    
    public boolean ktDoiMatKhau(String tenDangNhap, String matKhau){
        String sql ="EXEC SP_KTDOIMATKHAU ?,? ";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        boolean kq = false;
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                kq = true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return kq;
    }
    
    public void doiMatKhau(String tenDangNhap, String matKhau){
        String sql ="EXEC SP_DOIMATKHAU "+ tenDangNhap +", ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, matKhau);
            ps.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList layChucVu(){
        ArrayList ar = new ArrayList();
        
        String sql="EXEC SP_LAYCHUCVU";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ar.add(chucVu = new ChucVu(rs.getString(1), rs.getString(2), rs.getInt(3)));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ar;
    }
    public String xuLyLuong(float luong){
        
        Locale localeVN = new Locale("vi","VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String vnd = currencyVN.format(luong);
        return vnd;
    }
    public ArrayList layNhanVien(){
        ArrayList arr = new ArrayList();
        String sql = "EXEC SP_LAYNHANVIEN ";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                arr.add(nhanvien = new NhanVien(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getDate(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),rs.getFloat(10),rs.getInt(11)));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return arr;
    }
     public ArrayList timNhanVien(String sql) {
        ArrayList arry = new ArrayList();
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                arry.add(nhanvien = new NhanVien(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getDate(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),rs.getFloat(10),rs.getInt(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arry;
    }
    public ArrayList layNhanVienChuaTaoTK(){
        ArrayList arr = new ArrayList();
        String sql = "EXEC SP_LAYNHANVIENCHUATAOTK ";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                arr.add(nhanvien = new NhanVien(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getDate(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),rs.getFloat(10),rs.getInt(11)));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return arr;
    }
    public ArrayList layTTNhanVien(String maNhanVien){
        ArrayList arr = new ArrayList();
        String sql ="EXEC SP_LAYTHONGTINNHANVIEN ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                arr.add(nhanvien = new NhanVien(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getFloat(8), rs.getString(9)));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return arr;
    }
    public int layMaNhanVien(String maCV){
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql = "EXEC SP_LAYMANHANVIEN ?";
        int max =0;
        ArrayList list = new ArrayList();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maCV);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(rs.getString(1).trim().substring(2,rs.getString(1).trim().length()));
            }
            if(list.size() == 0){
                return list.size();
            }
            else{
                for(int i= 0; i<list.size();i++){
                    if(max < Integer.parseInt(list.get(i).toString())){
                        max = Integer.parseInt(list.get(i).toString());
                    }
                    else{
                        continue;
                    }
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    return max;
    }
    public boolean ktThemNhanVien(String maNV){
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql ="EXEC SP_KTTHEMNHANVIEN ?";
        boolean kq = true;
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNV);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                kq = false;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return kq;
    }
    public void themNhanVien(String maNhanVien, String hoTen, Date ngaySinh, String gioiTinh, String diaChi, String sdt, String email,
            float bacLuong, String maChucVu){
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql ="EXEC SP_THEMNHANVIEN ?,?,?,?,?,?,?,?,?";
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ps.setString(2, hoTen);
            ps.setDate(3, ngaySinh);
            ps.setString(4, gioiTinh);
            ps.setString(5, diaChi);
            ps.setString(6, sdt);
            ps.setString(7, email);
            ps.setFloat(8, bacLuong);
            ps.setString(9, maChucVu);
            ps.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void xoaNhanVien(String maNhanVien){
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql = "EXEC SP_XOANHANVIEN ?";
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ps.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }  
    public void suaNhanVien(String maNhanVien, String hoTen, Date ngaySinh, String gioiTinh, String diaChi, String sdt, String email,
            float bacLuong, String maChucVu,int trangThai ,String maNhanVienThayDoi){
        String sql ="EXEC SP_SUANHANVIEN ?,?,?,?,?,?,?,?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ps.setString(2, hoTen);
            ps.setDate(3, ngaySinh);
            ps.setString(4, gioiTinh);
            ps.setString(5, diaChi);
            ps.setString(6, sdt);
            ps.setString(7, email);
            ps.setFloat(8, bacLuong);
            ps.setString(9, maChucVu);
            ps.setInt(10, trangThai);
            ps.setString(11, maNhanVienThayDoi);
            ps.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public boolean ktThemChucVu(String maChucVu){
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql ="EXEC SP_KTTHEMCHUCVU ?";
        boolean kq = true;
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maChucVu);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                kq = false;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return kq;
    }
    public void themChucVu(String maChucVu, String tenChucVu, String luongCoBan){
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql = "EXEC SP_THEMCHUCVU ?,?,?";
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maChucVu);
            ps.setString(2, tenChucVu);
            ps.setString(3, luongCoBan);
            ps.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void suaChucVu(String maChucVu, String tenChucVu, String luongCoBan){
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql = "EXEC SP_SUACHUCVU ?,?,?";
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maChucVu);
            ps.setString(2, tenChucVu);
            ps.setString(3, luongCoBan);
            ps.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void xoaChucVu(String maChucVu){
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql = "EXEC SP_XOACHUCVU ?";
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maChucVu);
            ps.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public String layTenNV(String maNhanVien){
        String array = new String();
        String sql ="SELECT * FROM NHANVIEN WHERE MANHANVIEN = ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                array = rs.getString("HOTEN");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return array;
    }
    public String layNgaySinh(String maNhanVien){
        String array = new String();
        String sql ="SELECT * FROM NHANVIEN WHERE MANHANVIEN = ?";
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps =  ketNoi.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                array = dateformat.format(rs.getDate("NGAYSINH"));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return array;
    }
    public boolean ktTaiKhoan(String tenDangNhap){
        String sql ="SELECT * FROM TAIKHOAN WHERE TENDANGNHAP = ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        boolean kq = true;
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, tenDangNhap);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                kq = false;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return kq;
    }
    public void themTaiKhoan(String tenDangNhap, String matKhau, String maNhanVien,String loaiTaiKhoan){
         Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
         String sql ="EXEC SP_THEMTAIKHOAN ?,?,?,?";
         try{
             PreparedStatement ps = ketNoi.prepareStatement(sql);
             ps.setString(1, tenDangNhap);
             ps.setString(2, matKhau);
             ps.setString(3, maNhanVien);
             ps.setString(4, loaiTaiKhoan);
             ps.executeUpdate();
         }
         catch(Exception ex){
             ex.printStackTrace();
         }
    }
    public void quenMatKhau(String tenDangNhap, String matKhau){
         Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
         String sql ="EXEC SP_QUENMATKHAU ?,?";
         try{
             PreparedStatement ps = ketNoi.prepareStatement(sql);
             ps.setString(1, tenDangNhap);
             ps.setString(2, matKhau);
             ps.executeUpdate();
         }
         catch(Exception ex){
             ex.printStackTrace();
         }
    }
    public void suaTaiKhoan(String tenDangNhap, String loaiTaiKhoan, String tenDangNhapThayDoi){
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
         String sql ="EXEC SP_SUATAIKHOAN ?,?,?";
         try{
             PreparedStatement ps = ketNoi.prepareStatement(sql);
             ps.setString(1, tenDangNhap);
             ps.setString(2, loaiTaiKhoan);
             ps.setString(3, tenDangNhapThayDoi);
             ps.executeUpdate();
         }
         catch(Exception ex){
             ex.printStackTrace();
         }
    }
    public void xoaTaiKhoan(String tenDangNhap){
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql ="EXEC SP_XOATAIKHOAN ?";
        try{
             PreparedStatement ps = ketNoi.prepareStatement(sql);
             ps.setString(1, tenDangNhap);
             ps.executeUpdate();
         }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void guiMail(String toMail, String title, String text) throws AddressException, MessagingException{
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage mailMessage;
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);
        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
        mailMessage.setSubject(title);
        mailMessage.setText(text);
        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "nguyentuanphung270301@gmail.com", "odwavnvxyjtwekjs"); 
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }
    public String maHoaMatKhau(String matKhau){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(matKhau.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch(NoSuchAlgorithmException ex){
            throw new RuntimeException(ex);
        }
    }
    public ArrayList layLoaiThietBi(){
        ArrayList arr = new ArrayList();
        String sql ="SELECT * FROM LOAITHIETBI";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                arr.add(loaiThietBi = new LoaiThietBi(rs.getString(1), rs.getString(2)));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return arr;
    }
    public ArrayList layNhaSanXuat(){
        ArrayList arr = new ArrayList();
        String sql ="SELECT * FROM NHASANXUAT";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                arr.add(nhaSanXuat = new NhaSanXuat(rs.getString(1).trim(), rs.getString(2).trim(),rs.getString(3).trim(),rs.getString(4).trim(),rs.getString(5).trim()));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return arr;
    }
    public LoaiThietBi layLoaiThietBi(String maLoai) {
        String sql = "EXEC SP_LAYLOAITHIETBI ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maLoai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                loaiThietBi = new LoaiThietBi(rs.getString(1), rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loaiThietBi;
    }
    public NhaSanXuat layNhaSanXuat(String maNSX) {
        String sql = "EXEC SP_LAYNHASANXUAT ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNSX);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nhaSanXuat = new NhaSanXuat(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhaSanXuat;
    }
    public ArrayList layThietBi() {
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        ArrayList arry = new ArrayList();
        String sql = "SELECT * FROM THIETBI";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                arry.add(thietBi = new ThietBi(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getBytes(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arry;
    }
    public ThietBi layThietBiTheoMa(String maThietBi) {
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        ArrayList arry = new ArrayList();
        String sql = "SELECT * FROM THIETBI WHERE MATHIETBI = ?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maThietBi);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                thietBi = new ThietBi(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getBytes(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thietBi;
    }
    public int layMaThietBi(String maThietBi) {
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql = "EXEC SP_LAYMATHIETBI ?";
        int max = 0;
        ArrayList list = new ArrayList();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maThietBi);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("MATHIETBI").trim().substring(2, rs.getString("MATHIETBI").trim().length()));
            }
            if (list.size() == 0) {
                return max;
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (max < Integer.parseInt(list.get(i).toString())) {
                        max = Integer.parseInt(list.get(i).toString());
                    } else {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return max;
    }
     public boolean ktThemThietBi(String maThietBi) {
        String sql = "EXEC SP_LAYMATHIETBI ?";
        boolean ketQua = true;
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maThietBi);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ketQua = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
    public ArrayList layThietBiTheoMaLoai(String maLoai) {
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        ArrayList arry = new ArrayList();
        String sql = "EXEC SP_LAYTHIETBITHEOMALOAI ? ";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maLoai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                arry.add(thietBi = new ThietBi(rs.getString(1).trim(), rs.getString(2).trim(), rs.getString(3).trim(), rs.getString(4).trim(), rs.getString(5).trim(), rs.getInt(6), rs.getString(7).trim(), rs.getBytes(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arry;
    }
    public void themThietBi(String maThietBi, String tenThietBi, String maLoai, String maNSX, String tgBaoHanh, String gia, byte[] path) {
        String sql = "EXEC SP_THEMTHIETBI ?,?,?,?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maThietBi);
            ps.setString(2, tenThietBi);
            ps.setString(3, maLoai);
            ps.setString(4, maNSX);
            ps.setString(5, tgBaoHanh);
            ps.setString(6, gia );
            
            Blob hinh = new SerialBlob(path);
            ps.setBlob(7, hinh);
            
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
      public void suaThietBi( String tenThietBi, String maLoai, String maNSX, String tgBaoHanh, String gia, byte[] anh, String maThietBi) {
        String sql = "EXEC SP_SUATHIETBI ?,?,?,?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, tenThietBi);
            ps.setString(2, maLoai);
            ps.setString(3, maNSX);
            ps.setString(4, tgBaoHanh);
            ps.setString(5, gia );
            Blob hinh = new SerialBlob(anh);
            ps.setBlob(6, hinh);
            ps.setString(7, maThietBi);
            ps.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
      public void xoaThietBi(String maThietBi) {
        String sql = "EXEC SP_XOATHIETBI ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maThietBi);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public boolean ktLoaiThietBi(String maLoai) {
        boolean kq = true;
        String sql = "EXEC SP_LAYLOAITHIETBI ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maLoai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return kq;
    }
    public void themLoaiThietBi(String maLoai, String tenLoai) {
        String sql = "SP_THEMLOAITHIETBI ?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maLoai);
            ps.setString(2, tenLoai);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void suaLoaiThietBi(String maLoai, String tenLoai, String maLoaiThayDoi) {
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql = "EXEC SP_SUALOAITHIETBI ?,?,?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maLoai);
            ps.setString(2, tenLoai);
            ps.setString(3, maLoaiThayDoi);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     public void xoaLoaiThietBi(String maLoai) {
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql = "EXEC SP_XOALOAITHIETBI ?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maLoai);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     public boolean ktNhaSanXuat(String maNSX) {
        boolean kq = true;
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql = "SELECT * FROM NHASANXUAT";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (maNSX.equals(rs.getString("MANSX").toString().trim())) {
                    return false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return kq;
    }
    public void themNhaSanXuat(String maNSX, String Ten, String diaChi, String sdt, String email) {
        String sql = "EXEC SP_THEMNHASANXUAT ?,?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNSX);
            ps.setString(2, Ten);
            ps.setString(3, diaChi);
            ps.setString(4, sdt);
            ps.setString(5, email);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     public void suaNhaSanXuat(String maNSX, String Ten, String diaChi, String sdt, String email, String maNSXChange) {
        String sql = "EXEC SP_SUANHASANXUAT ?,?,?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNSX);
            ps.setString(2, Ten);
            ps.setString(3, diaChi);
            ps.setString(4, sdt);
            ps.setString(5, email);
            ps.setString(6, maNSXChange);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     public void xoaNhaSanXuat(String maNSX) {
        String sql = "EXEC SP_XOANHASANXUAT ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNSX);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     public ArrayList timthietBi(String sql) {
        ArrayList arry = new ArrayList();
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                arry.add(thietBi = new ThietBi(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getBytes(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arry;
    }
    public ArrayList layDatHang() {
        ArrayList array = new ArrayList();
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql = "SELECT * FROM DATHANG WHERE TRANGTHAI = 0";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                array.add(datHang = new DatHang(rs.getString(1).trim(), rs.getString(2).trim(), rs.getString(3).trim(), rs.getString(4).trim(),rs.getString(5),rs.getDate(6),rs.getInt(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
    public boolean ktmaDonHang() {
        String sql = "SELECT * FROM DATHANG";
        boolean ketQua = false;
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ketQua = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
    public int layMaDonHang() {
        int max = 0;
        ArrayList list = new ArrayList();
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        if (ktmaDonHang()) {
            String sql = "SELECT * FROM DATHANG";
            try {
                PreparedStatement ps = ketNoi.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString("MADDH").trim().substring(2, rs.getString("MADDH").trim().length()));
                }
                if (list.size() == 0) {
                    return list.size();
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        if (max < Integer.parseInt(list.get(i).toString())) {
                            max = Integer.parseInt(list.get(i).toString());
                        } else {
                            continue;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        return max;     
    }
    public DatHang layThongTinDatHang(String maDonHang) {
        String sql = "EXEC SP_LAYTHONGTINDATHANG ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
           PreparedStatement ps = ketNoi.prepareStatement(sql);
           ps.setString(1, maDonHang);
           ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                datHang = new DatHang(rs.getString(1).trim(), rs.getString(2).trim(), rs.getString(3).trim(), rs.getString(4).trim(),rs.getString(5),rs.getDate(6),rs.getInt(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datHang;
    }
    public ArrayList layThietBiDat(String maDonHang) {
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        ArrayList array = new ArrayList();
        String sql = "EXEC SP_LAYCTDATHANG ?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maDonHang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                array.add(chiTietDatHang = new ChiTietDatHang(rs.getString(1).trim(), rs.getString(2).trim(), rs.getInt(3), rs.getString(4).trim()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
    public ArrayList layDonHang(String maDonHang) {
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        ArrayList array = new ArrayList();
        String sql = "EXEC SP_LAYDONHANG ?";
        
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maDonHang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                array.add(hoaDon = new HoaDon(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
    public void thayDoiSoLuongThietBi(String maThietBi,  int soLuong) {
        String sql = "EXEC SP_THAYDOISOLUONGTHIETBI ?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(2, maThietBi);
            ps.setInt(1, soLuong);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void xoaThietBiKhoiDonDat(String maDonHang, String maThietBi) {
        String sql = "EXEC SP_XOATHIETBIKHOIDONHANG ?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maDonHang);
            ps.setString(2, maThietBi);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void xoaDonDatHang(String maDonHang) {
        String sql = "EXEC SP_XOADATHANG ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maDonHang);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void themDonHangMoi(String maDonHang, String tenKH, String sdt, String diaChi,String maNhanVien ,Date ngayDat, int trangThai) {
        String sql = "EXEC SP_THEMDONDATHANGMOI ?,?,?,?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maDonHang);
            ps.setString(2, tenKH);
            ps.setString(3, sdt);
            ps.setString(4, diaChi);
            ps.setString(5, maNhanVien);
            ps.setDate(6, ngayDat);
            ps.setInt(7, trangThai);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void suaDonHang( String tenKH, String sdt, String diaChi, Date ngayDat,String maDonHang) {
        String sql = "EXEC SP_SUADONDATHANG ?,?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maDonHang);
            ps.setString(2, tenKH);
            ps.setString(3, sdt);
            ps.setString(4, diaChi);
            ps.setDate(5, ngayDat);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public boolean ktThietBiDonHang(String maDonHang, String maThietBi) {
        String sql = "EXEC SP_KTTHIETBIDATHANG ?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        boolean ketQua = true;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maDonHang);
            ps.setString(2, maThietBi);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ketQua = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
    public void themThietBiDonHang(String maDonHang, String maThietBi, int soLuong, String thanhTien) {
        String sql = "EXEC SP_THEMTHIETBIDONHANG ?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maDonHang);
            ps.setString(2, maThietBi);
            ps.setInt(3, soLuong);
            ps.setString(4, thanhTien);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void suaThietBiDonHang(String maDonHang, String maThietBi, int soLuong, String thanhTien) {
        String sql = "EXEC SP_SUATHIETBIDONHANG ?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maDonHang);
            ps.setString(2, maThietBi);
            ps.setInt(3, soLuong);
             ps.setString(4, thanhTien);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void chuyenTrangThaiDatHang(String maDonHang){
        String sql = "EXEC SP_CHUYENTRANGTHAIDATHANG ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maDonHang);
            ps.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public ArrayList layHoaDon(String maHoaDon) {

        ArrayList array = new ArrayList();
        String sql = "EXEC SP_LAYHOADON ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                array.add(hoaDon = new HoaDon(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
     public int layMaHoaDon() {
        String sql = "SELECT * FROM PHIEUXUAT";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        int max = 0;
        ArrayList list = new ArrayList();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("MAPX").trim().substring(0, 2).endsWith("HD")) {
                    list.add(rs.getString("MAPX").trim().substring(2, rs.getString("MAPX").trim().length()));
                }
            }
            if (list.size() == 0) {
                return list.size();
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (max < Integer.parseInt(list.get(i).toString())) {
                        max = Integer.parseInt(list.get(i).toString());
                    } else {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return max;
    }
    public void xoaThietBiRaKhoiHoaDon(String maHoaDon ,String maThietBi) {
        String sql = "EXEC SP_XOATHIETBIRAKHOIHOADON ?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maHoaDon);
            ps.setString(2, maThietBi);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public boolean ktThietBiTrongHoaDon(String maHoaDon, String maThietBi) {
        String sql = "EXEC SP_KTTHIETBITRONGHOADON ?,? ";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        boolean ketQua = true;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maHoaDon);
            ps.setString(2, maThietBi);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ketQua = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
    public void themThietBiVaoHoaDon(String maHoaDon, String maThietBi,int soLuong, String thanhTien) {
        String sql = "EXEC SP_THEMTHIETBIVAOHOADON ?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maHoaDon);
            ps.setString(2, maThietBi);
            ps.setInt(3, soLuong);
            ps.setString(4, thanhTien);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void suaThietBiHoaDon(int soLuong, String thanhTien,String maHoaDon, String maThietBi) {
        String sql = "SP_SUATHIETBITRONGHOADON ?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setInt(1, soLuong);
            ps.setString(2, thanhTien);
            ps.setString(3, maHoaDon);
            ps.setString(4, maThietBi);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public boolean ktHoaDon(String maHoaDon) {
        String sql = "SELECT * FROM PHIEUXUAT WHERE MAPX = ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        boolean ketQua = true;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maHoaDon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ketQua = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
    public void themHoaDon(String maHoaDon, int trangThai) {
        String sql = "EXEC SP_THEMHOADON ?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maHoaDon);
            ps.setInt(2, trangThai);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }  
    public void themHoaDon(String maHoaDon,Date ngayBan,String maNhanVien,String gioBan,String tienNhan ,int trangThai) {
        String sql = "EXEC SP_THEMTHONGTINHOADON ?,?,?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maHoaDon);
            ps.setDate(2, ngayBan);
            ps.setString(3, maNhanVien);
            ps.setString(4, gioBan);
            ps.setString(5, tienNhan);
            ps.setInt(6, trangThai);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }  
    public ArrayList layThongKe() {

        ArrayList arry = new ArrayList();
        String sql = "EXEC SP_THONGKETDATHANG";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                arry.add(thongKe = new ThongKe(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arry;
    }
    public ArrayList layThongKeHoaDon() {

        ArrayList arry = new ArrayList();
        String sql = "EXEC SP_THONGKEHOADON";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                arry.add(thongKe = new ThongKe(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arry;
    }
     public ArrayList layThongKe(Date date1, Date date2) {

        ArrayList arry = new ArrayList();
        String sql = "EXEC SP_THONGKEDATHANGTHEONGAY ?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setDate(1, date1);
            ps.setDate(2, date2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                arry.add(thongKe = new ThongKe(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arry;
    }
      public ArrayList layThongKeHoaDon(Date date1, Date date2) {

        ArrayList arry = new ArrayList();
        String sql = "EXEC SP_THONGKEHOADONTHEONGAY ?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setDate(1, date1);
            ps.setDate(2, date2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                arry.add(thongKe = new ThongKe(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arry;
    }
      public ArrayList layNhapHang() {
        ArrayList array = new ArrayList();
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        String sql = "SELECT * FROM PHIEUNHAP WHERE TRANGTHAI = 0";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                array.add(nhapHang = new NhapHang(rs.getString(1).trim(), rs.getDate(2), rs.getString(3).trim(), rs.getInt(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
       public boolean ktmaPhieuNhap() {
        String sql = "SELECT * FROM PHIEUNHAP";
        boolean ketQua = false;
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ketQua = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
        public int layMaPhieuNhap() {
        int max = 0;
        ArrayList list = new ArrayList();
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        if (ktmaPhieuNhap()) {
            String sql = "SELECT * FROM PHIEUNHAP";
            try {
                PreparedStatement ps = ketNoi.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString("MAPN").trim().substring(2, rs.getString("MAPN").trim().length()));
                }
                if (list.size() == 0) {
                    return list.size();
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        if (max < Integer.parseInt(list.get(i).toString())) {
                            max = Integer.parseInt(list.get(i).toString());
                        } else {
                            continue;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        return max;
    }
        public NhapHang layThongTinNhapHang(String maPhieuNhap) {
        String sql = "EXEC SP_LAYTHONGTINNAHPHANG ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
           PreparedStatement ps = ketNoi.prepareStatement(sql);
           ps.setString(1, maPhieuNhap);
           ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nhapHang = new NhapHang(rs.getString(1).trim(), rs.getDate(2), rs.getString(3).trim(), rs.getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nhapHang;
    }
        public ArrayList layThietBiNhap(String maPhieuNhap) {
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        ArrayList array = new ArrayList();
        String sql = "EXEC SP_LAYCTNHAPHANG ?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maPhieuNhap);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                array.add(chiTietNhapHang = new ChiTietNhapHang(rs.getString(1).trim(), rs.getString(2).trim(), rs.getInt(3), rs.getString(4).trim()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
        public ArrayList layPhieuNhap(String maPhieuNhap) {
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        ArrayList array = new ArrayList();
        String sql = "EXEC SP_LAYPHIEUNHAP ?";
        
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maPhieuNhap);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                array.add(hoaDon = new HoaDon(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
    public void xoaThietBiKhoiPhieuNhap(String maPhieuNhap, String maThietBi) {
        String sql = "EXEC SP_XOATHIETBIRAKHOIPHIEUNHAP ?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maPhieuNhap);
            ps.setString(2, maThietBi);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void xoaPhieuNhap(String maPhieuNhap) {
        String sql = "EXEC SP_XOAPHIEUNHAP ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maPhieuNhap);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void themPhieuNhapMoi(String maDonHang ,Date ngayDat , String maNhanVien , int trangThai) {
        String sql = "EXEC SP_THEMPHIEUNHAPMOI ?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maDonHang);
            ps.setString(3, maNhanVien);
            ps.setDate(2, ngayDat);
            ps.setInt(4, trangThai);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void suaPhieuNhap( String maPhieuNhap, Date ngayDat) {
        String sql = "EXEC SP_SUAPHIEUNHAP ?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maPhieuNhap);
            ps.setDate(2, ngayDat);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public boolean ktThietBiNhapHang(String maPhieuNhap, String maThietBi) {
        String sql = "EXEC SP_KTTHIETBINHAPHANG ?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        boolean ketQua = true;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maPhieuNhap);
            ps.setString(2, maThietBi);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ketQua = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
    public void themThietBiNhapHang(String maPhieuNhap, String maThietBi, int soLuong, String thanhTien) {
        String sql = "EXEC SP_THEMTHIETBINHAPHANG ?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maPhieuNhap);
            ps.setString(2, maThietBi);
            ps.setInt(3, soLuong);
            ps.setString(4, thanhTien);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void suaThietBiNhapHang(String maPhieuNhap, String maThietBi, int soLuong, String thanhTien) {
        String sql = "EXEC SP_SUATHIETBINHAPHANG ?,?,?,?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maPhieuNhap);
            ps.setString(2, maThietBi);
            ps.setInt(3, soLuong);
             ps.setString(4, thanhTien);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void chuyenTrangThaiNhapHang(String maPhieuNhap){
        String sql = "EXEC SP_CHUYENTRANGTHAINHAPHANG ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maPhieuNhap);
            ps.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public boolean ktNhanVienTrongDonDatHang(String maNhanVien){
        String sql ="EXEC SP_KTNHANVIENTRONGDONDATHANG ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        boolean ketQua = true;
        try{
        PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ketQua = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
    public boolean ktNhanVienTrongPhieuNhap(String maNhanVien){
        String sql ="EXEC SP_KTNHANVIENTRONGPHIEUNHAP ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        boolean ketQua = true;
        try{
        PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ketQua = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
    public boolean ktNhanVienTrongPhieuXuat(String maNhanVien){
        String sql ="EXEC SP_KTNHANVIENTRONGPHIEUXUAT ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        boolean ketQua = true;
        try{
        PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ketQua = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
    public ArrayList layThongTinQuenMatKhau(String tenDangNhap){
        ArrayList arr = new ArrayList();
        String sql ="EXEC SP_LAYTHONGTINQUENMATKHAU ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, tenDangNhap);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                arr.add(nhanvien = new NhanVien(rs.getString(1), rs.getString(2)));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return arr;
    }
    public int ktXoaChucVu(String maChucVu){
        String sql ="EXEC SP_KTXOACHUCVU ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        int ketQua = 0;
        try{
        PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maChucVu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ketQua = 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
     public void xoaTaiKhoanNVNghiLam(String maNhanVien){
        String sql = "EXEC SP_XOATAIKHOANNVNGHILAM ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        try{
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNhanVien);
            ps.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
      public int ktNhaSanXuatTrongThietBi(String maNSX){
        String sql ="EXEC SP_KTNHASANXUAT ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        int ketQua = 0;
        try{
        PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNSX);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ketQua = 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
       public int ktLoaiTBTrongThietBi(String maLoai){
        String sql ="EXEC SP_KTLOAITHIETBI ?";
        Connection ketNoi = KetNoiCoSoDuLieu.layKetNoi();
        int ketQua = 0;
        try{
        PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maLoai);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ketQua = 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
}
