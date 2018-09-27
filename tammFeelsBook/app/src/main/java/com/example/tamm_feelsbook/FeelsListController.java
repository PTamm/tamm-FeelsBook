package com.example.tamm_feelsbook;

public class FeelsListController {
    private static FeelingList feelingList = null;

    /* Followed exemple of StudentPicker from Abram Hindle's youtube channel. */
    static public FeelingList getFeelingList(){
        if (feelingList == null){
            feelingList = new FeelingList();
        }
        return feelingList;
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
