/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.KetNoiCoSoDuLieu;
import DAO.ShareData;
import controller.XuLy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.ChucVu;
import model.NhanVien;
import java.util.Date;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author phucn
 */
public class FormQuanLyNhanVien extends javax.swing.JFrame {
    
    private XuLy xuLy;
    private boolean Them = false , Sua = false;
    public FormQuanLyNhanVien() {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Quản lý nhân viên");
        xuLy = new XuLy();
        taiMaiChucVu();
        taiBangNhanVien();
        
        taiBangChucVu();
        disableChucVu();
    }

    public void Enable(){
        cbxChucVu.setEnabled(true);
        txtMaNhanVien.setEnabled(true);
        txtHoTen.setEnabled(true);
        cbxGioiTinh.setEnabled(true);
        txtBacLuong.setEnabled(true);
        jDateChooserNgaySinh.setEnabled(true);
        txtDiaChi.setEnabled(true);
        txtEmail.setEnabled(true);
        txtSoDienThoai.setEnabled(true);
    }
    public void Disable(){
        cbxChucVu.setEnabled(false);
        txtMaNhanVien.setEnabled(false);
        txtHoTen.setEnabled(false);
        cbxGioiTinh.setEnabled(false);
        txtBacLuong.setEnabled(false);
        jDateChooserNgaySinh.setEnabled(false);
        txtDiaChi.setEnabled(false);
        txtEmail.setEnabled(false);
        txtSoDienThoai.setEnabled(false);
    }
    public void Refresh(){
        Them = false;
        Sua = false;
        cbxChucVu.removeAllItems();
        Disable();
        taiMaiChucVu();
        
        ((JTextField) jDateChooserNgaySinh.getDateEditor().getUiComponent()).setText("");
        txtMaNhanVien.setText("");
        txtHoTen.setText("");
        txtBacLuong.setText("");
        txtDiaChi.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtSoDienThoai.setText("");
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        btnLuu.setEnabled(false);
    }
    public boolean kiemTraRong(){
        boolean kq = true;
        if(txtMaNhanVien.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã nhân viên", "Thông báo", 2);
            return false;
        }
        if(txtHoTen.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập họ tên nhân viên", "Thông báo", 2);
            return false;
        }
        if(txtSoDienThoai.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại", "Thông báo", 2);
            return false;
        }
        if(((JTextField)jDateChooserNgaySinh.getDateEditor().getUiComponent()).getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập chọn ngày sinh", "Thông báo", 2);
            return false;
        }
        if(txtDiaChi.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ", "Thông báo", 2);
            return false;
        }
        if(txtEmail.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập email", "Thông báo", 2);
            return false;
        }
        if(txtBacLuong.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập bậc lương", "Thông báo", 2);
            return false;
        }
        return true;
    }
    private void taiMaiChucVu(){
        ArrayList ar = xuLy.layChucVu();
        for(int i =0;i< ar.size();i++){
            this.cbxChucVu.addItem(((ChucVu) ar.get(i)).getMaChucVu()+" ("+((ChucVu)ar.get(i)).getChucVu()+")");
        }
    }
    private void gioiTinh(String s){
        if(s.equals("NAM")){
            cbxGioiTinh.setSelectedIndex(0);
        }
        else{
            cbxGioiTinh.setSelectedIndex(1);
        }
    }
    private void taiBangNhanVien(){
        tableNhanVien.removeAll();
        String[] arr = {"Mã Nhân Viên","Họ Tên","Chức Vụ","Bậc Lương","Ngày Sinh","Giới Tính","Địa Chỉ","Số Điện Thoại","Email","Lương"};
        DefaultTableModel model = new DefaultTableModel(arr,0);
        ArrayList array = xuLy.layNhanVien();
        
        for(int i=0;i<array.size();i++){
            Vector vt = new Vector();
            vt.add(((NhanVien)array.get(i)).getMaNhanVien());
            vt.add(((NhanVien)array.get(i)).getHoTen());
            vt.add(((NhanVien)array.get(i)).getChucVu());
            vt.add(((NhanVien)array.get(i)).getBacLuong());
            vt.add(new SimpleDateFormat("dd/MM/yyyy").format(((NhanVien)array.get(i)).getNgaySinh()));
            vt.add(((NhanVien)array.get(i)).getGioiTinh());
            vt.add(((NhanVien)array.get(i)).getDiaChi());
            vt.add(((NhanVien)array.get(i)).getSdt());
            vt.add(((NhanVien)array.get(i)).getEmail());
            vt.add(xuLy.xuLyLuong(((NhanVien)array.get(i)).getLuong()));
            model.addRow(vt);
        }
        tableNhanVien.setModel(model);
    }
     private void View(ArrayList array){
        tableNhanVien.removeAll();
        String[] arr = {"Mã Nhân Viên","Họ Tên","Chức Vụ","Bậc Lương","Ngày Sinh","Giới Tính","Địa Chỉ","Số Điện Thoại","Email","Lương"};
        DefaultTableModel model = new DefaultTableModel(arr,0);
        
        for(int i=0;i<array.size();i++){
            Vector vt = new Vector();
            vt.add(((NhanVien)array.get(i)).getMaNhanVien());
            vt.add(((NhanVien)array.get(i)).getHoTen());
            vt.add(((NhanVien)array.get(i)).getChucVu());
            vt.add(((NhanVien)array.get(i)).getBacLuong());
            vt.add(new SimpleDateFormat("dd/MM/yyyy").format(((NhanVien)array.get(i)).getNgaySinh()));
            vt.add(((NhanVien)array.get(i)).getGioiTinh());
            vt.add(((NhanVien)array.get(i)).getDiaChi());
            vt.add(((NhanVien)array.get(i)).getSdt());
            vt.add(((NhanVien)array.get(i)).getEmail());
            vt.add(xuLy.xuLyLuong(((NhanVien)array.get(i)).getLuong()));
            model.addRow(vt);
        }
        tableNhanVien.setModel(model);
    }
    public void enableChucVu(){
        txtMaChucVu.setEnabled(true);
        txtChucVu.setEnabled(true);
        txtLuongCoBan.setEnabled(true);
    }
    public void disableChucVu(){
        txtMaChucVu.setEnabled(false);
        txtChucVu.setEnabled(false);
        txtLuongCoBan.setEnabled(false);
    }
    public void refreshChucVu(){
        Them = false ; Sua = false;
        txtMaChucVu.setText("");
        txtChucVu.setText("");
        txtLuongCoBan.setText("");
        
        btnThemChucVu.setEnabled(true);
        btnSuaChucVu.setEnabled(false);
        btnXoaChucVu.setEnabled(false);
        btnLuuChucVu.setEnabled(false);
        
        disableChucVu();
    }
    public boolean kiemtraRongChucVu(){
        boolean kq = true;   
        if(txtMaChucVu.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã chức vụ ! ", "Thông báo", 2);
        }
        else if(txtChucVu.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập chức vụ ! ", "Thông báo", 2);
        }
        else if(txtLuongCoBan.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập lương cơ bản ! ", "Thông báo", 2);
        }
        return kq;
    }
    
