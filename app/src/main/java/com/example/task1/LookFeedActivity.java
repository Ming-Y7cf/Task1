package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.task1.link.connect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LookFeedActivity extends AppCompatActivity {
    private int userid, count;
    private LinearLayout linearLayout;
    private TextView ftime, ftype, fstar, fclean, fatti, fprice, fother, fid, fcount;
    private Button back;
    private boolean flag = false;
    int[] a = new int[20];
    int[] b = new int[20];
    int[] c = new int[20];
    int[] d = new int[20];
    int[] e = new int[20];
    String[] f = new String[20];
    String[] g = new String[20];
    Date[] dates = new Date[20];

    Bundle bundle1 = new Bundle();
    Bundle next = new Bundle();
    connect link = new connect();
    Connection conn = null;
    String sql = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_feed);
        initBundle();
        initView();
        findUid();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LookFeedActivity.this, MyActivity.class);
                next.putInt("userid", bundle1.getInt("userid"));
                next.putString("username", bundle1.getString("username"));
                intent.putExtras(next);
                startActivity(intent);
            }
        });
    }

    void initView() {
        back = (Button) findViewById(R.id.Look_B_back);
        fcount = (TextView) findViewById(R.id.Look_TV_feedcount);
    }

    void initBundle() {
        bundle1 = this.getIntent().getExtras();
        if (bundle1 != null) userid = bundle1.getInt("userid");
        System.out.println("查看反馈记录：当前用户：" + userid + "用户名：" + bundle1.getString("username"));
    }

    void addView(int t, int[] a, int[] b, int[] c, int[] d, int[] e, String[] f, String[] g, Date[] date) {
        LayoutInflater inflater = LayoutInflater.from(LookFeedActivity.this);
        linearLayout = (LinearLayout) findViewById(R.id.Look_L_main2);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.morefeed, null).findViewById(R.id.morefeed_L_each);
        linearLayout.addView(layout);
        System.out.println("视图添加");
        t++;
        fid = (TextView) findViewById(R.id.morefeed_TV_fid);
        fstar = (TextView) findViewById(R.id.morefeed_TV_fstar);
        fatti = (TextView) findViewById(R.id.morefeed_TV_fatti);
        fclean = (TextView) findViewById(R.id.morefeed_TV_fclean);
        fprice = (TextView) findViewById(R.id.morefeed_TV_fprie);
        ftype = (TextView) findViewById(R.id.morefeed_TV_fype);
        fother = (TextView) findViewById(R.id.morefeed_TV_fother);
        ftime = (TextView) findViewById(R.id.morefeed_TV_ftime);

        //同 查看预约记录 页面
        testinitView();
        setfeed(t-1, a, b, c, d, e, f, g, date);
