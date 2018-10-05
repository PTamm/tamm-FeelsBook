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
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DateTimeKeyListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

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
    private static final String FILENAME = "savedFeels.sav";
    ArrayList<Feeling> feelingList;
    ArrayAdapter<Feeling> feelsAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.feelsList);

        // This came from Abram Hindle's 'Student Picker for Android: 6 ListView, ArrayAdapter and Observer Pattern' video
        // https://www.youtube.com/watch?v=7zKCuqScaRE&index=6&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O
        // 2018-09-26
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Feeling: "+feelingList.get(position).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        setContentView(R.layout.activity_main);
        loadFromFile();

        feelsAdapter = new ArrayAdapter<Feeling>(this, android.R.layout.simple_list_item_1, feelingList);


        // This came from Abram Hindle's 'Student Picker for Android: 6 ListView, ArrayAdapter and Observer Pattern' video
        // https://www.youtube.com/watch?v=7zKCuqScaRE&index=6&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O
        // 2018-09-26
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Feeling: "+feelingList.get(position).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    /*  Idea to use one function to handle all button clicks came from android developer guide.
    Site: https://developer.android.com/guide/topics/ui/controls/radiobutton#java
    Date: 2018-09-22
 */
    public void buttonAddClicked(View view) {

       switch (view.getId()) {
            case R.id.buttonLove:
                Toast.makeText(this, "Add Love feeling", Toast.LENGTH_SHORT).show();
                String loveFeeling = "Love";
                Feeling love = new Feeling(loveFeeling);
                feelingList.add(love);
                saveToFile();
                break;
            case R.id.buttonJoy:
                Toast.makeText(this, "Add Joy feeling", Toast.LENGTH_SHORT).show();
                String joyFeeling = "Joy";
                Feeling joy = new Feeling(joyFeeling);
                feelingList.add(joy);
                saveToFile();
                break;
            case R.id.buttonFear:
                Toast.makeText(this, "Add Fear feeling", Toast.LENGTH_SHORT).show();
                String fearFeeling = "Fear";
                Feeling fear = new Feeling(fearFeeling);
                feelingList.add(fear);
                saveToFile();
                break;
            case R.id.buttonAnger:
                Toast.makeText(this, "Add Anger feeling", Toast.LENGTH_SHORT).show();
                String angerFeeling = "Anger";
                Feeling anger = new Feeling(angerFeeling);
                feelingList.add(anger);
                saveToFile();
                break;
            case R.id.buttonSurprise:
                Toast.makeText(this, "Add Surprise feeling", Toast.LENGTH_SHORT).show();
                String surpriseFeeling = "Surprise";
                Feeling surprise = new Feeling(surpriseFeeling);
                feelingList.add(surprise);
                saveToFile();
                break;
            case R.id.buttonSadness:
                Toast.makeText(this, "Add Sadness feeling", Toast.LENGTH_SHORT).show();
                String sadnessFeeling = "Sadness";
                Feeling sadness = new Feeling(sadnessFeeling);
                feelingList.add(sadness);
                saveToFile();
                break;
        }
        feelsAdapter.notifyDataSetChanged();
    }

//    public void modifyFeeling(View view) {
//        Toast.makeText(this, "Modify Feeling", Toast.LENGTH_SHORT).show();
//        Intent additionalIntent = new Intent(MainActivity.this, ModifyFeeling.class);
//        startActivity(additionalIntent);
//    }
//
//    public void viewCount(View view) {
//        Toast.makeText(this, "View Feeling Count", Toast.LENGTH_SHORT).show();
//        Intent countIntent = new Intent(MainActivity.this, CountFeelings.class);
//        startActivity(countIntent);
//    }

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
