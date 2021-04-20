package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register2Activity extends AppCompatActivity {
    private EditText userName,passw1,passw2,phone;
    private Button login,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        initView();
        //用户名字数控制
        userName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String name=userName.getText().toString();
                if(name.length()<2){
                    Toast.makeText(Register2Activity.this,"用户名长度不能小于2",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //密码长度控制
        passw1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String pass=passw1.getText().toString();
                if(pass.length()<6||pass.length()>16){
                    Toast.makeText(Register2Activity.this,"密码长度为6-16位",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //确认密码
        passw2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String pass=passw2.getText().toString();
                if(pass.length()<6||pass.length()>16){
                    Toast.makeText(Register2Activity.this,"密码长度为6-16位",Toast.LENGTH_SHORT).show();
                }
                if(!passw1.getText().toString().equals(passw2.getText().toString())){
                    Toast.makeText(Register2Activity.this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String phone1=phone.getText().toString();
                if(phone1.length()!=11){
                    Toast.makeText(Register2Activity.this,"联系方式格式错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Register2Activity.this,registerActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Register2Activity.this,registerActivity.class);
                startActivity(intent);
            }
        });
    }
    void initView(){
        userName=(EditText)findViewById(R.id.register2_L_top2_E_userName);
        passw1=(EditText)findViewById(R.id.register2_L_top3_E_passw);
        passw2=(EditText)findViewById(R.id.register2_L_top4_E_passwE);
        back=(Button)findViewById(R.id.register2_R_top_B_back);
        login=(Button)findViewById(R.id.register2_L_top9_B_login);
        phone=(EditText)findViewById(R.id.register2_L_top8_E_phone);
    }
}
