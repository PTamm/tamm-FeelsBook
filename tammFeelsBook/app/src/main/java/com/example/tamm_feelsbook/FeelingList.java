package com.example.tamm_feelsbook;

import java.util.ArrayList;
import java.util.Collection;

public class FeelingList {
    protected ArrayList<Feeling> feelingList;

    public FeelingList(){
        feelingList = new ArrayList<Feeling>();
    }

    public Collection<Feeling> getFeelings(){
        return feelingList;
    }

    public void addFeeling(Feeling feel){
        feelingList.add(feel);
    }

    public void deleteFeeling(Feeling feel){
        feelingList.remove(feel);
    }
}
