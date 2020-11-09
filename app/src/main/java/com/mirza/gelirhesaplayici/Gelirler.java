package com.mirza.gelirhesaplayici;

public class Gelirler {

    private int gelirid;
    private String gelir;
    private double gelirTutar;

    public Gelirler() {
    }

    public Gelirler(int gelirid, String gelir, double gelirTutar) {
        this.gelirid = gelirid;
        this.gelir = gelir;
        this.gelirTutar = gelirTutar;
    }

    public int getGelirid() {
        return gelirid;
    }

    public void setGelirid(int gelirid) {
        this.gelirid = gelirid;
    }

    public String getGelir() {
        return gelir;
    }

    public void setGelir(String gelir) {
        this.gelir = gelir;
    }

    public double getGelirTutar() {
        return gelirTutar;
    }

    public void setGelirTutar(double gelirTutar) {
        this.gelirTutar = gelirTutar;
    }
}
