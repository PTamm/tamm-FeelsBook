package com.example.tamm_feelsbook;

import java.util.ArrayList;
import java.util.Collection;

public class FeelingList {
    protected ArrayList<Feeling> feelingList;
    protected ArrayList<FeelsListener> feelsListeners; //How to do this: Abram Hindle, 'Student Picker for Android: 6 ListView, ArrayAdapter and Observer Pattern' https://www.youtube.com/watch?v=7zKCuqScaRE&index=6&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O, 2018-09-27

    public FeelingList(){
        feelingList = new ArrayList<Feeling>();
        feelsListeners = new ArrayList<FeelsListener>();
    }

    public Collection<Feeling> getFeelings(){
        return feelingList;
    }

    public void addFeeling(Feeling feel){
        feelingList.add(feel);
        notifyListeners();
    }

    public void deleteFeeling(Feeling feel){
        feelingList.remove(feel);
        notifyListeners();
    }

    public void clearList(){
        feelingList.clear();
        notifyListeners();
    }

    public void notifyListeners(){
        for (FeelsListener fl : feelsListeners){
            fl.updateListener();
        }
    }

    public void addFeelsListener(FeelsListener fl){
        feelsListeners.add(fl);
    }

}
