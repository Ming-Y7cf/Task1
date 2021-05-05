package com.example.task1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    private Button appoint,navigation,my,main,query,gift;
    private TextView appointT,navigationT,queryT;
    private String address[]={"dd","dd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.main_UP_filpper);
        viewFlipper.startFlipping();
        initView();

        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,MyActivity.class);
                startActivity(intent);
            }
        });
        appoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,AppointActivity.class);
                startActivity(intent);
            }
        });
        appointT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,AppointActivity.class);
                startActivity(intent);
            }
        });
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("店铺指南")
                        .setMessage("XX电动车修理店   " +
                                "地址：XXXXXXXXX   " +
                                "联系方式：123456789")
                        .show();
            }
        });
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("店铺指南")
                        .setMessage("XX电动车修理店   " +
                                "地址：XXXXXXXXX   " +
                                "联系方式：123456789")
                        .show();
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "尚未设计数据库，暂时无法查询", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                MyToastTime(toast,1000);
            }
        });
        queryT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "尚未设计数据库，暂时无法查询", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                MyToastTime(toast,1000);
            }
        });
        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,GiftActivity.class);
                startActivity(intent);
            }
        });


    }

    void initView() {
        my = (Button) findViewById(R.id.main_Down_bottom_B_tomy);
        appoint=(Button)findViewById(R.id.main_Down_icon_appoint);
        appointT=(TextView) findViewById(R.id.main_Down_T_appoint);
        main=(Button)findViewById(R.id.main_Down_bottom_B_tomain);
        navigation=(Button)findViewById(R.id.main_Down_icon_navigation);
        navigationT=(TextView)findViewById(R.id.main_Down_T_navigation);
        query=(Button)findViewById(R.id.main_Down_icon_query);
        queryT=(TextView)findViewById(R.id.main_Down_T_query);
        gift=(Button)findViewById(R.id.main_Down_icon_gift);
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
