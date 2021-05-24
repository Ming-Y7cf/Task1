package com.example.task1.link;

import android.util.Log;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class connect {
    private String url="jdbc:mysql://10.0.2.2:3306/androidtest?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
    private String user="root";
    private String password="123456789";
    private Connection conn=null;
    private Statement sta=null;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println("加载驱动错误");
            System.out.println(e.getMessage());
        }
    }
    public Connection getconnection() {
        try {
            conn=DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println("链接失败");
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public Statement getstatement(){
        try {
            sta=conn.createStatement();
        }catch (SQLException e){
            System.out.println("sta错误");
            System.out.println(e.getMessage());
        }
        return sta;
    }
    }

