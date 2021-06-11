package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.ETC1;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.task1.link.connect;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarsAddActivity extends AppCompatActivity {
    private int userid;
    private Button back, submit;
    private EditText cname, cyears;
    private boolean flag=false;


    connect link = new connect();
    Connection conn = null;
    String sql = null;
    Bundle bundle1 = new Bundle();
    Bundle next = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_add);
        initBundle();
        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String name=cname.getText().toString().trim();
                        String Syear=cyears.getText().toString().trim();
                        flag=carsadd(name,Syear);
                        if (flag) {
                            Looper.prepare();
                            Toast.makeText(CarsAddActivity.this, "车辆增加成功", Toast.LENGTH_SHORT).show();
                            System.out.println("成功");
                            skip();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(CarsAddActivity.this, "车辆增加失败，请重试！", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                }).start();
            }
        });
    }

    void initBundle() {
        bundle1 = this.getIntent().getExtras();
        userid = bundle1.getInt("userid");
        System.out.println("车辆增加：当前用户" + userid);
    }

    void initView() {
        back = (Button) findViewById(R.id.carsadd_B_back);
        submit = (Button) findViewById(R.id.carsadd_B_submit);
        cname = (EditText) findViewById(R.id.carsadd_ET_cname);
        cyears = (EditText) findViewById(R.id.carsadd_ET_cyears);
    }

    void skip() {
        Intent intent = new Intent();
        intent.setClass(CarsAddActivity.this, CarsActivity.class);
        next.putInt("userid", bundle1.getInt("userid"));
        next.putString("username", bundle1.getString("username"));
        intent.putExtras(next);
        startActivity(intent);
    }

    boolean carsadd(String name, String cyear){
        boolean a;
        a = checkin(name, cyear);
        float years=Float.valueOf(cyear);
        if(years>=20){
            Looper.prepare();
            Toast.makeText(CarsAddActivity.this, "请输入准确年份！", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        }
        if (a) {
            conn = link.getconnection();
            System.out.println("添加车辆页面" + conn);
            sql = "insert into motors(Mtype,Myears,Masterid) values(?,?,?)";

            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setFloat(2, years);
                pst.setInt(3, userid);
                pst.execute();
                pst.close();
                return true;
            } catch (SQLException e) {
                System.out.println("数据错误");
                System.out.println(e.getMessage());
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    boolean checkin(String name,String years) {
        if (name.equals("") || name.equals(null)) {
            Looper.prepare();
            Toast.makeText(CarsAddActivity.this, "请输入车辆名称", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (years.equals("")||years.equals(null)) {
            Looper.prepare();
            Toast.makeText(CarsAddActivity.this, "请输入使用时间", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        }
        return true;
    }

}
