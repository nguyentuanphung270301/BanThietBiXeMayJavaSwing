/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.ShareData;
import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author phucn
 */
public class FormTrangChu_QuanLy extends javax.swing.JFrame implements Runnable{

    private Thread thread;
    public FormTrangChu_QuanLy() {
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
        btnCapNhatNhanVien = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnCapNhatTaiKhoan = new javax.swing.JButton();
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
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PHẦN MỀM BÁN THIẾT BỊ XE MÁY");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 925, 63));

        btnCapNhatNhanVien.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCapNhatNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Apps-system-users-icon.png"))); // NOI18N
        btnCapNhatNhanVien.setText("Quản lý nhân viên");
        btnCapNhatNhanVien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCapNhatNhanVien.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnCapNhatNhanVien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCapNhatNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatNhanVienActionPerformed(evt);
            }
        });
        getContentPane().add(btnCapNhatNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, 113));

        btnThongKe.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cash-icon.png"))); // NOI18N
        btnThongKe.setText("Thống kê doanh thu");
        btnThongKe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThongKe.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnThongKe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });
        getContentPane().add(btnThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, 113));

        btnCapNhatTaiKhoan.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCapNhatTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User-icon.png"))); // NOI18N
        btnCapNhatTaiKhoan.setText("Cập nhật tài khoản");
        btnCapNhatTaiKhoan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCapNhatTaiKhoan.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnCapNhatTaiKhoan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCapNhatTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatTaiKhoanActionPerformed(evt);
            }
        });
        getContentPane().add(btnCapNhatTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, 157, 113));

        btnDangXuat.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Apps-session-logout-icon.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        getContentPane().add(btnDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, 155, 113));

        btnThoat.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Windows-Close-Program-icon (1).png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThoat.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnThoat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        getContentPane().add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, 159, 113));

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
                    .addComponent(lblHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 18, Short.MAX_VALUE)))
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

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        int click = JOptionPane.showConfirmDialog(this,"Bạn có muốn thoát chương trình hay không ?","Thông báo", 2);
        if(click == JOptionPane.OK_OPTION){
            System.exit(0);
        }
        else if(click==JOptionPane.CANCEL_OPTION){
            this.setVisible(true);
        }
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
             FormDangNhap dangNhap = new FormDangNhap();
             dangNhap.setVisible(true);
             this.dispose();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnCapNhatNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatNhanVienActionPerformed
        FormQuanLyNhanVien capNhatNhanVien = new FormQuanLyNhanVien();
        capNhatNhanVien.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCapNhatNhanVienActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        FormThongKe thongKe = new FormThongKe();
        thongKe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnCapNhatTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatTaiKhoanActionPerformed
        FormCapNhatTaiKhoan capNhatTaiKhoan = new FormCapNhatTaiKhoan();
        capNhatTaiKhoan.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCapNhatTaiKhoanActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        DangNhapThanhCong();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         int click = JOptionPane.showConfirmDialog(this,"Bạn có muốn thoát chương trình hay không ?","Thông báo", 2);
        if(click == JOptionPane.OK_OPTION){
            System.exit(0);
        }
        else if(click==JOptionPane.CANCEL_OPTION){
            this.setVisible(true);
        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(FormTrangChu_QuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTrangChu_QuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTrangChu_QuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTrangChu_QuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTrangChu_QuanLy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatNhanVien;
    private javax.swing.JButton btnCapNhatTaiKhoan;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnThongKe;
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
