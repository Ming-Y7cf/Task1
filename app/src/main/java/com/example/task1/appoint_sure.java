package com.example.task1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class appoint_sure extends AppCompatActivity {
    protected static String str;
    private TextView sure, away, atime;
    private Button submit, back;
    private EditText name, address, phone;
    private boolean flag = false;

    connect link = new connect();
    Connection conn = null;
    String sql = null;
    Bundle bundle1 = new Bundle();
    Bundle next = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_sure);
        bundle1 = this.getIntent().getExtras();
        System.out.println("当前用户：" + bundle1.getInt("userid"));
        initView();
        //显示当前选择业务
        sure.setText(str);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String name1 = name.getText().toString().trim();
                        String address1 = address.getText().toString().trim();
                        String phone1 = phone.getText().toString().trim();
                        String away1 = away.getText().toString().trim();
                        String time1 = atime.getText().toString().trim();
                        flag = makeappoint(name1, address1, phone1, str, away1, time1);
                        if (flag) {
                            Looper.prepare();
                            Toast.makeText(appoint_sure.this, "预约提交成功", Toast.LENGTH_SHORT).show();
                            System.out.println("成功");
                            skip();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(appoint_sure.this, "提交失败，请重试！", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                }).start();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip();
            }
        });
        away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String items[] = {"到店维修", "上门维修"};
                AlertDialog.Builder builder = new AlertDialog.Builder(appoint_sure.this);
                builder.setTitle("请选择服务方式:")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                away.setText(items[which]);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        atime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String items[] = {"今天", "明天"};
                AlertDialog.Builder builder = new AlertDialog.Builder(appoint_sure.this);
                builder.setTitle("请选择服务时间:")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                atime.setText(items[which]);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }

    void initView() {
        sure = (TextView) findViewById(R.id.appoint_sure_TV_Atype);
        submit = (Button) findViewById(R.id.appoint_sure_submit);
        back = (Button) findViewById(R.id.appoint_sure_back);
        atime = (TextView) findViewById(R.id.appoint_sure_TV_Atime);
        away = (TextView) findViewById(R.id.appoint_sure_TV_Away);
        name = (EditText) findViewById(R.id.appoint_sure_ET_name);
        address = (EditText) findViewById(R.id.appoint_sure_ET_address);
        phone = (EditText) findViewById(R.id.appointsure_ET_phone);
    }

    boolean makeappoint(String name, String address, String phone, String type, String way, String time) {
        boolean a;
        a = checkin(name, address, phone,way,time);
        if (a) {
            //电话与数据库类型一致
            double x = Double.valueOf(phone);
            BigDecimal z = BigDecimal.valueOf(x);
            //记录当前时间
            java.sql.Date date_sql = new java.sql.Date(System.currentTimeMillis());
            System.out.println("this sql time" + date_sql);

            conn = link.getconnection();
            System.out.println("预约服务页面" + conn);
            sql = "insert into appoint(Aname,Aaddress,Aphone,Atype,Away,Anowtime,Aappointtime,Uid) values(?,?,?,?,?,?,?,?)";

            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, address);
                pst.setBigDecimal(3, z);
                pst.setString(4, type);
                pst.setString(5, way);
                pst.setDate(6, date_sql);
                pst.setString(7, time);
                pst.setInt(8, bundle1.getInt("userid"));
                pst.execute();//执行sql 返回值 Boolean
                //System.out.println(a);
                pst.close();
                //System.out.println(a);
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

    void skip() {
        Intent intent = new Intent();
        intent.setClass(appoint_sure.this, AppointActivity.class);
        next.putInt("userid", bundle1.getInt("userid"));
        intent.putExtras(next);
        startActivity(intent);
    }

    boolean checkin(String name, String address, String phone,String way,String time) {
        if (name.equals("") || name.equals(null)) {
            Looper.prepare();
            Toast.makeText(appoint_sure.this, "请输入姓名", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (address.equals("") || address.equals(null)) {
            Looper.prepare();
            Toast.makeText(appoint_sure.this, "请输入地址", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (phone.equals("") || phone.equals(null)) {
            Looper.prepare();
            Toast.makeText(appoint_sure.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        }else if (way.equals("") || way.equals(null)) {
            Looper.prepare();
            Toast.makeText(appoint_sure.this, "请选择服务方式", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        }else if (time.equals("") || time.equals(null)) {
            Looper.prepare();
            Toast.makeText(appoint_sure.this, "请选择服务时间", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        }
        return true;
    }
}
