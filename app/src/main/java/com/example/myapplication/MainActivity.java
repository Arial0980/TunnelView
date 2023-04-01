package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView coinImage = findViewById(R.id.coin_image);

        ObjectAnimator animator = ObjectAnimator.ofFloat(coinImage, "scaleX", 1f, -1f);
        animator.setDuration(1500);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(1000000);
        animator.start();
    }
    public void MappingPage(View view){
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        startActivity(intent);
    }
    public void ControlPage(View view){
        Intent intent = new Intent(MainActivity.this, ControlActivity.class);
        startActivity(intent);
    }
}