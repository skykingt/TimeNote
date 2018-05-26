package com.example.administrator.timenote.Util;

import java.sql.SQLException;

public class DBUtil {
    private static final String jdbcUrl="jdbc:mysql://localhost:3306/booklib";
    private static final String dbUser="root";
    private static final String dbPwd="123456";
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static java.sql.Connection getConnection() throws java.sql.SQLException{
        return java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
    }
    public static void main(String[] args)
    {
        java.sql.Connection conn=null;
        try {
            conn=DBUtil.getConnection();
            String sql="select * from BeanBook";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            java.sql.ResultSet rs=pst.executeQuery();
            rs.next();
            System.out.print(rs.getString(1)+rs.getString(2));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
