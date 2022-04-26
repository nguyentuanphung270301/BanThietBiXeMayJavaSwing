/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.ImageData;
import DAO.ShareData;
import controller.XuLy;
import java.awt.Image;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.LoaiThietBi;
import model.TaiKhoan;
import model.ThietBi;

/**
 *
 * @author phucn
 */
public class FormBanHang extends javax.swing.JFrame implements Runnable{

    private TaiKhoan taiKhoan;
    private  XuLy xuLy;
    private ThietBi thietBi;
    private Thread thread;
    private byte[] hinhThietBi;
    
    private boolean Them = false, Sua = false, thanhToan = false, hoaDonMoi = false;
    
    public FormBanHang(TaiKhoan tk) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Bán hàng");
        
        xuLy = new XuLy();
        taiKhoan = new TaiKhoan();
        setData();
        Start();
    }
    private void setData() {
        lbTenNhanVien.setText(ShareData.nguoiDangNhap.getHoTen());
        lbNgay.setText(String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date())));
    }
    private void Start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
    private void Update() {
        lbGio.setText(String.valueOf(new SimpleDateFormat("HH:mm:ss").format(new java.util.Date())));
    }
    private void Enabled() {
        cbxLoaiSanPham.setEnabled(true);
    }
    private void Disabled() {
        cbxLoaiSanPham.setEnabled(false);
        txtSoLuong.setEnabled(false);
        cbxTenSanPham.setEnabled(false);
    }
    private void refreshThietBi() {
        txtGia.setText("");
        txtSoLuong.setText("");
        txtThanhTien.setText("");
        txtBaoHanh.setText("");
        lbHinhAnh.setIcon(new JLabel().getIcon());
    }
    private void Refresh() {
        Them = false;
        Sua = false;
        thanhToan = false;
        hoaDonMoi = false;
        refreshThietBi();
        //txbNameClassify.setText("");
        txtGia.setText("");
        lbTienDu.setText("0 VND");
        cbxTenSanPham.removeAllItems();
        cbxLoaiSanPham.removeAllItems();
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(false);
        btnThem.setEnabled(false);
        btnInHoaDon.setEnabled(false);
        Disabled();
    }
    private double chuyenSangSo(String s) {
        String number = "";
        String[] array = s.replace(",", " ").split("\\s");
        for (String i : array) {
            number = number.concat(i);
        }
        return Double.parseDouble(number);
    }
     private String cutChar(String array) {
        return array.replaceAll("\\D+", "");
    }
     private void taiLoaiThietBi() {
        cbxLoaiSanPham.removeAllItems();
        ArrayList array = xuLy.layLoaiThietBi();
        for (int i = 0; i < array.size(); i++) {
            cbxLoaiSanPham.addItem(((LoaiThietBi) array.get(i)).getMaLoai()+" ("+((LoaiThietBi) array.get(i)).getTenLoai()+ ")");
        }
    }
    private void taiThietBi() {
        cbxTenSanPham.removeAllItems();
        String []array=cbxLoaiSanPham.getSelectedItem().toString().split("\\s");
        ArrayList array1 = xuLy.layThietBiTheoMaLoai(array[0]);

        for (int i = 0; i < array1.size(); i++) {
            cbxTenSanPham.addItem(((ThietBi) array1.get(i)).getMaThietBi()+" ("+((ThietBi) array1.get(i)).getTenThietBi()+")");
        }
    }
    private void taiThongTinThietBi() {
        try{
        thietBi = new ThietBi(xuLy.layThietBiTheoMa(cbxTenSanPham.getSelectedItem().toString()));
        //txbNameProduct.setText(product.getTenSanPham());
        txtGia.setText(thietBi.getGia());
        txtBaoHanh.setText(thietBi.getTgBaoHanh());
        if(thietBi.getHinhAnh()!= null ){
                        Image img = ImageData.createImageFromByArray(thietBi.getHinhAnh(), "jpg");
                        lbHinhAnh.setIcon(new ImageIcon(img));
                        hinhThietBi = thietBi.getHinhAnh();
                    }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void taiBangHoaDon() {
        tableHoaDon.removeAll();
        try {
            String[] arr = {"Mã Thiết Bị", "Tên Thiết Bị", "Số Lượng", "Giá"};
            DefaultTableModel model = new DefaultTableModel(arr, 0);

            ArrayList array = xuLy.layHoaDon();
            for (int i = 0; i < array.size(); i++) {
                Vector vector = new Vector();
                vector.add(((HoaDon) array.get(i)).getMaHoaDon());
                vector.add(((HoaDon) array.get(i)).getTenThietBi());
                vector.add(((HoaDon) array.get(i)).getSoLuong());
                vector.add(((HoaDon) array.get(i)).getThanhTien());

                lbMaHoaDon.setText(((HoaDon) array.get(i)).getMaHoaDon().trim());
                model.addRow(vector);
            }
            tableHoaDon.setModel(model);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void thanhToan() {
        lbTongTien.setText("0 VNĐ");
        ArrayList array = xuLy.layHoaDon();
        for (int i = 0; i < array.size(); i++) {
            String[] s1 = ((HoaDon) array.get(i)).getThanhTien().trim().split("\\s");
            String[] s2 = lbTongTien.getText().split("\\s");
            double totalMoney = chuyenSangSo(s1[0]) + chuyenSangSo(s2[0]);
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            lbTongTien.setText(formatter.format(totalMoney) + " " + s2[1]);
        }
    }
     private void Sucessful() {
        btnLuu.setEnabled(false);
        btnThem.setEnabled(true);
        btnHoaDonMoi.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }
     private void ktHoaDon() {
        if (tableHoaDon.getRowCount() == 0) {
            lbTongTien.setText("0 VNĐ");
            txtTien.setText("");
            lbTienDu.setText("0 VNĐ");
            btnThanhToan.setEnabled(false);
            txtTien.setEnabled(false);
        } else {
            btnThanhToan.setEnabled(true);
            txtTien.setEnabled(true);
        }
    }private void automatedCode() {

        lbMaHoaDon.setText("HD" + String.valueOf(xuLy.layMaHoaDon()+ 1));
    }
    private void consistency() {

        ArrayList arrayListBill = xuLy.layHoaDon();
        for (int i = 0; i < arrayListBill.size(); i++) {
            String []array=thietBi.getGia().split("\\s");
            thietBi = xuLy.layThietBiTheoMa(((HoaDon) arrayListBill.get(i)).getMaThietBi().trim());
            xuLy.thayDoiSoLuongThietBi(thietBi.getMaThietBi(), thietBi.getSoLuong() - ((HoaDon) arrayListBill.get(i)).getSoLuong());
        }
    }
       private void checkSLLK() {
        thietBi = new ThietBi(xuLy.layThietBiTheoMa(cbxTenSanPham.getSelectedItem().toString()));

        if (thietBi.getSoLuong() == 0) {
            lbTrangThai.setText("Sản phẩm này đã hết hàng!!");
            btnLuu.setEnabled(false);
            txtSoLuong.setEnabled(false);
        } else {
            lbTrangThai.setText("Mặt hàng này còn " + thietBi.getSoLuong() + " sản phẩm!!");
            btnLuu.setEnabled(true);
            txtSoLuong.setEnabled(true);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTrangChu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbxLoaiSanPham = new javax.swing.JComboBox<>();
        txtBaoHanh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxTenSanPham = new javax.swing.JComboBox<>();
        txtSoLuong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        txtThanhTien = new javax.swing.JTextField();
        lbHinhAnh = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnLamMoi = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        btnHoaDonMoi = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lbMaHoaDon = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTien = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        lbTienDu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHoaDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbNgay = new javax.swing.JLabel();
        lbGio = new javax.swing.JLabel();
        lbTenNhanVien = new javax.swing.JLabel();
        lbTrangThai = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1285, 720));

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
        jLabel1.setText("BÁN HÀNG");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Loại thiết bị");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Bảo hành");

        cbxLoaiSanPham.setEnabled(false);
        cbxLoaiSanPham.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbxLoaiSanPhamPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txtBaoHanh.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Tên thiết bị");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Số lượng");

        cbxTenSanPham.setEnabled(false);

        txtSoLuong.setEnabled(false);
        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Giá");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Thành tiền");

        txtGia.setEditable(false);

        txtThanhTien.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxLoaiSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSoLuong)
                    .addComponent(cbxTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(txtGia))
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cbxTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addComponent(lbHinhAnh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh-icon.png"))); // NOI18N
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/math-add-icon.png"))); // NOI18N
        btnThem.setText("Thêm Thiết Bị");
        btnThem.setEnabled(false);
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
        btnXoa.setText("Xoá SP");
        btnXoa.setEnabled(false);

        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save-icon.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setEnabled(false);

        btnInHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/printer-icon.png"))); // NOI18N
        btnInHoaDon.setText("Xuất hoá đơn");

        btnThanhToan.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Pay.png"))); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHoaDonMoi.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnHoaDonMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/New.png"))); // NOI18N
        btnHoaDonMoi.setText("Hóa Đơn Mới");
        btnHoaDonMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonMoiActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setText("Mã Hoá Đơn:");

        lbMaHoaDon.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbMaHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHoaDonMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(122, 122, 122)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnHoaDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(61, 61, 61))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(19, 19, 19)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(btnInHoaDon)
                            .addComponent(lbMaHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setText("Tổng tiền hoá đơn:");

        lbTongTien.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbTongTien.setText("0 VNĐ");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setText("Tiền nhận của khách: ");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel14.setText("Tiền dư của khách:");

        lbTienDu.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbTienDu.setText("0 VNĐ");

        tableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã thiết bị", "Tên thiết bị", "Số lượng", "Tiền"
            }
        ));
        jScrollPane1.setViewportView(tableHoaDon);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Tên NV:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Giờ:");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Ngày: ");

        lbNgay.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbNgay.setText("Date");

        lbGio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbGio.setText("Time");

        lbTenNhanVien.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTenNhanVien.setText("Name");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbNgay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbGio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbTenNhanVien))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNgay)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbGio))
                .addContainerGap())
        );

        lbTrangThai.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbTrangThai.setForeground(new java.awt.Color(255, 0, 0));
        lbTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTrangThai.setText("Trạng Thái !");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(txtTien, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(lbTienDu, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(450, 450, 450)
                        .addComponent(lbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTrangChu)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(lbTongTien)
                    .addComponent(jLabel13)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14)
                    .addComponent(lbTienDu))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTrangThai)
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHoaDonMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonMoiActionPerformed
      
    }//GEN-LAST:event_btnHoaDonMoiActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        FormTrangChu_NhanVien nhanVien = new FormTrangChu_NhanVien(taiKhoan);
        nhanVien.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void cbxLoaiSanPhamPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxLoaiSanPhamPopupMenuWillBecomeInvisible

        taiLoaiThietBi();
        if (cbxTenSanPham.getItemCount() == 0) {
            cbxTenSanPham.setEnabled(false);
            txtSoLuong.setEnabled(false);
            refreshThietBi();
        } else {
            refreshThietBi();
            cbxTenSanPham.setEnabled(true);
        }
    }//GEN-LAST:event_cbxLoaiSanPhamPopupMenuWillBecomeInvisible

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        DecimalFormat formatter = new DecimalFormat("###,###,###");

        txtSoLuong.setText(cutChar(txtSoLuong.getText()));

        if (txtSoLuong.getText().equals("")) {
            String[] s = txtGia.getText().split("\\s");
            txtThanhTien.setText("0" + " " + s[1]);
        } else {
            thietBi = xuLy.layThietBiTheoMa(cbxTenSanPham.getSelectedItem().toString());

            if ((thietBi.getSoLuong() - Integer.parseInt(txtSoLuong.getText())) < 0) {
                String[] s = txtGia.getText().split("\\s");
                txtThanhTien.setText("0" + " " + s[1]);

                lbTrangThai.setText("Số lượng sản phẩm bán không được vượt quá số lượng hàng trong kho!!");
                btnLuu.setEnabled(false);
            } else {
                int soluong = Integer.parseInt(txtSoLuong.getText().toString());
                String[] s = txtGia.getText().split("\\s");
                txtThanhTien.setText(formatter.format(chuyenSangSo(s[0]) * soluong) + " " + s[1]);

                lbTrangThai.setText("Số lượng sản phẩm bán hợp lệ!!");
                btnLuu.setEnabled(true);
            }
        }
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        Refresh();
        Them = true;
        btnThem.setEnabled(false);
        btnLuu.setEnabled(true);
        Enabled();
        taiLoaiThietBi();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        Them = false;
        Sua = true;
        btnThem.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(true);
        txtSoLuong.setEnabled(true);
    }//GEN-LAST:event_btnSuaActionPerformed

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
            java.util.logging.Logger.getLogger(FormBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBanHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TaiKhoan taiKhoan = new TaiKhoan();
                new FormBanHang(taiKhoan).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoaDonMoi;
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbxLoaiSanPham;
    private javax.swing.JComboBox<String> cbxTenSanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbGio;
    private javax.swing.JLabel lbHinhAnh;
    private javax.swing.JLabel lbMaHoaDon;
    private javax.swing.JLabel lbNgay;
    private javax.swing.JLabel lbTenNhanVien;
    private javax.swing.JLabel lbTienDu;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JLabel lbTrangThai;
    private javax.swing.JTable tableHoaDon;
    private javax.swing.JTextField txtBaoHanh;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTien;
    // End of variables declaration//GEN-END:variables
    @Override
    public void run() {
        while (true) {
            Update();
            try {
                Thread.sleep(1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
