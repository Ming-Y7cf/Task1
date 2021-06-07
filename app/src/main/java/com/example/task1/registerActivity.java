package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task1.link.connect;
import com.example.task1.util.CodeUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class registerActivity extends AppCompatActivity {
    private TextView register;
    private Button login;
    private EditText Euser, Epass, Ecode;
    private int userid;
    private String username, password, captcha, code;
    private Boolean flag = false, flag2 = false;
    private CodeUtil codeUtil;
    private ImageView check;

    connect link = new connect();
    Connection conn = null;
    String sql = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ininView();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(registerActivity.this, Register2Activity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        username = Euser.getText().toString().trim();
                        password = Epass.getText().toString().trim();
                        code = Ecode.getText().toString().trim();//trim去空格
                        flag = login(username, password, code);
                        //  System.out.println("?");
                        if (flag == true) {
                            int cichu = finduser(username, password);
                            if (cichu != 0) {
                                System.out.println(cichu);
                                loginsure(flag);
                            } else {
                                Looper.prepare();
                                Toast.makeText(registerActivity.this, "输入错误", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        } else {
                            Looper.prepare();
                            Toast.makeText(registerActivity.this, "输入错误", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }

                }).start();

            }
        });
        //刷新验证码
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeUtil = CodeUtil.getInstance();
                Bitmap bitmap = codeUtil.createBitmap();
                check.setImageBitmap(bitmap);
                captcha = codeUtil.getCode();
                System.out.println(captcha);
            }
        });

    }

    void ininView() {
        register = (TextView) findViewById(R.id.register_R_down_bottom_T_new);
        login = (Button) findViewById(R.id.register_R_down_B_login);
        Euser = (EditText) findViewById(R.id.register_R_down_E_account);
        Epass = (EditText) findViewById(R.id.register_R_down_E_password);
        check = (ImageView) findViewById(R.id.register_R_down_im_check);
        Ecode = (EditText) findViewById(R.id.register_R_down_ET_incheck);
    }

    int finduser(String username, String password) {
        conn = link.getconnection();
        System.out.println("用户查找数据库链接成功" + conn);
        sql = "select * from users_copy where username= ? and password= ?";
        int findid = 0;
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();//用于返回当前id，便于识别
            if (rs.next()) {
                findid = rs.getInt("id");
                System.out.println(findid + "此处id");
            }
            pst.close();
            return findid;
        } catch (SQLException e) {
            System.out.println("数据错误");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    boolean login(String username, String password, String code) {
        boolean a;
        //检查是否输入
        a = checkin(username, password, code);
        if (a) {
            //检查验证码规范
            if (captchacheck(code)) {
                //检查账号密码
                conn = link.getconnection();
                System.out.println("登录界面数据库链接成功" + conn);
                sql = "select * from users_copy where username= ? and password= ?";

                try {
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1, username);
                    pst.setString(2, password);
                    a = pst.execute();//执行sql 返回值 Boolean
                    ResultSet rs = pst.executeQuery();//用于返回当前id，便于识别
                    if (rs.next()) {
                        userid = rs.getInt("id");
                        System.out.println(userid + "此处id");
                    }
                    pst.close();
                    // System.out.println(a+"\n");
                    return a;
                } catch (SQLException e) {
                    System.out.println("数据错误");
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    void loginsure(Boolean x) {
        if (x) {
            Looper.prepare();
            Toast.makeText(registerActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            Intent intent = new Intent();
            intent.setClass(registerActivity.this, MainActivity.class);
            bundle.putInt("userid", userid);
            bundle.putString("username",username);
            intent.putExtras(bundle);
            startActivity(intent);
            Looper.loop();
        }
    }

    boolean checkin(String name, String pass, String code1) {
        if (name.equals("") || name.equals(null)) {
            Looper.prepare();
            Toast.makeText(registerActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (pass.equals("") || pass.equals(null)) {
            Looper.prepare();
            Toast.makeText(registerActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (code1.equals("") || code1.equals(null)) {
            Looper.prepare();
            Toast.makeText(registerActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        }
        return true;
    }

    boolean captchacheck(String code) {
        if (code.equals(captcha)) {
            return true;
        } else {
            return false;
        }
    }


}
