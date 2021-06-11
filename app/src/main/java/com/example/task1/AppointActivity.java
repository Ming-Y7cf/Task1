package com.example.task1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.task1.adapter.Adapter;

public class AppointActivity extends AppCompatActivity {
    private Button back;
    private ExpandableListView expand_list_id;
    //定义的数据
    private String[] groups = {"外部故障", "电机故障", "电瓶", "仪表盘故障"};
    private String[][] childs = {{"喇叭", "灯", "玻璃", "其他"}, {"电机时停时转", "电机噪音过大", "电机抖动", "电机不转"}, {"以旧换新", "电瓶维护", "电瓶行程短", "充电器故障"},
            {"仪表无显示", "仪表更换", "其他问题"}};
    Bundle bundle1=new Bundle();
    Bundle next=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint);
        bundle1=this.getIntent().getExtras();
        System.out.println("预约服务：当前用户："+bundle1.getInt("userid")+"用户名："+bundle1.getString("username"));
        initView();//设置默认值
        click();//列表按钮点击事件

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(AppointActivity.this,MainActivity.class);
                next.putInt("userid",bundle1.getInt("userid"));
                next.putString("username",bundle1.getString("username"));
                intent.putExtras(next);
                startActivity(intent);
            }
        });
    }

     void initView() {
        back=(Button)findViewById(R.id.appoint_back);

        expand_list_id = findViewById(R.id.appoint_Expandlistview);
        Adapter adapter = new Adapter(this, groups, childs);
        expand_list_id.setAdapter(adapter);
        //默认展开第一个数组
        expand_list_id.expandGroup(0);
        //关闭数组某个数组，可以通过该属性来实现全部展开和只展开一个列表功能
        //expand_list_id.collapseGroup(0);
    }

    void click() {
        expand_list_id.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            //childPosition代表着要接收的数组的列数
            //groupPosition代表着要接收的数组的行数
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //无论点击那个按钮 都会跳转 只是显示数据不同
                Intent intent = new Intent(AppointActivity.this,appoint_sure.class);
                //获取数据中数据 在下一个页面显示
                appoint_sure.str=childs[groupPosition][childPosition];
                next.putInt("userid",bundle1.getInt("userid"));
                next.putString("username",bundle1.getString("username"));
                intent.putExtras(next);
                startActivity(intent);
                //Toast.makeText(AppointActivity.this, childs[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}

