package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;


import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DataActivity extends AppCompatActivity {
    private DatabaseReference myRefDis1,myRefDis2,myRefDis3,myRefDidW,myRefAutoMapping,myRefPos,myRefDoNow;
    private int dis1,dis2,dis3,didW,d2=0,d3=0,autoMapping,position=0,z=0;
    private Bitmap bm;
    private final int One_SECONDS = 1000;
    private int x=685,y=1370;
    private boolean alreadyAutoControlled=false,change=false;
    private ImageView map;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        getSupportActionBar().hide();
        map=findViewById(R.id.map);
        TextView right=findViewById(R.id.Right_textView);
        TextView left=findViewById(R.id.Left_textView);
        TextView straight=findViewById(R.id.Straight_textView);

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        myRefPos=database.getReference("test/Position");
        myRefDoNow=database.getReference("test/doNow");
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

        myRefAutoMapping = database.getReference("to_altera");
        myRefAutoMapping.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                autoMapping = dataSnapshot.getValue(Integer.class);
                Log.d(TAG, "Value is: " + autoMapping);
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
                interrupt();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_1);
        bm= originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
    }
    public void interrupt() {
            handler.postDelayed(new Runnable() {
                public void run() {
                    if(didW==1 || didW==2) {
                        z++;
                        change = false;
                    }
                    else{
                        change=true;
                        z=0;
                    }
                    myRefDoNow.setValue(change);
                    if (change || z==1)
                        if(autoMapping==32){
                            drowMap(didW);
                            myRefDoNow.setValue(change);
                        }
                    handler.postDelayed(this, One_SECONDS);
                }
            }, One_SECONDS);
    }
    public void drowMap(int didWhat)
    {
        if(dis2<10&&dis2>=0)
            d2=0;
        else if(dis2<20&&dis2>=10)
            d2=5;
        else
            d2=10;

        if(dis3<10 && dis3>=0)
            d3=0;
        else if(dis3<20&&dis3>=10)
            d3=5;
        else
            d3=10;

            if (position > 3)
                position = 0;
            if (position < 0)
                position = 3;
            switch (position) {
                case 0: {
                    if (didWhat == 0) {
                        drowPX(x - 20-d2, y, Color.BLACK);
                        drowPX(x + 20+d3, y, Color.BLACK);
                        drowPX(x, y, Color.RED);
                        y -= 5;
                    }
                    if (didWhat == 1) {
                        drowPX(x, y, Color.RED);
                        for (int i = 0; i <= 20; i++)
                            drowPX(x + 20, y + i, Color.WHITE);
                        for (int i = 0; i <= 20; i++)
                            drowPX(x - 20, y - i, Color.BLACK);
                        for (int i = -20; i <= 20; i++)
                            drowPX(x - i, y - 20, Color.BLACK);
                        for (int i = 0; i <= 20; i++)
                            drowPX(x + i, y, Color.RED);
                        position++;
                        //myRefDidW.setValue(0);
                    }
                    if (didWhat == 2) {
                        drowPX(x, y, Color.RED);
                        for (int i = 0; i <= 20; i++)
                            drowPX(x - 20, y + i, Color.WHITE);
                        for (int i = 0; i <= 20; i++)
                            drowPX(x + 20, y - i, Color.BLACK);
                        for (int i = -20; i <= 20; i++)
                            drowPX(x + i, y - 20, Color.BLACK);
                        for (int i = 0; i <= 20; i++)
                            drowPX(x - i, y, Color.RED);
                        position--;
                        //myRefDidW.setValue(0);
                    }
                }
                break;
                case 1: {
                    if (didWhat == 0) {
                        drowPX(x + 20, y - 20-d3, Color.BLACK);
                        drowPX(x + 20, y + 20+d2, Color.BLACK);
                        for (int i = 0; i <= 20; i++)
                            drowPX(x + i, y, Color.RED);
                        x += 5;
                    }
                    if (didWhat == 1) {
                        for (int i = 0; i <= 40; i++)
                            drowPX(x + i, y - 20, Color.BLACK);
                        for (int i = -15; i <= -5; i++)
                            drowPX(x - i, y + 20, Color.WHITE);
                        for (int i = -20; i <= 20; i++)
                            drowPX(x + 40, y + i, Color.BLACK);
                        for (int i = 0; i <= 20; i++)
                            drowPX(x + 20, y + i, Color.RED);

                        y += 20;
                        x += 20;
                        position++;
                        //myRefDidW.setValue(0);
                    }
                    if (didWhat == 2) {
                        for (int i = 0; i <= 40; i++)
                            drowPX(x + i, y + 20, Color.BLACK);
                        for (int i = -15; i <= -5; i++)
                            drowPX(x - i, y - 20, Color.WHITE);
                        for (int i = -20; i <= 20; i++)
                            drowPX(x + 40, y + i, Color.BLACK);
                        for (int i = 0; i <= 20; i++)
                            drowPX(x + 20, y - i, Color.RED);
                        y -= 20;
                        x += 20;
                        position--;
                        //myRefDidW.setValue(0);
                    }
                }
                break;
                case 2: {
                    if (didWhat == 0) {
                        drowPX(x - 20-d2, y, Color.BLACK);
                        drowPX(x + 20+d3, y, Color.BLACK);
                        drowPX(x, y, Color.RED);
                        y += 5;
                    }
                    if (didWhat == 1) {
                        for (int i = 0; i <= 20; i++)
                            drowPX(x + 20, y + i, Color.BLACK);
                        for (int i = -20; i <= 20; i++)
                            drowPX(x - i, y + 20, Color.BLACK);
                        for (int i = 0; i < 20; i++)
                            drowPX(x - 20, y - i, Color.WHITE);
                        for (int i = 0; i <= 20; i++)
                            drowPX(x - i, y, Color.RED);
                        position = 3;
                        //myRefDidW.setValue(0);
                    }
                    if (didWhat == 2) {
                        for (int i = 0; i <= 40; i++)
                            drowPX(x - 20, y + i, Color.BLACK);
                        for (int i = -20; i <= 20; i++)
                            drowPX(x - i, y + 40, Color.BLACK);
                        for (int i = 0; i >= -10; i--)
                            drowPX(x, y, Color.WHITE);
                        for (int i = 0; i <= 20; i++)
                            drowPX(x, y + i, Color.RED);
                        y += 20;
                        position--;
                        //myRefDidW.setValue(0);
                    }
                }
                break;
                case 3: {
                    if (didWhat == 0) {
                        drowPX(x - 20, y - 20-d2, Color.BLACK);
                        drowPX(x - 20, y + 20+d3, Color.BLACK);
                        for (int i = 0; i <= 20; i++)
                            drowPX(x - i, y, Color.RED);
                        x -= 5;
                    }
                    if (didWhat == 1) {
                        for (int i = 0; i < 40; i++)
                            drowPX(x - i, y + 20, Color.BLACK);
                        for (int i = 0; i < 20; i++)
                            drowPX(x - i, y - 20, Color.WHITE);
                        for (int i = -20; i <= 20; i++)
                            drowPX(x - 40, y + i, Color.BLACK);
                        for (int i = 0; i <= 20; i++)
                            drowPX(x - 20, y - i, Color.RED);
                        y -= 20;
                        x -= 20;
                        position++;
                        //myRefDidW.setValue(0);
                    }
                    if (didWhat == 2) {
                        for (int i = 0; i < 40; i++)
                            drowPX(x - i, y - 20, Color.BLACK);
                        for (int i = 0; i < 20; i++)
                            drowPX(x - i, y + 20, Color.WHITE);
                        for (int i = -20; i <= 20; i++)
                            drowPX(x - 40, y + i, Color.BLACK);
                        for (int i = 0; i <= 20; i++)
                            drowPX(x - 20, y + i, Color.RED);
                        y += 20;
                        x -= 20;
                        position--;
                        //myRefDidW.setValue(0);
                    }
                }
                break;
            }

        map.setImageBitmap(bm);
        myRefPos.setValue(position);
    }
    public void exit(View view) {
        Intent intent = new Intent(DataActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void drowPX(int x1, int y1, @ColorInt int color){
        for(int j=0;j<4;j++)
        {
            for (int i=0;i<4;i++)
            {
                bm.setPixel(x1 - i, y1 - j, color);
                bm.setPixel(x1 + i, y1 + j, color);
                bm.setPixel(x1 - i, y1 + j, color);
                bm.setPixel(x1 + i, y1 - j, color);
            }
        }
    }
    public void AutomaticMapping(View view){
        if (alreadyAutoControlled) {
            myRefAutoMapping.setValue(0);
            alreadyAutoControlled=false;
        }
        else {
            myRefAutoMapping.setValue(32);
            alreadyAutoControlled=true;
        }
    }
}