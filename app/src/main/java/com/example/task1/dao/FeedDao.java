package com.example.task1.dao;

import com.example.task1.link.Feedback;
import com.example.task1.link.Motor;
import com.example.task1.link.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedDao {
    connect link =new connect();
    Connection conn=null;
    PreparedStatement preparedStatement=null;
    String sql=null;

    public boolean addFeed(Feedback feed){
        sql="inster into motors(Ftype,Fallstar,Fcelan,Fadditude,Fprice,Fother) values(?,?,?,?,?,?)";
        conn=link.getconnection();

        try {
            PreparedStatement pst=conn.prepareStatement(sql);//预处理

            pst.setFloat(1,feed.getFtype());
            pst.setFloat(2,feed.getFallstar());
            pst.setInt(3,feed.getFclean());
            pst.setInt(4,feed.getFadditude());
            pst.setInt(5,feed.getFprice());
            pst.setString(6,feed.getFother());
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
