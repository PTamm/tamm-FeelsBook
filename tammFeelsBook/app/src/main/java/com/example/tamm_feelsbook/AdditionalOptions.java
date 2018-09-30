package com.example.tamm_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AdditionalOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_options);
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

    public void delFeel(View view) {
        Toast.makeText(this, "Delete Feeling: Press and hold", Toast.LENGTH_LONG).show();
        Intent deleteIntent = new Intent(AdditionalOptions.this, DeleteFeeling.class);
        startActivity(deleteIntent);
    }

    public void viewCount(View view) {
        Toast.makeText(this, "View Feeling Count", Toast.LENGTH_SHORT).show();
        Intent countIntent = new Intent(AdditionalOptions.this, CountFeelings.class);
        startActivity(countIntent);
    }

    public void modifyFeeling(View view) {
        Toast.makeText(this, "Modify Feeling", Toast.LENGTH_SHORT).show();
        Intent modifyIntent = new Intent(AdditionalOptions.this, ModifyFeeling.class);
        startActivity(modifyIntent);
    }
}
