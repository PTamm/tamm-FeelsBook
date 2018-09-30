package com.example.tamm_feelsbook;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class ModifyFeeling extends AppCompatActivity {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final ArrayList<String> stringFeelingList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_feeling);
        ListView listView = (ListView) findViewById(R.id.modifyFeelsList);
        Collection<Feeling> feels = FeelsListController.getFeelingList().getFeelings(); //gets list of feelings
        final ArrayList<Feeling> feelingList = new ArrayList<Feeling>(feels);
        //final ArrayList<String> stringFeelingList = new ArrayList<String>();
        for (Feeling feel : feels){
            if (feel.getComment()==null){
                stringFeelingList.add(feel.getFeel()+"\n"+sdf.format(feel.getDate()));
            } else {
                stringFeelingList.add(feel.getFeel()+"\n"+feel.getComment()+"\n"+sdf.format(feel.getDate()));
            }
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
                for (Feeling feel : feels) {
                    if (feel.getComment() == null) {
                        stringFeelingList.add(feel.getFeel() + "\n" + sdf.format(feel.getDate()));
                    } else {
                        stringFeelingList.add(feel.getFeel() + "\n" + feel.getComment() + "\n" + sdf.format(feel.getDate()));
                    }
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
                Toast.makeText(ModifyFeeling.this, "You are selecting "+feelingList.get(position).toString(), Toast.LENGTH_SHORT).show();
                //Abram Hindle, how to use AlertDialog, https://www.youtube.com/watch?v=7zKCuqScaRE&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O&index=6, 2018-09-29
                final int pos = position;
                AlertDialog.Builder modifyAlert = new AlertDialog.Builder(ModifyFeeling.this);
                modifyAlert.setMessage("Modifying "+feelingList.get(position)+"?");
                modifyAlert.setCancelable(true);
                modifyAlert.setPositiveButton("Add comment", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Feeling feel = feelingList.get(pos);
                        //getting user input, https://developer.android.com/training/basics/firstapp/starting-activity#java, 2018-09-29
                        EditText editText = (EditText) findViewById(R.id.modifyComment);
                        String comment = editText.getText().toString();
                        feel.setComment(comment);
                    }
                });
                modifyAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing ...
                    }
                });
                modifyAlert.show();
                return false;
            }
        });
    }
}
