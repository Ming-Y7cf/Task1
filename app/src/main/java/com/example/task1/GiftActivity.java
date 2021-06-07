package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GiftActivity extends AppCompatActivity {
    private Button back;
    private ImageView img1,img2;

    Bundle bundle = new Bundle();
    Bundle next = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        initView();
        bundle = this.getIntent().getExtras();
        if (bundle != null)
            System.out.println("优惠活动：当前用户为：" + bundle.getInt("userid"));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(GiftActivity.this, MainActivity.class);
                next.putInt("userid", bundle.getInt("userid"));
                next.putString("username",bundle.getString("username"));
                intent.putExtras(next);
                startActivity(intent);
            }
        });
    }
    void initView(){
        back=(Button)findViewById(R.id.gift_B_back);
        img1=(ImageView)findViewById(R.id.gift_IM_1);
        img2=(ImageView)findViewById(R.id.gift_IM_2);
    }

}
