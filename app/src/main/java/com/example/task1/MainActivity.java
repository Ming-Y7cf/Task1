package com.example.task1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.task1.link.connect;

import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    private Button appoint, navigation, my, main, query, gift;
    private TextView appointT, navigationT, queryT, giftT;
    private String address[] = {"dd", "dd"};

    connect mainconn = new connect();
    Bundle bundle = new Bundle();
    Bundle next = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.main_UP_filpper);
        viewFlipper.startFlipping();
        bundle = this.getIntent().getExtras();
        initView();
        if (bundle != null)
            System.out.println("主页：当前用户为：" + bundle.getInt("userid"));
        //initDB();

        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bundle == null) {
                    Toast toast = Toast.makeText(MainActivity.this, "请先登录！！", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    MyToastTime(toast, 1000);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, MyActivity.class);
                    next.putInt("userid", bundle.getInt("userid"));
                    intent.putExtras(next);
                    startActivity(intent);
                }
            }
        });
        appoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bundle == null) {
                    Toast toast = Toast.makeText(MainActivity.this, "请先登录！！", Toast.LENGTH_SHORT);
                    MyToastTime(toast, 1000);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, AppointActivity.class);
                    next.putInt("userid", bundle.getInt("userid"));
                    intent.putExtras(next);
                    startActivity(intent);
                }
            }
        });
        appointT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bundle == null) {
                    Toast toast = Toast.makeText(MainActivity.this, "请先登录！！", Toast.LENGTH_SHORT);
                    MyToastTime(toast, 1000);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, AppointActivity.class);
                    next.putInt("userid", bundle.getInt("userid"));
                    intent.putExtras(next);
                    startActivity(intent);
                }
            }
        });
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });
        navigationT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, registerActivity.class);
                startActivity(intent);
            }
        });
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "尚未设计数据库，暂时无法查询", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                MyToastTime(toast, 1000);
            }
        });
        queryT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "尚未设计数据库，暂时无法查询", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                MyToastTime(toast, 1000);
            }
        });
        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, GiftActivity.class);
                startActivity(intent);
            }
        });
        giftT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, GiftActivity.class);
                startActivity(intent);
            }
        });


    }

    void initView() {
        my = (Button) findViewById(R.id.main_Down_bottom_B_tomy);
        appoint = (Button) findViewById(R.id.main_Down_icon_appoint);
        appointT = (TextView) findViewById(R.id.main_Down_T_appoint);
        main = (Button) findViewById(R.id.main_Down_bottom_B_tomain);
        navigation = (Button) findViewById(R.id.main_Down_icon_navigation);
        navigationT = (TextView) findViewById(R.id.main_Down_T_navigation);
        query = (Button) findViewById(R.id.main_Down_icon_query);
        queryT = (TextView) findViewById(R.id.main_Down_T_query);
        gift = (Button) findViewById(R.id.main_Down_icon_gift);
        giftT = (TextView) findViewById(R.id.main_Down_T_gift);
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
        }, cnt);
    }

    void ShowDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.initdialog, null, false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        Button cancel = view.findViewById(R.id.initdialog_B_cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    void initDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection connection = mainconn.getconnection();
                System.out.println("数据库链接成功" + connection);
            }
        }).start();
    }
}
