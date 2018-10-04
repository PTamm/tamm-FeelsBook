package com.example.tamm_feelsbook;

import android.content.Context;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class ModifyFeeling extends AppCompatActivity {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final ArrayList<String> stringFeelingList = new ArrayList<String>();
    private static final String FILENAME = "file.sav";
    ArrayList<Feeling> feelingList;
    ArrayAdapter<Feeling> feelsAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_feeling);

        listView = (ListView) findViewById(R.id.modifyFeelsList);

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

                /* Idea to use setItems(): https://developer.android.com/reference/android/app/AlertDialog.Builder#setItems(int, android.content.DialogInterface.OnClickListener), 2018-09-30
                 * How to implement:
                 *   User: https://stackoverflow.com/users/1706269/mark
                 *   Source: https://stackoverflow.com/questions/14480788/alertdialog-builder-setitems-list-with-another
                 *   Date: 2018-09-30
                 */

                modifyAlert.setTitle("Modifying "+feelingList.get(position)+"?");
                modifyAlert.setCancelable(true);

                final CharSequence[] choices = {"Add Comment","Change Feeling","Change Date","Delete This Feeling","Cancel"};

                modifyAlert.setItems(choices, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){ //Add comment ...
                            Feeling feel = feelingList.get(pos);
                            //getting user input, https://developer.android.com/training/basics/firstapp/starting-activity#java, 2018-09-29
                            EditText editText = (EditText) findViewById(R.id.modifyComment);
                            String comment = editText.getText().toString();
                            feel.setComment(comment);
                            Toast.makeText(ModifyFeeling.this,
                                    "Successfully added '"+comment+"' to "+feel.getFeel(),
                                    Toast.LENGTH_LONG).show();
                        } else if (which == 1){ //Change Feeling ...
                            Feeling feel = feelingList.get(pos);
                            //getting user input, https://developer.android.com/training/basics/firstapp/starting-activity#java, 2018-09-29
                            EditText editText = (EditText) findViewById(R.id.modifyComment);
                            String newFeel = editText.getText().toString();
                            String oldFeel = feel.getFeel();
                            feel.setFeel(newFeel);
                            Toast.makeText(ModifyFeeling.this,
                                    "Successfully changed "+oldFeel+" to "+newFeel,
                                    Toast.LENGTH_LONG).show();
                        } else if (which == 2){ //Change Date ...
                            Feeling feel = feelingList.get(pos);
                            //getting user input, https://developer.android.com/training/basics/firstapp/starting-activity#java, 2018-09-29
                            EditText editText = (EditText) findViewById(R.id.modifyComment);
                            String newDate = editText.getText().toString();
                            //handling exceptions, lonelyTwitter code, Cody Rosevear, 2018-09-30
                            try{
                                feel.setDate(newDate);
                            } catch (ParseException prsEx){
                                Toast.makeText(ModifyFeeling.this, "Parse Exception encountered.",Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(ModifyFeeling.this,
                                    "Changing date to "+newDate,
                                    Toast.LENGTH_LONG).show();
                        } else if (which == 3) { //Delete this feeling ...
                            Toast.makeText(ModifyFeeling.this, "You are deleting "+feelingList.get(pos).toString(), Toast.LENGTH_SHORT).show();
                            Feeling feel = feelingList.get(pos);
                        } else { //Last item clicked; cancel ...
                            Toast.makeText(ModifyFeeling.this,
                                    "Cancel",
                                    Toast.LENGTH_SHORT).show();
                        }
                        saveToFile();
                        feelsAdapter.notifyDataSetChanged();
                    }
                });
                //saveToFile();
                modifyAlert.show();
                return false;
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        setContentView(R.layout.activity_edit_feeling);

        feelsAdapter = new ArrayAdapter<Feeling>(this, android.R.layout.simple_list_item_1, feelingList);
        listView.setAdapter(feelsAdapter);

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

                /* Idea to use setItems(): https://developer.android.com/reference/android/app/AlertDialog.Builder#setItems(int, android.content.DialogInterface.OnClickListener), 2018-09-30
                 * How to implement:
                 *   User: https://stackoverflow.com/users/1706269/mark
                 *   Source: https://stackoverflow.com/questions/14480788/alertdialog-builder-setitems-list-with-another
                 *   Date: 2018-09-30
                 */

                modifyAlert.setTitle("Modifying "+feelingList.get(position)+"?");
                modifyAlert.setCancelable(true);

                final CharSequence[] choices = {"Add Comment","Change Feeling","Change Date","Delete This Feeling","Cancel"};

                modifyAlert.setItems(choices, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){ //Add comment ...
                            Feeling feel = feelingList.get(pos);
                            //getting user input, https://developer.android.com/training/basics/firstapp/starting-activity#java, 2018-09-29
                            EditText editText = (EditText) findViewById(R.id.modifyComment);
                            String comment = editText.getText().toString();
                            feel.setComment(comment);
                            Toast.makeText(ModifyFeeling.this,
                                    "Successfully added '"+comment+"' to "+feel.getFeel(),
                                    Toast.LENGTH_LONG).show();
                        } else if (which == 1){ //Change Feeling ...
                            Feeling feel = feelingList.get(pos);
                            //getting user input, https://developer.android.com/training/basics/firstapp/starting-activity#java, 2018-09-29
                            EditText editText = (EditText) findViewById(R.id.modifyComment);
                            String newFeel = editText.getText().toString();
                            String oldFeel = feel.getFeel();
                            feel.setFeel(newFeel);
                            Toast.makeText(ModifyFeeling.this,
                                    "Successfully changed "+oldFeel+" to "+newFeel,
                                    Toast.LENGTH_LONG).show();
                        } else if (which == 2){ //Change Date ...
                            Feeling feel = feelingList.get(pos);
                            //getting user input, https://developer.android.com/training/basics/firstapp/starting-activity#java, 2018-09-29
                            EditText editText = (EditText) findViewById(R.id.modifyComment);
                            String newDate = editText.getText().toString();
                            //handling exceptions, lonelyTwitter code, Cody Rosevear, 2018-09-30
                            try{
                                feel.setDate(newDate);
                            } catch (ParseException prsEx){
                                Toast.makeText(ModifyFeeling.this, "Parse Exception encountered.",Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(ModifyFeeling.this,
                                    "Changing date to "+newDate,
                                    Toast.LENGTH_LONG).show();
                        } else if (which == 3) { //Delete this feeling ...
                            Toast.makeText(ModifyFeeling.this, "You are deleting "+feelingList.get(pos).toString(), Toast.LENGTH_SHORT).show();
                            Feeling feel = feelingList.get(pos);
                        } else { //Last item clicked; cancel ...
                            Toast.makeText(ModifyFeeling.this,
                                    "Cancel",
                                    Toast.LENGTH_SHORT).show();
                        }
                        saveToFile();
                        feelsAdapter.notifyDataSetChanged();
                    }
                });
                //saveToFile();
                modifyAlert.show();
                return false;
            }
        });
    }

    public void clearList(View view){
        Toast.makeText(this, "Clearing Feeling List",Toast.LENGTH_SHORT).show();
        feelingList.clear();
        stringFeelingList.clear();
        saveToFile();
        feelsAdapter.notifyDataSetChanged();
    }

    //Using Gson and file input/output came from lonelyTwitter, Joshua Campbell (2015-09-14), Abdul Ali Bangash, 2018-10-02

    private void loadFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader input = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Feeling>>(){}.getType();

            feelingList = gson.fromJson(input, listType);

        } catch (FileNotFoundException fileNotFound){
            feelingList = new ArrayList<Feeling>();
        }
    }

    private void saveToFile(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

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
