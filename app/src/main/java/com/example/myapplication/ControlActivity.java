package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ControlActivity extends AppCompatActivity {
    private boolean straight=false,left=false,right=false,back=false,servo=false;//הגדרת משתנים בוליאנים אשר יעזרו לנו בבדיקה של כל פעולה
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        getSupportActionBar().hide();//שורת הקוד הזאת גורמת להסתרה של הבר בראש הדף

        database=FirebaseDatabase.getInstance();//פקודה זו מחזירה דגימה של אובייקט מתוך מסד הנתונים של Firebase
        myRef=database.getReference("to_altera");//פקודה זו מקבלת הפניה לאובייקט ספציפי מתוך מסד הנתונים האינטרנטי שלנו(Firebase)
    }
    public void moveStraight(View view){
    right=false;
    left=false;
    back=false;
    //הגדרת כל הכיוונים שהם לא ישר כfalse
        if (straight) {
            if(servo){
                myRef.setValue(16);
                straight=false;
            }
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
        //קטע קוד זה גורם לאפשור של נסיעה קדימה והפעלת המנוע סרוו במקביל בכך
        //שאם הרכב רק נוסע ישר אז הוא שולח את המספר 5 לFirebase אבל אם גם מפעילים את
        //המנוע סרוו תוך כדי אז הוא שולח לFirebase את המספר 21
    }
    public void moveRight(View view){
        straight=false;
        left=false;
        back=false;
        //הגדרת כל הכיוונים שהם לא ימינה כfalse
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
        //קטע קוד זה גורם לאפשור של פנייה ימינה והפעלת המנוע סרוו במקביל בכך
        //שאם הרכב רק פונה ימינה אז הוא שולח את המספר 6 לFirebase אבל אם גם מפעילים את
        //המנוע סרוו תוך כדי אז הוא שולח לFirebase את המספר 22
    }
    public void moveLeft(View view){
        right=false;
        straight=false;
        back=false;
        //הגדרת כל הכיוונים שהם לא שמאלה כfalse
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
        //קטע קוד זה גורם לאפשור של פנייה שמאלה והפעלת המנוע סרוו במקביל בכך
        //שאם הרכב רק פונה שמאלה אז הוא שולח את המספר 9 לFirebase אבל אם גם מפעילים את
        //המנוע סרוו תוך כדי אז הוא שולח לFirebase את המספר 25
    }
    public void moveBack(View view){
        right=false;
        left=false;
        straight=false;
        //הגדרת כל הכיוונים שהם לא אחורה כfalse
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
        //קטע קוד זה גורם לאפשור של נסיעה אחורה והפעלת המנוע סרוו במקביל בכך
        //שאם הרכב רק נוסע אחורה אז הוא שולח את המספר 9 לFirebase אבל אם גם מפעילים את
        //המנוע סרוו תוך כדי אז הוא שולח לFirebase את המספר 25
    }
    public void dropESP(View view)
    {
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
        // קוד זה מאפשר להפעיל את המנוע סרוו בכל אחד מהמצבים שיש לנו בכל כיוון
        // נסיעה הוא ישלח ערך אחר לFirebase וגם אם אנומו רוצים שהוא יפעל שהרכב לא זז
    }
    public void exit(View view) {
        Intent intent = new Intent(ControlActivity.this, MainActivity.class);
        startActivity(intent);
        //הפונקציה הזאת גורם למעבר מהדף הנוכחי לדף הראשי
    }

}