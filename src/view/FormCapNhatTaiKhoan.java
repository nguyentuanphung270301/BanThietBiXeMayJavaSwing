/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.ShareData;
import controller.XuLy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.NhanVien;
import model.TaiKhoan;
/**
 *
 * @author phucn
 */
public class FormCapNhatTaiKhoan extends javax.swing.JFrame {

    boolean Them = false, Sua = false;
    private XuLy xuLy;
    public FormCapNhatTaiKhoan() {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Cập nhật tài khoản");
     
        xuLy = new XuLy();
        taiDSNhanVien();
        taiBangTaiKhoan();
        Disable();
    }
    public void Disable(){
        txtTenDangNhap.setEnabled(false);
        cbxTenNhanVien.setEnabled(false);
        cbxLoaiTaiKhoan.setEnabled(false);
    }
     public void Enable(){
        txtTenDangNhap.setEnabled(true);
        cbxTenNhanVien.setEnabled(true);
        cbxLoaiTaiKhoan.setEnabled(true);
    }
     public void Refresh(){
         cbxTenNhanVien.removeAllItems();
         
         taiDSNhanVien();
         Disable();
         Them = false;
         Sua = false;
         txtTenDangNhap.setText("");
         btnSua.setEnabled(false);
         btnThem.setEnabled(true);
         btnXoa.setEnabled(false);
         btnLuu.setEnabled(false); 
     }
     private boolean kiemTraRong(){
         boolean kq = true;
         if(this.txtTenDangNhap.getText().equals("")){
             JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên đăng nhập !","Thông báo",2);
         }
         else if(this.cbxTenNhanVien.getSelectedIndex() == -1){
             JOptionPane.showMessageDialog(this, "Bạn chưa chọn nhân viên được cấp tài khoản !","Thông báo",2);
         }
        return kq;
     }
     private void taiDSNhanVien(){
         ArrayList array = xuLy.layNhanVienChuaTaoTK();
         for(int i = 0;i<array.size();i++){
             this.cbxTenNhanVien.addItem(((NhanVien) array.get(i)).getMaNhanVien().trim()+" ("+ ((NhanVien) array.get(i)).getHoTen().trim()+")");
         }
     }
     private void taiBangTaiKhoan(){
         tableTaiKhoan.removeAll();
         String [] arr ={"Tên Đăng Nhập","Mã Nhân Viên","Loại Tài Khoản"};
         DefaultTableModel model = new DefaultTableModel(arr,0);
         
         ArrayList array = xuLy.layTaiKhoan();
         for(int i =0;i < array.size();i++){
             Vector vt = new Vector();
             vt.add(((TaiKhoan) array.get(i)).getTenDangNhap());
             vt.add(((TaiKhoan) array.get(i)).getMaNhanVien()+" ("+xuLy.layTenNV(((TaiKhoan) array.get(i)).getMaNhanVien())+")");
             vt.add(((TaiKhoan) array.get(i)).getLoaiTaiKhoan());
             model.addRow(vt);
         }
         tableTaiKhoan.setModel(model);
     }
     public void loaiTaiKhoan(String s){
         if(s.equals("QUẢN LÝ")){
             cbxLoaiTaiKhoan.setSelectedIndex(0);
         }
         else{
             cbxLoaiTaiKhoan.setSelectedIndex(1);
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
        txtTenDangNhap = new javax.swing.JTextField();
        cbxTenNhanVien = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbxLoaiTaiKhoan = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTaiKhoan = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));

        btnTrangChu.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/return-48.png"))); // NOI18N
        btnTrangChu.setText("Quay Lại");
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CẬP NHẬT TÀI KHOẢN");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Tên đăng nhập:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, 40));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Danh sách nhân viên:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 40));

