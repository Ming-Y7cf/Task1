package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.task1.dao.UserDao;
import com.example.task1.link.DBUtils;
import com.example.task1.link.User;
import com.example.task1.link.Usertest;
import com.example.task1.link.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class Register2Activity extends AppCompatActivity {
    private EditText EuserName, Epassw1, Epassw2, Ephone;
    private Button login, back;
    private RadioButton man, woman;
    private String username, password, sex, phone;
    private UserDao userDao;
    private Boolean flag=false;

    connect link =new connect();
    Connection conn=null;
    PreparedStatement preparedStatement=null;
    String sql=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        initView();
        //用户名字数控制
        EuserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String name = EuserName.getText().toString();
                if (name.length() < 2) {
                    Toast.makeText(Register2Activity.this, "用户名长度不能小于2", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //密码长度控制
        Epassw1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String pass = Epassw1.getText().toString();
                if (pass.length() < 6 || pass.length() > 16) {
                    Toast.makeText(Register2Activity.this, "密码长度为6-16位", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //确认密码
        Epassw2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String pass = Epassw2.getText().toString();
                if (pass.length() < 6 || pass.length() > 16) {
                    Toast.makeText(Register2Activity.this, "密码长度为6-16位", Toast.LENGTH_SHORT).show();
                }
                if (!Epassw1.getText().toString().equals(Epassw2.getText().toString())) {
                    Toast.makeText(Register2Activity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Ephone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String phone1 = Ephone.getText().toString();
                if (phone1.length() != 11) {
                    Toast.makeText(Register2Activity.this, "联系方式格式错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Register2Activity.this, registerActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        if (EuserName.length() == 0)
//                            Toast.makeText(Register2Activity.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
//                        else if (Epassw2.length() == 0)
//                            Toast.makeText(Register2Activity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
//                        else if (!man.isClickable())
//                            if (!woman.isClickable())
//                                Toast.makeText(Register2Activity.this, "请选择性别", Toast.LENGTH_SHORT).show();
//                        username = EuserName.getText().toString();
//                        password = Epassw2.getText().toString();
//                        phone = Ephone.getText().toString();
//                        if (man.isClickable()) {
//                            sex = "男";
//                        } else if (woman.isClickable()) {
//                            sex = "女";
//                            //u = new User(username,password,sex);
//                        }
//                        User u = new User();
//                        u.setUsername(username);
//                        u.setName(username);
//                        u.setPassword(password);
//                        u.setSex(sex);
//                        u.setPhone(phone);
//                        boolean flag=userDao.register(u);
//                        if (flag){
//                            Looper.prepare();
//                            Toast.makeText(Register2Activity.this,"注册成功",Toast.LENGTH_SHORT).show();
//                            Looper.loop();
//                        }else {
//                            Looper.prepare();
//                            Toast.makeText(Register2Activity.this,"注册失败",Toast.LENGTH_SHORT).show();
//                            Looper.loop();
//                        }
                        //Usertest user = new Usertest();
                        username = EuserName.getText().toString();
                        password = Epassw2.getText().toString();
                       // user.setUsername(username);
                       // user.setPassword(password);
                       // System.out.println(user.getUsername().toString()+"/"+user.getPassword().toString());
                         //flag = userDao.adduser(EuserName.getText().toString(),Epassw2.getText().toString());
                        flag=add(username,password);
                        if (flag) {
                            Looper.prepare();
                            Toast.makeText(Register2Activity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent();
                            intent.setClass(Register2Activity.this,registerActivity.class);
                            startActivity(intent);
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(Register2Activity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            Looper.loop();
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
    }
    boolean add(String username,String password){
        conn=link.getconnection();
        sql = "insert into usertest values(?,?)";
        System.out.println("这里是插入用户\n");
        try {
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        }catch (SQLException e){
            System.out.println("数据错误");
            System.out.println(e.getMessage());
            return false;
        }

    }

}
