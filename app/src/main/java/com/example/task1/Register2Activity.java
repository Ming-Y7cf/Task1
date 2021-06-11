package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.os.Looper;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.task1.link.connect;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Register2Activity extends AppCompatActivity {
    private EditText EuserName, Epassw1, Epassw2, Ephone;
    private Button login, back;
    private RadioButton man, woman;
    private RadioGroup radioGroup;
    private String username, password, sex = "", phone;
    private Boolean flag = false;

    connect link = new connect();
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    String sql = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Register2Activity.this, registerActivity.class);
                startActivity(intent);
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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        username = EuserName.getText().toString().trim();
                        password = Epassw2.getText().toString().trim();
                        phone = Ephone.getText().toString().trim();
                        System.out.println(sex);
                        if (sex.equals("") || sex.equals(null)) {
                            Looper.prepare();
                            Toast.makeText(Register2Activity.this, "请选择性别", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            flag = add(username, password, Epassw1.getText().toString().trim(), sex, phone);
                            if (flag) {
                                Looper.prepare();
                                Toast.makeText(Register2Activity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(Register2Activity.this, registerActivity.class);
                                startActivity(intent);
                                Looper.loop();
                            } else {
                                Looper.prepare();
                                Toast.makeText(Register2Activity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }

                        }
                    }
                }).start();


            }
        });


    }

    void initView() {
        EuserName = (EditText) findViewById(R.id.register2_L_top2_E_userName);
        Epassw1 = (EditText) findViewById(R.id.register2_L_top3_E_passw);
        Epassw2 = (EditText) findViewById(R.id.register2_L_top4_E_passwE);
        back = (Button) findViewById(R.id.register2_R_top_B_back);
        login = (Button) findViewById(R.id.register2_L_top9_B_login);
        Ephone = (EditText) findViewById(R.id.register2_L_top8_E_phone);
        man = (RadioButton) findViewById(R.id.register2_L_top5_RB_sex1);
        woman = (RadioButton) findViewById(R.id.register2_L_top5_RB_sex2);
        radioGroup = (RadioGroup) findViewById(R.id.register2_L_top5_RB_asex);
    }

    boolean add(String username, String password, String password2, String sex, String phone) {
        boolean a = checkin(username, password, password2, phone);
        if (a) {
            a = phonenumber(phone);//电话号码是否是数字
            if (a) {
                a = findusername(username);//用户名是否重复
                System.out.println(a);
                if (a == false) {
                    double x = Double.valueOf(phone);
                    BigDecimal z = BigDecimal.valueOf(x);
                    conn = link.getconnection();
                    sql = "insert into users_copy(name,username,password,sex,phone) values(?,?,?,?,?)";
                    System.out.println("这里是插入用户\n");
                    try {
                        preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, username);
                        preparedStatement.setString(3, password);
                        preparedStatement.setString(4, sex);
                        preparedStatement.setBigDecimal(5, z);
                        preparedStatement.execute();
                        preparedStatement.close();
                        return true;
                    } catch (SQLException e) {
                        System.out.println("数据错误");
                        System.out.println(e.getMessage());
                        return false;
                    }
                } else {
                    Looper.prepare();
                    Toast.makeText(Register2Activity.this, "该用户已存在", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    return false;
                }
            } else {
                Looper.prepare();
                Toast.makeText(Register2Activity.this, "电话格式输入错误", Toast.LENGTH_SHORT).show();
                Looper.loop();
                return false;
            }
        } else {
            return false;
        }

    }

    boolean findusername(String findname) {
        conn = link.getconnection();
        sql = "select * from users_copy where name=?";
        System.out.println(findname);
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, findname);
            ResultSet set = pst.executeQuery();
            String[] a = new String[2];
            String a1;
            int i = 0;
            while (set.next()) {
                a1 = set.getString("name");
                a[i] = a1;
                i++;
            }
            System.out.println("：" + i);
            System.out.println("a：" + a[0]);
            pst.close();
            if (i == 0) {
                return false;//无同名
            } else {
                return true;
            }
        } catch (SQLException e) {
            //System.out.println("没有同名用户");
            System.out.println(e.getMessage());
            return false;
        }
    }

    boolean checkin(String name1, String pass1, String pass2, String phone1) {
        if (name1.equals("") || name1.equals(null)) {
            Looper.prepare();
            Toast.makeText(Register2Activity.this, "请输入账号", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (pass1.equals("") || pass1.equals(null)) {
            Looper.prepare();
            Toast.makeText(Register2Activity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (pass2.equals("") || pass2.equals(null)) {
            Looper.prepare();
            Toast.makeText(Register2Activity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (phone1.equals("") || phone1.equals(null)) {
            Looper.prepare();
            Toast.makeText(Register2Activity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (name1.length() < 3 || name1.length() > 10) {
            Looper.prepare();
            Toast.makeText(Register2Activity.this, "用户名长度为3-10位", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (!pass1.equals(pass2)) {
            Looper.prepare();
            Toast.makeText(Register2Activity.this, "密码输入不一致", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (pass2.length() > 16 || pass2.length() < 6) {
            Looper.prepare();
            Toast.makeText(Register2Activity.this, "密码长度为6-16位", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (phone1.length() > 11 || phone1.length() < 5) {
            Looper.prepare();
            Toast.makeText(Register2Activity.this, "联系方式为5-11位", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        }
        return true;
    }

    boolean phonenumber(String phone1) {
        return phone1.matches("[0-9]+");
    }
}
