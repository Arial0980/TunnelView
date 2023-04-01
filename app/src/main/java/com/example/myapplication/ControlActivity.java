package com.example.myapplication;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ControlActivity extends AppCompatActivity {
    private boolean straight=false,left=false,right=false,back=false,servo=false;
    private DatabaseReference myRef,ipcam, myRefDis1,myRefDis2,myRefDis3;
    private TextView rightView,leftView,straightView;
    private int dis1,dis2,dis3;
    private FirebaseDatabase database;
    private WebView cam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        getSupportActionBar().hide();

        rightView=findViewById(R.id.Right_textView);
        leftView=findViewById(R.id.Left_textView);
        straightView=findViewById(R.id.Straight_textView);

        database=FirebaseDatabase.getInstance();//פקודה זו מחזירה דגימה של אובייקט מתוך מסד הנתונים של Firebase
        myRef=database.getReference("to_altera");//פקודה זו מקבלת הפניה לאובייקט ספציפי מתוך מסד הנתונים האינטרנטי שלנו(Firebase)

        ipcam= database.getReference("ipcam");
        cam=findViewById(R.id.cam_ip);
        cam.getSettings().setJavaScriptEnabled(true);
        cam.getSettings().setBuiltInZoomControls(true);
        cam.getSettings().setDisplayZoomControls(false);
        ipcam.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ip = dataSnapshot.getValue(String.class);
                if (ip != null)
                    cam.loadUrl("http://" + ip.toString() + ":81/stream");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        myRefDis1 = database.getReference("test/dis1");
        myRefDis1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dis1 = dataSnapshot.getValue(Integer.class);
                straightView.setText("Straight:"+Integer.toString(dis1));
                Log.d(TAG, "Value is: " + dis1);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        myRefDis2 = database.getReference("test/dis2");
        myRefDis2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dis2 = dataSnapshot.getValue(Integer.class);
                leftView.setText("Left:"+Integer.toString(dis2));
                Log.d(TAG, "Value is: " + dis2);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        myRefDis3 = database.getReference("test/dis3");
        myRefDis3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dis3 = dataSnapshot.getValue(Integer.class);
                rightView.setText("Right:"+Integer.toString(dis3));
                Log.d(TAG, "Value is: " + dis3);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

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
        // נסיעה הוא ישלח ערך אחר לFirebase וגם אם אנחנו רוצים שהוא יפעל שהרכב לא זז
    }
    public void exit(View view) {
        myRef.setValue(0);
        Intent intent = new Intent(ControlActivity.this, MainActivity.class);
        startActivity(intent);
        //הפונקציה הזאת גורם למעבר מהדף הנוכחי לדף הראשי
    }
    public void reset(View view){
        straight=false;
        left=false;
        back=false;
        right=false;
        servo=false;
        myRef.setValue(0);
    }
    @Override
    protected void onStop() {
        cam.destroy();
        super.onStop();
    }
}