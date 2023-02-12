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
        createDelay(1620,splashImage);

        pullingToSide(splashImage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                // every 50 ms, the image will be smaller by 5 pixels

                ActivityOptions options = ActivityOptions.makeCustomAnimation(SplashScreenActivity.this, android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent, options.toBundle());
                finish();
            }
        }, 4650);
    }
    public void spin( ImageView splashImage)
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
    public void pullingToSide( ImageView splashImage)
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
    private void createDelay(int milliseconds,ImageView splashImage) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                spin(splashImage);
            }
        }, milliseconds);
    }

}