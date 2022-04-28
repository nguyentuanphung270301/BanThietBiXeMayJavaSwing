/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.ImageData;
import DAO.ShareData;
import DAO.KetNoiCoSoDuLieu;
import controller.XuLy;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.ChiTietNhapHang;
import model.HoaDon;
import model.LoaiThietBi;
import model.NhaSanXuat;
import model.NhapHang;
import model.TaiKhoan;
import model.ThietBi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author phucn
 */
public class FormQuanLyThietBi extends javax.swing.JFrame {

    private XuLy xuLy;
    private boolean Them = false , Sua = false;
    private boolean themDH = false, suaDonHang = false;
    private boolean themThietBi = false, suaThietBi = false;
    private boolean KH = false, TB = false;
    private byte[] hinhThietBi;
    private TaiKhoan taiKhoan;
    private ThietBi thietBi;
    private NhapHang nhapHang;
 
    public FormQuanLyThietBi(TaiKhoan tk) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Quản lý thiết bị");
        
        xuLy = new XuLy();
        thietBi = new ThietBi();
        taiKhoan = new TaiKhoan();
       
        Disable();
        DisableLoaiThietBi();
        DisableNSX();
        Disabled();
        
        taiMaLoaiThietBi();
        taiMaNhaSanXuat();
        taiBangThietBi();
        taiBangLoaiThietBi();
        taiBangNhaSanXuat();
        taiPhieuNhap();
        ktPhieuNhap();
   }
    
     public void Enable(){
        txtMaSP.setEnabled(true);
        txtTenSP.setEnabled(true);
        txtThoiGianBaoHanh.setEnabled(true);
        txtGia.setEnabled(true);
        btnCapNhatAnh.setEnabled(true);
        cbxLoaiSP.setEnabled(true);
        cbxNhaSanXuat.setEnabled(true);
        cbxTG.setEnabled(true);
    }
     
    public void Disable(){
        txtMaSP.setEnabled(false);
        txtTenSP.setEnabled(false);
        txtThoiGianBaoHanh.setEnabled(false);
        txtGia.setEnabled(false);
        btnCapNhatAnh.setEnabled(false);
        cbxLoaiSP.setEnabled(false);
        cbxNhaSanXuat.setEnabled(false);
        cbxTG.setEnabled(false);
    }
    
    public void EnableLoaiThietBi(){
        txtMaLoai.setEnabled(true);
        txtLoaiThietBi.setEnabled(true);
    }
    
    public void DisableLoaiThietBi(){
        txtMaLoai.setEnabled(false);
        txtLoaiThietBi.setEnabled(false);
    }
    
    public void EnableNSX(){
        txtMaNSX.setEnabled(true);
        txtNhaSanXuat.setEnabled(true);
        txtDiaChi.setEnabled(true);
        txtSoDienThoai.setEnabled(true);
        txtEmail.setEnabled(true);
    }
    public void DisableNSX(){
        txtMaNSX.setEnabled(false);
        txtNhaSanXuat.setEnabled(false);
        txtDiaChi.setEnabled(false);
        txtSoDienThoai.setEnabled(false);
        txtEmail.setEnabled(false);
    }
     private void EnabledPhieuNhap() {
        txtNgayNhap.setEnabled(true);
        lbTrangThai.setText("Trạng Thái!");
    }
     private void EnabledNhapTbietBi() {
        cbxThietBiNhap.setEnabled(true);
        cbxLoaiThietBiNhap.setEnabled(true);
        txtGiaThietBiNhap.setEnabled(true);
        txtDonGia.setEnabled(true);
        lbTrangThai.setText("Trạng Thái!");
    }
     private void Disabled() {
        cbxLoaiThietBiNhap.setEnabled(false);
        cbxThietBiNhap.setEnabled(false);
        txtSoLuongNhap.setEnabled(false);
        txtDonGia.setEnabled(false);
        txtNgayNhap.setEnabled(false);
        txtGiaThietBiNhap.setEnabled(false);
    }
    private void refreshThietBi() {
        txtSoLuongNhap.setText("");
        txtDonGia.setText("");
        txtGiaThietBiNhap.setText("");
        lbTongTienNhapHang.setText("0 VNĐ");
    }
       private void ktPhieuNhap() {
        if (cbxMaPhieuNhap.getItemCount() == 0) {
            cbxMaPhieuNhap.setEnabled(false);
        } else {
            cbxMaPhieuNhap.setEnabled(true);
        }
    }
       private void RefreshPhieuNhap() {
        ktPhieuNhap();
        String[] arr = {"Mã Thiết Bị", "Số Lượng", "Ngày Nhập", "Thành Tiền"};
        DefaultTableModel modle = new DefaultTableModel(arr, 0);
        tablePhieuNhap.setModel(modle);

        themDH = false;
        suaDonHang = false;
        themThietBi = false;
        suaThietBi = false;
        txtSoLuongNhap.setText("");
        txtDonGia.setText("");
        txtGiaThietBiNhap.setText("");
        lbTongTienNhapHang.setText("0 VNĐ");
        ((JTextField) txtNgayNhap.getDateEditor().getUiComponent()).setText("");
        cbxLoaiThietBiNhap.removeAllItems();
        cbxThietBiNhap.removeAllItems();
        btnXoaThietBi.setEnabled(false);
        btnSuaThietBi.setEnabled(false);
        btnLuuPhieuNhap.setEnabled(false);
        btnSuaPhieuNhap.setEnabled(false);
        btnXoaPhieuNhap.setEnabled(false);
        btnThemThietBi.setEnabled(false);
        btnThemPhieuNhap.setEnabled(true);
        taiPhieuNhap();
        Disabled();
    }
    public void Refresh(){
        cbxLoaiSP.removeAllItems();
        cbxNhaSanXuat.removeAllItems();
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtMaLoai.setText("");
        txtGia.setText("");
        txtThoiGianBaoHanh.setText("");
        
        txtMaLoai.setText("");
        txtLoaiThietBi.setText("");
        
        txtMaNSX.setText("");
        txtNhaSanXuat.setText("");
        txtDiaChi.setText("");
        txtSoDienThoai.setText("");
        txtEmail.setText("");
        
        btnThem.setEnabled(true);
        btnThemLoaiThietBi.setEnabled(true);
        btnThemNhaSanXuat.setEnabled(true);
        
        btnSua.setEnabled(false);
        btnSuaLoaiThietBi.setEnabled(false);
        btnSuaNhaSanXuat.setEnabled(false);
        
        btnXoa.setEnabled(false);
        btnXoaLoaiThietBi.setEnabled(false);
        btnXoaNhaSanXuat.setEnabled(false);
        
        btnLuu.setEnabled(false);
        btnLuuLoaiThietBi.setEnabled(false);
        btnLuuNhaSanXuat.setEnabled(false);
        
        Them = false;
        Sua = false;
        labelHinhAnh.setIcon(null);
        Disable();
        DisableLoaiThietBi();
        DisableNSX();
        taiMaLoaiThietBi();
        taiMaNhaSanXuat();
    }
    
    public boolean ktRong(){
        boolean kq = true;
        if(txtMaSP.getText().equals("")){
            JOptionPane.showMessageDialog(this, "bạn chưa nhập mã thiết bị !","Thông báo", 2);
            return false;
        }
        if(txtTenSP.getText().equals("")){
            JOptionPane.showMessageDialog(this, "bạn chưa nhập tên thiết bị !","Thông báo", 2);
            return false;
        }
        if(txtThoiGianBaoHanh.getText().equals("")){
            JOptionPane.showMessageDialog(this, "bạn chưa nhập thời gian bảo hành !","Thông báo", 2);
            return false;
        }
        if(txtGia.getText().equals("")){
            JOptionPane.showMessageDialog(this, "bạn chưa nhập giá thiết bị !","Thông báo", 2);
            return false;
        }
        return kq;
    }
    public boolean ktRongLoaiThietBi(){
        boolean kq = true;
        if(txtMaLoai.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã loại !","Thông báo",2);
            return false;
        }
        if(txtLoaiThietBi.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập loại thiết bị !","Thông báo",2);
            return false;
        }
        return kq;
    }
    public boolean ktRongNhaSanXuat(){
        boolean kq = true;
        if(txtMaNSX.getText().equals("")){
           JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã nhà sản xuất !","Thông báo",2); 
           return false;
        }
        if(txtNhaSanXuat.getText().equals("")){
           JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên nhà sản xuất !","Thông báo",2); 
           return false;
        }
        if(txtDiaChi.getText().equals("")){
           JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ nhà sản xuất !","Thông báo",2); 
           return false;
        }
        if(txtSoDienThoai.getText().equals("")){
           JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại nhà sản xuất !","Thông báo",2); 
           return false;
        }
        if(txtEmail.getText().equals("")){
           JOptionPane.showMessageDialog(this, "Bạn chưa nhập email nhà sản xuất !","Thông báo",2); 
           return false;
        }
        return kq;
    }
    private void taiMaLoaiThietBi(){
        ArrayList arr = xuLy.layLoaiThietBi();
        for(int i =0 ; i<arr.size();i++){
            this.cbxLoaiSP.addItem(((LoaiThietBi) arr.get(i)).getMaLoai()+" ("+((LoaiThietBi) arr.get(i)).getTenLoai()+")");
        }
    }
    private void taiBangLoaiThietBi(){
        tableLoaiSanPham.removeAll();
        String[] arr = {"Mã loại thiết bị","Tên loại thiết bị"};
        DefaultTableModel model = new DefaultTableModel(arr,0);
        
        ArrayList array = xuLy.layLoaiThietBi();
        for(int i =0 ; i<array.size();i++){
            Vector vt = new Vector();
            vt.add(((LoaiThietBi) array.get(i)).getMaLoai());
            vt.add(((LoaiThietBi) array.get(i)).getTenLoai());
            model.addRow(vt);
        }
        tableLoaiSanPham.setModel(model);
    }
    private void taiBangNhaSanXuat(){
        tableNhaSanXuat.removeAll();
        String[] arr = {"Mã nhà sản xuất","Tên nhà sản xuất","Địa chỉ","Số điện thoại","Email"};
        DefaultTableModel model = new DefaultTableModel(arr,0);
        
        ArrayList array = xuLy.layNhaSanXuat();
        for(int i =0; i<array.size();i++){
            Vector vt = new Vector();
            vt.add(((NhaSanXuat) array.get(i)).getMaNSX());
            vt.add(((NhaSanXuat) array.get(i)).getTenNSX());
            vt.add(((NhaSanXuat) array.get(i)).getDiaChi());
            vt.add(((NhaSanXuat) array.get(i)).getSDT());
            vt.add(((NhaSanXuat) array.get(i)).getEmail());
            model.addRow(vt);
        }
        tableNhaSanXuat.setModel(model);
    }
    public void taiLoaiThietBi(){
        LoaiThietBi vt = xuLy.layLoaiThietBi(cbxLoaiSP.getSelectedItem().toString());
    }
    private void taiMaNhaSanXuat() {
        ArrayList arry = xuLy.layNhaSanXuat();
        for (int i = 0; i < arry.size(); i++) {
            this.cbxNhaSanXuat.addItem(((NhaSanXuat) arry.get(i)).getMaNSX()+ " (" + ((NhaSanXuat) arry.get(i)).getTenNSX()+ ")");
        }
    }
    private void taiNhaSanXuat() {
        NhaSanXuat nhaSanXuat = xuLy.layNhaSanXuat(cbxNhaSanXuat.getSelectedItem().toString());
    }
      
    public char catTen(String ten){
        String []arry=ten.split("\\s");
        char []chuoi=arry[0].toCharArray();
        return chuoi[0];
    }
      public boolean soSanhTen(ThietBi thietBi_1,ThietBi thietBi_2){
        if(catTen(thietBi_1.getTenThietBi())<catTen(thietBi_2.getTenThietBi())){
            return true;
        }
        return false;
    }
       public void bubbleSortName(ArrayList arry) {
        for (int i = 0; i <= arry.size() - 2; i++) {
            for (int j = arry.size() - 1; j >= i + 1; j--) {
                if (soSanhTen((ThietBi) arry.get(j), (ThietBi) arry.get(j - 1))) {
                    thietBi = (ThietBi) arry.get(j);
                    arry.set(j, arry.get(j - 1));
                    arry.set(j - 1, thietBi);
                }
            }
        }
    }
    public boolean soSanh(ThietBi thietBi_1, ThietBi thietBi_2) {
        String[] s1 = thietBi_1.getGia().split("\\s");
        String[] s2 = thietBi_2.getGia().split("\\s");
        if (chuyenSangSo(s1[0]) < chuyenSangSo(s2[0])) {
            return true;
        }
        return false;
    }
    public void bubbleSortGia(ArrayList arry) {
        for (int i = 0; i <= arry.size() - 2; i++) {
            for (int j = arry.size() - 1; j >= i + 1; j--) {
                if (soSanh((ThietBi) arry.get(j), (ThietBi) arry.get(j - 1))) {
                    thietBi = (ThietBi) arry.get(j);
                    arry.set(j, arry.get(j - 1));
                    arry.set(j - 1, thietBi);
                }
            }
        }
    }
    private void taiBangThietBi() {
        ArrayList array = xuLy.layThietBi();
        View(array);
    }
    private String cutChar(String arry) {
        return arry.replaceAll("\\D+", "");
    }
    private void thoiGian(String s) {
        if (s.equals("Ngày")) {
            cbxTG.setSelectedIndex(0);
        } else if (s.equals("Tháng")) {
            cbxTG.setSelectedIndex(1);
        } else {
            cbxTG.setSelectedIndex(2);
        }
    }
    private void taiLoaiThietBiVaNhaSanXuat() {
        LoaiThietBi loaiThietBi = xuLy.layLoaiThietBi(cbxLoaiSP.getSelectedItem().toString());
        NhaSanXuat nhaSanXuat = xuLy.layNhaSanXuat(cbxNhaSanXuat.getSelectedItem().toString());
    }    
    private void sapXepTen() {
        ArrayList arry = xuLy.layThietBi();
        bubbleSortName(arry);
        View(arry);
    }
    private void View(ArrayList array){
        tableSanPham.removeAll();
        String[] arr = {"Mã Thiết Bị", "Tên Thiết Bị", "Mã Loại", "Mã Nhà Sản Xuất", "Thời Gian Bảo Hành", "Số Lượng", "Giá","Hình Ảnh"};
        DefaultTableModel model = new DefaultTableModel(arr, 0);
        
        for(int i =0 ; i <array.size();i++){
            Vector vt = new Vector();
            vt.add(((ThietBi) array.get(i)).getMaThietBi());
            vt.add(((ThietBi) array.get(i)).getTenThietBi());
            vt.add(((ThietBi) array.get(i)).getMaLoai().trim()+" ("+xuLy.layLoaiThietBi(((ThietBi) array.get(i)).getMaLoai()).getTenLoai().trim()+")");
            vt.add(((ThietBi) array.get(i)).getMaNSX().trim()+" ("+xuLy.layNhaSanXuat(((ThietBi) array.get(i)).getMaNSX()).getTenNSX().trim()+")");
            vt.add(((ThietBi) array.get(i)).getTgBaoHanh());
            vt.add(((ThietBi) array.get(i)).getSoLuong());
            vt.add(xuLyGia((float) chuyenSangSo(((ThietBi) array.get(i)).getGia())));
            vt.add(((ThietBi) array.get(i)).getHinhAnh());
            model.addRow(vt);
        }
        tableSanPham.setModel(model);
    }
    private void sortSGia() {
        ArrayList array = xuLy.layThietBi();
        bubbleSortGia(array);
        View(array);
    }
    private void maTuDong() {
        String[] arry = cbxLoaiSP.getSelectedItem().toString().split("\\s");
        if (xuLy.layMaThietBi(arry[0]) < 10) {
            txtMaSP.setText(arry[0] + "0" + String.valueOf(xuLy.layMaThietBi(arry[0]) + 1));
        } else {
            txtMaSP.setText(arry[0] + String.valueOf(xuLy.layMaThietBi(arry[0]) + 1));
        }
    }
     private float convertedToNumbers(String s) {
        String number = "";
        String[] array = s.replace(",", " ").split("\\s");
        for (String i : array) {
            number = number.concat(i);
        }
        return Float.parseFloat(number);
    }
      public String xuLyGia(float luong){
        
        Locale localeVN = new Locale("vi","VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String vnd = currencyVN.format(luong);
        return vnd;
    }
      private String xuLyGia(String s){
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(chuyenSangSo(s));
    }
       private String xuLyGiaNhap(String s){
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        double gia = chuyenSangSo(s)-(chuyenSangSo(s))*10/100;
        return formatter.format(gia);
    }
       private void timThietBi(String sql) {
        ArrayList array = xuLy.timthietBi(sql);
        View(array);
    }
    private double chuyenSangSo(String s) {
        String number = "";
        String[] array = s.replace(",", " ").split("\\s");
        for (String i : array) {
            number = number.concat(i);
        }
        return Double.parseDouble(number);
    }
    public boolean ktSDT(String s){
        String Phone = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        boolean kt = s.matches(Phone);
        return kt;
    }
    public boolean ktEmail(String mail){
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
     Matcher matcher = pattern.matcher(mail);

        return matcher.matches();
    }
    private void taiMaLoaiThietBiNhap() {
        cbxLoaiThietBiNhap.removeAllItems();
        ArrayList arry = xuLy.layLoaiThietBi();
        for (int i = 0; i < arry.size(); i++) {
            cbxLoaiThietBiNhap.addItem(((LoaiThietBi) arry.get(i)).getMaLoai() + " (" + ((LoaiThietBi) arry.get(i)).getTenLoai() + ")");
        }
    }
    private void taiThietBiNhap() {
        cbxThietBiNhap.removeAllItems();
        String[] test = cbxLoaiThietBiNhap.getSelectedItem().toString().split("\\s");

        ArrayList arry = xuLy.layThietBiTheoMaLoai(test[0]);

        for (int i = 0; i < arry.size(); i++) {
            cbxThietBiNhap.addItem(((ThietBi) arry.get(i)).getMaThietBi()+ " (" + ((ThietBi) arry.get(i)).getTenThietBi()+ ")");
        }
    }
    private void taiThongTinThietBi() {

        String[] test = cbxThietBiNhap.getSelectedItem().toString().split("\\s");

        thietBi = new ThietBi(xuLy.layThietBiTheoMa(test[0]));
        txtGiaThietBiNhap.setText(xuLyGiaNhap((thietBi.getGia())) + " VNĐ");
    }
     private void taiPhieuNhap() {
        cbxMaPhieuNhap.removeAllItems();
        ArrayList array = xuLy.layNhapHang();
        for (int i = 0; i < array.size(); i++) {
            cbxMaPhieuNhap.addItem(((NhapHang) array.get(i)).getMaPhieuNhap());
        }
    }
     private void phieuNhapMoi() {
        if (xuLy.layMaPhieuNhap()< 10) {
            cbxMaPhieuNhap.addItem("PN" + "0" + String.valueOf(xuLy.layMaPhieuNhap()+ 1));
            cbxMaPhieuNhap.setSelectedItem("PN" + "0" + String.valueOf(xuLy.layMaPhieuNhap()+ 1));
        } else {
            cbxMaPhieuNhap.addItem("PN" + String.valueOf(xuLy.layMaPhieuNhap()+ 1));
            cbxMaPhieuNhap.setSelectedItem("PN" + String.valueOf(xuLy.layMaPhieuNhap()+ 1));
        }
    }
      private void taiThongTinNhapHang() {
        nhapHang = new NhapHang(xuLy.layThongTinNhapHang(cbxMaPhieuNhap.getSelectedItem().toString()));
        txtNgayNhap.setDate(nhapHang.getNgayNhap());
    }
      private void taiThietBiNhapHang() {
        tablePhieuNhap.removeAll();
        String[] arr = {"Mã Thiết Bị", "Số Lượng", "Thành Tiền"};
        DefaultTableModel model = new DefaultTableModel(arr, 0);

        ArrayList array = xuLy.layThietBiNhap(cbxMaPhieuNhap.getSelectedItem().toString());
        for (int i = 0; i < array.size(); i++) {
            Vector vector = new Vector();
            vector.add(((ChiTietNhapHang) array.get(i)).getMaThietBi()+ " (" + xuLy.layThietBiTheoMa(((ChiTietNhapHang) array.get(i)).getMaThietBi()).getTenThietBi().trim() + ")");
            vector.add(((ChiTietNhapHang) array.get(i)).getSoLuong());
            vector.add(xuLyGia(((ChiTietNhapHang) array.get(i)).getThanhTien())+ " VNĐ");
            model.addRow(vector);
        }
        tablePhieuNhap.setModel(model);
    }
      private void thanhToan() {
        lbTongTienNhapHang.setText("0 VNĐ");
        ArrayList array = xuLy.layThietBiNhap(cbxMaPhieuNhap.getSelectedItem().toString());
        for (int i = 0; i < array.size(); i++) {
            String[] s1 = ((ChiTietNhapHang) array.get(i)).getThanhTien().trim().split("\\s");
            String[] s2 = lbTongTienNhapHang.getText().split("\\s");
            double tongTien = chuyenSangSo(s1[0]) +  chuyenSangSo(s2[0]);
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            lbTongTienNhapHang.setText(formatter.format(tongTien) + " VNĐ");
        }
      }
      private void ktNhapHang() {
        ArrayList array = xuLy.layThietBiNhap(cbxMaPhieuNhap.getSelectedItem().toString());
        if (array.size() != 0) {
            btnInPhieuNhap.setEnabled(true);
        } else {
            btnInPhieuNhap.setEnabled(false);
        }
    }
    private void consistency(String maPhieuNhap) {

        ArrayList arrayListHoaDon = xuLy.layPhieuNhap(maPhieuNhap);
        for (int i = 0; i < arrayListHoaDon.size(); i++) {
            thietBi = xuLy.layThietBiTheoMa(((HoaDon) arrayListHoaDon.get(i)).getMaThietBi().trim());
            //String []array=thietBi.getGia().split("\\s");
            xuLy.thayDoiSoLuongThietBi(thietBi.getMaThietBi(), thietBi.getSoLuong() + ((HoaDon) arrayListHoaDon.get(i)).getSoLuong());
        }
    }
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnTrangChu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPaneQuanLiThietBi = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cbxLoaiSP = new javax.swing.JComboBox<>();
        cbxNhaSanXuat = new javax.swing.JComboBox<>();
        txtThoiGianBaoHanh = new javax.swing.JTextField();
        btnCapNhatAnh = new javax.swing.JButton();
        labelHinhAnh = new javax.swing.JLabel();
        cbxTG = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnInDanhSach = new javax.swing.JButton();
        btnSapXep = new javax.swing.JButton();
        radioGiaThietBi = new javax.swing.JRadioButton();
        radioTenThietBi = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSanPham = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtMaLoai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtLoaiThietBi = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnLamMoi2 = new javax.swing.JButton();
        btnThemLoaiThietBi = new javax.swing.JButton();
        btnSuaLoaiThietBi = new javax.swing.JButton();
        btnXoaLoaiThietBi = new javax.swing.JButton();
        btnLuuLoaiThietBi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableLoaiSanPham = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        txtMaNSX = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtNhaSanXuat = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnLamMoi1 = new javax.swing.JButton();
        btnThemNhaSanXuat = new javax.swing.JButton();
        btnSuaNhaSanXuat = new javax.swing.JButton();
        btnXoaNhaSanXuat = new javax.swing.JButton();
        btnLuuNhaSanXuat = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableNhaSanXuat = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        txtDonGia = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cbxThietBiNhap = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        txtSoLuongNhap = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        cbxLoaiThietBiNhap = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        txtGiaThietBiNhap = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        btnThemPhieuNhap = new javax.swing.JButton();
        btnLamMoiPhieuNhap = new javax.swing.JButton();
        btnSuaPhieuNhap = new javax.swing.JButton();
        btnXoaPhieuNhap = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        btnThemThietBi = new javax.swing.JButton();
        btnSuaThietBi = new javax.swing.JButton();
        btnXoaThietBi = new javax.swing.JButton();
        btnLuuPhieuNhap = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablePhieuNhap = new javax.swing.JTable();
        lbTrangThai = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cbxMaPhieuNhap = new javax.swing.JComboBox<>();
        txtNgayNhap = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbTongTienNhapHang = new javax.swing.JLabel();
        btnInPhieuNhap = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnTrangChu.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow-back-icon.png"))); // NOI18N
        btnTrangChu.setText("Trang Chủ");
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" QUẢN LÝ THIẾT BỊ");

        jTabbedPaneQuanLiThietBi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Mã thiết bị:");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 70, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Tên thiết bị:");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 80, -1));

        txtMaSP.setEnabled(false);
        jPanel6.add(txtMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 130, 30));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Nhà sản xuất:");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 90, -1));

        txtTenSP.setEnabled(false);
        jPanel6.add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, 130, 30));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Thời gian bảo hành:");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 40, 130, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Giá:");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 120, 30, -1));

        txtGia.setEnabled(false);
        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });
        txtGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiaKeyReleased(evt);
            }
        });
        jPanel6.add(txtGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 110, 130, 30));

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("Loại thiết bị:");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 116, 80, 20));

        cbxLoaiSP.setEnabled(false);
        cbxLoaiSP.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbxLoaiSPPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel6.add(cbxLoaiSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 150, 30));

        cbxNhaSanXuat.setEnabled(false);
        jPanel6.add(cbxNhaSanXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 130, 30));

        txtThoiGianBaoHanh.setEnabled(false);
        txtThoiGianBaoHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThoiGianBaoHanhActionPerformed(evt);
            }
        });
        txtThoiGianBaoHanh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtThoiGianBaoHanhKeyReleased(evt);
            }
        });
        jPanel6.add(txtThoiGianBaoHanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 30, 90, 30));

        btnCapNhatAnh.setText("Cập nhật ảnh");
        btnCapNhatAnh.setEnabled(false);
        btnCapNhatAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatAnhActionPerformed(evt);
            }
        });
        jPanel6.add(btnCapNhatAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 150, 30));
        jPanel6.add(labelHinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 13, 110, 144));

        cbxTG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày", "Tháng", "Năm" }));
        cbxTG.setEnabled(false);
        jPanel6.add(cbxTG, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 30, -1, 30));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 13, 1300, 170));

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh-icon.png"))); // NOI18N
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/math-add-icon.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pencil-icon.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setEnabled(false);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Recycle-Bin-icon.png"))); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.setEnabled(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save-icon.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setEnabled(false);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnInDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/printer-icon.png"))); // NOI18N
        btnInDanhSach.setText("In Danh Sách");
        btnInDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInDanhSachActionPerformed(evt);
            }
        });

        btnSapXep.setText("Sắp xếp");
        btnSapXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapXepActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioGiaThietBi);
        radioGiaThietBi.setText("Giá thiết bị");

        buttonGroup1.add(radioTenThietBi);
        radioTenThietBi.setText("Tên thiết bị");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addGap(36, 36, 36)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(radioGiaThietBi)
                .addGap(18, 18, 18)
                .addComponent(radioTenThietBi)
                .addGap(49, 49, 49)
                .addComponent(btnInDanhSach)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoi)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnInDanhSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(radioGiaThietBi)
                        .addComponent(radioTenThietBi)))
                .addContainerGap())
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 1320, -1));

        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã thiết bị", "Tên thiết bị", "Loại thiết bị", "Nhà sản xuất", "Thời gian bảo hành", "Số lượng ", "Giá"
            }
        ));
        tableSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSanPham);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 1310, 240));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel3.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 280, 30));

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });
        jPanel3.add(btnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 110, 30));

        jTabbedPaneQuanLiThietBi.addTab("Thông tin thiết bị", jPanel3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaLoai.setEnabled(false);
        jPanel4.add(txtMaLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 170, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Mã loại");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, -1, 30));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Loại thiết bị");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, 90, 30));

        txtLoaiThietBi.setEnabled(false);
        jPanel4.add(txtLoaiThietBi, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 60, 190, 30));

        btnLamMoi2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh-icon.png"))); // NOI18N
        btnLamMoi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi2ActionPerformed(evt);
            }
        });

        btnThemLoaiThietBi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/math-add-icon.png"))); // NOI18N
        btnThemLoaiThietBi.setText("Thêm");
        btnThemLoaiThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiThietBiActionPerformed(evt);
            }
        });

        btnSuaLoaiThietBi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pencil-icon.png"))); // NOI18N
        btnSuaLoaiThietBi.setText("Sửa");
        btnSuaLoaiThietBi.setEnabled(false);
        btnSuaLoaiThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLoaiThietBiActionPerformed(evt);
            }
        });

        btnXoaLoaiThietBi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Recycle-Bin-icon.png"))); // NOI18N
        btnXoaLoaiThietBi.setText("Xoá");
        btnXoaLoaiThietBi.setEnabled(false);
        btnXoaLoaiThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLoaiThietBiActionPerformed(evt);
            }
        });

        btnLuuLoaiThietBi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save-icon.png"))); // NOI18N
        btnLuuLoaiThietBi.setText("Lưu");
        btnLuuLoaiThietBi.setEnabled(false);
        btnLuuLoaiThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuLoaiThietBiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnLamMoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addComponent(btnThemLoaiThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(btnSuaLoaiThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(btnXoaLoaiThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnLuuLoaiThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoi2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLuuLoaiThietBi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaLoaiThietBi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSuaLoaiThietBi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemLoaiThietBi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 144, 1320, -1));

        tableLoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLoaiSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableLoaiSanPham);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 254, 1310, 300));

        jTabbedPaneQuanLiThietBi.addTab("Cập nhật loại thiết bị", jPanel4);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Mã NSX");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Số điện thoại");

        txtSoDienThoai.setEnabled(false);
        txtSoDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoDienThoaiKeyReleased(evt);
            }
        });

        txtMaNSX.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Email");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Nhà sản xuất");

        txtEmail.setEnabled(false);

        txtNhaSanXuat.setEnabled(false);
        txtNhaSanXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNhaSanXuatActionPerformed(evt);
            }
        });

        txtDiaChi.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Địa chỉ");

        btnLamMoi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh-icon.png"))); // NOI18N
        btnLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi1ActionPerformed(evt);
            }
        });

        btnThemNhaSanXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/math-add-icon.png"))); // NOI18N
        btnThemNhaSanXuat.setText("Thêm");
        btnThemNhaSanXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhaSanXuatActionPerformed(evt);
            }
        });

        btnSuaNhaSanXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pencil-icon.png"))); // NOI18N
        btnSuaNhaSanXuat.setText("Sửa");
        btnSuaNhaSanXuat.setEnabled(false);
        btnSuaNhaSanXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNhaSanXuatActionPerformed(evt);
            }
        });

        btnXoaNhaSanXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Recycle-Bin-icon.png"))); // NOI18N
        btnXoaNhaSanXuat.setText("Xoá");
        btnXoaNhaSanXuat.setEnabled(false);
        btnXoaNhaSanXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNhaSanXuatActionPerformed(evt);
            }
        });

        btnLuuNhaSanXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save-icon.png"))); // NOI18N
        btnLuuNhaSanXuat.setText("Lưu");
        btnLuuNhaSanXuat.setEnabled(false);
        btnLuuNhaSanXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuNhaSanXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnLamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(btnThemNhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                .addComponent(btnSuaNhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(btnXoaNhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(btnLuuNhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLuuNhaSanXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaNhaSanXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSuaNhaSanXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemNhaSanXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tableNhaSanXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableNhaSanXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhaSanXuatMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableNhaSanXuat);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaNSX)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(42, 42, 42)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtNhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(jLabel9)
                        .addGap(40, 40, 40)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(85, 85, 85))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtNhaSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jTabbedPaneQuanLiThietBi.addTab("Cập nhật nhà sản xuất", jPanel5);

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtDonGia.setEditable(false);

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setText("Thiết Bị:");

        cbxThietBiNhap.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbxThietBiNhapPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Số lượng nhập:");

        txtSoLuongNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongNhapKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("Đơn giá:");

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setText("Loại Thiết Bị:");

        cbxLoaiThietBiNhap.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbxLoaiThietBiNhapPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel21.setText("Giá:");

        txtGiaThietBiNhap.setEditable(false);
        txtGiaThietBiNhap.setEnabled(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel15)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxLoaiThietBiNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(txtSoLuongNhap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxThietBiNhap, 0, 191, Short.MAX_VALUE)
                    .addComponent(txtGiaThietBiNhap))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbxLoaiThietBiNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxThietBiNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(25, 25, 25)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtSoLuongNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(txtGiaThietBiNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnThemPhieuNhap.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnThemPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/New.png"))); // NOI18N
        btnThemPhieuNhap.setText(" Phiếu Nhập");
        btnThemPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPhieuNhapActionPerformed(evt);
            }
        });

        btnLamMoiPhieuNhap.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLamMoiPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh-icon.png"))); // NOI18N

        btnSuaPhieuNhap.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSuaPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pencil-icon.png"))); // NOI18N
        btnSuaPhieuNhap.setText("Phiếu nhập");
        btnSuaPhieuNhap.setEnabled(false);
        btnSuaPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaPhieuNhapActionPerformed(evt);
            }
        });

        btnXoaPhieuNhap.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnXoaPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Recycle-Bin-icon.png"))); // NOI18N
        btnXoaPhieuNhap.setText("Phiếu nhập");
        btnXoaPhieuNhap.setEnabled(false);
        btnXoaPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaPhieuNhapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSuaPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(btnLamMoiPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoiPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoaPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnThemThietBi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnThemThietBi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/New.png"))); // NOI18N
        btnThemThietBi.setText("Thiết Bị");
        btnThemThietBi.setEnabled(false);
        btnThemThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThietBiActionPerformed(evt);
            }
        });

        btnSuaThietBi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSuaThietBi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pencil-icon.png"))); // NOI18N
        btnSuaThietBi.setText("Thiết Bị");
        btnSuaThietBi.setEnabled(false);
        btnSuaThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThietBiActionPerformed(evt);
            }
        });

        btnXoaThietBi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnXoaThietBi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Recycle-Bin-icon.png"))); // NOI18N
        btnXoaThietBi.setText("Thiết Bị");
        btnXoaThietBi.setEnabled(false);
        btnXoaThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThietBiActionPerformed(evt);
            }
        });

        btnLuuPhieuNhap.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLuuPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save-icon.png"))); // NOI18N
        btnLuuPhieuNhap.setText("Lưu");
        btnLuuPhieuNhap.setEnabled(false);
        btnLuuPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuPhieuNhapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThemThietBi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSuaThietBi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoaThietBi, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(btnLuuPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaThietBi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemThietBi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSuaThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tablePhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablePhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablePhieuNhap);

        lbTrangThai.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbTrangThai.setForeground(new java.awt.Color(255, 51, 51));
        lbTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTrangThai.setText("Trạng Thái !");

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Mã Phiếu Nhập:");

        cbxMaPhieuNhap.setEnabled(false);
        cbxMaPhieuNhap.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbxMaPhieuNhapPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txtNgayNhap.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Ngày Nhập:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(cbxMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setText("Tổng Tiền Nhập Hàng:");

        lbTongTienNhapHang.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbTongTienNhapHang.setText("0 VNĐ");

        btnInPhieuNhap.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnInPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/printer-icon.png"))); // NOI18N
        btnInPhieuNhap.setText("In phiếu nhập");
        btnInPhieuNhap.setEnabled(false);
        btnInPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInPhieuNhapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 315, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTongTienNhapHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnInPhieuNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addComponent(btnInPhieuNhap))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTrangThai)
                            .addComponent(lbTongTienNhapHang)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneQuanLiThietBi.addTab("Nhập hàng", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPaneQuanLiThietBi)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrangChu))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPaneQuanLiThietBi)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        FormTrangChu_NhanVien nhanVien = new FormTrangChu_NhanVien(taiKhoan);
        nhanVien.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi1ActionPerformed
        Refresh();
    }//GEN-LAST:event_btnLamMoi1ActionPerformed

    private void txtNhaSanXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNhaSanXuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhaSanXuatActionPerformed

    private void btnXoaLoaiThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLoaiThietBiActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn loại thiết bị hay không?", "Thông Báo", 2);
        if (Click == JOptionPane.YES_OPTION) {
            xuLy.xoaLoaiThietBi(txtMaLoai.getText());
            Refresh();
            taiBangLoaiThietBi();
            JOptionPane.showMessageDialog(this, "Xoá loại thiết bị thành công !","Thông báo",1);
        }
    }//GEN-LAST:event_btnXoaLoaiThietBiActionPerformed

    private void btnLamMoi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi2ActionPerformed
        Refresh();
    }//GEN-LAST:event_btnLamMoi2ActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        Refresh();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void txtThoiGianBaoHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThoiGianBaoHanhActionPerformed
        txtThoiGianBaoHanh.setText(cutChar(txtThoiGianBaoHanh.getText()));
    }//GEN-LAST:event_txtThoiGianBaoHanhActionPerformed

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        Refresh();
        Them = true;
        Enable();
        btnThem.setEnabled(false);
        btnLuu.setEnabled(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        Them = false;
        Sua = true;
        Enable();
        cbxLoaiSP.removeAllItems();
        cbxNhaSanXuat.removeAllItems();

        taiMaNhaSanXuat();
        taiMaLoaiThietBi();

        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnThem.setEnabled(false);
        btnLuu.setEnabled(true);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (ktRong()) {
            String[] array_Loai = cbxLoaiSP.getSelectedItem().toString().split("\\s");
            String[] array_NSX = cbxNhaSanXuat.getSelectedItem().toString().split("\\s");
            if (Them == true) {
                if (xuLy.ktThemThietBi(txtMaSP.getText())) {
                    xuLy.themThietBi(txtMaSP.getText(), txtTenSP.getText(), array_Loai[0], array_NSX[0], txtThoiGianBaoHanh.getText() + " " + cbxTG.getSelectedItem().toString(), txtGia.getText(), hinhThietBi);
                    Disable();
                    Refresh();
                    taiBangThietBi();
                    JOptionPane.showMessageDialog(this, "Thêm thiết bị thành công !","Thông báo",1);
                    }
                else {
                    JOptionPane.showMessageDialog(this, "Mã thiết bị đã tồn tại !","Thông báo",2);
                }
            }   else if (Sua == true) {
                int Click = tableSanPham.getSelectedRow();
                TableModel model = tableSanPham.getModel();
                
                        xuLy.suaThietBi(txtMaSP.getText(), txtTenSP.getText(),array_Loai[0], array_NSX[0], txtThoiGianBaoHanh.getText() + " " + cbxTG.getSelectedItem().toString(), txtGia.getText(),hinhThietBi, model.getValueAt(Click, 0).toString().trim());
                        Disable();
                        Refresh();
                        taiBangThietBi();
                        JOptionPane.showMessageDialog(this, "Lưu thay đổi thành công !","Thông báo",1);
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thiết bị này hay không?", "Thông Báo", 2);
        if (Click == JOptionPane.YES_OPTION) {
            String[] array_Loai = cbxLoaiSP.getSelectedItem().toString().split("\\s");
            String[] array_NSX = cbxNhaSanXuat.getSelectedItem().toString().split("\\s");
            xuLy.xoaThietBi(txtMaSP.getText());
            Refresh();
            taiBangThietBi();
            JOptionPane.showMessageDialog(this, "Xoá thiết bị thành công !");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtGiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaKeyReleased
         DecimalFormat formatter = new DecimalFormat("###,###,###");

        txtGia.setText(cutChar(txtGia.getText()));
        if (txtGia.getText().equals("")) {
            return;
        } else {
            txtGia.setText(formatter.format(convertedToNumbers(txtGia.getText())));
        }
    }//GEN-LAST:event_txtGiaKeyReleased

    private void btnCapNhatAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatAnhActionPerformed
      JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileFilter() { 
            @Override
            public boolean accept(File f) {
               if(f.isDirectory()){
                   return true;
               }
               else{
                   return f.getName().toLowerCase().endsWith(".jpg");
               }
            }
            @Override
            public String getDescription() {
                return "Image File (*.jpg)";
            }
        });
        if(chooser.showOpenDialog(this) == JOptionPane.CANCEL_OPTION){
            return ;
        }
        File file = chooser.getSelectedFile();
        try {
            ImageIcon icon = new ImageIcon(file.getPath());
            Image img = ImageData.resize(icon.getImage(), 140, 160);
            ImageIcon resizedIcon = new ImageIcon(img);
            labelHinhAnh.setIcon(resizedIcon);
            hinhThietBi = ImageData.toByteArray(img, "jpg");
        } catch (Exception e) {           
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCapNhatAnhActionPerformed

    private void btnThemLoaiThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiThietBiActionPerformed
        Refresh();
        Them = true;
        EnableLoaiThietBi();
        btnThemLoaiThietBi.setEnabled(false);
        btnLuuLoaiThietBi.setEnabled(true);
    }//GEN-LAST:event_btnThemLoaiThietBiActionPerformed

    private void btnSuaLoaiThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLoaiThietBiActionPerformed
        Them = false;
        Sua = true;
        EnableLoaiThietBi();

        btnThemLoaiThietBi.setEnabled(false);
        btnXoaLoaiThietBi.setEnabled(false);
        btnSuaLoaiThietBi.setEnabled(false);
        btnLuuLoaiThietBi.setEnabled(true);
    }//GEN-LAST:event_btnSuaLoaiThietBiActionPerformed

    private void btnLuuLoaiThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuLoaiThietBiActionPerformed
        if (ktRongLoaiThietBi()) {
            if (Them == true) {
                if (xuLy.ktLoaiThietBi(txtMaLoai.getText())) {
                    xuLy.themLoaiThietBi(txtMaLoai.getText(), txtLoaiThietBi.getText());
                    taiBangLoaiThietBi();
                    DisableLoaiThietBi();
                    Refresh();
                    JOptionPane.showMessageDialog(this,"Thêm loại thiết bị thành công !","Thông báo",1);
                } else {
                    JOptionPane.showMessageDialog(this,"Mã loại thiết bị đã tồn tại !","Thông báo",1);
                }
            } else if (Sua == true) {
                int Click = tableLoaiSanPham.getSelectedRow();
                TableModel model = tableLoaiSanPham.getModel();

                if (model.getValueAt(Click, 0).toString().equals(txtMaLoai.getText())) {
                    xuLy.suaLoaiThietBi(txtMaLoai.getText(), txtLoaiThietBi.getText(), model.getValueAt(Click, 0).toString());
                    DisableLoaiThietBi();
                    taiBangLoaiThietBi();
                    Refresh();
                    JOptionPane.showMessageDialog(this,"Lưu thay đổi thành công !","Thông báo",1);
                } else if (xuLy.ktLoaiThietBi(txtMaLoai.getText())) {
                    xuLy.suaLoaiThietBi(txtMaLoai.getText(), txtLoaiThietBi.getText(), model.getValueAt(Click, 0).toString());
                    DisableLoaiThietBi();
                    taiBangLoaiThietBi();
                    Refresh();
                    JOptionPane.showMessageDialog(this,"Lưu thay đổi thành công !","Thông báo",1);
                } else {
                    JOptionPane.showMessageDialog(this,"Mã loại thiết bị đã tồn tại !","Thông báo",1); 
                }
            }
        }
    }//GEN-LAST:event_btnLuuLoaiThietBiActionPerformed

    private void tableLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLoaiSanPhamMouseClicked
        int Click = tableLoaiSanPham.getSelectedRow();
        TableModel model = tableLoaiSanPham.getModel();

        txtMaLoai.setText(model.getValueAt(Click, 0).toString());
        txtLoaiThietBi.setText(model.getValueAt(Click, 1).toString());
        btnSuaLoaiThietBi.setEnabled(true);
        btnXoaLoaiThietBi.setEnabled(true);
    }//GEN-LAST:event_tableLoaiSanPhamMouseClicked

    private void tableNhaSanXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNhaSanXuatMouseClicked
        int Click = tableNhaSanXuat.getSelectedRow();
        TableModel model = tableNhaSanXuat.getModel();

        txtMaNSX.setText(model.getValueAt(Click, 0).toString());
        txtNhaSanXuat.setText(model.getValueAt(Click, 1).toString());
        txtDiaChi.setText(model.getValueAt(Click, 2).toString());
        txtSoDienThoai.setText(model.getValueAt(Click, 3).toString());
        txtEmail.setText(model.getValueAt(Click, 4).toString());

        btnSuaNhaSanXuat.setEnabled(true);
        btnXoaNhaSanXuat.setEnabled(true);
    }//GEN-LAST:event_tableNhaSanXuatMouseClicked

    private void btnThemNhaSanXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhaSanXuatActionPerformed
        Refresh();
        Them = true;
        btnThemNhaSanXuat.setEnabled(false);
        btnLuuNhaSanXuat.setEnabled(true);
        EnableNSX();
    }//GEN-LAST:event_btnThemNhaSanXuatActionPerformed

    private void btnSuaNhaSanXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNhaSanXuatActionPerformed
        Them = false;
        Sua = true;
        btnThemNhaSanXuat.setEnabled(false);
        btnSuaNhaSanXuat.setEnabled(false);
        btnXoaNhaSanXuat.setEnabled(false);
        btnLuuNhaSanXuat.setEnabled(true);
        EnableNSX();
    }//GEN-LAST:event_btnSuaNhaSanXuatActionPerformed

    private void btnLuuNhaSanXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuNhaSanXuatActionPerformed
        if (ktRongNhaSanXuat()) {
            if (Them == true) {
                if (xuLy.ktNhaSanXuat(txtMaNSX.getText())) {
                    if(ktSDT(txtSoDienThoai.getText())== false){
                        JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại !","Lỗi",2);
                    }
                    else if(!ktEmail(txtEmail.getText())){
                        JOptionPane.showMessageDialog(this, "Sai định dạng email !","Lỗi",2);
                    }
                    else{
                    xuLy.themNhaSanXuat(txtMaNSX.getText(), txtNhaSanXuat.getText(), txtDiaChi.getText(), txtSoDienThoai.getText(), txtEmail.getText());
                    taiBangNhaSanXuat();
                    DisableNSX();
                    Refresh();
                    JOptionPane.showMessageDialog(this,"Thêm nhà sản xuất thành công !","Thông báo",1);
        }
                } else {
                    JOptionPane.showMessageDialog(this,"Mã nhà sản xuất đã tồn tại !","Thông báo",1);
                }
            } else if (Sua == true) {
                int Click = tableNhaSanXuat.getSelectedRow();
                TableModel model = tableNhaSanXuat.getModel();
                if (model.getValueAt(Click, 0).toString().equals(txtMaNSX.getText())) {
                    if(ktSDT(txtSoDienThoai.getText())== false){
                        JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại !","Lỗi",2);
                    }
                    else if(!ktEmail(txtEmail.getText())){
                        JOptionPane.showMessageDialog(this, "Sai định dạng email !","Lỗi",2);
                    }
                    else{
                    xuLy.suaNhaSanXuat(txtMaNSX.getText(), txtNhaSanXuat.getText(), txtDiaChi.getText(), txtSoDienThoai.getText(), txtEmail.getText(), model.getValueAt(Click, 0).toString());
                    DisableNSX();
                    Refresh();
                    taiBangNhaSanXuat();
                    JOptionPane.showMessageDialog(this,"Lưu thay đổi thành công !","Thông báo",1);
                    }
                } else if (xuLy.ktNhaSanXuat(txtMaNSX.getText())) {
                    if(ktSDT(txtSoDienThoai.getText())== false){
                        JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại !","Lỗi",2);
                    }
                    else if(!ktEmail(txtEmail.getText())){
                        JOptionPane.showMessageDialog(this, "Sai định dạng email !","Lỗi",2);
                    }
                    else{
                    xuLy.suaNhaSanXuat(txtMaNSX.getText(), txtNhaSanXuat.getText(), txtDiaChi.getText(), txtSoDienThoai.getText(), txtEmail.getText(), model.getValueAt(Click, 0).toString());
                    DisableNSX();
                    taiBangNhaSanXuat();
                    Refresh();
                    JOptionPane.showMessageDialog(this,"Lưu thay đổi thành công !","Thông báo",1);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,"Mã nhà sản xuất đã tồn tại !","Thông báo",1);
                }
            }
        }
    }//GEN-LAST:event_btnLuuNhaSanXuatActionPerformed

    private void btnXoaNhaSanXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNhaSanXuatActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa nhà sản xuất hay không?", "Thông Báo", 2);
        if (Click == JOptionPane.YES_OPTION) {
            xuLy.xoaNhaSanXuat(txtMaNSX.getText());
            Refresh();
            taiBangNhaSanXuat();
            JOptionPane.showMessageDialog(this,"Xoá nhà sản xuất thành công !","Thông báo",1);
        }
    }//GEN-LAST:event_btnXoaNhaSanXuatActionPerformed

    private void cbxLoaiSPPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxLoaiSPPopupMenuWillBecomeInvisible
        maTuDong();
    }//GEN-LAST:event_cbxLoaiSPPopupMenuWillBecomeInvisible

    private void tableSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSanPhamMouseClicked
        try{
            taiLoaiThietBiVaNhaSanXuat();

            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);

            int Click = tableSanPham.getSelectedRow();
            TableModel model = tableSanPham.getModel();
            thietBi = xuLy.layThietBiTheoMa(model.getValueAt(Click, 0).toString());
            cbxNhaSanXuat.removeAllItems();
            cbxLoaiSP.removeAllItems();
            //String[] arr = {"Mã Thiết Bị", "Tên Thiết Bị", "Mã Loại", "Mã Nhà Sản Xuất", "Thời Gian Bảo Hành", "Số Lượng", "Giá","Hình Ảnh"};
            txtMaSP.setText(model.getValueAt(Click, 0).toString());
            txtTenSP.setText(model.getValueAt(Click, 1).toString());
            cbxLoaiSP.addItem(model.getValueAt(Click, 2).toString());
            cbxNhaSanXuat.addItem(model.getValueAt(Click, 3).toString());
            String[] s = model.getValueAt(Click, 4).toString().trim().split("\\s");

            txtThoiGianBaoHanh.setText(s[0]);

            thoiGian(s[1]);
            txtGia.setText(model.getValueAt(Click, 6).toString());
             if(thietBi.getHinhAnh()!= null ){
                        Image img = ImageData.createImageFromByArray(thietBi.getHinhAnh(), "jpg");
                        labelHinhAnh.setIcon(new ImageIcon(img));
                        hinhThietBi = thietBi.getHinhAnh();
                    }
        }
        catch(Exception ex){
           ex.printStackTrace();
        }

       
    }//GEN-LAST:event_tableSanPhamMouseClicked

    private void btnSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXepActionPerformed
        if (radioGiaThietBi.isSelected() == false && radioTenThietBi.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Bạn cần chọn 1 loại sắp xếp","Thông báo",2);
        } else {
            if (radioTenThietBi.isSelected()) {
                sapXepTen();
            } else if (radioGiaThietBi.isSelected()) {
                sortSGia();
            }
        }
    }//GEN-LAST:event_btnSapXepActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String sql = "SELECT * FROM THIETBI WHERE MATHIETBI LIKE N'%"+this.txtTimKiem.getText()+"%' OR MALOAI LIKE N'%"+this.txtTimKiem.getText()+"%' OR TENTHIETBI LIKE N'%"+this.txtTimKiem.getText()+"%' OR MANSX LIKE N'%"+this.txtTimKiem.getText()+"%' OR TGBAOHANH LIKE N'%"+this.txtTimKiem.getText()+"%' OR SOLUONG LIKE N'%"+this.txtTimKiem.getText()+"%' OR GIA LIKE N'%"+this.txtTimKiem.getText()+"%'";
        timThietBi(sql);
        txtTimKiem.setText("");
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtThoiGianBaoHanhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtThoiGianBaoHanhKeyReleased
        txtThoiGianBaoHanh.setText(cutChar(txtThoiGianBaoHanh.getText()));
    }//GEN-LAST:event_txtThoiGianBaoHanhKeyReleased

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
    String sql = "SELECT * FROM THIETBI WHERE MATHIETBI LIKE N'%"+this.txtTimKiem.getText()+"%' OR MALOAI LIKE N'%"+this.txtTimKiem.getText()+"%' OR TENTHIETBI LIKE N'%"+this.txtTimKiem.getText()+"%' OR MANSX LIKE N'%"+this.txtTimKiem.getText()+"%' OR TGBAOHANH LIKE N'%"+this.txtTimKiem.getText()+"%' OR SOLUONG LIKE N'%"+this.txtTimKiem.getText()+"%' OR GIA LIKE N'%"+this.txtTimKiem.getText()+"%'";
    timThietBi(sql);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtSoDienThoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKeyReleased
         txtSoDienThoai.setText(cutChar(txtSoDienThoai.getText()));
    }//GEN-LAST:event_txtSoDienThoaiKeyReleased

    private void txtSoLuongNhapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongNhapKeyReleased
         txtSoLuongNhap.setText(cutChar(txtSoLuongNhap.getText()));
          DecimalFormat formatter = new DecimalFormat("###,###,###");
        if (txtSoLuongNhap.getText().equals("")) {
            String[] s = txtGiaThietBiNhap.getText().split("\\s");
            txtDonGia.setText("0" + " " + s[1]);
        } else {
            int soluong = Integer.parseInt(txtSoLuongNhap.getText());

            String[] s = txtGiaThietBiNhap.getText().split("\\s");

            txtDonGia.setText(formatter.format(chuyenSangSo(s[0]) * soluong) + " " + s[1]);
        }
    }//GEN-LAST:event_txtSoLuongNhapKeyReleased

    private void btnInDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInDanhSachActionPerformed
           try {
            JasperReport report = JasperCompileManager.compileReport("src\\view\\ThietBi.jrxml");

            JasperPrint print = JasperFillManager.fillReport(report, null, KetNoiCoSoDuLieu.layKetNoi());

            JasperViewer.viewReport(print, false);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnInDanhSachActionPerformed

    private void btnSuaThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThietBiActionPerformed
        themThietBi = false;
        KH = false;
        TB=  true;
        suaThietBi = true;

        btnThemThietBi.setEnabled(false);
        btnXoaThietBi.setEnabled(false);
        btnSuaThietBi.setEnabled(false);
        btnLuuPhieuNhap.setEnabled(true);

        txtSoLuongNhap.setEnabled(true);
        txtGiaThietBiNhap.setEnabled(true);
        txtDonGia.setEnabled(true);
        taiMaLoaiThietBiNhap();
    }//GEN-LAST:event_btnSuaThietBiActionPerformed

    private void btnThemPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemPhieuNhapActionPerformed
        int Click = JOptionPane.showConfirmDialog(this, "Bạn có muốn tạo 1 đơn nhập hàng mới hay không?", "Thông Báo", 2);
        if (Click == JOptionPane.YES_OPTION) {
            Refresh();
            themDH = true;
            suaDonHang = false;
            KH = true;
            btnThemPhieuNhap.setEnabled(false);
            btnLuuPhieuNhap.setEnabled(true);
            EnabledPhieuNhap();
        }
    }//GEN-LAST:event_btnThemPhieuNhapActionPerformed

    private void btnThemThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThietBiActionPerformed
        themThietBi = true;
        KH = false;
        TB = true;
        suaThietBi = false;

        btnThemThietBi.setEnabled(false);
        btnLuuPhieuNhap.setEnabled(true);
        EnabledNhapTbietBi();
        taiMaLoaiThietBiNhap();
    }//GEN-LAST:event_btnThemThietBiActionPerformed

    private void btnXoaThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThietBiActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thiết bị khỏi đơn đặt hàng hay không?", "Thông Báo", 2);
        if (Click == JOptionPane.YES_OPTION) {
            String[] array = cbxThietBiNhap.getSelectedItem().toString().split("\\s");
            xuLy.xoaThietBiKhoiPhieuNhap(cbxMaPhieuNhap.getSelectedItem().toString(), array[0]);
            taiThietBiNhapHang();
            refreshThietBi();
            ktPhieuNhap();
            thanhToan();
            btnThemThietBi.setEnabled(true);
            btnSuaThietBi.setEnabled(false);
            btnXoaThietBi.setEnabled(false);
            this.lbTrangThai.setText("Xóa thiết bị thành công!");
        }
    }//GEN-LAST:event_btnXoaThietBiActionPerformed

    private void btnSuaPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaPhieuNhapActionPerformed
        suaDonHang = true;
        themDH = false;
        KH = true;
        TB = false;
        btnThemPhieuNhap.setEnabled(false);
        btnXoaPhieuNhap.setEnabled(false);
        btnSuaPhieuNhap.setEnabled(false);
        btnThemThietBi.setEnabled(false);
        btnSuaThietBi.setEnabled(false);
        btnXoaThietBi.setEnabled(false);
        btnLuuPhieuNhap.setEnabled(true);

        Disabled();
        refreshThietBi();
        EnabledPhieuNhap();
    }//GEN-LAST:event_btnSuaPhieuNhapActionPerformed

    private void btnXoaPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaPhieuNhapActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa đơn hàng này hay không?", "Thông Báo", 2);
        if (Click == JOptionPane.YES_OPTION) {
            xuLy.xoaPhieuNhap(cbxMaPhieuNhap.getSelectedItem().toString());
            taiThietBiNhapHang();
            RefreshPhieuNhap();
            ktPhieuNhap();
            refreshThietBi();
            this.lbTrangThai.setText("Xóa đơn hàng thành công!");
        }
    }//GEN-LAST:event_btnXoaPhieuNhapActionPerformed

    private void btnLuuPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuPhieuNhapActionPerformed
        if (KH == true) {
            if (themDH == true) {
                phieuNhapMoi();
                xuLy.themPhieuNhapMoi(cbxMaPhieuNhap.getSelectedItem().toString(),
                new java.sql.Date(txtNgayNhap.getDate().getTime()),ShareData.nguoiDangNhap.getMaNhanVien() ,0);
                taiPhieuNhap();
                RefreshPhieuNhap();
                ktPhieuNhap();
                lbTrangThai.setText("Thêm phiếu nhập thành công!!");
                    
            } else if (suaDonHang == true) {
                xuLy.suaPhieuNhap(cbxMaPhieuNhap.getSelectedItem().toString(),new java.sql.Date(txtNgayNhap.getDate().getTime()));
                taiThongTinNhapHang();
                RefreshPhieuNhap();
                Disabled();
                btnLuuPhieuNhap.setEnabled(false);
                lbTrangThai.setText("Sửa phiếu đặt thành công!!");
            }
            
        }else if (TB == true) {
            if (themThietBi == true) {
                String[] array = cbxThietBiNhap.getSelectedItem().toString().split("\\s");
                String[] gia = txtDonGia.getText().split("\\s");
                if (xuLy.ktThietBiNhapHang(cbxMaPhieuNhap.getSelectedItem().toString(), array[0])) {
                    xuLy.themThietBiNhapHang(cbxMaPhieuNhap.getSelectedItem().toString(), array[0], Integer.parseInt(txtSoLuongNhap.getText()), gia[0]);                  
                    taiThietBiNhapHang();
                    refreshThietBi();
                    Disabled();
                    thanhToan();
                    btnLuuPhieuNhap.setEnabled(false);
                    btnThemThietBi.setEnabled(true);
                    
                } else {
                    lbTrangThai.setText("Thiết bị đã tồn tại trong phiếu nhập hàng!!");
                }
            } else if (suaThietBi == true) {
                String[] gia = txtDonGia.getText().split("\\s");
                String[] arry1 = cbxThietBiNhap.getSelectedItem().toString().split("\\s");
                xuLy.suaThietBiNhapHang(cbxMaPhieuNhap.getSelectedItem().toString(), arry1[0], Integer.parseInt(txtSoLuongNhap.getText()), gia[0]);
                taiThietBiNhapHang();
                refreshThietBi();
                Disabled();
                thanhToan();
                btnLuuPhieuNhap.setEnabled(false);
                btnThemThietBi.setEnabled(true);
                lbTrangThai.setText("Sửa thiết bị thành công !!");
            }
        }
        ktNhapHang();
    }//GEN-LAST:event_btnLuuPhieuNhapActionPerformed

    private void cbxThietBiNhapPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxThietBiNhapPopupMenuWillBecomeInvisible
        taiThongTinThietBi();
        txtSoLuongNhap.setEnabled(true);
    }//GEN-LAST:event_cbxThietBiNhapPopupMenuWillBecomeInvisible

    private void cbxLoaiThietBiNhapPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxLoaiThietBiNhapPopupMenuWillBecomeInvisible
         taiThietBiNhap();
        if (cbxThietBiNhap.getItemCount() == 0) {
            cbxThietBiNhap.setEnabled(false);
            txtSoLuongNhap.setEnabled(false);
            refreshThietBi();
        } else {
            refreshThietBi();
            cbxThietBiNhap.setEnabled(true);
        }
    }//GEN-LAST:event_cbxLoaiThietBiNhapPopupMenuWillBecomeInvisible

    private void cbxMaPhieuNhapPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxMaPhieuNhapPopupMenuWillBecomeInvisible
        refreshThietBi();
        taiThongTinNhapHang();
        taiThietBiNhapHang();

        ktNhapHang();
        thanhToan();
        btnSuaPhieuNhap.setEnabled(true);
        btnXoaPhieuNhap.setEnabled(true);
        btnThemThietBi.setEnabled(true);
    }//GEN-LAST:event_cbxMaPhieuNhapPopupMenuWillBecomeInvisible

    private void tablePhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePhieuNhapMouseClicked
        cbxThietBiNhap.removeAllItems();
        cbxLoaiThietBiNhap.removeAllItems();
        int Click = tablePhieuNhap.getSelectedRow();
        TableModel model = tablePhieuNhap.getModel();
        cbxThietBiNhap.addItem(model.getValueAt(Click, 0).toString());
        txtSoLuongNhap.setText(model.getValueAt(Click, 1).toString());
        txtDonGia.setText(model.getValueAt(Click, 2).toString());
        taiThongTinThietBi();
        btnThemThietBi.setEnabled(true);
        btnXoaThietBi.setEnabled(true);
        btnSuaThietBi.setEnabled(true);
    }//GEN-LAST:event_tablePhieuNhapMouseClicked

    private void btnInPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInPhieuNhapActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xuất đơn đặt hàng hay không?", "Thông Báo", 2);
        String MAPN = cbxMaPhieuNhap.getSelectedItem().toString().trim();
        if (Click == JOptionPane.YES_OPTION) {
            try {
                HashMap hash = new HashMap();
                hash.put("MAPN",MAPN);
                JasperReport report = JasperCompileManager.compileReport("src\\View\\PhieuNhap.jrxml");
                JasperPrint print = JasperFillManager.fillReport(report, hash, KetNoiCoSoDuLieu.layKetNoi());
                lbTrangThai.setText("Thực hiện xuất đơn đặt hàng công!");
                JasperViewer.viewReport(print, false);
                xuLy.chuyenTrangThaiNhapHang(MAPN);
                taiPhieuNhap();
                consistency(MAPN);
                taiBangThietBi();
            } catch (JRException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnInPhieuNhapActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyThietBi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyThietBi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyThietBi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyThietBi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TaiKhoan taiKhoan = new TaiKhoan();
                new FormQuanLyThietBi(taiKhoan).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatAnh;
    private javax.swing.JButton btnInDanhSach;
    private javax.swing.JButton btnInPhieuNhap;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnLamMoi2;
    private javax.swing.JButton btnLamMoiPhieuNhap;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnLuuLoaiThietBi;
    private javax.swing.JButton btnLuuNhaSanXuat;
    private javax.swing.JButton btnLuuPhieuNhap;
    private javax.swing.JButton btnSapXep;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaLoaiThietBi;
    private javax.swing.JButton btnSuaNhaSanXuat;
    private javax.swing.JButton btnSuaPhieuNhap;
    private javax.swing.JButton btnSuaThietBi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemLoaiThietBi;
    private javax.swing.JButton btnThemNhaSanXuat;
    private javax.swing.JButton btnThemPhieuNhap;
    private javax.swing.JButton btnThemThietBi;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaLoaiThietBi;
    private javax.swing.JButton btnXoaNhaSanXuat;
    private javax.swing.JButton btnXoaPhieuNhap;
    private javax.swing.JButton btnXoaThietBi;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxLoaiSP;
    private javax.swing.JComboBox<String> cbxLoaiThietBiNhap;
    private javax.swing.JComboBox<String> cbxMaPhieuNhap;
    private javax.swing.JComboBox<String> cbxNhaSanXuat;
    private javax.swing.JComboBox<String> cbxTG;
    private javax.swing.JComboBox<String> cbxThietBiNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPaneQuanLiThietBi;
    private javax.swing.JLabel labelHinhAnh;
    private javax.swing.JLabel lbTongTienNhapHang;
    private javax.swing.JLabel lbTrangThai;
    private javax.swing.JRadioButton radioGiaThietBi;
    private javax.swing.JRadioButton radioTenThietBi;
    private javax.swing.JTable tableLoaiSanPham;
    private javax.swing.JTable tableNhaSanXuat;
    private javax.swing.JTable tablePhieuNhap;
    private javax.swing.JTable tableSanPham;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtGiaThietBiNhap;
    private javax.swing.JTextField txtLoaiThietBi;
    private javax.swing.JTextField txtMaLoai;
    private javax.swing.JTextField txtMaNSX;
    private javax.swing.JTextField txtMaSP;
    private com.toedter.calendar.JDateChooser txtNgayNhap;
    private javax.swing.JTextField txtNhaSanXuat;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtSoLuongNhap;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtThoiGianBaoHanh;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
