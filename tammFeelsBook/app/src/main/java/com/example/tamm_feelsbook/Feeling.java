package com.example.tamm_feelsbook;

import java.util.Date;

public class Feeling {
    private String feeling;
    private Date date;
    private String comment;

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

    public void setDate(Date newDate){
        this.date = newDate;
    }

    public void setComment(String text){
        this.comment = text;
    }

    public String getComment(){
        return this.comment;
    }
}