package com.example.task1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class appoint_sure extends AppCompatActivity {
    protected static String str;
    private TextView sure,away,atime;
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
        away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String items[] = { "到店维修", "上门维修"};
                AlertDialog.Builder builder=new AlertDialog.Builder(appoint_sure.this);
                builder.setTitle("请选择服务方式:")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                away.setText(items[which]);
                            }
                        });
                AlertDialog dialog= builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        atime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String items[] = { "今天", "明天"};
                AlertDialog.Builder builder=new AlertDialog.Builder(appoint_sure.this);
                builder.setTitle("请选择服务时间:")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                atime.setText(items[which]);
                            }
                        });
                AlertDialog dialog= builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }
    void initView(){
        sure=(TextView)findViewById(R.id.appoint_sure_TV_Atype);
        submit=(Button)findViewById(R.id.appoint_sure_submit);
        back=(Button)findViewById(R.id.appoint_sure_back);
        atime=(TextView)findViewById(R.id.appoint_sure_TV_Atime);
        away=(TextView)findViewById(R.id.appoint_sure_TV_Away);
    }
}
