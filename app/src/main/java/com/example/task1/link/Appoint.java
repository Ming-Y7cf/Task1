package com.example.task1.link;

import java.sql.Date;

public class Appoint {
    private int Aid;
    private String Aname;
    private String Aaddress;
    private String Aphone;
    private float Atype;
    private String Away;
    private Date Anowtime;
    private String Aappointtime;

    public Appoint(int aid, String aname, String aaddress, String aphone, float atype, String away, Date anowtime, String aappointtime) {
        Aid = aid;
        Aname = aname;
        Aaddress = aaddress;
        Aphone = aphone;
        Atype = atype;
        Away = away;
        Anowtime = anowtime;
        Aappointtime = aappointtime;
    }

    public int getAid() {
        return Aid;
    }

    public void setAid(int aid) {
        Aid = aid;
    }

    public String getAname() {
        return Aname;
    }

    public void setAname(String aname) {
        Aname = aname;
    }

    public String getAaddress() {
        return Aaddress;
    }

    public void setAaddress(String aaddress) {
        Aaddress = aaddress;
    }

    public String getAphone() {
        return Aphone;
    }

    public void setAphone(String aphone) {
        Aphone = aphone;
    }

    public float getAtype() {
        return Atype;
    }

    public void setAtype(float atype) {
        Atype = atype;
    }

    public String getAway() {
        return Away;
    }

    public void setAway(String away) {
        Away = away;
    }

    public Date getAnowtime() {
        return Anowtime;
    }

    public void setAnowtime(Date anowtime) {
        Anowtime = anowtime;
    }

    public String getAappointtime() {
        return Aappointtime;
    }

    public void setAappointtime(String aappointtime) {
        Aappointtime = aappointtime;
    }
}
