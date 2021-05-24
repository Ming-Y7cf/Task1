package com.example.task1.dao;

import com.example.task1.link.User;
import com.example.task1.link.DBUtils;
import com.example.task1.link.Usertest;
import com.example.task1.link.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    connect link =new connect();
    Connection conn=null;
    PreparedStatement preparedStatement=null;
    String sql=null;

    public boolean login(String name,String password){

        sql = "select * from users where name = ? and password = ?";

        conn =link.getconnection();

        try {
            PreparedStatement pst=conn.prepareStatement(sql);

            pst.setString(1,name);
            pst.setString(2,password);
            pst.execute();
            pst.close();
            return true;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

    }
    //注册测试
    public boolean adduser(String name,String pass){
        conn=link.getconnection();
        sql = "insert into usertest values(?,?)";
        try {
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,pass);
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        }catch (SQLException e){
            System.out.println("数据错误");
            System.out.println(e.getMessage());
            return false;
        }
    }
    //注册
    public boolean register(User user){

        sql = "insert into users(name,username,password,sex,phone) values (?,?,?,?,?)";
        conn=link.getconnection();

        try {
            PreparedStatement pst=conn.prepareStatement(sql);//预处理

            pst.setString(1,user.getUsername());
            pst.setString(2,user.getUsername());
            pst.setString(3,user.getPassword());
            pst.setString(4,user.getSex());
            pst.setString(5,user.getPhone());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            System.out.println("注册失败");
            System.out.println(e.getMessage());
            return false;
        }
    }
    //用于注册账号时查重
    public User findUser(String name){

        sql = "select * from users where name = ?";
        conn=link.getconnection();
        User user = null;
        try {
            PreparedStatement pst=conn.prepareStatement(sql);

            pst.setString(1,name);

            ResultSet rs = pst.executeQuery();

            while (rs.next()){

                int id = rs.getInt(0);
                String namedb = rs.getString(1);
                String username = rs.getString(2);
                String passworddb  = rs.getString(3);
                String sexdb =rs.getString(4);
                String phone = rs.getString(6);
                user = new User(id,namedb,username,passworddb,sexdb,phone);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }
    public boolean updateUser(){return false;}

}

