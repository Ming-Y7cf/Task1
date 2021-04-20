package com.example.task1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FeedBackActivity extends AppCompatActivity {
    private Button submit;
    private EditText star,item,clean,attitude,price;
    private String allitem[]={"电瓶更换","刹车维修","车灯维修"},stars[]={"★★★★★","★★★★","★★★","★★","★"},
            cleanliness[]={"★★★★★","★★★★","★★★","★★","★"},attitude1[]={"★★★★★","★★★★","★★★","★★","★"},
            price1[]={"★★★★★","★★★★","★★★","★★","★"};


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
                startActivity(intent);
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
        item=(EditText)findViewById(R.id.Feed_UP_L_1_E_item);
        star=(EditText)findViewById(R.id.Feed_UP_L_2_allstar);
        clean=(EditText)findViewById(R.id.Feed_UP_L_3_cleanliness);
        attitude=(EditText)findViewById(R.id.Feed_UP_L_4_attitude);
        price=(EditText)findViewById(R.id.Feed_UP_L_5_price);
    }
}
