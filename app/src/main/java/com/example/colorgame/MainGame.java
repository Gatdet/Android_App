package com.example.colorgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainGame extends AppCompatActivity {

    private Button blue,red,purple,yellow,green,white,grey, black,magenta;
    private ImageView backbtn;
    private TextView colorname, score, scorecount, timer;
    private String[] colorList = {"blue","red","purple","yellow","green","white","grey", "black", "magenta"};
    private Random generator = new Random();
    private int randomIndex = generator.nextInt(colorList.length), count, timersec;

    private CountDownTimer countdowntimer;

    private void PlayMusic(){
        MediaPlayer mediaPlayer;
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.kidssss);

        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
                mediaPlayer.start();
            }
        });



    }

    private void TenSecTimer() {
        Boolean timer10 = MainActivity.timer10;
        timersec = 10;

        if (timer10 == true) {

              countdowntimer =  new CountDownTimer(10000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer.setText(String.valueOf(timersec));
                    timersec--;
                }

                public void onFinish() {
                    btnclickable();
                    timer.setText("FINISH!!");

                }
            }.start();
        }
    }

    private void GetCustomTimer(){
        Boolean customtimer = MainActivity.customtimer;

        if(customtimer) {

            timersec = Integer.parseInt(MainActivity.txt.getText().toString());

        }
        else{
            return;
        }

        if (customtimer == true) {
            countdowntimer =  new CountDownTimer(102000, 1000) {
                public void onTick(long millisUntilFinished) {
                    timer.setText(String.valueOf(timersec));
                    timersec--;
                    if(timersec ==-1){
                        timer.setText("FINISH!!");
                        btnclickable();
                        countdowntimer.cancel();

                    }
                }
                public void onFinish() {
                    timer.setText("FINISH!!");

                }
            }.start();
        }
    };




