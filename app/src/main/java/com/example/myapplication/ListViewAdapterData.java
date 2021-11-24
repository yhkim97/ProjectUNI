package com.example.myapplication;

public class ListViewAdapterData {

    private String c_level;
    private String c_location;
    private String c_location2;
    private String c_name;
    private Double latitude;
    private Double longitude;
    private String info;


    public void setInfo(String info){this.info = info;}
    public void setC_level(String c_level){this.c_level = c_level;}
    public void setC_location(String c_location){this.c_location = c_location;}
    public void setC_location2(String c_location2){this.c_location2 = c_location2;}
    public void setC_name(String c_name){this.c_name = c_name;}
    public void setLatitude(Double latitude){this.latitude = latitude;}
    public void setLongitude(Double longitude){this.longitude = longitude;}

    public String getInfo() {return info;}
    public String getC_level(){return this.c_level;}
    public String getC_location(){return this.c_location;}
    public String getC_location2(){return this.c_location2;}
    public String getC_name(){return c_name;}
    public Double getLatitude(){return latitude;}
    public Double getLongitude(){return longitude;}



}
