package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {
    private TextView register;
    private Button login;
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
                Intent intent =new Intent();
                intent.setClass(registerActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(registerActivity.this,"欢迎使用XXXX",Toast.LENGTH_SHORT).show();
            }
        });
    }

    void ininView(){
        register=(TextView)findViewById(R.id.register_R_down_bottom_T_new);
        login=(Button)findViewById(R.id.register_R_down_B_login);
    }
}
