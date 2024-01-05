package com.example.colorgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.Path;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView playbtn;
    private Button arcadebtn, timed10btn, customtimerbtn;
    public static boolean timer10 = false, customtimer = false;
    public static EditText txt; // user input bar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialize();

        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playbtn.setVisibility(View.INVISIBLE);
                arcadebtn.setVisibility(View.VISIBLE);
                timed10btn.setVisibility(View.VISIBLE);
                customtimerbtn.setVisibility(View.VISIBLE);
            }
        });
        arcadebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainGame.class);
                startActivity(intent);
            }
        });
        timed10btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainGame.class);
                startActivity(intent);
                timer10 = true;
                customtimer=false;
            }
        });
        customtimerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertName = new AlertDialog.Builder(MainActivity.this);
                final EditText editTextName1 = new EditText(MainActivity.this);
                alertName.setTitle("Set A Custom Timer");
                // titles can be used regardless of a custom layout or not
                alertName.setView(editTextName1);
                LinearLayout layoutName = new LinearLayout(MainActivity.this);
                layoutName.setOrientation(LinearLayout.VERTICAL);
                layoutName.addView(editTextName1); // displays the user input bar
                alertName.setView(layoutName);
                alertName.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        
                        txt = editTextName1; // variable to collect user input
                        customtimer=true;
                        timer10=false;
                        try {
                            Integer.parseInt(MainActivity.txt.getText().toString());
                        }
                        catch (Exception e){
                            Toast.makeText(MainActivity.this, "Enter a valid timer", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent = new Intent(MainActivity.this, MainGame.class);
                        startActivity(intent);
                    }
                });

                alertName.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel(); // closes dialog
                    }
                });

                AlertDialog test = alertName.create();
                test.setCancelable(false);
                test.show();
            }


        });


    }

    private void Initialize(){

        playbtn = findViewById(R.id.startbtn);
        arcadebtn = findViewById(R.id.arcadebtn);
        customtimerbtn = findViewById(R.id.customtimerbtn);
        timed10btn = findViewById(R.id.timedbtn);




    }




}