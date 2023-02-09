package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ControlActivity extends AppCompatActivity {
    private boolean straight=false,left=false,right=false,back=false,servo=false;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        getSupportActionBar().hide();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
         myRef=database.getReference("to_altera");
    }
    public void moveStraight(View view){
    right=false;
    left=false;
    back=false;
        if (straight) {
            if(servo){
                myRef.setValue(16);
                straight=false;}
            else
            {
                myRef.setValue(0);
                straight=false;
            }
        }
        else{
            if(servo){
                myRef.setValue(21);
                straight=true;}
            else
            {
                myRef.setValue(5);
                straight=true;
            }
            }

    }
    public void moveRight(View view){
        straight=false;
        left=false;
        back=false;
        if (right) {
            if(servo)
                myRef.setValue(16);
            else
                myRef.setValue(0);
            right=false;
        }
        else{
            if(servo)
                myRef.setValue(22);
            else
                myRef.setValue(6);
            right=true;
        }

    }
    public void moveLeft(View view){
        right=false;
        straight=false;
        back=false;
        if (left) {
            if(servo)
                myRef.setValue(16);
            else
                myRef.setValue(0);
            left=false;
        }
        else{
            if(servo)
                myRef.setValue(25);
            else
                myRef.setValue(9);
            left=true;
        }
    }
    public void moveBack(View view){
        right=false;
        left=false;
        straight=false;
        if (back) {
            if(servo)
                myRef.setValue(16);
            else
                myRef.setValue(0);
            back=false;
        }
        else{
            if(servo)
                myRef.setValue(26);
            else
                myRef.setValue(10);

            back=true;
        }
    }
    public void dropESP(View view){
        if (servo) {
            if (straight && !right && !left && !back)
                myRef.setValue(5);
            else if(!straight && right && !left && !back)
                myRef.setValue(6);
            else if(!straight && !right && left && !back)
                myRef.setValue(9);
            else if(!straight && !right && !left && back)
                myRef.setValue(10);
            else
                myRef.setValue(0);
            servo = false;
        }
        else {
            if (straight && !right && !left && !back)
                myRef.setValue(21);
            else if (!straight && right && !left && !back)
                myRef.setValue(22);
            else if (!straight && !right && left && !back)
                myRef.setValue(25);
            else if (!straight && !right && !left && back)
                myRef.setValue(26);
            else if(!straight && !right && !left && !back)
                myRef.setValue(16);
            servo=true;
        }

        }
    public void exit(View view) {
        Intent intent = new Intent(ControlActivity.this, MainActivity.class);
        startActivity(intent);
    }

}