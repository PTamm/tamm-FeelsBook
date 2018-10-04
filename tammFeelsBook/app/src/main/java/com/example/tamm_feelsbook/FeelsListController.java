package com.example.tamm_feelsbook;

import android.content.Context;

public class FeelsListController {
    private static FeelingList feelingList = null;

    /* Followed example of StudentPicker from Abram Hindle's youtube channel. */
    static public FeelingList getFeelingList(Context context){
        if (feelingList == null){
            feelingList = new FeelingList(context);
        }
        return feelingList;
    }

    public void addFeeling(Feeling feel, Context context) {
        getFeelingList(context).addFeeling(feel, context);
    }

    public void delFeeling(Feeling feel, Context context){
        getFeelingList(context).deleteFeeling(feel, context);
    }

    public void clearList(Context context){
        getFeelingList(context).clearList(context);
    }
}
