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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(GiftActivity.this,MainActivity.class);
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
