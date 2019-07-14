package com.example.Rate;
public class Data {
    String day;
    String time;
    String contnet;
    String importance;

    public Data(String day, String time, String contnet, String importance) {
        this.day = day;
        this.time = time;
        this.contnet = contnet;
        this.importance = importance;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContnet() {
        return contnet;
    }

    public void setContnet(String contnet) {
        this.contnet = contnet;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

}