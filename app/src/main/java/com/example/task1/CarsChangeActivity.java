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
    private EditText name, years, phone;
    private Button back, sure;
    private TextView mid;
    private int userid;
    private int Mid;
    private Boolean flag = false, flag2 = false;

    connect link = new connect();
    Connection conn = null;
    String sql = null;
    Bundle bundle1 = new Bundle();
    Bundle next = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_change);
        initView();
        bundle1 = this.getIntent().getExtras();
        userid = bundle1.getInt("userid");
        Mid = bundle1.getInt("Mid");
        mid.setText("" + Mid);//标记
        System.out.println("车辆信息修改：当前用户：" + userid + "用户名" + bundle1.getString("username"));

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
                        String upname = name.getText().toString();
                        String upyears = years.getText().toString();
                        String upphone = phone.getText().toString();
                        flag2 = checkin(upname, upyears, upphone);
                        if (flag2) {
                            flag2 = phonenumber(upphone);
                            if (flag2) {
                                float x = Float.valueOf(upyears);
                                double y = Double.valueOf(upphone);
                                BigDecimal z = BigDecimal.valueOf(y);
                                System.out.println("年龄" + x + "name" + upname + "phone" + z);

                                if (x >= 20) {
                                    Looper.prepare();
                                    Toast.makeText(CarsChangeActivity.this, "请输入准确年份！", Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                } else {
                                    flag = Upcars(upname, x, z, Mid);
                                    if (flag == true) {
                                        skip();
                                    } else {
                                        Looper.prepare();
                                        Toast.makeText(CarsChangeActivity.this, "修改失败，请重试！", Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    }
                                }
                            } else {
                                Looper.prepare();
                                Toast.makeText(CarsChangeActivity.this, "电话输入错误，请重试！", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        } else {
                            Looper.prepare();
                            Toast.makeText(CarsChangeActivity.this, "输入错误，请重试！", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                }).start();
            }
        });

    }

    void initView() {
        name = (EditText) findViewById(R.id.carschange_ET_name);
        years = (EditText) findViewById(R.id.carschange_ET_years);
        phone = (EditText) findViewById(R.id.carschange_ET_phone);
        back = (Button) findViewById(R.id.carschange_B_back);
        sure = (Button) findViewById(R.id.Carschange_B_sure);
        mid = (TextView) findViewById(R.id.carschange_TV_Mid);
    }

    void skip() {
        Intent intent = new Intent();
        intent.setClass(CarsChangeActivity.this, CarsActivity.class);
        next.putInt("userid", bundle1.getInt("userid"));
        next.putString("username", bundle1.getString("username"));
        intent.putExtras(next);
        startActivity(intent);
    }

    boolean Upcars(String type, float years, BigDecimal phone, int cid) {
        conn = link.getconnection();
        sql = "update motors set Mtype = ?,Myears = ?,Mphone = ? where Mid= ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, type);
            pst.setFloat(2, years);
            pst.setBigDecimal(3, phone);
            pst.setInt(4, cid);
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            System.out.println("数据错误");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    boolean checkin(String name, String years, String phone) {
        if (name.equals("") || name.equals(null)) {
            Looper.prepare();
            Toast.makeText(CarsChangeActivity.this, "请输入车辆名称", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (years.equals("") || years.equals(null)) {
            Looper.prepare();
            Toast.makeText(CarsChangeActivity.this, "请输入使用时间", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (phone.equals("") || phone.equals(null)) {
            Looper.prepare();
            Toast.makeText(CarsChangeActivity.this, "请输入联系方式", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (phone.length() > 11 || phone.length() < 5) {
            Looper.prepare();
            Toast.makeText(CarsChangeActivity.this, "联系方式为5-11位", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        }
        return true;
    }

    boolean phonenumber(String phone1) {
        return phone1.matches("[0-9]+");
    }
}
