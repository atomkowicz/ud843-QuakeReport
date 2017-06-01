package com.example.android.quakereport;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {
    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double pMagnitude) {
        magnitude = pMagnitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String pLocation) {
        location = pLocation;
    }

    public Long getTimeInMilliseconds() {
        return miliseconds;
    }

    public void setMiliseconds(Long pMiliseconds) {
        miliseconds = pMiliseconds;
    }

    private double magnitude;
    private String location;
    private Long miliseconds;

    public String getUrl() {
        return url;
    }

    public void setUrl(String pUrl) {
        url = pUrl;
    }

    private String url;

    public Earthquake(double magnitude, String location, Long miliseconds, String url) {
        this.magnitude = magnitude;
        this.location = location;
        this.miliseconds = miliseconds;
        this.url = url;
    }
}
