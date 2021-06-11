package com.example.task1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task1.link.Feedback;
import com.example.task1.link.connect;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedBackActivity extends AppCompatActivity {
    private Button submit, back;
    private TextView item, clean, star, attitude, price;
    private EditText ETother;
    private String allitem[] = {"外部故障", "电机故障", "电瓶相关", "仪表盘故障"}, stars[] = {"★★★★★", "★★★★", "★★★", "★★", "★"},
            cleanliness[] = {"★★★★★", "★★★★", "★★★", "★★", "★"}, attitude1[] = {"★★★★★", "★★★★", "★★★", "★★", "★"},
            price1[] = {"★★★★★", "★★★★", "★★★", "★★", "★"};
    private int getstars, gclean, gatti, gprice, userid;
    private String others,gitem;
    private boolean flag = false;

    connect link = new connect();
    Connection conn = null;
    String sql = null;
    Bundle bundle1 = new Bundle();
    Bundle next = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initView();
        bundle1 = this.getIntent().getExtras();
        userid = bundle1.getInt("userid");
        System.out.println("反馈：当前用户：" + userid + "用户名：" + bundle1.getString("username"));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        gitem = item.getText().toString().trim();
                        getstars = star.getText().length();
                        gclean = clean.getText().length();
                        gatti = attitude.getText().length();
                        gprice = price.getText().length();
                        others = ETother.getText().toString().trim();
                        flag = makefeedback(gitem, getstars, gclean, gatti, gprice,others);
                        if (flag) {
                            Looper.prepare();
                            Toast.makeText(FeedBackActivity.this, "反馈提交成功", Toast.LENGTH_LONG).show();
                            skip();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(FeedBackActivity.this, "反馈提交失败，请重试！", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                }).start();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip();
            }
        });
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FeedBackActivity.this);
                builder.setTitle("请选择服务项目")
                        .setIcon(R.mipmap.my)
                        .setItems(allitem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                item.setText(allitem[which]);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FeedBackActivity.this);
                builder.setTitle("请评价星级")
                        .setIcon(R.mipmap.my)
                        .setItems(stars, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                star.setText(stars[which]);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FeedBackActivity.this);
                builder.setTitle("请评价星级")
                        .setIcon(R.mipmap.my1)
                        .setItems(cleanliness, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                clean.setText(cleanliness[which]);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        attitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FeedBackActivity.this);
                builder.setTitle("请评价星级")
                        .setIcon(R.mipmap.my1)
                        .setItems(attitude1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                attitude.setText(attitude1[which]);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FeedBackActivity.this);
                builder.setTitle("请评价星级")
                        .setIcon(R.mipmap.my1)
                        .setItems(price1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                price.setText(price1[which]);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

    }

    void initView() {
        submit = (Button) findViewById(R.id.Feed_Bottom_B_submit);
        item = (TextView) findViewById(R.id.Feed_UP_L_1_T_item);
        star = (TextView) findViewById(R.id.Feed_UP_L_2_allstar);
        clean = (TextView) findViewById(R.id.Feed_UP_L_3_cleanliness);
        attitude = (TextView) findViewById(R.id.Feed_UP_L_4_attitude);
        price = (TextView) findViewById(R.id.Feed_UP_L_5_price);
        back = (Button) findViewById(R.id.Feed_B_back);
        ETother = (EditText) findViewById(R.id.Feed_UP_L_7_others);
    }

    boolean makefeedback(String ftype, int fstars, int fclean, int fattitu, int fprice, String others) {
        boolean a;
        a = checkin(ftype,fstars,fclean,fattitu,fprice);
        if (a) {
            if(others.equals(null)||others.equals(""))
                others="无";

            //记录当前时间
            java.sql.Date date_sql = new java.sql.Date(System.currentTimeMillis());
            System.out.println("this sql time" + date_sql);
            conn = link.getconnection();
            System.out.println("问题反馈页面" + conn);
            sql = "insert into feedback(Ftype,Fallstar,fclean,Fadditude,Fprice,Fother,Uid,Ftime) values(?,?,?,?,?,?,?,?)";

            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, ftype);
                pst.setInt(2, fstars);
                pst.setInt(3, fclean);
                pst.setInt(4, fattitu);
                pst.setInt(5, fprice);
                pst.setString(6, others);
                pst.setInt(7, bundle1.getInt("userid"));
                pst.setDate(8,date_sql);
                pst.execute();//执行sql
                pst.close();
                return true;

            } catch (SQLException e) {
                System.out.println("输入错误");
                System.out.println(e.getMessage());
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    boolean checkin(String type, int allstar, int clean, int attitude, int price) {
        if (type.equals("")||type.equals(null)) {
            Looper.prepare();
            Toast.makeText(FeedBackActivity.this, "请选择业务类型", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (allstar == 0) {
            Looper.prepare();
            Toast.makeText(FeedBackActivity.this, "请评价总星级", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (clean == 0) {
            Looper.prepare();
            Toast.makeText(FeedBackActivity.this, "请评价清洁度", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (attitude == 0) {
            Looper.prepare();
            Toast.makeText(FeedBackActivity.this, "请评价态度", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        } else if (price == 0) {
            Looper.prepare();
            Toast.makeText(FeedBackActivity.this, "请评价价格", Toast.LENGTH_SHORT).show();
            Looper.loop();
            return false;
        }
        return true;
    }

    void skip() {
        Intent intent = new Intent();
        intent.setClass(FeedBackActivity.this, MyActivity.class);
        next.putInt("userid", bundle1.getInt("userid"));
        next.putString("username", bundle1.getString("username"));
        intent.putExtras(next);
        startActivity(intent);
    }
}
