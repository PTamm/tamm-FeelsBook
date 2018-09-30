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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.feelsList);
        final Collection<Feeling> feels = FeelsListController.getFeelingList().getFeelings(); //gets list of feelings
        final ArrayList<Feeling> feelingList = new ArrayList<Feeling>(feels);
        final ArrayList<String> stringFeelingList = new ArrayList<String>();
        for (Feeling feel : feels){
            if (feel.getComment() == null) {
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
                for (Feeling feel : feels){
                    if (feel.getComment() == null) {
                        stringFeelingList.add(feel.getFeel()+"\n"+sdf.format(feel.getDate()));
                    } else {
                        stringFeelingList.add(feel.getFeel()+"\n"+feel.getComment()+"\n"+sdf.format(feel.getDate()));
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
                Toast.makeText(MainActivity.this, "Feeling: "+feelingList.get(position).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.feelsList);
        final Collection<Feeling> feels = FeelsListController.getFeelingList().getFeelings(); //gets list of feelings
        final ArrayList<Feeling> feelingList = new ArrayList<Feeling>(feels);
        final ArrayList<String> stringFeelingList = new ArrayList<String>();
        for (Feeling feel : feels){
            if (feel.getComment() == null) {
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
                for (Feeling feel : feels){
                    if (feel.getComment() == null) {
                        stringFeelingList.add(feel.getFeel()+"\n"+sdf.format(feel.getDate()));
                    } else {
                        stringFeelingList.add(feel.getFeel()+"\n"+feel.getComment()+"\n"+sdf.format(feel.getDate()));
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
                Toast.makeText(MainActivity.this, "Feeling: "+feelingList.get(position).toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    /*  Idea to use one function to handle all button clicks came from android developer guide.
    Site: https://developer.android.com/guide/topics/ui/controls/radiobutton#java
    Date: 2018-09-22
 */
    public void buttonAddClicked(View view){

        FeelsListController flc = new FeelsListController();

        switch(view.getId()) {
            case R.id.buttonLove:
                Toast.makeText(this, "Add Love feeling", Toast.LENGTH_SHORT).show();
                String loveFeeling = "Love";
                Feeling love = new Feeling(loveFeeling);
                flc.addFeeling(love);
                break;
            case R.id.buttonJoy:
                Toast.makeText(this, "Add Joy feeling", Toast.LENGTH_SHORT).show();
                String joyFeeling = "Joy";
                Feeling joy = new Feeling(joyFeeling);
                flc.addFeeling(joy);
                break;
            case R.id.buttonFear:
                Toast.makeText(this, "Add Fear feeling", Toast.LENGTH_SHORT).show();
                String fearFeeling = "Fear";
                Feeling fear = new Feeling(fearFeeling);
                flc.addFeeling(fear);
                break;
            case R.id.buttonAnger:
                Toast.makeText(this, "Add Anger feeling", Toast.LENGTH_SHORT).show();
                String angerFeeling = "Anger";
                Feeling anger = new Feeling(angerFeeling);
                flc.addFeeling(anger);
                break;
            case R.id.buttonSurprise:
                Toast.makeText(this, "Add Surprise feeling", Toast.LENGTH_SHORT).show();
                String surpriseFeeling = "Surprise";
                Feeling surprise = new Feeling(surpriseFeeling);
                flc.addFeeling(surprise);
                break;
            case R.id.buttonSadness:
                Toast.makeText(this, "Add Sadness feeling", Toast.LENGTH_SHORT).show();
                String sadnessFeeling = "Sadness";
                Feeling sadness = new Feeling(sadnessFeeling);
                flc.addFeeling(sadness);
                break;
        }
    }



/*    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.feelsList);
        Collection<Feeling> feels = FeelsListController.getFeelingList().getFeelings(); //gets list of feelings
        final ArrayList<Feeling> feelingList = new ArrayList<Feeling>(feels);
        ArrayAdapter<Feeling> feelsAdapter = new ArrayAdapter<Feeling>(this, android.R.layout.simple_list_item_1, feelingList);
        listView.setAdapter(feelsAdapter);
    }
*/

    public void additionalOptions(View view) {
        Toast.makeText(this, "Additional Options", Toast.LENGTH_SHORT).show();
        Intent additionalIntent = new Intent(MainActivity.this, AdditionalOptions.class);
        startActivity(additionalIntent);
    }
}
