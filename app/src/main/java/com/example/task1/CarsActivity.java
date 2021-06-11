package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task1.link.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarsActivity extends AppCompatActivity {
    private Button back, bdelete, badd, bchange;
    private TextView tadd, tmaster, tmid, tmyears, tnum, tmname;
    private LinearLayout linearLayout;
    private ImageView imag;
    private int userid;
    private String username;
    private int Mid1, Mid2, count;
    private boolean flag = false,deflag=false;
    public static String TAG = CarsActivity.class.getSimpleName();


    int[] a = new int[5];//车牌
    float[] b = new float[5];//使用年限
    String[] c = new String[5];//车名

    connect link = new connect();
    Connection conn = null;
    String sql = null;
    Bundle bundle1 = new Bundle();
    Bundle next = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        initView();
        initBundle();
        findUid();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(CarsActivity.this, MyActivity.class);
                next.putInt("userid", bundle1.getInt("userid"));
                next.putString("username", bundle1.getString("username"));
                intent.putExtras(next);
                startActivity(intent);
            }
        });
        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //当车辆数为3时，不能再增加
                        if (count == 3) {
                            Looper.prepare();
                            Toast.makeText(CarsActivity.this, "每人名下最多登记三辆车", Toast.LENGTH_LONG).show();
                            Looper.loop();
                        } else {
                            Intent intent = new Intent();
                            intent.setClass(CarsActivity.this, CarsAddActivity.class);
                            next.putInt("userid", bundle1.getInt("userid"));
                            next.putString("username", bundle1.getString("username"));
                            intent.putExtras(next);
                            startActivity(intent);
                        }
                    }
                }).start();
            }
        });
    }

    void initView() {
        back = (Button) findViewById(R.id.cars_B_back);
        badd = (Button) findViewById(R.id.cars_B_addcars);
        tadd = (TextView) findViewById(R.id.cars_TV_addcars);
        tnum = (TextView) findViewById(R.id.cars_TV_carsnum);
    }

    boolean findMid() {
        conn = link.getconnection();
        System.out.println("个人车辆信息页面" + conn);
        sql = "select * from motors where Masterid = ?";


        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, userid);
            ResultSet res = pst.executeQuery();
            int mid;
            String mname;
            float myears;
            int i = 0;
            while (res.next()) {
                mid = res.getInt("Mid");
                myears = res.getFloat("Myears");
                mname = res.getString("Mtype");
                a[i] = mid;
                b[i] = myears;
                c[i] = mname;
                i++;
            }
            System.out.println("我有几辆车：" + i);
            tnum.setText("" + i);
            count = i;
            pst.close();
            return true;
        } catch (SQLException e) {
            System.out.println("数据错误");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    void findUid() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                flag = findMid();//获取车辆信息
                System.out.println(flag);
                if (flag) {
                    System.out.println("count:" + count);
                    if (count > 0) {
                        setlook(a, b, c);
                    }
                }
            }
        }).start();
    }

    void setlook(int[] a1, float[] b1, String[] c1) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    addView(i, a1, b1, c1);
                }
            }
        });
    }

    void addView(int t, int[] a, float[] b, String[] c) {
        LayoutInflater inflater = LayoutInflater.from(CarsActivity.this);
        linearLayout = (LinearLayout) findViewById(R.id.cars_L_main2);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.morecars, null).findViewById(R.id.morecars_L_main);
        linearLayout.addView(layout);
        System.out.println("视图添加");
        t++;
        tmname = (TextView) findViewById(R.id.morecars_TV_Mtype);
        tmid = (TextView) findViewById(R.id.morecars_TV_Mid);
        tmyears = (TextView) findViewById(R.id.morecars_TV_Myears);
        tmaster = (TextView) findViewById(R.id.morecars_TV_Masterid);
        bchange = (Button) findViewById(R.id.morecars_B_change);
        imag = (ImageView) findViewById(R.id.morecars_img);
        bdelete=(Button)findViewById(R.id.morecars_B_delete);
        switch (t) {
            case 1:
                tmid.setId(R.id.LookCars_TV_Mid1);
                tmaster.setId(R.id.LookCars_TV_Master1);
                tmname.setId(R.id.LookCars_TV_Mname1);
                tmyears.setId(R.id.LookCars_TV_Myears1);
                bchange.setId(R.id.LookCars_B_change1);
                bdelete.setId(R.id.LookCars_B_delete1);
                setcars(0, a, b, c);
                buttonchange(1);
                break;
            case 2:
                tmid.setId(R.id.LookCars_TV_Mid2);
                tmaster.setId(R.id.LookCars_TV_Master2);
                tmname.setId(R.id.LookCars_TV_Mname2);
                tmyears.setId(R.id.LookCars_TV_Myears2);
                bchange.setId(R.id.LookCars_B_change2);
                bdelete.setId(R.id.LookCars_B_delete2);
                setcars(1, a, b, c);
                buttonchange(2);
                break;
            case 3:
                tmid.setId(R.id.LookCars_TV_Mid3);
                tmaster.setId(R.id.LookCars_TV_Master3);
                tmname.setId(R.id.LookCars_TV_Mname3);
                tmyears.setId(R.id.LookCars_TV_Myears3);
                bchange.setId(R.id.LookCars_B_change3);
                bdelete.setId(R.id.LookCars_B_delete3);
                setcars(2, a, b, c);
                buttonchange(3);
                break;
        }
    }

    //设置车辆车牌和年龄
    void setcars(int i, int[] x, float[] y, String[] k) {
        tmid.setText("" + x[i]);
        tmyears.setText("" + y[i]);
        tmname.setText("" + k[i]);
        tmaster.setText("" +username);
    }

    void initBundle() {
        bundle1 = this.getIntent().getExtras();
        userid = bundle1.getInt("userid");
        username=bundle1.getString("username");
        System.out.println("我的车辆：当前用户" + userid+"用户名："+username);
    }
    void buttonchange(int i){
        switch (i){
            case 1:
                bchange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(CarsActivity.this, CarsChangeActivity.class);
                        next.putInt("userid", bundle1.getInt("userid"));
                        next.putString("username", bundle1.getString("username"));
                        next.putInt("Mid",a[0]);
                        intent.putExtras(next);
                        startActivity(intent);
                    }
                });
                bdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tipDialog(a[0]);
                    }
                });
                break;
            case 2:
                bchange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(CarsActivity.this, CarsChangeActivity.class);
                        next.putInt("userid", bundle1.getInt("userid"));
                        next.putString("username", bundle1.getString("username"));
                        next.putInt("Mid",a[1]);
                        intent.putExtras(next);
                        startActivity(intent);
                    }
                });
                bdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tipDialog(a[1]);
                    }
                });
                break;
            case 3:
                bchange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(CarsActivity.this, CarsChangeActivity.class);
                        next.putInt("userid", bundle1.getInt("userid"));
                        next.putString("username", bundle1.getString("username"));
                        next.putInt("Mid",a[2]);
                        intent.putExtras(next);
                        startActivity(intent);
                    }
                });
                bdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tipDialog(a[2]);
                    }
                });
                break;
        }
    }
     void tipDialog(int Mid1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CarsActivity.this);
        builder.setTitle("提示：");
        builder.setMessage("确定移除该车辆？");
        builder.setCancelable(true);            //点击对话框以外的区域是否让对话框消失

        //设置正面按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        deflag=delectCars(Mid1);
                        if(deflag){
                            //删除成功后，需要重新加载页面
                            //是否可以重定位当前页面
                            Looper.prepare();
                            Intent intent=new Intent();
                            intent.setClass(CarsActivity.this, CarsActivity.class);
                            next.putInt("userid", bundle1.getInt("userid"));
                            next.putString("username", bundle1.getString("username"));
                            intent.putExtras(next);
                            startActivity(intent);
                            Toast.makeText(CarsActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }else {
                            Looper.prepare();
                            Toast.makeText(CarsActivity.this,"删除失败",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                        dialog.dismiss();
                    }
                }).start();
            }
        });
        //设置反面按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    boolean delectCars(int Mid) {
            conn = link.getconnection();
            System.out.println("预约服务页面" + conn);
            sql = "delete from motors where Mid=?";

            try {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, Mid);
                pst.execute();
                pst.close();
                return true;

            } catch (SQLException e) {
                System.out.println("数据错误");
                System.out.println(e.getMessage());
                e.printStackTrace();
                return false;
            }
    }
}
