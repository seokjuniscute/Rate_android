package com.example.Rate;
public class Data {
    String Day;
    String Time;
    String Contnet;
    String Importance;

    public Data(String day, String time, String contnet, String importance) {
        Day = day;
        Time = time;
        Contnet = contnet;
        Importance = importance;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getContnet() {
        return Contnet;
    }

    public void setContnet(String contnet) {
        Contnet = contnet;
    }

    public String getImportance() {
        return Importance;
    }

    public void setImportance(String importance) {
        Importance = importance;
    }

}