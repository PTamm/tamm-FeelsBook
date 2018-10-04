package com.example.tamm_feelsbook;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class FeelingList {
    protected ArrayList<Feeling> feelingList;
    protected ArrayList<FeelsListener> feelsListeners; //How to do this: Abram Hindle, 'Student Picker for Android: 6 ListView, ArrayAdapter and Observer Pattern' https://www.youtube.com/watch?v=7zKCuqScaRE&index=6&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O, 2018-09-27

    private static final String FILENAME = "savedFeels.sav";

    public FeelingList(Context context){
        loadFromFile(context);
        feelsListeners = new ArrayList<FeelsListener>();
    }

    public Collection<Feeling> getFeelings(){
        return feelingList;
    }

    public void addFeeling(Feeling feel, Context context){
        feelingList.add(feel);
        notifyListeners();
        saveToFile(context);
    }

    public void deleteFeeling(Feeling feel, Context context){
        feelingList.remove(feel);
        notifyListeners();
        saveToFile(context);
    }

    public void clearList(Context context){
        feelingList.clear();
        notifyListeners();
        saveToFile(context);
    }

    public void notifyListeners(){
        for (FeelsListener fl : feelsListeners){
            fl.updateListener();
        }
    }

    public ArrayList<Feeling> getFeelingList(){
        return this.feelingList;
    }

    public void addFeelsListener(FeelsListener fl){
        feelsListeners.add(fl);
    }


    //Using Gson and file input/output came from lonelyTwitter, Joshua Campbell (2015-09-14), Abdul Ali Bangash, 2018-10-02
    private void loadFromFile(Context context){

        try {

            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader input = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Feeling>>(){}.getType();

            feelingList = gson.fromJson(input, listType);

        } catch (FileNotFoundException fileNotFound){
            feelingList = new ArrayList<Feeling>();
        }
    }

    private void saveToFile(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(feelingList,output);
            output.flush();
            fos.close();

        } catch (FileNotFoundException fileNotFound){
            fileNotFound.printStackTrace();
        } catch (IOException ioError){
            ioError.printStackTrace();
        }
    }

}
