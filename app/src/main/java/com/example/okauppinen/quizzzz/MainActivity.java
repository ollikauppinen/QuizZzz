package com.example.okauppinen.quizzzz;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import static android.R.id.button1;
import static android.R.id.button2;
import static android.R.id.button3;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper flipper;
    private int round = 0;
    private Button button1,button2, button3;
    private TextView question;
    private int pressed, score;
    private String getQuestion;
    private ImageView bgq;
    private CheckBox cbReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.but1);
        button2 = (Button) findViewById(R.id.but2);
        button3 = (Button) findViewById(R.id.but3);
        question = (TextView) findViewById(R.id.question_field);
        flipper = (ViewFlipper) findViewById(R.id.flip_view);
        bgq = (ImageView) findViewById(R.id.background_q);
        score = 0;
    }

    public void StartQuiz(View view){
        //First time animation for flipper
        flipper.setInAnimation(this, R.anim.fadein);
        flipper.setOutAnimation(this, R.anim.fadeout);

        UpdateQuestion();

        flipper.showNext();
    }

    public void Answer(View view){

        //Get buttonID to check if answer was correct
        switch(view.getId()) {
            case R.id.but1:
                // handle button 1 click
                pressed = 1;
                break;

            case R.id.but2:
                // handle button 2 click
                pressed = 2;
                break;

            case R.id.but3:
                // handle button 3 click
                pressed = 3;
                break;

            default:
                throw new RuntimeException("Unknown button ID");
        }
        Log.d("logi", "value "+ pressed + "round " + round);
        // Switch for rounds, new round new questions
        switch(round){

            case 0:

                if(pressed == 3){
                    CorrectAnswer();
                }
                else {
                    WrongAnswer();
                }

                break;

            case 1:

                if(pressed == 2){
                    CorrectAnswer();
                }
                else {
                    WrongAnswer();
                }

                break;

            case 2:

                if(pressed == 2){
                    CorrectAnswer();
                }
                else {
                    WrongAnswer();
                }
                break;



            default:
                throw new RuntimeException("Unknown button ID");
        }
    }

    private void CorrectAnswer(){
        score++;
        Toast.makeText(MainActivity.this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
        round++;
        UpdateQuestion();
        pressed = 0;
    }

    private void WrongAnswer(){
        Toast.makeText(MainActivity.this, getString(R.string.wrong), Toast.LENGTH_SHORT).show();
        round++;
        UpdateQuestion();
        pressed = 0;
    }

    private void UpdateQuestion(){
        switch(round) {

            case 0:

                question.setText(getResources().getString(R.string.q1));
                //Set first group of answers
                button1.setText("Apocalyptica");
                button2.setText("Amorphis");
                button3.setText("Sentenced");

                break;

            case 1:

                bgq.setImageResource(R.drawable.anselmo);
                question.setText(getResources().getString(R.string.q2));
                //Set first group of answers
                button1.setText("Ozzy Osbourne");
                button2.setText("Aleister Crowley");
                button3.setText("Christopher Lee");

                break;

            case 2:

                bgq.setImageResource(R.drawable.duplantier);
                question.setText(getResources().getString(R.string.q3));
                //Set first group of answers
                button1.setText("Mario Duplantier");
                button2.setText("Joseph Duplantier");
                button3.setText("Peter Duplantier");
                break;

            case 3:

                flipper.showNext();
                break;
        }
    }

    public void EndThis(View view){

        for (int i = 1; i < 7; i++){
            String idCheck = "check" + i;
            int resId = getResources().getIdentifier(idCheck, "id", getPackageName());
            cbReader = (CheckBox) findViewById(resId);
            if (i == 1 && cbReader.isChecked()){
                score++;
            }

            else if(i == 3 && cbReader.isChecked()){
                score++;
            }

            else if(i == 4 && cbReader.isChecked()){
                score++;
            }
        }//1, 3 ,4

        FinalScore();
        flipper.showNext();

    }

    private void FinalScore(){
        TextView scoreKeeper = (TextView) findViewById(R.id.result);
        TextView judge = (TextView) findViewById(R.id.judgement);
        int maxScore = 6;
        String playerScore = score + " / " + maxScore;
        scoreKeeper.setText(playerScore);

        if(score == 0){
            judge.setText(getResources().getString(R.string.zero));
        }
        else if (score < 4){
            judge.setText(getResources().getString(R.string.low));
        }
        else if (score < 6){
            judge.setText(getResources().getString(R.string.high));
        }
        else if (score == 6){
            judge.setText(getResources().getString(R.string.perfect));
        }

    }
}
