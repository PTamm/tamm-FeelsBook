package com.example.tamm_feelsbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Feeling {
    private String feeling;
    private Date date;
    private String comment;


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Feeling (String feel) {
        this.feeling = feel;
        this.date = new Date();
        this.comment = null;
    }

    public String getFeel(){
        return this.feeling;
    }

    public String toString(){
        return getFeel();
    }

    public Date getDate(){
        return this.date;
    }

    public void setFeel(String newFeel){
        this.feeling = newFeel;
    }

    public void setDate(String newDateString) throws ParseException {
        this.date = sdf.parse(newDateString);
    }

    public void setComment(String text){
        this.comment = text;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getComment(){
        return this.comment;
    }
}