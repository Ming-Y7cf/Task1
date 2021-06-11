package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.task1.link.connect;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LookAppointActivity extends AppCompatActivity {
    private int userid,count;
    private Button back;
    private TextView acount,aid,aaddress,atime,away,atype,anowtime,aphone,aname;
    private boolean flag=false,flagmain=false;
    private LinearLayout linearLayout;

    int[] a = new int[100];
    String[] b = new String[100];
    String[] c = new String[100];
    String[] d = new String[100];
    BigDecimal[] e = new BigDecimal[100];
    String[] f = new String[100];
    String[] g = new String[100];
    Date[] dates = new Date[100];

    Bundle bundle1 = new Bundle();
    Bundle next = new Bundle();
    connect link = new connect();
    Connection conn = null;
    String sql = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_appoint);
        initBundle();
        initView();
        findUid();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flagmain){
                    Intent intent = new Intent();
                    intent.setClass(LookAppointActivity.this, MainActivity.class);
                    next.putInt("userid", bundle1.getInt("userid"));
                    next.putString("username", bundle1.getString("username"));
                    intent.putExtras(next);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent();
                    intent.setClass(LookAppointActivity.this, MyActivity.class);
                    next.putInt("userid", bundle1.getInt("userid"));
                    next.putString("username", bundle1.getString("username"));
                    intent.putExtras(next);
                    startActivity(intent);
                }
            }
        });
    }
    void initBundle() {
        bundle1 = this.getIntent().getExtras();
        if (bundle1 != null) userid = bundle1.getInt("userid");
        System.out.println("查看预约记录：当前用户：" + userid + "用户名：" + bundle1.getString("username"));
        String main=bundle1.getString("ismain");
        if(main.equals("main")){
            flagmain=true;
        }
    }
    void initView() {
        back = (Button) findViewById(R.id.LookAppiont_B_back);
        acount = (TextView) findViewById(R.id.Lookappoint_TV_appointcount);
    }
    void findUid() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                flag = findUid2();
                System.out.println(flag);
                if (flag) {
                    System.out.println("count:" + count);
                    if (count > 0) {
                        setlook(a, b, c, d, e, f, g, dates);
                    }
                }
            }
        }).start();
    }
    boolean findUid2() {
        conn = link.getconnection();
        System.out.println("订单页面" + conn);
        sql = "select * from appoint where Uid = ?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, userid);
            ResultSet res = pst.executeQuery();
            int aaid ;
            String aatype,aatime,aaway,aaadress,aaname;
            BigDecimal aaphone;
            Date aanow;
            int i = 0;
            while (res.next()) {
                aaid = res.getInt("Aid");
                aatype=res.getString("Atype");
                aatime=res.getString("Aappointtime");
                aaway=res.getString("Away");
                aaphone=res.getBigDecimal("Aphone");
                aaadress=res.getString("Aaddress");
                aaname=res.getString("Aname");
                aanow = res.getDate("Anowtime");


                a[i] = aaid;//预约单号
                b[i] = aatype;//服务类型
                c[i] = aatime;//计划时间
                d[i] = aaway;//服务方式
                e[i] = aaphone;//联系方式
                f[i] = aaadress;//地址
                g[i] = aaname;//姓名
                dates[i] = aanow;//预约提交时间
                i++;
            }
            System.out.println("我有几条反馈：" + i);
            acount.setText("" + i);
            count = i;
            pst.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println("数据错误");
            System.out.println(sqle.getMessage());
            sqle.printStackTrace();
            return false;
        }
    }
    void setlook(int[] a1, String[] b1, String[] c1, String[] d1, BigDecimal[] e1, String[] f1, String[] g1, Date[] date1) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    addView(i, a1, b1, c1, d1, e1, f1, g1, date1);
                }
            }
        });
    }
    void addView(int t, int[] a, String[] b, String[] c, String[] d, BigDecimal[] e, String[] f, String[] g, Date[] date) {
        LayoutInflater inflater = LayoutInflater.from(LookAppointActivity.this);
        linearLayout = (LinearLayout) findViewById(R.id.Lookappoint_L_main);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.moreappoint, null).findViewById(R.id.moreappoint_L_main);
        linearLayout.addView(layout);
        System.out.println("视图添加");
        t++;
        aid = (TextView) findViewById(R.id.moreappoint_TV_aid);
        aaddress = (TextView) findViewById(R.id.moreappoint_TV_aaddress);
        aname = (TextView) findViewById(R.id.moreappoint_TV_aname);
        anowtime = (TextView) findViewById(R.id.moreappoint_TV_anowtime);
        aphone = (TextView) findViewById(R.id.moreappoint_TV_aphone);
        atime = (TextView) findViewById(R.id.moreappoint_TV_atime);
        atype = (TextView) findViewById(R.id.moreappoint_TV_atype);
        away = (TextView) findViewById(R.id.moreappoint_TV_away);
        /**
         * 实现了多记录查询的简化操作
         * 但我认为，这是建立在“查询”，不对生成的数据继续进行操作才能实现
         * 同理，在车辆查看页面，该方法无效；因为需要对车辆进行修改信息操作时，Button必须设置id与当前车辆一一对应
         */
        testinitView();
        setfeed(t-1, a, b, c, d, e, f, g, date);

//        switch (t) {
//            case 1:
//                testinitView();
//                setfeed(0, a, b, c, d, e, f, g, date);
//                break;
//            case 2:
//                testinitView();
//                setfeed(1, a, b, c, d, e, f, g, date);
//                break;
//            case 3:
//                testinitView();
//                setfeed(2, a, b, c, d, e, f, g, date);
//                break;
//            case 4:
//                testinitView();
//                setfeed(3, a, b, c, d, e, f, g, date);
//                break;
//            case 5:
//                testinitView();
//                setfeed(4, a, b, c, d, e, f, g, date);
//                break;
//            case 6:
//                testinitView();
//                setfeed(5, a, b, c, d, e, f, g, date);
//                break;
//            case 7:
//                testinitView();
//                setfeed(6, a, b, c, d, e, f, g, date);
//                break;
//            case 8:
//                testinitView();
//                setfeed(7, a, b, c, d, e, f, g, date);
//                break;
//            case 9:
//                setfeed(8, a, b, c, d, e, f, g, date);
//                break;
//            case 10:
//                setfeed(9, a, b, c, d, e, f, g, date);
//                break;
//            case 11:
//                setfeed(10, a, b, c, d, e, f, g, date);
//                break;
//        }
    }
    void setfeed(int i, int[] a2, String[] b2, String[] c2, String[] d2, BigDecimal[] e2, String[] f2, String[] g2, Date[] date2) {
        aid.setText("" +a2[i]);
        atype.setText("" + b2[i]);
        atime.setText("" + c2[i]);
        away.setText("" + d2[i]);
        aphone.setText("" + e2[i]);
        aaddress.setText("" + f2[i]);
        aname.setText("" + g2[i]);
        anowtime.setText("" + date2[i]);
    }
    void testinitView(){
        aid.setId(R.id.LookAppoint_TV_Aid3);
        aaddress.setId(R.id.LookAppoint_TV_Aaddress3);
        aname.setId(R.id.LookAppoint_TV_Aname3);
        anowtime.setId(R.id.LookAppoint_TV_Anowtime3);
        aphone.setId(R.id.LookAppoint_TV_Aphone3);
        atime.setId(R.id.LookAppoint_TV_Atime3);
        atype.setId(R.id.LookAppoint_TV_Atype3);
        away.setId(R.id.LookAppoint_TV_Away3);
    }
}
