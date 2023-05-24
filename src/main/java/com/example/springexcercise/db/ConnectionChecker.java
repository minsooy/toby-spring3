package com.example.springexcercise.db;
import com.mysql.cj.x.protobuf.MysqlxSql;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

import java.sql.*;


public class ConnectionChecker {
    public void check() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://ec2-54-177-243-253.us-west-1.compute.amazonaws.com/spring-db","root","12345678");

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SHOW DATABASES");
        rs = st.getResultSet();
        while (rs.next()) {
            String str = rs.getString(1);
            System.out.println(str);
        }
    }

    public void add() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://ec2-54-177-243-253.us-west-1.compute.amazonaws.com/spring-db",
                "root", "12345678");

        PreparedStatement pstmt = con.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, "1");
        pstmt.setString(2, "minsoo");
        pstmt.setString(3, "12345678");
        pstmt.executeUpdate();
    }

    public void create() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://ec2-54-177-243-253.us-west-1.compute.amazonaws.com/spring-db",
                "root", "12345678");
        Statement st = con.createStatement();
        String sql = "CREATE TABLE `users` (`id` VARCHAR(10) NOT NULL, name VARCHAR(20) NOT NULL, " +
                "password VARCHAR(10) NOT NULL, PRIMARY KEY (`id`))";
        st.executeUpdate(sql);
    }

    public void select() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://ec2-54-177-243-253.us-west-1.compute.amazonaws.com/spring-db",
                "root", "12345678");

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from users");
        rs = st.getResultSet();
        while (rs.next()) {
            String str = rs.getString(1);
            String str2 = rs.getString(2);
            String str3 = rs.getString(3);
            System.out.println(str + str2 + str3);
        }
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionChecker cc = new ConnectionChecker();
        cc.create();
    }

}

