package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class changeNameActivity extends AppCompatActivity {
    private Button back,save1,save2;
    private int userid;

    Bundle bundle1=new Bundle();
    Bundle next=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        initView();
        bundle1=this.getIntent().getExtras();
        userid=bundle1.getInt("userid");
        System.out.println("修改信息页面：当前用户："+userid);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(changeNameActivity.this,MyActivity.class);
                next.putInt("userid",bundle1.getInt("userid"));
                intent.putExtras(next);
                startActivity(intent);
            }
        });
        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(changeNameActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
            }
        });
        save2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(changeNameActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
    void initView(){
        back=(Button)findViewById(R.id.change_R_up_1_B_back);
        save1=(Button)findViewById(R.id.change_Bottom_save);
        save2=(Button)findViewById(R.id.change_R_up_1_B_save);

    }
}
