package com.example.tamm_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

public class CountFeelings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int loveCount = 0;
        int joyCount = 0;
        int surpriseCount = 0;
        int angerCount = 0;
        int sadnessCount = 0;
        int fearCount = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_feelings);
        Collection<Feeling> feels = FeelsListController.getFeelingList().getFeelings(); //gets list of feelings
        ArrayList<Feeling> feelingList = new ArrayList<Feeling>(feels);

        /* string comparison with equals(): https://stackoverflow.com/questions/513832/how-do-i-compare-strings-in-java/513839#513839
        * Users: https://stackoverflow.com/users/2603/aaron-maenpaa (original poster), and others
        *       https://stackoverflow.com/posts/513839/revisions (community wiki)
        * Date: 2018-09-27
         */
        for (Feeling feel : feelingList){
            if (feel.getFeel().equals("Love")){
                loveCount = loveCount + 1;
            } else if (feel.getFeel().equals("Joy")){
                joyCount = joyCount + 1;
            } else if (feel.getFeel().equals("Surprise")){
                surpriseCount = surpriseCount + 1;
            } else if (feel.getFeel().equals("Anger")){
                angerCount = angerCount + 1;
            } else if (feel.getFeel().equals("Sadness")){
                sadnessCount = sadnessCount + 1;
            } else if (feel.getFeel().equals("Fear")) {
                fearCount = fearCount + 1;
            }
        }

        // TODO: Consider try block later ...

        TextView loveView = (TextView) findViewById(R.id.dispLoveCount);
        loveView.setText("Love Count: "+loveCount);

        TextView joyView = (TextView) findViewById(R.id.dispJoyCount);
        joyView.setText("Joy Count: "+joyCount);

        TextView surpriseView = (TextView) findViewById(R.id.dispSurpriseCount);
        surpriseView.setText("Surprise Count: "+surpriseCount);

        TextView angerView = (TextView) findViewById(R.id.dispAngerCount);
        angerView.setText("Anger Count: "+angerCount);

        TextView sadnessView = (TextView) findViewById(R.id.dispSadnessCount);
        sadnessView.setText("Sadness Count: "+sadnessCount);

        TextView fearView = (TextView) findViewById(R.id.dispFearCount);
        fearView.setText("Fear Count: "+fearCount);
    }

    @Override
    protected void onRestart(){
        int loveCount = 0;
        int joyCount = 0;
        int surpriseCount = 0;
        int angerCount = 0;
        int sadnessCount = 0;
        int fearCount = 0;

        super.onRestart();
        setContentView(R.layout.activity_count_feelings);

        Collection<Feeling> feels = FeelsListController.getFeelingList().getFeelings(); //gets list of feelings
        ArrayList<Feeling> feelingList = new ArrayList<Feeling>(feels);

        /* string comparison with equals(): https://stackoverflow.com/questions/513832/how-do-i-compare-strings-in-java/513839#513839
         * Users: https://stackoverflow.com/users/2603/aaron-maenpaa (original poster), and others
         *       https://stackoverflow.com/posts/513839/revisions (community wiki)
         * Date: 2018-09-27
         */
        for (Feeling feel : feelingList){
            if (feel.getFeel().equals("Love")){
                loveCount = loveCount + 1;
            } else if (feel.getFeel().equals("Joy")){
                joyCount = joyCount + 1;
            } else if (feel.getFeel().equals("Surprise")){
                surpriseCount = surpriseCount + 1;
            } else if (feel.getFeel().equals("Anger")){
                angerCount = angerCount + 1;
            } else if (feel.getFeel().equals("Sadness")){
                sadnessCount = sadnessCount + 1;
            } else if (feel.getFeel().equals("Fear")) {
                fearCount = fearCount + 1;
            }
        }

        // TODO: Consider try block later ...

        /* How to set TextView: Abram Hindle, https://www.youtube.com/watch?v=uLnoI7mbuEo&list=PL240uJOh_Vb4PtMZ0f7N8ACYkCLv0673O&index=5, 2018-09-27*/

        TextView loveView = (TextView) findViewById(R.id.dispLoveCount);
        loveView.setText("Love Count: "+loveCount);

        TextView joyView = (TextView) findViewById(R.id.dispJoyCount);
        joyView.setText("Joy Count: "+joyCount);

        TextView surpriseView = (TextView) findViewById(R.id.dispSurpriseCount);
        surpriseView.setText("Surprise Count: "+surpriseCount);

        TextView angerView = (TextView) findViewById(R.id.dispAngerCount);
        angerView.setText("Anger Count: "+angerCount);

        TextView sadnessView = (TextView) findViewById(R.id.dispSadnessCount);
        sadnessView.setText("Sadness Count: "+sadnessCount);

        TextView fearView = (TextView) findViewById(R.id.dispFearCount);
        fearView.setText("Fear Count: "+fearCount);
    }
}
