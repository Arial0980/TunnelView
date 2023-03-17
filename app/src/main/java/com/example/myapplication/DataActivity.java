package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;


import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class DataActivity extends AppCompatActivity {
    private DatabaseReference myRefDis1,myRefDis2,myRefDis3,myRefDidW;
    private int dis1,dis2,dis3,didW;
    private Bitmap image;
    private final int FIVE_SECONDS = 5000;
    private int WIDTH=100,HEIGHT=100,x=50,y=100;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        getSupportActionBar().hide();




        TextView right=findViewById(R.id.Right_textView);
        TextView left=findViewById(R.id.Left_textView);
        TextView straight=findViewById(R.id.Straight_textView);
        // Read from the database
        FirebaseDatabase database= FirebaseDatabase.getInstance();

        myRefDis1 = database.getReference("test/dis1");
        myRefDis1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dis1 = dataSnapshot.getValue(Integer.class);
                straight.setText("Straight:"+Integer.toString(dis1));
                Log.d(TAG, "Value is: " + dis1);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        myRefDis2 = database.getReference("test/dis2");
        myRefDis2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dis2 = dataSnapshot.getValue(Integer.class);
                left.setText("Left:"+Integer.toString(dis2));
                Log.d(TAG, "Value is: " + dis2);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        myRefDis3 = database.getReference("test/dis3");
        myRefDis3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dis3 = dataSnapshot.getValue(Integer.class);
                right.setText("Right:"+Integer.toString(dis3));
                Log.d(TAG, "Value is: " + dis3);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        myRefDidW = database.getReference("test/didW");
        myRefDidW.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                didW = dataSnapshot.getValue(Integer.class);
                Log.d(TAG, "Value is: " + didW);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        image = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
        scheduleSendLocation();
    }
    public void scheduleSendLocation() {
        handler.postDelayed(new Runnable() {
            public void run() {
                drowMap(didW);
                handler.postDelayed(this, FIVE_SECONDS);
            }
        }, FIVE_SECONDS);
    }
    public void drowMap(int didWhat){
        int z;
        if(didWhat==0){
            image.setPixel(x-5,y, Color.BLACK);
            image.setPixel(x+5,y, Color.BLACK);
            image.setPixel(x,y, Color.YELLOW);
            y++;

        }
        image.setPixel(x,y, Color.YELLOW);
        if(didWhat==1){
            image.setPixel(x,y, Color.YELLOW);
            for(int i=0; i<=3; i++)
                image.setPixel(x+5,y-i, Color.WHITE);
            for(int i=0; i<=3; i++)
                image.setPixel(x-5,y+i, Color.BLACK);
            for(int i=-5; i<=5; i++)
                image.setPixel(x-i,y+3, Color.BLACK);
            z=x;
            x=y;
            y=z;
        }
        if(didWhat==2){
            image.setPixel(x,y, Color.YELLOW);
            for(int i=0; i<=3; i++)
                image.setPixel(x-5,y-i, Color.WHITE);
            for(int i=0; i<=3; i++)
                image.setPixel(x+5,y+i, Color.BLACK);
            for(int i=-5; i<=5; i++)
                image.setPixel(x-i,y+3, Color.BLACK);
            z=x;
            x=y;
            y=z;
        }
    }
    public void exit(View view) {
        Intent intent = new Intent(DataActivity.this, MainActivity.class);
        startActivity(intent);
    }
}