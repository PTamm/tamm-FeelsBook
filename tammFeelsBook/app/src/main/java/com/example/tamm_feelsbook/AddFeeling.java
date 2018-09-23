package com.example.tamm_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddFeeling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feeling);
    }

    /*  Idea to use one function to handle all button clicks came from android developer guide.
        Site: https://developer.android.com/guide/topics/ui/controls/radiobutton#java
        Date: 2018-09-22
     */
    public void buttonFeelClicked(View view){

        switch(view.getId()) {
            case R.id.buttonLove:
                Toast.makeText(this, "Add Love feeling", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonJoy:
                Toast.makeText(this, "Add Joy feeling", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonFear:
                Toast.makeText(this, "Add Fear feeling", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonAnger:
                Toast.makeText(this, "Add Anger feeling", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonSurprise:
                Toast.makeText(this, "Add Surprise feeling", Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonSadness:
                Toast.makeText(this, "Add Sadness feeling", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
