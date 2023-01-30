package com.example.prayerlog;

public class Prayer
{
    private String prayerName;
    private String prayerDate;
    private Boolean isPray;
    private Boolean bajamat;
    private int numOfRakats;


    public Boolean getBajamat() {
        return bajamat;
    }

    public void setBajamat(Boolean bajamat) {
        this.bajamat = bajamat;
    }

    public int getNumOfRakats() {
        return numOfRakats;
    }

    public void setNumOfRakats(int numOfRakats) {
        this.numOfRakats = numOfRakats;
    }

    public Prayer(String name, String date, Boolean pray, Boolean bajamat, int rakats)
    {
        this.bajamat = bajamat;
        this.isPray = pray;
        this.prayerDate = date;
        this.prayerName = name;
        this.numOfRakats = rakats;
    }

    @Override
    public String toString() {
        return  "PrayerName='" + prayerName + '\'' +
                "\nPrayerDate='" + prayerDate + '\'' +
                "\nisPray=" + (isPray?"YES":"NO") +
                "\nBajamat=" + (bajamat?"YES":"NO") +
                "\nRakats=" + numOfRakats;
    }

    public Boolean getPray() {
        return isPray;
    }

    public void setPray(Boolean pray) {
        isPray = pray;
    }

    public String getPrayerDate() {
        return prayerDate;
    }

    public void setPrayerDate(String prayerDate) {
        this.prayerDate = prayerDate;
    }

    public String getPrayerName() {
        return prayerName;
    }

    public void setPrayerName(String prayerName) {
        this.prayerName = prayerName;
    }
}
