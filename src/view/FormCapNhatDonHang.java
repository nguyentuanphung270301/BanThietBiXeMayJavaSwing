
package view;

import DAO.KetNoiCoSoDuLieu;
import DAO.ShareData;
import controller.XuLy;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.ChiTietDatHang;
import model.DatHang;
import model.HoaDon;
import model.LoaiThietBi;
import model.TaiKhoan;
import model.ThietBi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class FormCapNhatDonHang extends javax.swing.JFrame {

    private TaiKhoan taiKhoan;
    private XuLy xuLy;
    private ThietBi thietBi; 
    private DatHang datHang;
    private HoaDon hoaDon;
    
    private boolean themDH = false, suaDonHang = false;
    private boolean themThietBi = false, suaThietBi = false;
    private boolean KH = false, TB = false;
    
    public FormCapNhatDonHang(TaiKhoan taiKhoan) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Cập nhật đơn đặt hàng");
        
        xuLy = new XuLy();
        taiKhoan = new TaiKhoan();
        
        taiDonDH();
        Disabled();
        ktHoaDon();
    }
    
     private void Enabled() {
        txtTenKhachHang.setEnabled(true);
        txtDiaChi.setEnabled(true);
        txtSoDienThoai.setEnabled(true);
        txtNgayDat.setEnabled(true);
        lbTrangThai.setText("Trạng Thái!");
    }
    private void EnabledThemTbietBi() {
        cbxLoaiSanPham.setEnabled(true);
        txtGia.setEnabled(true);
        txtThanhTien.setEnabled(true);
        txtBaoHanh.setEnabled(true);
        lbTrangThai.setText("Trạng Thái!");
    }
    private void Disabled() {
        cbxLoaiSanPham.setEnabled(false);
        txtTenKhachHang.setEnabled(false);
        txtDiaChi.setEnabled(false);
        txtSoDienThoai.setEnabled(false);
        cbxSanPham.setEnabled(false);
        txtSoLuong.setEnabled(false);
        txtGia.setEnabled(false);
        txtThanhTien.setEnabled(false);
        txtNgayDat.setEnabled(false);
        txtBaoHanh.setEnabled(false);
    }
    private void Refresh() {
        ktHoaDon();
        String[] arr = {"Mã Thiết Bị", "Số Lượng", "Ngày Đặt", "Thành Tiền"};
        DefaultTableModel modle = new DefaultTableModel(arr, 0);
        tableDonHang.setModel(modle);

        themDH = false;
        suaDonHang = false;
        themThietBi = false;
        suaThietBi = false;
        txtTenKhachHang.setText("");
        txtDiaChi.setText("");
        txtSoDienThoai.setText("");
        txtSoLuong.setText("");
        txtGia.setText("");
        txtThanhTien.setText("");
        lblTongTien.setText("0 VNĐ");
        ((JTextField) txtNgayDat.getDateEditor().getUiComponent()).setText("");
        cbxLoaiSanPham.removeAllItems();
        cbxSanPham.removeAllItems();
        btnXoaThietBi.setEnabled(false);
        btnSuaThietBi.setEnabled(false);
        btnLuu.setEnabled(false);
        btnSuaDonHang.setEnabled(false);
        btnXoaDonHang.setEnabled(false);
        btnThemThietBi.setEnabled(false);
        btnThemDonHang.setEnabled(true);
        taiDonDH();
        Disabled();

    }
     private void refreshThietBi() {
        txtGia.setText("");
        txtSoLuong.setText("");
        txtThanhTien.setText("");
        txtBaoHanh.setText("");
        lblTongTien.setText("0 VNĐ");
    }
    private void ktHoaDon() {
        if (cbxMaDonHang.getItemCount() == 0) {
            cbxMaDonHang.setEnabled(false);
        } else {
            cbxMaDonHang.setEnabled(true);
        }
    }
    private void taiMaLoaiThietBi() {
        cbxLoaiSanPham.removeAllItems();
        ArrayList arry = xuLy.layLoaiThietBi();
        for (int i = 0; i < arry.size(); i++) {
            cbxLoaiSanPham.addItem(((LoaiThietBi) arry.get(i)).getMaLoai() + " (" + ((LoaiThietBi) arry.get(i)).getTenLoai() + ")");
        }
    }
    private void taiMaThietBi() {
        cbxSanPham.removeAllItems();
        String[] test = cbxLoaiSanPham.getSelectedItem().toString().split("\\s");

        ArrayList arry = xuLy.layThietBiTheoMaLoai(test[0]);

        for (int i = 0; i < arry.size(); i++) {
            cbxSanPham.addItem(((ThietBi) arry.get(i)).getMaThietBi()+ " (" + ((ThietBi) arry.get(i)).getTenThietBi()+ ")");
        }
    }
    private void taiThongTinThietBi() {

        String[] test = cbxSanPham.getSelectedItem().toString().split("\\s");

        thietBi = new ThietBi(xuLy.layThietBiTheoMa(test[0]));
        txtGia.setText(xuLyGia(thietBi.getGia()) + " VNĐ");
        txtBaoHanh.setText(thietBi.getTgBaoHanh());
    }
    
    private void ktSoLuongThietBi() {
        thietBi = new ThietBi(xuLy.layThietBiTheoMa(cbxSanPham.getSelectedItem().toString()));

        if (thietBi.getSoLuong() <= 0) {
            lbTrangThai.setText("Sản phẩm này đã hết hàng!!");
            btnLuu.setEnabled(false);
            txtSoLuong.setEnabled(false);
        } else {
            lbTrangThai.setText("Mặt hàng này còn " + thietBi.getSoLuong() + " sản phẩm!!");
            btnLuu.setEnabled(true);
            txtSoLuong.setEnabled(true);
        }
    }
    private boolean ktSoLuongThietBi(String soLuongDat) {
        thietBi = new ThietBi(xuLy.layThietBiTheoMa(cbxSanPham.getSelectedItem().toString()));
        boolean kq = true;
        if (thietBi.getSoLuong() < Integer.parseInt(soLuongDat)) {
            lbTrangThai.setText("Số lượng đặt quá lớn so với số lượng hàng hiện có !!");
            kq = false;
            txtSoLuong.setText("");
        } 
        return kq; 
    }
    private float chuyenSangSo(String s) {
        String number = "";
        String[] array = s.replace(",", " ").split("\\s");
        for (String i : array) {
            number = number.concat(i);
        }
        return Float.parseFloat(number);
    }
    private String cutChar(String arry) {
        return arry.replaceAll("\\D+", "");
    }
    private void taiDonDH() {
        cbxMaDonHang.removeAllItems();
        ArrayList array = xuLy.layDatHang();
        for (int i = 0; i < array.size(); i++) {
            cbxMaDonHang.addItem(((DatHang) array.get(i)).getMaDonHang());
        }
    }
    
    private void donHangMoi() {
        if (xuLy.layMaDonHang()< 10) {
            cbxMaDonHang.addItem("DH" + "0" + String.valueOf(xuLy.layMaDonHang()+ 1));
            cbxMaDonHang.setSelectedItem("ĐH" + "0" + String.valueOf(xuLy.layMaDonHang()+ 1));
        } else {
            cbxMaDonHang.addItem("DH" + String.valueOf(xuLy.layMaDonHang()+ 1));
            cbxMaDonHang.setSelectedItem("DH" + String.valueOf(xuLy.layMaDonHang()+ 1));
        }
    }
    private void taiThongTinDonHang() {
        datHang = new DatHang(xuLy.layThongTinDatHang(cbxMaDonHang.getSelectedItem().toString()));
        txtTenKhachHang.setText(datHang.getTenKhachHang());
        txtDiaChi.setText(datHang.getDiaChi());
        txtSoDienThoai.setText(datHang.getSoDienThoai());
        txtNgayDat.setDate(datHang.getNgayDat());
    }
    private void taiThietBiDatHang() {
        tableDonHang.removeAll();
        String[] arr = {"Mã Thiết Bị", "Số Lượng", "Thành Tiền"};
        DefaultTableModel modle = new DefaultTableModel(arr, 0);

        ArrayList array = xuLy.layThietBiDat(cbxMaDonHang.getSelectedItem().toString());
        for (int i = 0; i < array.size(); i++) {
            Vector vector = new Vector();
            vector.add(((ChiTietDatHang) array.get(i)).getMaThietBi()+ " (" + xuLy.layThietBiTheoMa(((ChiTietDatHang) array.get(i)).getMaThietBi()).getTenThietBi().trim() + ")");
            vector.add(((ChiTietDatHang) array.get(i)).getSoLuong());
            vector.add(xuLyGia(((ChiTietDatHang) array.get(i)).getThanhTien())+ " VNĐ");
            modle.addRow(vector);
        }
        tableDonHang.setModel(modle);
    }
    private void thanhToan() {
        lblTongTien.setText("0 VNĐ");
        ArrayList array = xuLy.layThietBiDat(cbxMaDonHang.getSelectedItem().toString());
        for (int i = 0; i < array.size(); i++) {
            String[] s1 = ((ChiTietDatHang) array.get(i)).getThanhTien().trim().split("\\s");
            String[] s2 = lblTongTien.getText().split("\\s");
            double tongTien = chuyenSangSo(s1[0]) +  chuyenSangSo(s2[0]);
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            lblTongTien.setText(formatter.format(tongTien) + " VNĐ");
        }
    }
    private void ktDonHang() {
        ArrayList array = xuLy.layThietBiDat(cbxMaDonHang.getSelectedItem().toString());
        if (array.size() != 0) {
            btnInDonHang.setEnabled(true);
        } else {
            btnInDonHang.setEnabled(false);
        }
    }
     private void consistency(String maDonHang) {

        ArrayList arrayListHoaDon = xuLy.layDonHang(maDonHang);
        for (int i = 0; i < arrayListHoaDon.size(); i++) {
            thietBi = xuLy.layThietBiTheoMa(((HoaDon) arrayListHoaDon.get(i)).getMaThietBi().trim());
            //String []array=thietBi.getGia().split("\\s");
            xuLy.thayDoiSoLuongThietBi(thietBi.getMaThietBi(), thietBi.getSoLuong() - ((HoaDon) arrayListHoaDon.get(i)).getSoLuong());
        }
    }
    private String xuLyGia(String s){
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(chuyenSangSo(s));
    }
     public boolean ktSDT(String s){
        String Phone = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        boolean kt = s.matches(Phone);
        return kt;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnTrangChu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtNgayDat = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        btnLamMoi = new javax.swing.JButton();
        btnThemDonHang = new javax.swing.JButton();
        btnSuaThietBi = new javax.swing.JButton();
        btnXoaDonHang = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnThemThietBi = new javax.swing.JButton();
        btnSuaDonHang = new javax.swing.JButton();
        btnXoaThietBi = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableDonHang = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbxLoaiSanPham = new javax.swing.JComboBox<>();
        cbxSanPham = new javax.swing.JComboBox<>();
        txtBaoHanh = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        txtThanhTien = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbxMaDonHang = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        btnInDonHang = new javax.swing.JButton();
        lbTrangThai = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTrangChu.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow-back-icon.png"))); // NOI18N
        btnTrangChu.setText("Trang Chủ");
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });
        getContentPane().add(btnTrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 186, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CẬP NHẬT ĐƠN HÀNG");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 13, 1030, 57));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Tên khách hàng");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Địa chỉ");

        txtTenKhachHang.setEnabled(false);

        txtDiaChi.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Số điện thoại");

        txtSoDienThoai.setEnabled(false);
        txtSoDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoDienThoaiKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Ngày đặt");

        txtNgayDat.setDateFormatString("dd/MM/yyyy");
        txtNgayDat.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(12, 12, 12)
                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtDiaChi))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(85, 85, 85))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgayDat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 88, 760, 120));

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh-icon.png"))); // NOI18N
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThemDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/math-add-icon.png"))); // NOI18N
        btnThemDonHang.setText("Đơn hàng");
        btnThemDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDonHangActionPerformed(evt);
            }
        });

        btnSuaThietBi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pencil-icon.png"))); // NOI18N
        btnSuaThietBi.setText("Thiết bị");
        btnSuaThietBi.setEnabled(false);
        btnSuaThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThietBiActionPerformed(evt);
            }
        });

        btnXoaDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Recycle-Bin-icon.png"))); // NOI18N
        btnXoaDonHang.setText("Đơn hàng");
        btnXoaDonHang.setEnabled(false);
        btnXoaDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDonHangActionPerformed(evt);
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

        btnThemThietBi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/math-add-icon.png"))); // NOI18N
        btnThemThietBi.setText("Thiết bị");
        btnThemThietBi.setEnabled(false);
        btnThemThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThietBiActionPerformed(evt);
            }
        });

        btnSuaDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/pencil-icon.png"))); // NOI18N
        btnSuaDonHang.setText("Đơn hàng");
        btnSuaDonHang.setEnabled(false);
        btnSuaDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDonHangActionPerformed(evt);
            }
        });

        btnXoaThietBi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Recycle-Bin-icon.png"))); // NOI18N
        btnXoaThietBi.setText("Thiết bị");
        btnXoaThietBi.setEnabled(false);
        btnXoaThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThietBiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnThemDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSuaDonHang)
                .addGap(18, 18, 18)
                .addComponent(btnXoaDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThemThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoi)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaThietBi)
                            .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemDonHang)
                        .addComponent(btnSuaDonHang)
                        .addComponent(btnXoaDonHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThemThietBi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSuaThietBi)))
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 1230, 70));

        tableDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDonHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableDonHang);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 435, 1240, 260));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Loại thiết bị:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Bảo Hành:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Số Lượng:");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Giá:");
        jLabel10.setToolTipText("");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Thành Tiền:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Thiết bị:");

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

        cbxSanPham.setEnabled(false);
        cbxSanPham.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbxSanPhamPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        txtBaoHanh.setEditable(false);
        txtBaoHanh.setEnabled(false);

        txtGia.setEditable(false);
        txtGia.setEnabled(false);

        txtThanhTien.setEditable(false);
        txtThanhTien.setEnabled(false);

        txtSoLuong.setEnabled(false);
        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBaoHanh)
                            .addComponent(cbxLoaiSanPham, javax.swing.GroupLayout.Alignment.LEADING, 0, 73, Short.MAX_VALUE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoLuong)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel12)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbxSanPham, 0, 127, Short.MAX_VALUE)
                    .addComponent(txtGia)
                    .addComponent(txtThanhTien))
                .addGap(37, 37, 37))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel6)
                    .addComponent(cbxLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(33, 33, 33))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 80, 460, 200));

        jLabel15.setFont(new java.awt.Font("Tahoma", 3, 20)); // NOI18N
        jLabel15.setText("Mã Đơn Hàng:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 154, 30));

        cbxMaDonHang.setEnabled(false);
        cbxMaDonHang.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbxMaDonHangPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        getContentPane().add(cbxMaDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 180, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Tổng Tiền Đơn Hàng:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 180, -1));

        lblTongTien.setFont(new java.awt.Font("Tahoma", 3, 15)); // NOI18N
        lblTongTien.setText("0 VNĐ");
        getContentPane().add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 240, 190, -1));

        btnInDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/printer-icon.png"))); // NOI18N
        btnInDonHang.setText("In Đơn Hàng");
        btnInDonHang.setEnabled(false);
        btnInDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInDonHangActionPerformed(evt);
            }
        });
        getContentPane().add(btnInDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        lbTrangThai.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbTrangThai.setForeground(new java.awt.Color(255, 0, 51));
        lbTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTrangThai.setText("Trạng thái!");
        getContentPane().add(lbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, 670, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        Refresh();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
       FormTrangChu_NhanVien nhanVien = new FormTrangChu_NhanVien(taiKhoan);
        nhanVien.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnSuaThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThietBiActionPerformed
        themThietBi = false;
        KH = false;
        TB=  true;
        suaThietBi = true;

        btnThemThietBi.setEnabled(false);
        btnXoaThietBi.setEnabled(false);
        btnSuaThietBi.setEnabled(false);
        btnLuu.setEnabled(true);

        txtSoLuong.setEnabled(true);
        txtGia.setEnabled(true);
        txtThanhTien.setEnabled(true);
        txtBaoHanh.setEnabled(true);
        taiMaLoaiThietBi();
    }//GEN-LAST:event_btnSuaThietBiActionPerformed

    private void btnThemDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDonHangActionPerformed
        int Click = JOptionPane.showConfirmDialog(this, "Bạn có muốn tạo 1 hóa đơn bán hàng mới hay không?", "Thông Báo", 2);
        if (Click == JOptionPane.YES_OPTION) {
            Refresh();
            themDH = true;
            suaDonHang = false;
            KH = true;
            btnThemDonHang.setEnabled(false);
            btnLuu.setEnabled(true);
            Enabled();
        }
    }//GEN-LAST:event_btnThemDonHangActionPerformed

    private void btnInDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInDonHangActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xuất đơn đặt hàng hay không?", "Thông Báo", 2);
        String MADDH = cbxMaDonHang.getSelectedItem().toString().trim();
        if (Click == JOptionPane.YES_OPTION) {
            try {
                HashMap hash = new HashMap();
                hash.put("MADDH",MADDH);
                JasperReport report = JasperCompileManager.compileReport("src\\View\\DonDatHang.jrxml");
                JasperPrint print = JasperFillManager.fillReport(report, hash, KetNoiCoSoDuLieu.layKetNoi());
                lbTrangThai.setText("Thực hiện xuất đơn đặt hàng công!");
                JasperViewer.viewReport(print, false);
                xuLy.chuyenTrangThaiDatHang(MADDH);
                taiDonDH();
                consistency(MADDH);
            } catch (JRException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnInDonHangActionPerformed

    private void btnThemThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThietBiActionPerformed
        themThietBi = true;
        KH = false;
        TB = true;
        suaThietBi = false;

        btnThemThietBi.setEnabled(false);
        btnLuu.setEnabled(true);
        EnabledThemTbietBi();
        taiMaLoaiThietBi();
    }//GEN-LAST:event_btnThemThietBiActionPerformed

    private void btnXoaThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThietBiActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thiết bị khỏi đơn đặt hàng hay không?", "Thông Báo", 2);
        if (Click == JOptionPane.YES_OPTION) {
            String[] array = cbxSanPham.getSelectedItem().toString().split("\\s");
            xuLy.xoaThietBiKhoiDonDat(cbxMaDonHang.getSelectedItem().toString(), array[0]);
            taiThietBiDatHang();
            refreshThietBi();
            ktHoaDon();
            thanhToan();
            btnThemThietBi.setEnabled(true);
            btnSuaThietBi.setEnabled(false);
            btnXoaThietBi.setEnabled(false);
            this.lbTrangThai.setText("Xóa thiết bị thành công!");
        }
    }//GEN-LAST:event_btnXoaThietBiActionPerformed

    private void btnSuaDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDonHangActionPerformed
        suaDonHang = true;
        themDH = false;
        KH = true;
        TB = false;
        btnThemDonHang.setEnabled(false);
        btnXoaDonHang.setEnabled(false);
        btnSuaDonHang.setEnabled(false);
        btnThemThietBi.setEnabled(false);
        btnSuaThietBi.setEnabled(false);
        btnXoaThietBi.setEnabled(false);
        btnLuu.setEnabled(true);

        Disabled();
        refreshThietBi();
        Enabled();
    }//GEN-LAST:event_btnSuaDonHangActionPerformed

    private void btnXoaDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDonHangActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa đơn hàng này hay không?", "Thông Báo", 2);
        if (Click == JOptionPane.YES_OPTION) {
            xuLy.xoaDonDatHang(cbxMaDonHang.getSelectedItem().toString());
            taiThietBiDatHang();
            Refresh();
            ktHoaDon();
            refreshThietBi();
            this.lbTrangThai.setText("Xóa đơn hàng thành công!");
        }
    }//GEN-LAST:event_btnXoaDonHangActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
         if (KH == true) {
            if (themDH == true) {
                if(ktSDT(txtSoDienThoai.getText())== false){
                        JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại !","Lỗi",2);
                    }
                    else{
                donHangMoi();
                xuLy.themDonHangMoi(cbxMaDonHang.getSelectedItem().toString(), txtTenKhachHang.getText(),
                txtDiaChi.getText(), txtSoDienThoai.getText(),ShareData.nguoiDangNhap.getMaNhanVien() ,
                new java.sql.Date(txtNgayDat.getDate().getTime()),0);
                taiDonDH();
                Refresh();
                ktHoaDon();
                lbTrangThai.setText("Thêm đơn đặt hàng thành công!!");
                    }
            } else if (suaDonHang == true) {
                if(ktSDT(txtSoDienThoai.getText())== false){
                        JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại !","Lỗi",2);
                    }
                    else{
                xuLy.suaDonHang(txtTenKhachHang.getText(), txtDiaChi.getText(), txtSoDienThoai.getText(),
                        new java.sql.Date(txtNgayDat.getDate().getTime()),cbxMaDonHang.getSelectedItem().toString());
                taiThongTinDonHang();
                Refresh();
                Disabled();
                btnLuu.setEnabled(false);
                lbTrangThai.setText("Sửa đơn đặt hàng thành công!!");
                }
            }
            
        }else if (TB == true) {
            if (themThietBi == true) {
                String[] array = cbxSanPham.getSelectedItem().toString().split("\\s");
                String[] gia = txtThanhTien.getText().split("\\s");
                if (xuLy.ktThietBiDonHang(cbxMaDonHang.getSelectedItem().toString(), array[0])) {
                    if(ktSoLuongThietBi(txtSoLuong.getText())){
                    xuLy.themThietBiDonHang(cbxMaDonHang.getSelectedItem().toString(), array[0], Integer.parseInt(txtSoLuong.getText()), gia[0]);
                    
                    taiThietBiDatHang();
                    refreshThietBi();
                    Disabled();
                    thanhToan();
                    btnLuu.setEnabled(false);
                    btnThemThietBi.setEnabled(true);
                    }
                } else {
                    lbTrangThai.setText("Thiết bị đã tồn tại trong đơn hàng!!");
                }
            } else if (suaThietBi == true) {
                String[] gia = txtThanhTien.getText().split("\\s");
                String[] arry1 = cbxSanPham.getSelectedItem().toString().split("\\s");
                xuLy.suaThietBiDonHang(cbxMaDonHang.getSelectedItem().toString(), arry1[0], Integer.parseInt(txtSoLuong.getText()), gia[0]);
                taiThietBiDatHang();
                refreshThietBi();
                Disabled();
                thanhToan();
                btnLuu.setEnabled(false);
                btnThemThietBi.setEnabled(true);
                lbTrangThai.setText("Sửa thiết bị thành công !!");
            }
        }
        ktDonHang();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void txtSoDienThoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDienThoaiKeyReleased
        txtSoDienThoai.setText(cutChar(txtSoDienThoai.getText()));
    }//GEN-LAST:event_txtSoDienThoaiKeyReleased

    private void cbxSanPhamPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxSanPhamPopupMenuWillBecomeInvisible
        taiThongTinThietBi();
        txtSoLuong.setEnabled(true);
        ktSoLuongThietBi();
    }//GEN-LAST:event_cbxSanPhamPopupMenuWillBecomeInvisible

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        txtSoLuong.setText(cutChar(txtSoLuong.getText()));
        if (txtSoLuong.getText().equals("")) {
            String[] s = txtGia.getText().split("\\s");
            txtThanhTien.setText("0" + " " + s[1]);
        } else {
            int soluong = Integer.parseInt(txtSoLuong.getText());

            String[] s = txtGia.getText().split("\\s");

            txtThanhTien.setText(formatter.format(chuyenSangSo(s[0]) * soluong) + " " + s[1]);
        }
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void cbxLoaiSanPhamPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxLoaiSanPhamPopupMenuWillBecomeInvisible
        taiMaThietBi();
        if (cbxSanPham.getItemCount() == 0) {
            cbxSanPham.setEnabled(false);
            txtSoLuong.setEnabled(false);
            refreshThietBi();
        } else {
            refreshThietBi();
            cbxSanPham.setEnabled(true);
        }
    }//GEN-LAST:event_cbxLoaiSanPhamPopupMenuWillBecomeInvisible

    private void cbxMaDonHangPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxMaDonHangPopupMenuWillBecomeInvisible
        refreshThietBi();
        taiThongTinDonHang();
        taiThietBiDatHang();

        ktDonHang();
        thanhToan();
        btnSuaDonHang.setEnabled(true);
        btnXoaDonHang.setEnabled(true);
        btnThemThietBi.setEnabled(true);
    }//GEN-LAST:event_cbxMaDonHangPopupMenuWillBecomeInvisible

    private void tableDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDonHangMouseClicked
        cbxSanPham.removeAllItems();
        cbxLoaiSanPham.removeAllItems();

        int Click = tableDonHang.getSelectedRow();
        TableModel model = tableDonHang.getModel();

        cbxSanPham.addItem(model.getValueAt(Click, 0).toString());
        txtSoLuong.setText(model.getValueAt(Click, 1).toString());

        txtThanhTien.setText(model.getValueAt(Click, 2).toString());

        taiThongTinThietBi();

        btnThemThietBi.setEnabled(true);
        btnXoaThietBi.setEnabled(true);
        btnSuaThietBi.setEnabled(true);
    }//GEN-LAST:event_tableDonHangMouseClicked

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
            java.util.logging.Logger.getLogger(FormCapNhatDonHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCapNhatDonHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCapNhatDonHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCapNhatDonHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TaiKhoan taiKhoan = new TaiKhoan(); 
               new FormCapNhatDonHang(taiKhoan).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInDonHang;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSuaDonHang;
    private javax.swing.JButton btnSuaThietBi;
    private javax.swing.JButton btnThemDonHang;
    private javax.swing.JButton btnThemThietBi;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JButton btnXoaDonHang;
    private javax.swing.JButton btnXoaThietBi;
    private javax.swing.JComboBox<String> cbxLoaiSanPham;
    private javax.swing.JComboBox<String> cbxMaDonHang;
    private javax.swing.JComboBox<String> cbxSanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lbTrangThai;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tableDonHang;
    private javax.swing.JTextField txtBaoHanh;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtGia;
    private com.toedter.calendar.JDateChooser txtNgayDat;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtThanhTien;
    // End of variables declaration//GEN-END:variables
}
