package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyActivity extends AppCompatActivity {

    private TextView nickname;
    private Button feedback,main,my;
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
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(MyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    void initView(){
        nickname=(TextView)findViewById(R.id.my_R_up_nickname);
        feedback=(Button)findViewById(R.id.my_R_down_B_feedback);
        photo=(ImageView)findViewById(R.id.change_R_up_img);
        main=(Button)findViewById(R.id.change_bottom_B_tomain);
        my=(Button)findViewById(R.id.change_bottom_B_tomy);
    }
}
