package com.example.task1.link;

public class Feedback {
    private float Ftype;
    private int Fallstar;
    private int Fclean;
    private int Fprice;
    private int Fadditude;
    private String Fother;

    public Feedback(float ftype, int fallstar, int fclean, int fprice, int fadditude, String fother) {
        Ftype = ftype;
        Fallstar = fallstar;
        Fclean = fclean;
        Fprice = fprice;
        Fadditude = fadditude;
        Fother = fother;
    }

    public float getFtype() {
        return Ftype;
    }

    public void setFtype(float ftype) {
        Ftype = ftype;
    }

    public int getFallstar() {
        return Fallstar;
    }

    public void setFallstar(int fallstar) {
        Fallstar = fallstar;
    }

    public int getFclean() {
        return Fclean;
    }

    public void setFclean(int fclean) {
        Fclean = fclean;
    }

    public int getFprice() {
        return Fprice;
    }

    public void setFprice(int fprice) {
        Fprice = fprice;
    }

    public int getFadditude() {
        return Fadditude;
    }

    public void setFadditude(int fadditude) {
        Fadditude = fadditude;
    }

    public String getFother() {
        return Fother;
    }

    public void setFother(String fother) {
        Fother = fother;
    }
}
