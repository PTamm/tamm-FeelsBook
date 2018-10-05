/*
tamm-FeelsBook: Allows users to keep a log of their feelings; gives type and date.

        Copyright (C) 2018 Patrick Tamm tamm@ualberta.ca



        This program is free software: you can redistribute it and/or modify

        it under the terms of the GNU General Public License as published by

        the Free Software Foundation, either version 3 of the License, or

        (at your option) any later version.



        This program is distributed in the hope that it will be useful,

        but WITHOUT ANY WARRANTY; without even the implied warranty of

        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the

        GNU General Public License for more details.



        You should have received a copy of the GNU General Public License

        along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.example.tamm_feelsbook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DateTimeKeyListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class ModifyFeeling extends AppCompatActivity {

    /* Using SimpleDateFormat
     * https://stackoverflow.com/questions/3469507/how-can-i-change-the-date-format-in-java/3469715#3469715
     * User: https://stackoverflow.com/users/27583/christopher-parker, and
     *       https://stackoverflow.com/users/401667/molske
     * Date: 2018-09-28
     *
     * https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
     * Date: 2018-09-28*/

    /* Had trouble with format string displaying minutes for the month. How to properly type format string:
     * https://stackoverflow.com/questions/1459656/how-to-get-the-current-time-in-yyyy-mm-dd-hhmisec-millisecond-format-in-java
     * Users: https://stackoverflow.com/users/111988/sunil-kumar-sahoo, and
     *        https://stackoverflow.com/users/9282270/yassadi, and
     *        https://stackoverflow.com/users/171675/jayjay
     * Date: 2018-09-28*/

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String FILENAME = "saveFile.sav";
    ArrayList<Feeling> feelingList;
    ArrayList<String>stringFeelingList;
    ArrayAdapter<String> feelsAdapter;
    private ListView modifyFeelsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_feeling);

        modifyFeelsList = (ListView) findViewById(R.id.modifyFeelsList);

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
        modifyFeelsList.setAdapter(feelsAdapter);

        // feelsAdapter.notifyDataSetChanged();
        // This came from Abram Hindle's 'Student Picker for Android: 6 ListView, ArrayAdapter and Observer Pattern' video
        // https://www.youtube.com/watch?v=7zKCuqScaRE&index=6&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O
        // 2018-09-26
        modifyFeelsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(ModifyFeeling.this, "Feeling: "+feelingList.get(position).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // This came from Abram Hindle's 'Student Picker for Android: 6 ListView, ArrayAdapter and Observer Pattern' video
        // https://www.youtube.com/watch?v=7zKCuqScaRE&index=6&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O
        // 2018-09-26
        modifyFeelsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
                            try {
                                feel.setComment(comment);
                            } catch (CommentTooLongException e){
                                Toast.makeText(ModifyFeeling.this, "Comment too long: 100 characters max", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(ModifyFeeling.this,
                                    "Successfully added '"+comment+"' to "+feel.getFeel(),
                                    Toast.LENGTH_LONG).show();
                            feelsAdapter.notifyDataSetChanged();
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
                            feelsAdapter.notifyDataSetChanged();
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
                            feelsAdapter.notifyDataSetChanged();
                        } else if (which == 3) { //Delete this feeling ...
                            Toast.makeText(ModifyFeeling.this, "You are deleting "+feelingList.get(pos).toString(), Toast.LENGTH_SHORT).show();
                            Feeling feel = feelingList.get(pos);
                            feelingList.remove(pos);
                            stringFeelingList.remove(pos);
                            feelsAdapter.notifyDataSetChanged();
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

        feelsAdapter.notifyDataSetChanged();


    }


    public void clearList(View view){
        Toast.makeText(this, "Clearing Feeling List",Toast.LENGTH_SHORT).show();
        feelingList.clear();
        stringFeelingList.clear();
        saveToFile();
        feelsAdapter.notifyDataSetChanged();
    }

    public void feelingHistory(View view) {
        Toast.makeText(this, "View Feeling History", Toast.LENGTH_SHORT).show();
        Intent additionalIntent = new Intent(ModifyFeeling.this, FeelingHistory.class);
        startActivity(additionalIntent);
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

    private void saveToFile(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(new GsonDES()).create();
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
