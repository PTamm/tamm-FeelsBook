package com.example.tamm_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addFeeling(View view){
        Toast.makeText(this, "Add a Feeling", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, add_feeling.class);
        startActivity(intent);
    }
}
