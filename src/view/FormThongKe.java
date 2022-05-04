/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.KetNoiCoSoDuLieu;
import controller.XuLy;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.ThongKe;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author phucn
 */
public class FormThongKe extends javax.swing.JFrame {

    private XuLy xuLy;
    private String ngay = "1/1/2020";
    public FormThongKe() {
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Thống kê");
        
        xuLy = new XuLy();
        load();
    }

    private void taiNgay() {
        try {
            txtTuNgay.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(ngay));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        txtDenNgay.setDate(new java.util.Date());
    }
    private void load() {
        int count = 0;
        double tongTien = 0;
        DecimalFormat formatter = new DecimalFormat("###,###,###");

        String[] arr = {"Mã hóa đơn", "Nhân viên bán hàng", "Ngày bán","Tổng tiền hóa đơn"};
        DefaultTableModel model = new DefaultTableModel(arr, 0);

        ArrayList array_DonHang = xuLy.layThongKe();
        ArrayList array_HoaDon = xuLy.layThongKeHoaDon();
        ArrayList array = new ArrayList();
        array.addAll(array_DonHang);
        array.addAll(array_HoaDon);
        for (int i = 0; i < array.size(); i++) {
            Vector vector = new Vector();
            vector.add(((ThongKe) array.get(i)).getMaHoaDon());
            vector.add(((ThongKe) array.get(i)).getHoTenNhanVien());
            vector.add(((ThongKe) array.get(i)).getNgayBan());
            vector.add(xuLyGia(((ThongKe) array.get(i)).getTongTien())+" VNĐ");
            
           model.addRow(vector);
            String[] s = ((ThongKe) array.get(i)).getTongTien().trim().split("\\s");

            tongTien = chuyenSangSo(s[0]) + tongTien;
            count++;
        }
        tableThongKe.setModel(model);
        lbSoHoaDon.setText(String.valueOf(count));
        lbTongDoanhThu.setText(formatter.format(tongTien) + " " + "VNĐ");
    }
    private void taiThongKe() {
        int count = 0;
        double tongTien = 0;
        DecimalFormat formatter = new DecimalFormat("###,###,###");

        String[] arr = {"Mã hóa đơn", "Nhân viên bán hàng", "Ngày bán", "Tổng tiền hóa đơn"};
        DefaultTableModel model = new DefaultTableModel(arr, 0);

        ArrayList array_DonDat = xuLy.layThongKe(new java.sql.Date(txtTuNgay.getDate().getTime()), new java.sql.Date(txtDenNgay.getDate().getTime()));
        ArrayList array_HoaDon = xuLy.layThongKeHoaDon(new java.sql.Date(txtTuNgay.getDate().getTime()), new java.sql.Date(txtDenNgay.getDate().getTime()));
        ArrayList array = new ArrayList();
        array.addAll(array_DonDat);
        array.addAll(array_HoaDon);
        for (int i = 0; i < array.size(); i++) {
            Vector vector = new Vector();
            vector.add(((ThongKe) array.get(i)).getMaHoaDon());
            vector.add(((ThongKe) array.get(i)).getHoTenNhanVien());
            vector.add(((ThongKe) array.get(i)).getNgayBan());
            vector.add(xuLyGia(((ThongKe) array.get(i)).getTongTien()));

            model.addRow(vector);
            String[] s = ((ThongKe) array.get(i)).getTongTien().trim().split("\\s");

            tongTien = chuyenSangSo(s[0]) + tongTien;
            count++;
        }

        tableThongKe.setModel(model);
        lbSoHoaDon.setText(String.valueOf(count));
        lbTongDoanhThu.setText(formatter.format(tongTien) + " " + "VNĐ");
    }
    private Double chuyenSangSo(String s) {
        String number = "";
        String[] array = s.replace(",", " ").split("\\s");
        for (String i : array) {
            number = number.concat(i);
        }
        return Double.parseDouble(number);
    }
    private String xuLyGia(String s){
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(chuyenSangSo(s));
    }
        private void bieuDo(){
        
        ArrayList array_DonDat = xuLy.layThongKe(new java.sql.Date(txtTuNgay.getDate().getTime()), new java.sql.Date(txtDenNgay.getDate().getTime()));
        ArrayList array_HoaDon = xuLy.layThongKeHoaDon(new java.sql.Date(txtTuNgay.getDate().getTime()), new java.sql.Date(txtDenNgay.getDate().getTime()));
        ArrayList array = new ArrayList();
        array.addAll(array_DonDat);
        array.addAll(array_HoaDon);

        int count = 0;

        String[] thoiGianKT = new String[array.size()];
        int[] soHoaDonKT = new int[array.size()];
        double[] doanhThu = new double[array.size()];

        int j = 0;
        for (int i = 0; i < array.size(); i++) {

            String[] chuoi = ((ThongKe) array.get(i)).getNgayBan().toString().split("-");
            String[] mang = ((ThongKe) array.get(i)).getTongTien().split("\\s");

            double doanhso = chuyenSangSo(mang[0]);
            if (i == 0) {
                thoiGianKT[j] = chuoi[1] + "/" + chuoi[0];
                soHoaDonKT[j] = 1;
                doanhThu[j] = doanhso;
                j++;
                count = count + 1;
            } else {
                String[] ktra = thoiGianKT[j - 1].replace("/", " ").split("\\s");
                if (chuoi[1].equals(ktra[0]) && chuoi[0].equals(ktra[1])) {
                    soHoaDonKT[j - 1] = soHoaDonKT[j - 1] + 1;
                    doanhThu[j - 1] = doanhThu[j - 1] + doanhso;
                } else {
                    thoiGianKT[j] = chuoi[1] + "/" + chuoi[0];
                    soHoaDonKT[j] = 1;
                    doanhThu[j] = doanhso;
                    j++;
                    count = count + 1;
                }

            }
        }
        
        LineChart LineChart = new LineChart(thoiGianKT, soHoaDonKT, doanhThu);

        ChartPanel chartPanel = new ChartPanel(LineChart.getLineChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));

