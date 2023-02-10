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
        createDelay(5,splashImage);

        animation2(splashImage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                // every 50 ms, the image will be smaller by 5 pixels

                ActivityOptions options = ActivityOptions.makeCustomAnimation(SplashScreenActivity.this, android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent, options.toBundle());
                finish();
            }
        }, 8000);
    }
    public void animation1( ImageView splashImage)
    {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 0.5f,
                1.0f, 0.5f,
                Animation.RELATIVE_TO_SELF, -0.35f,
                Animation.RELATIVE_TO_SELF, 0.3f
        );
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillAfter(true);
        splashImage.startAnimation(scaleAnimation);
    }
    public void animation2( ImageView splashImage)
    {
        RotateAnimation rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(2500);
        splashImage.startAnimation(rotate);
    }
    private void createDelay(int seconds,ImageView splashImage) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animation1(splashImage);
            }
        }, seconds * 1000);
    }

}