package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private FireBaseController fireBaseController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fireBaseController=FireBaseController.getInstance();
        VideoView videoView = findViewById(R.id.videoView);
        Uri uri = Uri.parse("");
        videoView.setVideoURI(uri);
        videoView.start();

    }
    public void AutomaticMapping(View view){
        fireBaseController.sendAutoControl();
    }
    public void DataPage(View view){
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        startActivity(intent);

    }
}