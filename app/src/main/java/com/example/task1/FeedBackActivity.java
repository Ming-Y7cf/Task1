package com.example.task1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FeedBackActivity extends AppCompatActivity {
    private Button submit,back;
    private TextView item,clean,star,attitude,price;
    private String allitem[]={"电瓶更换","刹车维修","车灯维修","分类4","分类5"},stars[]={"★★★★★","★★★★","★★★","★★","★"},
            cleanliness[]={"★★★★★","★★★★","★★★","★★","★"},attitude1[]={"★★★★★","★★★★","★★★","★★","★"},
            price1[]={"★★★★★","★★★★","★★★","★★","★"};

    Bundle bundle1=new Bundle();
    Bundle next=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initView();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(FeedBackActivity.this,MyActivity.class);
                next.putInt("userid",bundle1.getInt("userid"));
                intent.putExtras(next);
                startActivity(intent);
                Toast.makeText(FeedBackActivity.this,"提交成功",Toast.LENGTH_LONG).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(FeedBackActivity.this,MyActivity.class);
                next.putInt("userid",bundle1.getInt("userid"));
                intent.putExtras(next);
                startActivity(intent);
                Toast.makeText(FeedBackActivity.this,"提交成功",Toast.LENGTH_LONG).show();
            }
        });
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(FeedBackActivity.this);
                builder.setTitle("请选择服务项目")
                        .setIcon(R.mipmap.my)
                        .setItems(allitem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                item.setText(allitem[which]);
                            }
                        });
                AlertDialog dialog= builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(FeedBackActivity.this);
                builder.setTitle("请评价星级")
                        .setIcon(R.mipmap.my)
                        .setItems(stars, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                star.setText(stars[which]);
                            }
                        });
                AlertDialog dialog= builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(FeedBackActivity.this);
                builder.setTitle("请评价星级")
                        .setIcon(R.mipmap.my1)
                        .setItems(cleanliness, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                clean.setText(cleanliness[which]);
                            }
                        });
                AlertDialog dialog= builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        attitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(FeedBackActivity.this);
                builder.setTitle("请评价星级")
                        .setIcon(R.mipmap.my1)
                        .setItems(attitude1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                attitude.setText(attitude1[which]);
                            }
                        });
                AlertDialog dialog= builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(FeedBackActivity.this);
                builder.setTitle("请评价星级")
                        .setIcon(R.mipmap.my1)
                        .setItems(price1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                price.setText(price1[which]);
                            }
                        });
                AlertDialog dialog= builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

    }
    void initView(){
        submit=(Button)findViewById(R.id.Feed_Bottom_B_submit);
        item=(TextView)findViewById(R.id.Feed_UP_L_1_T_item);
        star=(TextView) findViewById(R.id.Feed_UP_L_2_allstar);
        clean=(TextView) findViewById(R.id.Feed_UP_L_3_cleanliness);
        attitude=(TextView) findViewById(R.id.Feed_UP_L_4_attitude);
        price=(TextView) findViewById(R.id.Feed_UP_L_5_price);
    }
}
