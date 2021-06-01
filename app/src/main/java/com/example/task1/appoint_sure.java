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
    Bundle bundle1=new Bundle();
    Bundle next=new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_sure);
        bundle1=this.getIntent().getExtras();
        System.out.println("当前用户："+bundle1.getInt("userid"));
        initView();
        //显示当前选择业务
        sure.setText(str);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(appoint_sure.this,MainActivity.class);
                next.putInt("userid",bundle1.getInt("userid"));
                intent.putExtras(next);
                startActivity(intent);
                Toast.makeText(appoint_sure.this,"预约提交成功",Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(appoint_sure.this,AppointActivity.class);
                next.putInt("userid",bundle1.getInt("userid"));
                intent.putExtras(next);
                startActivity(intent);
            }
        });
    }
    void initView(){
        sure=(TextView)findViewById(R.id.appoint_sure_TV_Atype);
        submit=(Button)findViewById(R.id.appoint_sure_submit);
        back=(Button)findViewById(R.id.appoint_sure_back);
    }
}
