
package DAO;

import java.sql.*;
public class KetNoiCoSoDuLieu {
    
    public static Connection layKetNoi(){
        Connection ketNoi = null;
        String uRL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyThietBiXeMay";
        String userName ="sa";
        String passWord ="123456";
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            ketNoi = DriverManager.getConnection(uRL,userName,passWord);
            System.out.println("Kết nối với CSDL thành công");
        }
        catch(ClassNotFoundException | SQLException ex){
            System.out.println("Kết nối với CSDL thất bại");
        }
        return ketNoi;
    }
}
