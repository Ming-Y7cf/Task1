package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task1.link.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class registerActivity extends AppCompatActivity {
    private TextView register;
    private Button login;
    private EditText Euser,Epass;
    private int userid;
    private String username,password,Suserid;
    private Boolean flag=false;

    connect link =new connect();
    Connection conn=null;
    String sql=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ininView();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(registerActivity.this,Register2Activity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        username=Euser.getText().toString();
                        password=Epass.getText().toString();
                        flag=login(username,password);

                        if(flag==true) {
                            int cichu=finduser(username,password);
                            if (cichu!=0) {
                                System.out.println(cichu);
                                loginsure(flag);
                            }else{
                                Looper.prepare();
                                Toast.makeText(registerActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }else {
                            Looper.prepare();
                            Toast.makeText(registerActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                }).start();

            }
        });
    }

    void ininView(){
        register=(TextView)findViewById(R.id.register_R_down_bottom_T_new);
        login=(Button)findViewById(R.id.register_R_down_B_login);
        Euser=(EditText)findViewById(R.id.register_R_down_E_account);
        Epass=(EditText)findViewById(R.id.register_R_down_E_password);
    }
    int finduser(String username,String password){
        conn=link.getconnection();
        System.out.println("用户查找数据库链接成功"+conn);
        sql="select * from users where username= ? and password= ?";
        int findid=0;
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,password);
            ResultSet rs=pst.executeQuery();//用于返回当前id，便于识别
            if(rs.next()){
                findid=rs.getInt("id");
                System.out.println(findid+"此处id");
            }
            pst.close();
            return findid;
        }catch (SQLException e){
            System.out.println("数据错误");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
    boolean login(String username,String password){

        conn=link.getconnection();
        System.out.println("登录界面数据库链接成功"+conn);
        sql="select * from users where username= ? and password= ?";
        boolean a;
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,password);
            a=pst.execute();//执行sql 返回值 Boolean
            ResultSet rs=pst.executeQuery();//用于返回当前id，便于识别
            if(rs.next()){
                userid=rs.getInt("id");
                System.out.println(userid+"此处id");
            }
            pst.close();
           // System.out.println(a+"\n");
            return a;
        }catch (SQLException e){
            System.out.println("数据错误");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }
    void loginsure(Boolean x){
        if (x) {
            Looper.prepare();
            Toast.makeText(registerActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            Bundle bundle=new Bundle();
            Intent intent=new Intent();
            intent.setClass(registerActivity.this,MainActivity.class);
            bundle.putInt("userid",userid);
            intent.putExtras(bundle);
            startActivity(intent);
            Looper.loop();
        }
    }

}