        ChartPanel chart = new ChartPanel(LineChart.getLine());
        chart.setPreferredSize(new java.awt.Dimension(400, 400));

        JPanel jpanel = new JPanel();
        jpanel.setSize(1000, 400);

        jpanel.setLayout(new GridLayout());
        jpanel.add(chartPanel);
        jpanel.add(chart);

        JFrame jframe = new JFrame();
        jframe.setTitle("Biểu Đồ Thống Kê");

        jframe.add(jpanel);

        jframe.setSize(1280, 600);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.setVisible(true);

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnTrangChu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTuNgay = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtDenNgay = new com.toedter.calendar.JDateChooser();
        btnLamMoi = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnInThongKe = new javax.swing.JButton();
        rdDonDatHang = new javax.swing.JRadioButton();
        rdHoaDon = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableThongKe = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lbSoHoaDon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbTongDoanhThu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));

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
        jLabel1.setText("THỐNG KÊ DOANH THU");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Thống kê từ ngày:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 38, -1, 30));
        jPanel1.add(txtTuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 38, 220, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Đến ngày:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 124, -1, -1));
        jPanel1.add(txtDenNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 216, 30));

        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/refresh-32.png"))); // NOI18N
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel1.add(btnLamMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 149, 73));

        btnThongKe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/revenue-32.png"))); // NOI18N
        btnThongKe.setText("Thống Kê");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });
        jPanel1.add(btnThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, 170, 70));

        btnInThongKe.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnInThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_new/print-32.png"))); // NOI18N
        btnInThongKe.setText("In Thống Kê");
        btnInThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInThongKeActionPerformed(evt);
            }
        });
        jPanel1.add(btnInThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 40, 150, 70));

        buttonGroup1.add(rdDonDatHang);
        rdDonDatHang.setText("Đơn Đặt Hàng");
        jPanel1.add(rdDonDatHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 120, -1, -1));

        buttonGroup1.add(rdHoaDon);
        rdHoaDon.setText("Hoá Đơn");
        jPanel1.add(rdHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 120, -1, -1));

        tableThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableThongKe);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Tổng Số Hoá Đơn Bán Ra:");

        lbSoHoaDon.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbSoHoaDon.setText("0");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Tổng Tiền Hoá Đơn:");

        lbTongDoanhThu.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbTongDoanhThu.setText("0 VNĐ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lbSoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(lbTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTrangChu)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbSoHoaDon)
                    .addComponent(jLabel5)
                    .addComponent(lbTongDoanhThu))
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        taiNgay();
        load();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        if(((JTextField)txtTuNgay.getDateEditor().getUiComponent()).getText().equals("") || ((JTextField)txtDenNgay.getDateEditor().getUiComponent()).getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn khoảng thời gian thống kê","Thông Báo",1);
        }
        else{
        taiThongKe();
        bieuDo();
        }
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        FormTrangChu_QuanLy trangChu = new FormTrangChu_QuanLy();
        trangChu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnInThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInThongKeActionPerformed
        int Click = JOptionPane.showConfirmDialog(null, "Bạn có muốn in thống kê hay không?", "Thông Báo", 2);
        String tuNgay = ((JTextField)txtTuNgay.getDateEditor().getUiComponent()).getText();
        String denNgay = ((JTextField)txtDenNgay.getDateEditor().getUiComponent()).getText();
        if(Click == JOptionPane.YES_OPTION){
        if(rdDonDatHang.isSelected() == false && rdHoaDon.isSelected() == false){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn loại đơn cần in thống kê","Thông báo",1);
        }
        else if(((JTextField)txtTuNgay.getDateEditor().getUiComponent()).getText().equals("") || ((JTextField)txtDenNgay.getDateEditor().getUiComponent()).getText().equals("")){
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn khoảng thời gian thống kê","Thông Báo",1);
        }
        else if(rdDonDatHang.isSelected() == true){
            try{
                HashMap hash = new HashMap();
                hash.put("TUNGAY",tuNgay );
                hash.put("DENNGAY", denNgay);
                JasperReport report = JasperCompileManager.compileReport("src\\view\\ThongKe_DonDatHang.jrxml");
                JasperPrint print = JasperFillManager.fillReport(report, hash, KetNoiCoSoDuLieu.layKetNoi());
                JasperViewer.viewReport(print, false);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        else if(rdHoaDon.isSelected() == true){
            try{
                HashMap hash = new HashMap();
                hash.put("TUNGAY",tuNgay );
                hash.put("DENNGAY", denNgay);
                JasperReport report = JasperCompileManager.compileReport("src\\view\\ThongKe_HoaDon.jrxml");
                JasperPrint print = JasperFillManager.fillReport(report, hash, KetNoiCoSoDuLieu.layKetNoi());
                JasperViewer.viewReport(print, false);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        }
    }//GEN-LAST:event_btnInThongKeActionPerformed

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
            java.util.logging.Logger.getLogger(FormThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInThongKe;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbSoHoaDon;
    private javax.swing.JLabel lbTongDoanhThu;
    private javax.swing.JRadioButton rdDonDatHang;
    private javax.swing.JRadioButton rdHoaDon;
    private javax.swing.JTable tableThongKe;
    private com.toedter.calendar.JDateChooser txtDenNgay;
    private com.toedter.calendar.JDateChooser txtTuNgay;
    // End of variables declaration//GEN-END:variables
}
