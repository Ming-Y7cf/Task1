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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarsChangeActivity extends AppCompatActivity {
    private EditText name,years,phone;
    private Button back,sure;
    private TextView mid;
    private int userid;
    private int Mid;
    private Boolean flag=false;

    connect link =new connect();
    Connection conn=null;
    String sql=null;
    Bundle bundle1=new Bundle();
    Bundle next=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_change);
        initView();
        bundle1=this.getIntent().getExtras();
        userid=bundle1.getInt("userid");
        Mid=bundle1.getInt("Mid");
        mid.setText(""+Mid);//标记
        System.out.println("车辆信息修改：当前用户："+userid);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               skip();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String upname=name.getText().toString();
                        String upyears=years.getText().toString();
                        String upphone=phone.getText().toString();
                        float x=Float.valueOf(upyears);
                        int g=Integer.valueOf(upphone);
                        //BigInteger h=BigInteger.valueOf(g);
                        double y=Double.valueOf(upphone);
                         BigDecimal z=BigDecimal.valueOf(y);
                        System.out.println("年龄"+x+"name"+upname+"phone"+z);
                        flag=Upcars(upname,x,z,Mid);
                        System.out.println(""+flag);
                        //存在问题 待观察
                        if(flag==true){
                            skip();
                        }else {
                            Looper.prepare();
                            Toast.makeText(CarsChangeActivity.this,"修改失败，请重试！",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                }).start();
            }
        });

    }
    void initView(){
        name=(EditText)findViewById(R.id.carschange_ET_name);
        years=(EditText)findViewById(R.id.carschange_ET_years);
        phone=(EditText)findViewById(R.id.carschange_ET_phone);
        back=(Button)findViewById(R.id.carschange_B_back);
        sure=(Button)findViewById(R.id.Carschange_B_sure);
        mid=(TextView)findViewById(R.id.carschange_TV_Mid);
    }

    void skip(){
        Intent intent=new Intent();
        intent.setClass(CarsChangeActivity.this,CarsActivity.class);
        next.putInt("userid",bundle1.getInt("userid"));
        intent.putExtras(next);
        startActivity(intent);
    }
    boolean Upcars(String type, float years, BigDecimal phone, int cid){
        conn=link.getconnection();
        sql = "update motors set Mtype = ?,Myears = ?,Mphone = ? where Mid= ?";
        boolean a;
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,type);
            pst.setFloat(2,years);
            pst.setBigDecimal(3,phone);
            pst.setInt(4,cid);
            a=pst.execute();
            System.out.println("a为："+a);
            pst.close();
            return a;
        }catch (SQLException e){
            System.out.println("数据错误");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }
}
