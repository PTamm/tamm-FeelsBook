package com.example.tamm_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CountFeelings extends AppCompatActivity {

    private int loveCount = 0;
    private int joyCount = 0;
    private int surpriseCount = 0;
    private int angerCount = 0;
    private int sadnessCount = 0;
    private int fearCount = 0;


    private static final String FILENAME = "saveFile.sav";
    ArrayList<Feeling> feelingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_feelings);

        loadFromFile();
        /* string comparison with equals(): https://stackoverflow.com/questions/513832/how-do-i-compare-strings-in-java/513839#513839
         * Users: https://stackoverflow.com/users/2603/aaron-maenpaa (original poster), and others
         *       https://stackoverflow.com/posts/513839/revisions (community wiki)
         * Date: 2018-09-27
         */
        for (Feeling feel : feelingList){
            if (feel.getFeel().equals("Love")){
                loveCount = loveCount + 1;
            } else if (feel.getFeel().equals("Joy")){
                joyCount = joyCount + 1;
            } else if (feel.getFeel().equals("Surprise")){
                surpriseCount = surpriseCount + 1;
            } else if (feel.getFeel().equals("Anger")){
                angerCount = angerCount + 1;
            } else if (feel.getFeel().equals("Sadness")){
                sadnessCount = sadnessCount + 1;
            } else if (feel.getFeel().equals("Fear")) {
                fearCount = fearCount + 1;
            }
        }

        // TODO: Consider try block later ...

        TextView loveView = (TextView) findViewById(R.id.dispLoveCount);
        loveView.setText("Love Count: "+loveCount);

        TextView joyView = (TextView) findViewById(R.id.dispJoyCount);
        joyView.setText("Joy Count: "+joyCount);

        TextView surpriseView = (TextView) findViewById(R.id.dispSurpriseCount);
        surpriseView.setText("Surprise Count: "+surpriseCount);

        TextView angerView = (TextView) findViewById(R.id.dispAngerCount);
        angerView.setText("Anger Count: "+angerCount);

        TextView sadnessView = (TextView) findViewById(R.id.dispSadnessCount);
        sadnessView.setText("Sadness Count: "+sadnessCount);

        TextView fearView = (TextView) findViewById(R.id.dispFearCount);
        fearView.setText("Fear Count: "+fearCount);
    }

    //Using Gson and file input/output came from lonelyTwitter, Joshua Campbell (2015-09-14), Abdul Ali Bangash, 2018-10-02

    private void loadFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader input = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(new GsonDES()).create();
            Type listType = new TypeToken<ArrayList<Feeling>>(){}.getType();

            feelingList = gson.fromJson(input, listType);

        } catch (FileNotFoundException fileNotFound){
            feelingList = new ArrayList<Feeling>();
        }
    }
}
