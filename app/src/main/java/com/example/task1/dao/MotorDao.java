package com.example.task1.dao;

import com.example.task1.link.Motor;
import com.example.task1.link.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MotorDao {
    connect link =new connect();
    Connection conn=null;
    PreparedStatement preparedStatement=null;
    String sql=null;

    public boolean addMotor(Motor motor){
        sql="inster into motors(Mtype,Myears,Masterid,Mphone) values(?,?,?,?)";
        conn=link.getconnection();

        try {
            PreparedStatement pst=conn.prepareStatement(sql);//预处理

            pst.setString(1,motor.getType());
            pst.setFloat(2,motor.getYears());
            pst.setInt(3,motor.getMasterid());
            pst.setString(4,motor.getMphone());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            System.out.println("注册失败");
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean updateMotor(){
        return false;
    }
    public boolean deleteMotor(){return false;}

}
