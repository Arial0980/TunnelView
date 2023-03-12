package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private boolean alreadyAutoControlled=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView splashImage = findViewById(R.id.imageView2);
        splashImage.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_YES);
        ImageView coinImage = findViewById(R.id.coin_image);

            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    5.0f, 0.0f,
                    5.0f, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
            );
            scaleAnimation.setDuration(1300);
            scaleAnimation.setFillAfter(true);
            splashImage.startAnimation(scaleAnimation);

        ObjectAnimator animator = ObjectAnimator.ofFloat(coinImage, "scaleX", 1f, -1f);
        animator.setDuration(1500);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(1000000);
        animator.start();

    }
    public void AutomaticMapping(View view){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("to_altera");
        if (alreadyAutoControlled) {
            myRef.setValue(0);
            alreadyAutoControlled=false;
        }
        else {
            myRef.setValue(32);
            alreadyAutoControlled=true;
        }
    }
    public void DataPage(View view){
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        startActivity(intent);

    }
    public void ControlPage(View view){
        Intent intent = new Intent(MainActivity.this, ControlActivity.class);
        startActivity(intent);

    }
}