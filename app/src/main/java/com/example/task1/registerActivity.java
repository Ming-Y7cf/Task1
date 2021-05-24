package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task1.dao.UserDao;
import com.example.task1.link.User;


public class registerActivity extends AppCompatActivity {
    private TextView register;
    private Button login;
    private EditText Euser,Epass;
    private boolean aa;
    private int msg=0;
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
                new Thread(){
                    @Override
                    public void run() {
                        UserDao userDao = new UserDao();
                        aa = userDao.login(Euser.getText().toString(),Epass.getText().toString());
                        msg = 0;
                        if(aa){
                            msg = 1;
                        }
                        System.out.println(msg);
                        hand1.sendEmptyMessage(msg);
                    }
                }.start();
            }
        });
    }
    final Handler hand1 = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1)
            {
                Toast.makeText(registerActivity.this,"登录成功",Toast.LENGTH_LONG).show();

            }
            else
            {
                Toast.makeText(registerActivity.this,"登录失败",Toast.LENGTH_LONG).show();
            }
        }
    };
    void ininView(){
        register=(TextView)findViewById(R.id.register_R_down_bottom_T_new);
        login=(Button)findViewById(R.id.register_R_down_B_login);
        Euser=(EditText)findViewById(R.id.register_R_down_E_account);
        Epass=(EditText)findViewById(R.id.register_R_down_E_password);
    }
//    void check(){
//        if(user.getUsername().equals("tyz")&&user.getPassword().equals("tyz")){
//            Intent intent =new Intent();
//            intent.setClass(registerActivity.this,MainActivity.class);
//            startActivity(intent);
//        }else {
//            Toast.makeText(registerActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
//        }
//    }
}
