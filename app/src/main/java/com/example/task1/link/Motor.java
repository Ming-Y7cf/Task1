package com.example.task1.link;

public class Motor {
    private int Mid;
    private String Mtype;//车辆类型
    private float Myears;//使用年限
    private int Masterid;//车主id
    private String Mphone;//车主电话


    public Motor(int mid, String mtype, float myears, int masterid, String mphone) {
        this.Mid = mid;
        this.Mtype = mtype;
        this.Myears = myears;
        this.Masterid = masterid;
        this.Mphone = mphone;
    }

    public int getMid() {
        return Mid;
    }

    public void setMid(int mid) {
        Mid = mid;
    }

    public String getType() {
        return Mtype;
    }

    public void setType(String type) {
        this.Mtype = type;
    }

    public float getYears() {
        return Myears;
    }

    public void setYears(float years) {
        this.Myears = years;
    }

    public int getMasterid() {
        return Masterid;
    }

    public void setMasterid(int masterid) {
        this.Masterid = masterid;
    }

    public String getMphone() {
        return Mphone;
    }

    public void setMphone(String mphone) {
        this.Mphone = mphone;
    }
}
