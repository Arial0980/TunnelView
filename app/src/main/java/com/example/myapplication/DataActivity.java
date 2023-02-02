package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {
    private FireBaseController fireBaseController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        fireBaseController=FireBaseController.getInstance();
        TextView right=findViewById(R.id.Right_textView);
        TextView left=findViewById(R.id.Left_textView);
        TextView straight=findViewById(R.id.Straight_textView);
        straight.setText(""+straight.getText()+fireBaseController.getStraightCoordinate());
        left.setText(""+left.getText()+fireBaseController.getLeftCoordinate());
        right.setText(""+right.getText()+fireBaseController.getRightCoordinate());
    }
}