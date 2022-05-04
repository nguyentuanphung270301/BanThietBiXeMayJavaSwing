/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.ShareData;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import model.TaiKhoan;

/**
 *
 * @author phucn
 */
public class FormTrangChu_NhanVien extends javax.swing.JFrame implements Runnable{

    private TaiKhoan taiKhoan;
    private Thread thread;
    public FormTrangChu_NhanVien(TaiKhoan taiKhoan) {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Trang Chủ");
        Start();
    }

    public void Start(){
        if(thread == null){
            thread = new Thread(this);
            thread.start();
       }
    }
    
    public void Update(){
        lblDiaChi.setLocation(lblDiaChi.getX()-1, lblDiaChi.getY());
        if(lblDiaChi.getX()+lblDiaChi.getWidth()<0){
            lblDiaChi.setLocation(this.getWidth(),lblDiaChi.getY());
        }
    }
    public void DangNhapThanhCong(){
        lblHoTen.setText(ShareData.nguoiDangNhap.getHoTen());
        lblChucVu.setText(ShareData.nguoiDangNhap.getLoaiTaiKhoan());
        lblNgay.setText(String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date())));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnBanHang = new javax.swing.JButton();
        btnCapNhatSanPham = new javax.swing.JButton();
        btnCapNhatDonHang = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        lblDiaChi = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNgay = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 560));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PHẦN MỀM BÁN THIẾT BỊ XE MÁY");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 21, 925, 63));

        btnBanHang.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/sell-72.png"))); // NOI18N
        btnBanHang.setText("Bán hàng");
        btnBanHang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBanHang.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBanHang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });
        getContentPane().add(btnBanHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 222, 150, 130));

        btnCapNhatSanPham.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCapNhatSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/garage-72.png"))); // NOI18N
        btnCapNhatSanPham.setText("Quản lý thiết bị");
        btnCapNhatSanPham.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCapNhatSanPham.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnCapNhatSanPham.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCapNhatSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSanPhamActionPerformed(evt);
            }
        });
        getContentPane().add(btnCapNhatSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 222, 138, 130));

        btnCapNhatDonHang.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCapNhatDonHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/order-72.png"))); // NOI18N
        btnCapNhatDonHang.setText("Cập nhật đơn hàng");
        btnCapNhatDonHang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCapNhatDonHang.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnCapNhatDonHang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCapNhatDonHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatDonHangActionPerformed(evt);
            }
        });
        getContentPane().add(btnCapNhatDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 222, -1, 130));

        btnDangXuat.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/sign-out-72.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        getContentPane().add(btnDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(531, 222, 157, 130));

        btnThoat.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/delete-1-icon-72x72.png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThoat.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnThoat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        getContentPane().add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(706, 222, 159, 130));

        lblDiaChi.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblDiaChi.setForeground(new java.awt.Color(255, 51, 51));
        lblDiaChi.setText("Địa chỉ: 97 Đường Man Thiện, Hiệp Phú, Thành Phố Thủ Đức, Thành phố Hồ Chí Minh");
        getContentPane().add(lblDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 720, 50));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Tên nhân viên:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Chức vụ:");

        lblChucVu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblHoTen.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Ngày:");

        lblNgay.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(lblChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHoTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 430, 120));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        FormDangNhap dangNhap = new FormDangNhap();
        dangNhap.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        int click = JOptionPane.showConfirmDialog(this,"Bạn có muốn thoát chương trình hay không ?","Thông báo",2);
        if(click == JOptionPane.CANCEL_OPTION){
            this.setVisible(true);
        }
        else if(click == JOptionPane.OK_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        FormBanHang banHang = new FormBanHang(taiKhoan);
        banHang.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnCapNhatSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSanPhamActionPerformed
        FormQuanLyThietBi quanLySanPham = new FormQuanLyThietBi(taiKhoan);
        quanLySanPham.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCapNhatSanPhamActionPerformed

    private void btnCapNhatDonHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatDonHangActionPerformed
        FormCapNhatDonHang capNhatDonHang = new FormCapNhatDonHang(taiKhoan);
        capNhatDonHang.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCapNhatDonHangActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        DangNhapThanhCong();
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(FormTrangChu_NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTrangChu_NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTrangChu_NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTrangChu_NhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TaiKhoan taiKhoan = new TaiKhoan();
                new FormTrangChu_NhanVien(taiKhoan).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnCapNhatDonHang;
    private javax.swing.JButton btnCapNhatSanPham;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblNgay;
    // End of variables declaration//GEN-END:variables
@Override
    public void run(){
        long FPS =80;
        long period = 1000*1000000/FPS;
        long beginTime, sleepTime;
        
        beginTime = System.nanoTime();
        while(true){
            Update();
            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;
            try{
                if(sleepTime>0)
                    Thread.sleep(sleepTime/1000000);
                else    Thread.sleep(period/2000000);               
            }
            catch(Exception e){
                e.printStackTrace();
            }
            beginTime=System.nanoTime();
        }
    }
}
