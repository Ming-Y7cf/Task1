package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task1.link.connect;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class changeNameActivity extends AppCompatActivity {
    private Button back, save;
    private EditText cname, cphone, cpass;
    private TextView changeimag;
    private int userid;
    private RadioGroup radioGroup;
    private RadioButton man, woman;
    private String username, password, sex = "", phone;
    private boolean flag = false;

    Bundle bundle1 = new Bundle();
    Bundle next = new Bundle();
    connect link = new connect();
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    String sql = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        initView();
        bundle1 = this.getIntent().getExtras();
        userid = bundle1.getInt("userid");
        System.out.println("修改个人信息页面：当前用户：" + userid);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == man.getId()) {
                    sex = "男";
                } else {
                    sex = "女";
                }
            }
        });
        changeimag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(changeNameActivity.this, "该功能开发中！敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        username = cname.getText().toString().trim();
                        password = cpass.getText().toString().trim();
                        phone = cphone.getText().toString().trim();
                        System.out.println(sex);
                        if (sex.equals("") || sex.equals(null)) {
                            Looper.prepare();
                            Toast.makeText(changeNameActivity.this, "请选择性别", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            flag = changeinfo(username, password, sex, phone);
                            if (flag) {
                                Looper.prepare();
                                Toast.makeText(changeNameActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(changeNameActivity.this, MyActivity.class);
                                next.putInt("userid", userid);
                                next.putString("username", username);
                                intent.putExtras(next);
                                startActivity(intent);
                                Looper.loop();
                            } else {
                                Looper.prepare();
                                Toast.makeText(changeNameActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }
                    }
                }).start();
            }
        });

    }

    void initView() {
        back = (Button) findViewById(R.id.change_R_up_1_B_back);
        save = (Button) findViewById(R.id.change_Bottom_save);
        radioGroup = (RadioGroup) findViewById(R.id.change_RG_sex);
        man = (RadioButton) findViewById(R.id.change_RB_man);
        woman = (RadioButton) findViewById(R.id.change_RB_woman);
        cname = (EditText) findViewById(R.id.change_ET_name);
        cpass = (EditText) findViewById(R.id.change_ET_password);
        cphone = (EditText) findViewById(R.id.change_ET_phone);
        changeimag = (TextView) findViewById(R.id.change_TV_changeimage);
    }

    void skip() {
        Intent intent = new Intent();
        intent.setClass(changeNameActivity.this, MyActivity.class);
        next.putInt("userid", bundle1.getInt("userid"));
        next.putString("username", bundle1.getString("username"));
        intent.putExtras(next);
        startActivity(intent);
    }

    boolean changeinfo(String username, String password, String sex, String phone) {
        boolean a = checkin(username, password, phone);
        if (a) {
            a=phonenumber(phone);
            if (a) {
                double x = Double.valueOf(phone);
                BigDecimal z = BigDecimal.valueOf(x);
                conn = link.getconnection();
                sql = "update users_copy set name=?,username=?,password=?,sex=?,phone=? where id=?";
                // System.out.println("这里是修改用户信息\n");
                try {
                    preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, username);
                    preparedStatement.setString(3, password);
                    preparedStatement.setString(4, sex);
                    preparedStatement.setBigDecimal(5, z);
                    preparedStatement.setInt(6, bundle1.getInt("userid"));
                    preparedStatement.execute();
                    preparedStatement.close();
                    return true;
                } catch (SQLException e) {
                    System.out.println("数据错误");
                    System.out.println(e.getMessage());
                    return false;
                }
            }else {
                Looper.prepare();
                Toast.makeText(changeNameActivity.this, "电话格式输入错误", Toast.LENGTH_SHORT).show();
                Looper.loop();
                return false;
            }
        } else {
            return false;
        }

    }

    boolean checkin(String name1, String pass1, String phone1) {
        if (name1.equals("") || name1.equals(null)) {
            Looper.prepare();
            Toast.makeText(changeNameActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (pass1.equals("") || pass1.equals(null)) {
            Looper.prepare();
            Toast.makeText(changeNameActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (phone1.equals("") || phone1.equals(null)) {
            Looper.prepare();
            Toast.makeText(changeNameActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (name1.length() < 2 || name1.length() > 10) {
            Looper.prepare();
            Toast.makeText(changeNameActivity.this, "用户名长度为2-10位", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (pass1.length() > 16 || pass1.length() < 6) {
            Looper.prepare();
            Toast.makeText(changeNameActivity.this, "密码长度为6-16位", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (phone1.length() > 11 || phone1.length() < 5) {
            Looper.prepare();
            Toast.makeText(changeNameActivity.this, "联系方式为5-11位", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        }
        return true;
    }
    boolean phonenumber(String phone1) {
        return phone1.matches("[0-9]+");
    }
}
