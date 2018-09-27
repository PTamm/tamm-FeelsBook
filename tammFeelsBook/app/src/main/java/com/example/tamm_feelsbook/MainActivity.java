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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.feelsList);
        Collection<Feeling> feels = FeelsListController.getFeelingList().getFeelings(); //gets list of feelings
        ArrayList<Feeling> feelingList = new ArrayList<Feeling>(feels);
        ArrayAdapter<Feeling> feelsAdapter = new ArrayAdapter<Feeling>(this, android.R.layout.simple_list_item_1, feelingList);
        listView.setAdapter(feelsAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.feelsList);
        Collection<Feeling> feels = FeelsListController.getFeelingList().getFeelings(); //gets list of feelings
        ArrayList<Feeling> feelingList = new ArrayList<Feeling>(feels);
        ArrayAdapter<Feeling> feelsAdapter = new ArrayAdapter<Feeling>(this, android.R.layout.simple_list_item_1, feelingList);
        listView.setAdapter(feelsAdapter);
    }



    public void addFeeling(View view){
        Toast.makeText(this, "Add a Feeling", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, AddFeeling.class);
        startActivity(intent);
    }

    public void deleteFeeling(View view){
        Toast.makeText(this, "Delete a Feeling", Toast.LENGTH_SHORT).show();
        Intent delIntent = new Intent(MainActivity.this, DeleteFeeling.class);
        startActivity(delIntent);
    }

    public void viewCount(View view) {
        Toast.makeText(this, "View Feeling Count", Toast.LENGTH_SHORT).show();
        Intent countIntent = new Intent(MainActivity.this, CountFeelings.class);
        startActivity(countIntent);
    }
}
