package com.mirza.gelirhesaplayici;

public class Giderler {
    private int giderid;
    private String gider;
    private double giderTutar;

    public Giderler() { //boş constructor
    }

    public Giderler(int giderid, String gider, double giderTutar) {
        this.giderid = giderid;
        this.gider = gider;
        this.giderTutar = giderTutar;
    }

    public int getGiderid() {
        return giderid;
    }

    public void setGiderid(int giderid) {
        this.giderid = giderid;
    }

    public String getGider() {
        return gider;
    }

    public void setGider(String gider) {
        this.gider = gider;
    }

    public double getGiderTutar() {
        return giderTutar;
    }

    public void setGiderTutar(double giderTutar) {
        this.giderTutar = giderTutar;
    }
}
