package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.task1.link.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarsActivity extends AppCompatActivity {
    private Button back,change1,change2,Badd;
    private TextView TVadd,TVuserid,mid1,mid2,myears1,myears2,userid1,userid2,num;
    private int userid;
    private int Mid1,Mid2;

    connect link =new connect();
    Connection conn=null;
    String sql=null;
    Bundle bundle1=new Bundle();
    Bundle next=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        initView();
        bundle1=this.getIntent().getExtras();
        userid=bundle1.getInt("userid");
        System.out.println("我的车辆页面："+userid);
        initCars(userid);//加载车主
        findMid(userid);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(CarsActivity.this,MyActivity.class);
                next.putInt("userid",bundle1.getInt("userid"));
                intent.putExtras(next);
                startActivity(intent);
            }
        });
        change1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent=new Intent();
                        intent.setClass(CarsActivity.this,CarsChangeActivity.class);
                        next.putInt("userid",bundle1.getInt("userid"));
                        next.putInt("Mid",Mid1);
                        intent.putExtras(next);
                        startActivity(intent);
            }
        });
        change2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(CarsActivity.this,CarsChangeActivity.class);
                next.putInt("userid",bundle1.getInt("userid"));
                next.putInt("Mid",Mid2);
                intent.putExtras(next);
                startActivity(intent);
            }
        });

    }
    void initView(){
        back=(Button)findViewById(R.id.cars_B_back);
        Badd=(Button)findViewById(R.id.cars_B_addcars);
        TVadd=(TextView)findViewById(R.id.cars_TV_addcars);
        mid1=(TextView)findViewById(R.id.cars_1_Mid);
        mid2=(TextView)findViewById(R.id.cars_2_Mid);
        myears1=(TextView)findViewById(R.id.cars_1_years);
        myears2=(TextView)findViewById(R.id.cars_2_years);
        userid1=(TextView)findViewById(R.id.cars_1_userid);
        userid2=(TextView)findViewById(R.id.cars_2_userid);
        num=(TextView)findViewById(R.id.cars_TV_carsnum);
        change1=(Button)findViewById(R.id.cars_1_change);
        change2=(Button)findViewById(R.id.cars_2_change);

    }
    void initCars(int Uid){
        userid1.setText(""+Uid);
        userid2.setText(""+Uid);
    }
    void  findMid(int masterid){
        new Thread(new Runnable() {
            @Override
            public void run() {
                conn=link.getconnection();
                System.out.println("个人车辆信息页面"+conn);
                sql="select * from motors where Masterid = ?";

                int[] a=new int[5];
                float[] b=new float[5];
                try{
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setInt(1,masterid);
                    ResultSet res=pst.executeQuery();
                    int mid;
                    float myears;
                    int i=0;
                    while (res.next()){
                        mid=res.getInt("Mid");
                        myears=res.getFloat("Myears");
                        a[i]=mid;
                        b[i]=myears;
                        i++;
                    }
                    System.out.println("我有几辆车："+i);
                    num.setText(""+i);
                    if (i>0){
                        setcars(a,b,i);
                    }
                    pst.close();
                }catch (SQLException e){
                    System.out.println("数据错误");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }

        }).start();
    }
    //设置车辆车牌和年龄
    void setcars(int[] x,float[] y,int k){
       // int i;
//        for(i=0;i<x.length;i++){
//            System.out.println("type："+x[i]);
//            System.out.println("year："+y[i]);
//        }
        Mid1=x[0];
        Mid2=x[1];
        myears1.setText(""+y[0]);
        mid1.setText(""+x[0]);
        if(k>1){
        myears2.setText(""+y[1]);
        mid2.setText(""+x[1]);
        }
    }


}
