package com.example.Rate;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Rate {
    @SerializedName("what_you_do") String whatYouDo;
    @SerializedName("what_you_good") String whatYouGood;
    @SerializedName("what_you_bad") String whatYouBad;
    @SerializedName("date") Date date;

    public Rate(String whatYouDo, String whatYouGood, String whatYouBad, Date date){
        this.whatYouDo = whatYouDo;
        this.whatYouGood = whatYouGood;
        this.whatYouBad = whatYouBad;
        this.date = date;
    }

    public String getWhatYouDo() {
        return whatYouDo;
    }

    public void setWhatYouDo(String whatYouDo) {
        this.whatYouDo = whatYouDo;
    }

    public String getWhatYouGood() {
        return whatYouGood;
    }

    public void setWhatYouGood(String whatYouGood) {
        this.whatYouGood = whatYouGood;
    }

    public String getWhatYouBad() {
        return whatYouBad;
    }

    public void setWhatYouBad(String whatYouBad) {
        this.whatYouBad = whatYouBad;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
