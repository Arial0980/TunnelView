package com.example.myapplication;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
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
    private DatabaseReference myRefDis1,myRefDis2,myRefDis3,myRefMapping,myRefWhat_To_Do;
    private int dis1,dis2,dis3,i_Current=0,x=5,y=11,i_FB,what_To_Do;
    private String[][] matrix = new String[11][11];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        getSupportActionBar().hide();

        for (int i=0;i<11;i++)
        {
            for (int j=0;j<11;j++)
            {
                matrix[i][j]="   ";
            }
        }
        TextView right=findViewById(R.id.Right_textView);
        TextView left=findViewById(R.id.Left_textView);
        TextView straight=findViewById(R.id.Straight_textView);
        // Read from the database
        FirebaseDatabase database= FirebaseDatabase.getInstance();


        myRefMapping = database.getReference("i_FB");
        myRefMapping.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                i_FB = dataSnapshot.getValue(Integer.class);
                //i_view.setText("Straight:"+Integer.toString(i_FB));
                Log.d(TAG, "Value is: " + i_FB);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        myRefWhat_To_Do = database.getReference("What_To_Do");
        myRefWhat_To_Do.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                what_To_Do = dataSnapshot.getValue(Integer.class);
                //i_view.setText("Straight:"+Integer.toString(i_FB));
                Log.d(TAG, "Value is: " + what_To_Do);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        myRefDis1 = database.getReference("dis1");
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

        myRefDis2 = database.getReference("dis2");
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

        myRefDis3 = database.getReference("dis3");
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


    }
    public void mapping(int i_FB,int what_TO_Do)
    {

        while (i_FB>i_Current)
        {
            if(what_TO_Do==2)
            {
                x++;
            }
            else if(what_TO_Do==3)
            {
                x--;
            }
            else if(what_TO_Do==4)
            {
               y--;
            }
            matrix[y][x+2]=" . ";
            matrix[y][x-2]=" . ";
            matrix[y][x]=" * ";
            y++;
        }
    }

    public void exit(View view) {
        Intent intent = new Intent(DataActivity.this, MainActivity.class);
        startActivity(intent);
    }
}