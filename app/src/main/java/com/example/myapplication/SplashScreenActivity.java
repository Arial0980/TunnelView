package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        ImageView splashImage = findViewById(R.id.imageView);
        createDelay1(1300,splashImage);
        createDelay2(2500,splashImage);
        bigger(splashImage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                // every 50 ms, the image will be smaller by 5 pixels

                ActivityOptions options = ActivityOptions.makeCustomAnimation(SplashScreenActivity.this, android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent, options.toBundle());
                finish();
            }
        }, 4900);
    }
    public void pullingToSide( ImageView splashImage)
    {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 0.5f,
                1.0f, 0.5f,
                Animation.RELATIVE_TO_SELF, -0.37f,
                Animation.RELATIVE_TO_SELF, 0.25f
        );
        scaleAnimation.setDuration(3050);
        scaleAnimation.setFillAfter(true);
        splashImage.startAnimation(scaleAnimation);
    }
    public void bigger( ImageView splashImage)
    {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.5f,
                1.0f, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(1300);
        scaleAnimation.setFillAfter(true);
        splashImage.startAnimation(scaleAnimation);
    }
    public void smaller( ImageView splashImage)
    {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.5f, 1.0f,
                1.5f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(1300);
        scaleAnimation.setFillAfter(true);
        splashImage.startAnimation(scaleAnimation);
    }
    public void spin( ImageView splashImage)
    {
        RotateAnimation rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(1620);
        splashImage.startAnimation(rotate);
    }
    private void createDelay2(int milliseconds,ImageView splashImage) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pullingToSide(splashImage);
            }
        }, milliseconds);
    }

    private void createDelay1(int milliseconds,ImageView splashImage) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                smaller(splashImage);
            }
        }, milliseconds);
    }
}