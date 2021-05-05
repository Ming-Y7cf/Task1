package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class appoint_sure extends AppCompatActivity {
    protected static String str;
    private TextView sure;
    private Button submit,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_sure);
        initView();
        //显示当前选择业务
        sure.setText(str);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(appoint_sure.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(appoint_sure.this,"预约提交成功",Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(appoint_sure.this,AppointActivity.class);
                startActivity(intent);
            }
        });
    }
    void initView(){
        sure=(TextView)findViewById(R.id.appointsure_TV_top);
        submit=(Button)findViewById(R.id.appoint_sure_submit);
        back=(Button)findViewById(R.id.appoint_sure_back);
    }
}
