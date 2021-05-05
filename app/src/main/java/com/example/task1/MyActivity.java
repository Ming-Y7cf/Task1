package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyActivity extends AppCompatActivity {

    private TextView nickname;
    private Button feedback,main,mycars,score,appointment,record;
    private ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initView();
        nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MyActivity.this,changeNameActivity.class);
                startActivity(intent);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(MyActivity.this,FeedBackActivity.class);
                startActivity(intent);
            }
        });
        mycars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(MyActivity.this,CarsActivity.class);
                startActivity(intent);
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(MyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MyActivity.this, "该功能开发中...", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                MyToastTime(toast,1000);
            }
        });
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MyActivity.this, "尚未设计数据库，暂时无法查询", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                MyToastTime(toast,1000);
            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MyActivity.this, "尚未设计数据库，暂时无法查询", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                MyToastTime(toast,1000);
            }
        });

    }
    void initView(){
        nickname=(TextView)findViewById(R.id.my_R_up_nickname);
        feedback=(Button)findViewById(R.id.my_R_down_B_feedback);
        photo=(ImageView)findViewById(R.id.change_R_up_img);
        main=(Button)findViewById(R.id.my_tomain);
        mycars=(Button)findViewById(R.id.my_R_down_B_cars);
        score=(Button)findViewById(R.id.my_R_down_B_score);
        appointment=(Button)findViewById(R.id.my_R_down_B_appointment);
        record=(Button)findViewById(R.id.my_R_down_B_record);
    }
    void MyToastTime(final Toast toast, final int cnt) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt );
    }
}
