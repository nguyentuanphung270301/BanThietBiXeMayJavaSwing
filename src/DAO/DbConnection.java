package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DbConnection {
    private static Connection getConnection() throws Exception {
        String uRL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThietBiXeMay"
                + ";encrypt=true;trustServerCertificate=true;";
        String userName ="sa";
        String passWord ="root";
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch(ClassNotFoundException ex){
            throw new Exception("Hệ thống gặp sự cố, vui lòng thông báo cho Quản trị viên để được hỗ trợ!");
        }
        
        Connection cn = null;

        try {
            cn = DriverManager.getConnection(uRL, userName, passWord);
        } catch (SQLException ex) {
            throw new Exception("Không thể kết nối tới cơ sở dữ liệu");
        }

        return cn;
    }
    
    public static ResultSet executeQuery(String queryText, Object ...params)
            throws Exception {
        Connection cn = getConnection();
        PreparedStatement ps = cn.prepareStatement(queryText);
        
        for (int i = 0; i < params.length; i ++) {
            ps.setObject(i + 1, params[i]);
        }
        
        ResultSet rs = ps.executeQuery();

        return rs;
    }
}