//
//        switch (t) {
//            case 1:
//                fid.setId(R.id.Look_fid1);
//                fstar.setId(R.id.Lookf_fstar1);
//                fclean.setId(R.id.Lookf_fclean1);
//                fatti.setId(R.id.Lookf_fatti1);
//                fprice.setId(R.id.Lookf_fprice1);
//                fother.setId(R.id.Lookf_fother1);
//                ftime.setId(R.id.Look_ftime1);
//                ftype.setId(R.id.Lookf_ftype1);
//                setfeed(0, a, b, c, d, e, f, g, date);
//                break;
//            case 2:
//                fid.setId(R.id.Lookf_fid2);
//                fstar.setId(R.id.Lookf_fstar2);
//                fclean.setId(R.id.Lookf_fclean2);
//                fatti.setId(R.id.Lookf_fatti2);
//                fprice.setId(R.id.Lookf_fprice2);
//                fother.setId(R.id.Lookf_fother2);
//                ftime.setId(R.id.Lookf_ftime2);
//                ftype.setId(R.id.Lookf_ftype2);
//                setfeed(1, a, b, c, d, e, f, g, date);
//                break;
//            case 3:
//                fid.setId(R.id.Lookf_fid3);
//                fstar.setId(R.id.Lookf_fstar3);
//                fclean.setId(R.id.Lookf_fclean3);
//                fatti.setId(R.id.Lookf_fatti3);
//                fprice.setId(R.id.Lookf_fprice3);
//                fother.setId(R.id.Lookf_fother3);
//                ftime.setId(R.id.Lookf_ftime3);
//                ftype.setId(R.id.Lookf_ftype3);
//                setfeed(2, a, b, c, d, e, f, g, date);
//                break;
//            case 4:
//                fid.setId(R.id.Lookf_fid4);
//                fstar.setId(R.id.Lookf_fstar4);
//                fclean.setId(R.id.Lookf_fclean4);
//                fatti.setId(R.id.Lookf_fatti4);
//                fprice.setId(R.id.Lookf_fprice4);
//                fother.setId(R.id.Lookf_fother4);
//                ftime.setId(R.id.Lookf_ftime4);
//                ftype.setId(R.id.Lookf_ftype4);
//                setfeed(3, a, b, c, d, e, f, g, date);
//                break;
//            case 5:
//                fid.setId(R.id.Lookf_fid5);
//                fstar.setId(R.id.Lookf_fstar5);
//                fclean.setId(R.id.Lookf_fclean5);
//                fatti.setId(R.id.Lookf_fatti5);
//                fprice.setId(R.id.Lookf_fprice5);
//                fother.setId(R.id.Lookf_fother5);
//                ftime.setId(R.id.Lookf_ftime5);
//                ftype.setId(R.id.Lookf_ftype5);
//                setfeed(4, a, b, c, d, e, f, g, date);
//                break;
//        }
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

    void setlook(int[] a1, int[] b1, int[] c1, int[] d1, int[] e1, String[] f1, String[] g1, Date[] date1) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    addView(i, a1, b1, c1, d1, e1, f1, g1, date1);
                }
            }
        });
    }

    void setfeed(int i, int[] a2, int[] b2, int[] c2, int[] d2, int[] e2, String[] f2, String[] g2, Date[] date2) {
        fid.setText("单号:" + a2[i]);
        fstar.setText("星级:" + b2[i]);
        fclean.setText("清洁:" + c2[i]);
        fatti.setText("态度:" + d2[i]);
        fprice.setText("价格:" + e2[i]);
        fother.setText("业务:" + f2[i]);
        ftime.setText("备注:" + g2[i]);
        ftype.setText("时间:" + date2[i]);
    }

    boolean findUid2() {
        conn = link.getconnection();
        System.out.println("反馈页面" + conn);
        sql = "select * from feedback where Uid = ?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, userid);
            ResultSet res = pst.executeQuery();
            int ffid, ffstar, ffclean, ffatti, ffpri;
            String fftype, ffother;
            Date ffdate;
            int i = 0;
            while (res.next()) {
                ffid = res.getInt("Fid");
                ffstar = res.getInt("Fallstar");
                ffclean = res.getInt("Fclean");
                ffatti = res.getInt("Fadditude");
                ffpri = res.getInt("Fprice");
                fftype = res.getString("Ftype");
                ffother = res.getString("Fother");
                ffdate = res.getDate("Ftime");

                a[i] = ffid;//反馈单号
                b[i] = ffstar;//总星级
                c[i] = ffclean;//清洁度
                d[i] = ffatti;//态度
                e[i] = ffpri;//价格
                f[i] = fftype;//业务类型
                g[i] = ffother;//备注
                dates[i] = ffdate;//反馈提交时间
                i++;
            }
            System.out.println("我有几条反馈：" + i);
            fcount.setText("" + i);
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
    void testinitView(){
        fid.setId(R.id.Look_fid1);
        fstar.setId(R.id.Lookf_fstar1);
        fclean.setId(R.id.Lookf_fclean1);
        fatti.setId(R.id.Lookf_fatti1);
        fprice.setId(R.id.Lookf_fprice1);
        fother.setId(R.id.Lookf_fother1);
        ftime.setId(R.id.Look_ftime1);
        ftype.setId(R.id.Lookf_ftype1);
    }
}
