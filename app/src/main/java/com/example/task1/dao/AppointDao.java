package com.example.task1.dao;

import com.example.task1.link.Appoint;
import com.example.task1.link.Feedback;
import com.example.task1.link.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppointDao {
    connect link =new connect();
    Connection conn=null;
    PreparedStatement preparedStatement=null;
    String sql=null;

    public boolean addFeed(Appoint appoint){
        sql="inster into appoint(Aname,Aaddress,Aphone,Atype,Away,Anowtime,Aappointtime) values(?,?,?,?,?,?,?)";
        conn=link.getconnection();

        try {
            PreparedStatement pst=conn.prepareStatement(sql);//预处理

            pst.setString(1,appoint.getAname());
            pst.setString(2,appoint.getAaddress());
            pst.setString(3,appoint.getAphone());
            pst.setFloat(4,appoint.getAtype());
            pst.setString(5,appoint.getAway());
            pst.setDate(6,appoint.getAnowtime());
            pst.setString(7,appoint.getAappointtime());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            System.out.println("反馈失败");
            System.out.println(e.getMessage());
            return false;
        }
    }
}
