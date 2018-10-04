package com.example.tamm_feelsbook;

import java.util.ArrayList;

public class FeelsListController {
    private static FeelingList feelingList = null;

    /* Followed example of StudentPicker from Abram Hindle's youtube channel. */
    static public FeelingList getFeelingList(){
        if (feelingList == null){
            feelingList = new FeelingList();
        }
        return feelingList;
    }

    static public void setFeelingList(ArrayList<Feeling> feelsList){
        getFeelingList().setFeelingList(feelsList);
    }

    public void addFeeling(Feeling feel) {
        getFeelingList().addFeeling(feel);
    }

    public void delFeeling(Feeling feel){
        getFeelingList().deleteFeeling(feel);
    }

    public void clearList(){
        getFeelingList().clearList();
    }
}
