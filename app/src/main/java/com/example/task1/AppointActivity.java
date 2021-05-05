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
    private String[] groups = {"分类1", "分类2", "分类3", "分类4", "分类5"};
    private String[][] childs = {{"1.1", "1.2", "1.3", "1.4"}, {"2.1", "2.2", "2.3", "2.4"}, {"3.1", "3.2", "3.3", "3.4"},
            {"4.1", "4.2", "4.3", "4.4"}, {"5.1", "5.2", "5.3", "5.4"}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint);
        initView();//设置默认值
        click();//列表按钮点击事件
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(AppointActivity.this,MainActivity.class);
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
                startActivity(intent);
                //Toast.makeText(AppointActivity.this, childs[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}

