package com.example.tamm_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FeelingHistory extends AppCompatActivity {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String FILENAME = "saveFile.sav";
    ArrayList<Feeling> feelingList;
    ArrayList<String> stringFeelingList;
    ArrayAdapter<String> feelsAdapter;
    private ListView feelsHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        feelsHistoryList = (ListView) findViewById(R.id.historyList);

        // This came from Abram Hindle's 'Student Picker for Android: 6 ListView, ArrayAdapter and Observer Pattern' video
        // https://www.youtube.com/watch?v=7zKCuqScaRE&index=6&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O
        // 2018-09-26
        feelsHistoryList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(FeelingHistory.this, "Feeling: "+feelingList.get(position).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        stringFeelingList = new ArrayList<String>();


        for (Feeling feel : feelingList){
            if (feel.getComment() == null) {
                stringFeelingList.add(feel.getFeel()+"\n"+sdf.format(feel.getDate()));
            } else {
                stringFeelingList.add(feel.getFeel()+"\n"+feel.getComment()+"\n"+sdf.format(feel.getDate()));
            }
        }

        feelsAdapter = new ArrayAdapter<String>(this, R.layout.list_item, stringFeelingList);
        feelsHistoryList.setAdapter(feelsAdapter);

        // feelsAdapter.notifyDataSetChanged();
        // This came from Abram Hindle's 'Student Picker for Android: 6 ListView, ArrayAdapter and Observer Pattern' video
        // https://www.youtube.com/watch?v=7zKCuqScaRE&index=6&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O
        // 2018-09-26
        feelsHistoryList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(FeelingHistory.this, "Feeling: "+feelingList.get(position).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        feelsAdapter.notifyDataSetChanged();

    }


    public void backToMain(View view) {
        Toast.makeText(this, "Back To Main", Toast.LENGTH_SHORT).show();
        Intent additionalIntent = new Intent(FeelingHistory.this, MainActivity.class);
        startActivity(additionalIntent);
    }

    public void modifyFeeling(View view) {
        Toast.makeText(this, "Modify Feeling", Toast.LENGTH_SHORT).show();
        Intent additionalIntent = new Intent(FeelingHistory.this, ModifyFeeling.class);
        startActivity(additionalIntent);
    }

    public void viewCount(View view) {
        Toast.makeText(this, "View Feeling Count", Toast.LENGTH_SHORT).show();
        Intent countIntent = new Intent(FeelingHistory.this, CountFeelings.class);
        startActivity(countIntent);
    }


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
