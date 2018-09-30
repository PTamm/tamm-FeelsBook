package com.example.tamm_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class DeleteFeeling extends AppCompatActivity {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final ArrayList<String> stringFeelingList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_feeling);
        ListView listView = (ListView) findViewById(R.id.delFeelsList);
        Collection<Feeling> feels = FeelsListController.getFeelingList().getFeelings(); //gets list of feelings
        final ArrayList<Feeling> feelingList = new ArrayList<Feeling>(feels);
        //final ArrayList<String> stringFeelingList = new ArrayList<String>();
        for (Feeling feel : feels){
            stringFeelingList.add(feel.getFeel()+"\n"+sdf.format(feel.getDate()));
        }
        final ArrayAdapter<String> feelsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringFeelingList);
        listView.setAdapter(feelsAdapter);

        // Abram Hindle, https://www.youtube.com/watch?v=7zKCuqScaRE&index=6&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O, 2018-09-27
        FeelsListController.getFeelingList().addFeelsListener(new FeelsListener() {
            @Override
            public void updateListener() {
                feelingList.clear();
                stringFeelingList.clear();
                Collection<Feeling> feels = FeelsListController.getFeelingList().getFeelings();
                feelingList.addAll(feels);
                for (Feeling feel : feels){
                    stringFeelingList.add(feel.getFeel()+"\n"+sdf.format(feel.getDate()));
                }
                feelsAdapter.notifyDataSetChanged();
            }
        });

        // This came from Abram Hindle's 'Student Picker for Android: 6 ListView, ArrayAdapter and Observer Pattern' video
        // https://www.youtube.com/watch?v=7zKCuqScaRE&index=6&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O
        // 2018-09-26
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(DeleteFeeling.this, "You are deleting "+feelingList.get(position).toString(), Toast.LENGTH_SHORT).show();
                Feeling feel = feelingList.get(position);
                FeelsListController.getFeelingList().deleteFeeling(feel);
                return false;
            }
        });
    }

    public void clearList(View view){
        Toast.makeText(this, "Clearing Feeling List",Toast.LENGTH_SHORT).show();
        FeelsListController flc = new FeelsListController();
        flc.clearList();
        stringFeelingList.clear();
        FeelsListController.getFeelingList().notifyListeners();
    }

}