        txtTenDangNhap.setEnabled(false);
        jPanel1.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 200, 30));

        cbxTenNhanVien.setEnabled(false);
        cbxTenNhanVien.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbxTenNhanVienPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel1.add(cbxTenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 200, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Loại tài khoản:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, -1, 30));

        cbxLoaiTaiKhoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "QUẢN LÝ", "NHÂN VIÊN" }));
        cbxLoaiTaiKhoan.setEnabled(false);
        cbxLoaiTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLoaiTaiKhoanActionPerformed(evt);
            }
        });
        jPanel1.add(cbxLoaiTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 190, 30));

        tableTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableTaiKhoan);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/plus-math-32.png"))); // NOI18N
        btnThem.setText("Tạo tài khoản");
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

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/refresh-32.png"))); // NOI18N
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addComponent(btnLamMoi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa)
                    .addComponent(btnLuu))
                .addGap(49, 49, 49)
                .addComponent(btnSua)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Danh Sách Tài Khoản");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1012, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTrangChu)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        Refresh();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int click = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá tài khoản này hay không ?","Xác nhận",2);
        String [] arr = cbxTenNhanVien.getSelectedItem().toString().split("\\s");
        if(click == JOptionPane.OK_OPTION){
            if(arr[0].equals(ShareData.nguoiDangNhap.getMaNhanVien().toString())){
                JOptionPane.showMessageDialog(this,"Không thể xoá tài khoản đang đăng nhập","Thông báo",2);
            }
            else{
            xuLy.xoaTaiKhoan(txtTenDangNhap.getText());
            Refresh();
            taiBangTaiKhoan();
            JOptionPane.showMessageDialog(this, "Xoá tài khoản thành công !","Thông báo",1);
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
       FormTrangChu_QuanLy trangChu = new FormTrangChu_QuanLy();
       trangChu.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void cbxLoaiTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLoaiTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxLoaiTaiKhoanActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        Refresh();
        Them = true;
        Enable();
        btnThem.setEnabled(false);
        btnLuu.setEnabled(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void cbxTenNhanVienPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxTenNhanVienPopupMenuWillBecomeInvisible
 
    }//GEN-LAST:event_cbxTenNhanVienPopupMenuWillBecomeInvisible

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        Them = false;
        Sua = true;
        txtTenDangNhap.setEnabled(true);
        cbxLoaiTaiKhoan.setEnabled(true);
        cbxTenNhanVien.setEnabled(false);
        btnSua.setEnabled(false);
        btnThem.setEnabled(false);
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(true);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        String[] array = cbxTenNhanVien.getSelectedItem().toString().split("\\s");
        String matKhau = xuLy.matKhauNgauNhien(8);
        ArrayList arr = xuLy.layTTNhanVien(array[0]);
        if(kiemTraRong()){
            if(Them == true){
                if(xuLy.ktTaiKhoan(txtTenDangNhap.getText())){
                    xuLy.themTaiKhoan(txtTenDangNhap.getText(),xuLy.maHoaMatKhau(matKhau),array[0],cbxLoaiTaiKhoan.getSelectedItem().toString());
                    System.out.println(matKhau);
                    Disable();
                    Refresh();
                    taiBangTaiKhoan();
                    try {
                        xuLy.guiMail(((NhanVien)arr.get(0)).getEmail().toString(),"Mật Khẩu Tài Khoản "+((NhanVien)arr.get(0)).getHoTen().toString(),"Mật khẩu tài khoản của bạn là: "+matKhau);
                        System.out.println("Gửi mail thành công !"); 
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(this, "Tạo tài khoản thành công !\n Mật khẩu được gửi về mail của bạn !","Thông báo",1);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại !","Thông báo",1);
                }
            }
            else{
                if(Sua == true){
                    int click = tableTaiKhoan.getSelectedRow();
                    TableModel model = tableTaiKhoan.getModel();
                    
                    if(model.getValueAt(click,0).toString().equals(txtTenDangNhap.getText())){
                        xuLy.suaTaiKhoan(txtTenDangNhap.getText(),cbxLoaiTaiKhoan.getSelectedItem().toString(), model.getValueAt(click, 0).toString().trim());
                        Disable();
                    Refresh();
                    taiBangTaiKhoan();
                    JOptionPane.showMessageDialog(this, "Lưu thay đổi thành công !","Thông báo",1);
                    }
                    else{
                        if(xuLy.ktTaiKhoan(txtTenDangNhap.getText())){
                            xuLy.suaTaiKhoan(txtTenDangNhap.getText(),cbxLoaiTaiKhoan.getSelectedItem().toString(),model.getValueAt(click, 0).toString().trim());
                            Disable();
                            Refresh();
                            taiBangTaiKhoan();
                            JOptionPane.showMessageDialog(this, "Lưu thay đổi thành công !","Thông báo",1);
                    }
                        else{
                            JOptionPane.showMessageDialog(this, "Tài khoản đăng nhập bạn sửa đã tồn tại !","Thông báo",1);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void tableTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTaiKhoanMouseClicked
        int click = tableTaiKhoan.getSelectedRow();
        TableModel model = tableTaiKhoan.getModel();
        
        cbxTenNhanVien.removeAllItems();
        txtTenDangNhap.setText(model.getValueAt(click,0).toString());
        cbxTenNhanVien.addItem(model.getValueAt(click,1).toString());
        loaiTaiKhoan(model.getValueAt(click, 2).toString());
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
    }//GEN-LAST:event_tableTaiKhoanMouseClicked

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
            java.util.logging.Logger.getLogger(FormCapNhatTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCapNhatTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCapNhatTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCapNhatTaiKhoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCapNhatTaiKhoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbxLoaiTaiKhoan;
    private javax.swing.JComboBox<String> cbxTenNhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableTaiKhoan;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
