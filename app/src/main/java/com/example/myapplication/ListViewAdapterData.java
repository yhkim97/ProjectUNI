package com.example.myapplication;

public class ListViewAdapterData {

    private int c_level;
    private String c_location;
    private String c_location2;
    private String c_name;
    private double latitude;
    private double longitude;
    private int no;

    public void setC_level(int c_level){this.c_level = c_level;}
    public void setC_location(String c_location){this.c_location = c_location;}
    public void setC_location2(String c_location2){this.c_location2 = c_location2;}
    public void setC_name(String c_name){this.c_name = c_name;}
    public void setLatitude(double latitude){this.latitude = latitude;}
    public void setLongitude(double longitude){this.longitude = longitude;}
    public void setNo(int no){this.no = no;}

    public int getC_level(){return this.c_level;}
    public String getC_location(){return this.c_location;}
    public String getC_location2(){return this.c_location2;}
    public String getC_name(){return this.c_name;}
    public double getLatitude(){return this.latitude;}
    public double getLongitude(){return this.longitude;}
    public int getNo(){return this.no;}


}