    public void taiBangChucVu(){
        tableChucVu.removeAll();
        String[] arr ={"Mã Chức Vụ","Chức Vụ","Lương Cơ Bản"};
        DefaultTableModel model = new DefaultTableModel(arr,0);
        
        ArrayList array = xuLy.layChucVu();
        for(int i =0; i < array.size();i++){
            Vector vt = new Vector();
            vt.add(((ChucVu) array.get(i)).getMaChucVu());
            vt.add(((ChucVu) array.get(i)).getChucVu());
            vt.add(xuLy.xuLyLuong(((ChucVu) array.get(i)).getLuong()));
            model.addRow(vt);
        }
        tableChucVu.setModel(model);
    }
    public void maTuDong(){
        String[] array = cbxChucVu.getSelectedItem().toString().split("\\s");
        if(xuLy.layMaNhanVien(array[0]) <10 ){
            txtMaNhanVien.setText(array[0] + "0"+ String.valueOf(xuLy.layMaNhanVien(array[0])+1));
        }
        else{
            txtMaNhanVien.setText(array[0] + String.valueOf(xuLy.layMaNhanVien(array[0])+1));
        }
    }
    public String cutChar(String s){
        return s.replaceAll("\\D+","");
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
     private float chuyenSangSo(String s) {
        String number = "";
        String[] array = s.replace(",", " ").split("\\s");
        for (String i : array) {
            number = number.concat(i);
        }
        return Float.parseFloat(number);
    }
       private void timNhanVien(String sql) {
        ArrayList array = xuLy.timNhanVien(sql);
        View(array);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTrangChu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPaneQuanLyNhanVien = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbxChucVu = new javax.swing.JComboBox<>();
        txtBacLuong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        jDateChooserNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbxGioiTinh = new javax.swing.JComboBox<>();
        txtEmail = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnInDanhSach = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNhanVien = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        txtMaChucVu = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtChucVu = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtLuongCoBan = new javax.swing.JTextField();
        btnSuaChucVu = new javax.swing.JButton();
        btnThemChucVu = new javax.swing.JButton();
        btnLamMoiChucVu = new javax.swing.JButton();
        btnXoaChucVu = new javax.swing.JButton();
        btnLuuChucVu = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableChucVu = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTrangChu.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/return-48.png"))); // NOI18N
        btnTrangChu.setText("Quay Lại");
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });
        getContentPane().add(btnTrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 186, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 13, 1060, 57));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 806, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 202, -1, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Chức vụ:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Bậc Lương:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 77, -1, 30));

        cbxChucVu.setEnabled(false);
        cbxChucVu.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbxChucVuPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel2.add(cbxChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 120, 30));

        txtBacLuong.setEnabled(false);
        txtBacLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBacLuongKeyReleased(evt);
            }
        });
        jPanel2.add(txtBacLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 120, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Mã nhân viên:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Ngày Sinh:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, 30));

        txtMaNhanVien.setEnabled(false);
        jPanel2.add(txtMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 140, 30));

        jDateChooserNgaySinh.setDateFormatString("dd/MM/yyyy");
        jDateChooserNgaySinh.setEnabled(false);
        jPanel2.add(jDateChooserNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 160, 30));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Họ và tên:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 80, 30));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Địa chỉ:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, -1, -1));

        txtHoTen.setEnabled(false);
        txtHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenActionPerformed(evt);
            }
        });
        jPanel2.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 150, 30));

        txtDiaChi.setEnabled(false);
        jPanel2.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 72, 150, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Giới tính:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(954, 10, -1, 30));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Email:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 70, -1, 34));

        cbxGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NAM", "NỮ" }));
        cbxGioiTinh.setEnabled(false);
        jPanel2.add(cbxGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1029, 13, 156, 30));

        txtEmail.setEnabled(false);
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 70, 156, 30));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Số điện thoại:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 137, -1, 30));

        txtSoDienThoai.setEnabled(false);
        txtSoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDienThoaiActionPerformed(evt);
            }
        });
        txtSoDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoDienThoaiKeyReleased(evt);
            }
        });
        jPanel2.add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 140, 30));

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/refresh-32.png"))); // NOI18N
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/plus-math-32.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/edit-32.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setEnabled(false);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/delete-32.png"))); // NOI18N
        btnXoa.setText("Xoá");
        btnXoa.setEnabled(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/save-32.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setEnabled(false);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnInDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/print-32.png"))); // NOI18N
        btnInDanhSach.setText("In Danh Sách");
        btnInDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInDanhSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(btnInDanhSach)
                .addGap(39, 39, 39))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInDanhSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );

        tableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableNhanVien);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Danh Sách Nhân Viên");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Tìm Kiếm:");

        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1221, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(244, 244, 244))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneQuanLyNhanVien.addTab("Thông tin nhân viên", jPanel3);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaChucVu.setEnabled(false);
        jPanel6.add(txtMaChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 170, 30));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Mã chức vụ");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 37, -1, 20));

        txtChucVu.setEnabled(false);
        jPanel6.add(txtChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 180, 30));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Chức vụ");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, 30));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Lương cơ bản");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 30, -1, 30));

        txtLuongCoBan.setEnabled(false);
        txtLuongCoBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLuongCoBanKeyReleased(evt);
            }
        });
        jPanel6.add(txtLuongCoBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 30, 180, 30));

        btnSuaChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/edit-32.png"))); // NOI18N
        btnSuaChucVu.setText("Sửa");
        btnSuaChucVu.setEnabled(false);
        btnSuaChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaChucVuActionPerformed(evt);
            }
        });
        jPanel6.add(btnSuaChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 181, -1));

        btnThemChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/plus-math-32.png"))); // NOI18N
        btnThemChucVu.setText("Thêm");
        btnThemChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChucVuActionPerformed(evt);
            }
        });
        jPanel6.add(btnThemChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 167, -1));

        btnLamMoiChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/refresh-32.png"))); // NOI18N
        btnLamMoiChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiChucVuActionPerformed(evt);
            }
        });
        jPanel6.add(btnLamMoiChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 143, -1));

        btnXoaChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/delete-32.png"))); // NOI18N
        btnXoaChucVu.setText("Xoá");
        btnXoaChucVu.setEnabled(false);
        btnXoaChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaChucVuActionPerformed(evt);
            }
        });
        jPanel6.add(btnXoaChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 120, 186, -1));

        btnLuuChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/save-32.png"))); // NOI18N
        btnLuuChucVu.setText("Lưu");
        btnLuuChucVu.setEnabled(false);
        btnLuuChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuChucVuActionPerformed(evt);
            }
        });
        jPanel6.add(btnLuuChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 120, 182, -1));

        tableChucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableChucVuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableChucVu);

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("Danh Sách Chức Vụ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(51, 51, 51))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneQuanLyNhanVien.addTab("Cập nhật chức vụ", jPanel4);

        getContentPane().add(jTabbedPaneQuanLyNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 88, 1250, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        FormTrangChu_QuanLy trangChu = new FormTrangChu_QuanLy();
        trangChu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        Refresh();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnLamMoiChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiChucVuActionPerformed
        refreshChucVu();
    }//GEN-LAST:event_btnLamMoiChucVuActionPerformed

    private void cbxChucVuPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxChucVuPopupMenuWillBecomeInvisible
        maTuDong();
    }//GEN-LAST:event_cbxChucVuPopupMenuWillBecomeInvisible

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        Refresh();
        Them = true;
        Enable();
        btnThem.setEnabled(false);
        btnLuu.setEnabled(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtSoDienThoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKeyReleased
        txtSoDienThoai.setText(cutChar(txtSoDienThoai.getText()));
    }//GEN-LAST:event_txtSoDienThoaiKeyReleased

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        Them = false;
        Sua = true;
        Enable();
        
        cbxChucVu.removeAllItems();
        taiMaiChucVu();
        
        btnSua.setEnabled(false);
        btnThem.setEnabled(false);
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(true);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        String[] array = cbxChucVu.getSelectedItem().toString().split("\\s");
        if(kiemTraRong()){
            if(Them == true){
                if(xuLy.ktThemNhanVien(txtMaNhanVien.getText())){
                    if(ktSDT(txtSoDienThoai.getText())== false){
                        JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại !","Lỗi",2);
                    }
                    else if(!ktEmail(txtEmail.getText())){
                        JOptionPane.showMessageDialog(this, "Sai định dạng email !","Lỗi",2);
                    }
                    else{
                    xuLy.themNhanVien(txtMaNhanVien.getText().toUpperCase(), txtHoTen.getText().toUpperCase(),new java.sql.Date(jDateChooserNgaySinh.getDate().getTime()),
                    cbxGioiTinh.getSelectedItem().toString(), txtDiaChi.getText().toUpperCase(), txtSoDienThoai.getText(), txtEmail.getText(),Float.parseFloat(txtBacLuong.getText()), array[0]);
                    Disable();
                    Refresh();
                   taiBangNhanVien();
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công !","Thông báo",1);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại !","Thông báo",2);
                }
            }
            else if(Sua == true){
                int click = tableNhanVien.getSelectedRow();
                TableModel model = tableNhanVien.getModel();
                
                if(model.getValueAt(click,0).toString().equals(txtMaNhanVien.getText())){
                     if(ktSDT(txtSoDienThoai.getText())== false){
                        JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại !","Lỗi",2);
                    }
                    else if(!ktEmail(txtEmail.getText())){
                        JOptionPane.showMessageDialog(this, "Sai định dạng email !","Lỗi",2);
                    }
                    else{
                        xuLy.suaNhanVien(txtMaNhanVien.getText().toUpperCase(), txtHoTen.getText().toUpperCase(),new java.sql.Date(jDateChooserNgaySinh.getDate().getTime()),
                            cbxGioiTinh.getSelectedItem().toString(), txtDiaChi.getText().toUpperCase(), txtSoDienThoai.getText(), txtEmail.getText(),
                            Float.parseFloat(txtBacLuong.getText()),array[0],model.getValueAt(click,0).toString().trim());
                    System.out.println(array[0]);
                    Disable();
                    Refresh();
                    taiBangNhanVien();
                    JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công !","Thông báo",1);
                    }
                }
                else if(xuLy.ktThemNhanVien(txtMaNhanVien.getText())){
                     if(ktSDT(txtSoDienThoai.getText())== false){
                        JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại !","Lỗi",2);
                    }
                    else if(!ktEmail(txtEmail.getText())){
                        JOptionPane.showMessageDialog(this, "Sai định dạng email !","Lỗi",2);
                    }
                    else{
                    xuLy.suaNhanVien(txtMaNhanVien.getText().toUpperCase(), txtHoTen.getText(),new java.sql.Date(jDateChooserNgaySinh.getDate().getTime()),
                            cbxGioiTinh.getSelectedItem().toString(), txtDiaChi.getText().toUpperCase(), txtSoDienThoai.getText(), txtEmail.getText(),
                            Float.parseFloat(txtBacLuong.getText()),array[0],model.getValueAt(click,0).toString().trim());
                    System.out.println(array[0]);
                    Disable();
                    Refresh();
                    taiBangNhanVien();
                    JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công !","Thông báo",1);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(this, "Mã nhân viên bạn sửa đổi đã tồn tại !","Thông báo",2);
                }
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int click = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá nhân viên hay không ?","Thông báo",2);
        if(click == JOptionPane.OK_OPTION){
            if(txtMaNhanVien.getText().equals(ShareData.nguoiDangNhap.getMaNhanVien().toString())){
                JOptionPane.showMessageDialog(this, "Không thể xoá nhân viên này vì đang đăng nhập !");
            }
            else{
            xuLy.xoaNhanVien(txtMaNhanVien.getText());
            Refresh();
            taiBangNhanVien();
            JOptionPane.showMessageDialog(this, "Xoá nhân viên thành công !","Thông báo",1);
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void txtSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiActionPerformed

    private void tableChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableChucVuMouseClicked
        int click = tableChucVu.getSelectedRow();
        TableModel model = tableChucVu.getModel();
        
        txtMaChucVu.setText(model.getValueAt(click,0).toString());
        txtChucVu.setText(model.getValueAt(click,1).toString());
        txtLuongCoBan.setText(cutChar(model.getValueAt(click,2).toString()) + " VNĐ");
        
        btnSuaChucVu.setEnabled(true);
        btnXoaChucVu.setEnabled(true);
    }//GEN-LAST:event_tableChucVuMouseClicked

    private void tableNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNhanVienMouseClicked
        int click = tableNhanVien.getSelectedRow();
        TableModel model = tableNhanVien.getModel();
        //String[] arr = {"Mã Nhân Viên","Họ Tên","Chức Vụ","Bậc Lương","Ngày Sinh","Giới Tính","Địa Chỉ","Số Điện Thoại","Email","Lương"};
        cbxChucVu.removeAllItems();
        txtMaNhanVien.setText(model.getValueAt(click,0).toString());
        txtHoTen.setText(model.getValueAt(click,1).toString());
        cbxChucVu.addItem(model.getValueAt(click, 2).toString());
        txtBacLuong.setText(model.getValueAt(click, 3).toString());
        ((JTextField) jDateChooserNgaySinh.getDateEditor().getUiComponent()).setText(model.getValueAt(click,4).toString());
        gioiTinh(model.getValueAt(click,5).toString());
        txtDiaChi.setText(model.getValueAt(click, 6).toString());
        txtSoDienThoai.setText(model.getValueAt(click, 7).toString());
        txtEmail.setText(model.getValueAt(click, 8).toString());
        
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
    }//GEN-LAST:event_tableNhanVienMouseClicked

    private void txtBacLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBacLuongKeyReleased

    }//GEN-LAST:event_txtBacLuongKeyReleased

    private void btnThemChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChucVuActionPerformed
        refreshChucVu();
        Them = true;
        enableChucVu();
        btnThemChucVu.setEnabled(false);
        btnLuuChucVu.setEnabled(true);
    }//GEN-LAST:event_btnThemChucVuActionPerformed

    private void btnSuaChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaChucVuActionPerformed
        Them = false;
        Sua = true;
        enableChucVu();
         btnThemChucVu.setEnabled(false);
         btnXoaChucVu.setEnabled(false);
         btnSuaChucVu.setEnabled(false);
         btnLuuChucVu.setEnabled(true);
         txtMaChucVu.setEditable(false);
    }//GEN-LAST:event_btnSuaChucVuActionPerformed

    private void btnLuuChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuChucVuActionPerformed
        String[] luong = txtLuongCoBan.getText().split("\\s");
        if(kiemtraRongChucVu()){
            if(Them == true){
                if(xuLy.ktThemChucVu(txtMaChucVu.getText())){
                    xuLy.themChucVu(txtMaChucVu.getText().toUpperCase(), txtChucVu.getText().toUpperCase(),luong[0]);
                    disableChucVu();
                    refreshChucVu();
                    taiBangChucVu();
                    JOptionPane.showMessageDialog(this, "Thêm chức vụ thành công !","Thông báo",1);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Mã chức vụ đã tồn tại !","Thông báo",1);
                }
            }
            else if(Sua == true){
                int click = tableChucVu.getSelectedRow();
                TableModel model = tableChucVu.getModel();
               if(model.getValueAt(click,0).toString().equals(txtMaChucVu.getText())){
                   xuLy.suaChucVu(model.getValueAt(click,0).toString().toUpperCase(), txtChucVu.getText().toUpperCase(), 
                   luong[0]);
                   disableChucVu();
                   refreshChucVu();
                   taiBangChucVu();
                   taiBangNhanVien();
                   JOptionPane.showMessageDialog(this, "Cập nhật chức vụ thành công !","Thông báo",1);
               }
            }
        }
    }//GEN-LAST:event_btnLuuChucVuActionPerformed

    private void btnXoaChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaChucVuActionPerformed
        int click = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá chức vụ hay không ?","Xác nhận",2);
        if(click == JOptionPane.OK_OPTION){
            if(xuLy.ktThemChucVu(txtMaChucVu.getText())== false){
            xuLy.xoaChucVu(txtMaChucVu.getText());
            refreshChucVu();
            taiBangChucVu();
            JOptionPane.showMessageDialog(this,"Xoá chức vụ thành công !","Thông báo",1);
            }
            else{
            JOptionPane.showMessageDialog(this,"Không thể xoá chức vụ này !","Cảnh báo",2);
            }
        }
    }//GEN-LAST:event_btnXoaChucVuActionPerformed

    private void txtLuongCoBanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLuongCoBanKeyReleased
         DecimalFormat formatter = new DecimalFormat("###,###,###");

        txtLuongCoBan.setText(cutChar(txtLuongCoBan.getText()));
        if (txtLuongCoBan.getText().equals("")) {
            return;
        } else {
            txtLuongCoBan.setText(formatter.format(chuyenSangSo(txtLuongCoBan.getText()))+ " VNĐ");
        }
    }//GEN-LAST:event_txtLuongCoBanKeyReleased

    private void btnInDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInDanhSachActionPerformed
        try{
            JasperReport report = JasperCompileManager.compileReport("src\\view\\NhanVien.jrxml");
            
            JasperPrint print = JasperFillManager.fillReport(report, null,KetNoiCoSoDuLieu.layKetNoi());
            
            JasperViewer.viewReport(print, false);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnInDanhSachActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String sql ="SELECT NV.MANHANVIEN, NV.HOTEN, CV.TENCHUCVU , NV.BACLUONG, NV.NGAYSINH, NV.GIOITINH, NV.DIACHI, NV.SDT, NV.EMAIL ,"
                + " LUONG= NV.BACLUONG * CV.LUONGCOBAN " +
                    "FROM NHANVIEN AS NV, CHUCVU AS CV WHERE NV.MACHUCVU = CV.MACHUCVU AND ( NV.MANHANVIEN LIKE  '%"+ this.txtTimKiem.getText()+ "%' OR NV.HOTEN LIKE N'%" + this.txtTimKiem.getText() +
               "%')";
        timNhanVien(sql);       
    }//GEN-LAST:event_txtTimKiemKeyReleased

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormQuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInDanhSach;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoiChucVu;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnLuuChucVu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaChucVu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemChucVu;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaChucVu;
    private javax.swing.JComboBox<String> cbxChucVu;
    private javax.swing.JComboBox<String> cbxGioiTinh;
    private com.toedter.calendar.JDateChooser jDateChooserNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPaneQuanLyNhanVien;
    private javax.swing.JTable tableChucVu;
    private javax.swing.JTable tableNhanVien;
    private javax.swing.JTextField txtBacLuong;
    private javax.swing.JTextField txtChucVu;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuongCoBan;
    private javax.swing.JTextField txtMaChucVu;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