//24286649
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        Initialize();
        Btnclicked();
        TenSecTimer();
        GetCustomTimer();
        PlayMusic();


        colorname.setTextColor(Color.parseColor(colorList[randomIndex])); //setting text color
        randomIndex = generator.nextInt(colorList.length); //genrerating random color
        colorname.setText(colorList[randomIndex]); // setting text

        String brown = "brown";
        String orange = "orange";

        if(colorname.getText().toString().equals("#964B00")){
            colorname.setText("brown");
        }
        if(colorname.getText().toString().equals("#FFA500")){
            colorname.setText("orange");
        }




        if(colorname.getText().toString()==colorList[randomIndex] || colorname.getText().toString()==brown
            || colorname.getText().toString()==orange){
            randomIndex = generator.nextInt(colorList.length); //genrerating random color
            colorname.setText(colorList[randomIndex]);

            if(colorname.getText().toString().equals("#964B00")){
                colorname.setText("brown");
            }
            if(colorname.getText().toString().equals("#FFA500")){
                colorname.setText("orange");
            }
        }

    }

    private void btnclickable(){
        yellow.setClickable(false);
        green.setClickable(false);
        red.setClickable(false);
        blue.setClickable(false);
        magenta.setClickable(false);
        purple.setClickable(false);
        black.setClickable(false);
        grey.setClickable(false);
        white.setClickable(false);





    }
    private void alertDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this, R.style.AlertDialogStyle);

        dialog.setMessage("OOPS You Got It Wrong");
        dialog.setTitle("Retry");
        dialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        count=0;
                        scorecount.setText(Integer.toString(count));
                        randomIndex = generator.nextInt(colorList.length); //genrerating random color
                        colorname.setTextColor(Color.parseColor(colorList[randomIndex])); //setting text color
                        randomIndex = generator.nextInt(colorList.length); //genrerating random color
                        colorname.setText(colorList[randomIndex]); // setting text
                        timersec = 10;
                        countdowntimer.start();
                    }
                });
        dialog.setNegativeButton("NO",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainGame.this, MainActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }


    private void Btnclicked(){
        if(colorname.getText().toString().equals("#964B00")){
            colorname.setText("brown");
        }
        if(colorname.getText().toString().equals("#FFA500")){
            colorname.setText("orange");
        }

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainGame.this, MainActivity.class);
                startActivity(intent);
                MainActivity.timer10 = false;
                MainActivity.customtimer = false;
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(colorname.getText().toString().equals("blue")){randomIndex = generator.nextInt(colorList.length);
                    colorname.setTextColor(Color.parseColor(colorList[randomIndex])); //setting text color
                    randomIndex = generator.nextInt(colorList.length); //genrerating random color
                    colorname.setText(colorList[randomIndex]); // setting text
                    count++;
                    scorecount.setText(Integer.toString(count));

                }
                else{
                    alertDialog();
                    countdowntimer.cancel();

                }
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(colorname.getText().toString().equals("green")){randomIndex = generator.nextInt(colorList.length);
                    colorname.setTextColor(Color.parseColor(colorList[randomIndex])); //setting text color
                    randomIndex = generator.nextInt(colorList.length); //genrerating random color
                    colorname.setText(colorList[randomIndex]); // setting text
                    count++;
                    scorecount.setText(Integer.toString(count));
                }
                else{
                    alertDialog();
                    countdowntimer.cancel();

                }
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(colorname.getText().toString().equals("yellow")){randomIndex = generator.nextInt(colorList.length);
                    colorname.setTextColor(Color.parseColor(colorList[randomIndex])); //setting text color
                    randomIndex = generator.nextInt(colorList.length); //genrerating random color
                    colorname.setText(colorList[randomIndex]); // setting text
                    count++;
                    scorecount.setText(Integer.toString(count));

                }
                else{
                    alertDialog();
                    countdowntimer.cancel();
                }
            }

        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(colorname.getText().toString().equals("red")){randomIndex = generator.nextInt(colorList.length);
                    colorname.setTextColor(Color.parseColor(colorList[randomIndex])); //setting text color
                    randomIndex = generator.nextInt(colorList.length); //genrerating random color
                    colorname.setText(colorList[randomIndex]); // setting text
                    count++;
                    scorecount.setText(Integer.toString(count));
                }
                else{
                    alertDialog();
                    countdowntimer.cancel();

                }
            }
        });
        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(colorname.getText().toString().equals("purple")){randomIndex = generator.nextInt(colorList.length);
                    colorname.setTextColor(Color.parseColor(colorList[randomIndex])); //setting text color
                    randomIndex = generator.nextInt(colorList.length); //genrerating random color
                    colorname.setText(colorList[randomIndex]); // setting text
                    count++;
                    scorecount.setText(Integer.toString(count));
                }
                else{
                    alertDialog();
                    countdowntimer.cancel();

                }
            }
        });
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(colorname.getText().toString().equals("black")){randomIndex = generator.nextInt(colorList.length);
                    colorname.setTextColor(Color.parseColor(colorList[randomIndex])); //setting text color
                    randomIndex = generator.nextInt(colorList.length); //genrerating random color
                    colorname.setText(colorList[randomIndex]); // setting text
                    count++;
                    scorecount.setText(Integer.toString(count));
                }
                else{
                    alertDialog();
                    countdowntimer.cancel();

                }
            }
        });
        grey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(colorname.getText().toString().equals("grey")){randomIndex = generator.nextInt(colorList.length);
                    colorname.setTextColor(Color.parseColor(colorList[randomIndex])); //setting text color
                    randomIndex = generator.nextInt(colorList.length); //genrerating random color
                    colorname.setText(colorList[randomIndex]); // setting text
                    count++;
                    scorecount.setText(Integer.toString(count));
                }
                else{
                    alertDialog();
                    countdowntimer.cancel();

                }
            }
        });
        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(colorname.getText().toString().equals("white")){randomIndex = generator.nextInt(colorList.length);
                    colorname.setTextColor(Color.parseColor(colorList[randomIndex])); //setting text color
                    randomIndex = generator.nextInt(colorList.length); //genrerating random color
                    colorname.setText(colorList[randomIndex]); // setting text
                    count++;
                    scorecount.setText(Integer.toString(count));
                }
                else{
                    alertDialog();
                    countdowntimer.cancel();

                }
            }
        });
        magenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(colorname.getText().toString().equals("magenta")){randomIndex = generator.nextInt(colorList.length);
                    colorname.setTextColor(Color.parseColor(colorList[randomIndex])); //setting text color
                    randomIndex = generator.nextInt(colorList.length); //genrerating random color
                    colorname.setText(colorList[randomIndex]); // setting text
                    count++;
                    scorecount.setText(Integer.toString(count));
                }
                else{
                    alertDialog();
                    countdowntimer.cancel();

                }
            }
        });

    }

    private void Initialize(){
        backbtn = findViewById(R.id.backbtn);
        blue = findViewById(R.id.blue);
        red = findViewById(R.id.red);
        yellow = findViewById(R.id.yellow);
        green = findViewById(R.id.green);
        magenta = findViewById(R.id.magenta);
        black = findViewById(R.id.black);
        white = findViewById(R.id.white);
        purple = findViewById(R.id.purple);
        grey = findViewById(R.id.grey);
        colorname = findViewById(R.id.colorname);
        score = findViewById(R.id.score);
        scorecount = findViewById(R.id.scorecount);
        timer = findViewById(R.id.timer);

    }
}