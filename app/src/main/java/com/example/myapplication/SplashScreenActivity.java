package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        ImageView splashImage = findViewById(R.id.imageView);//קטע קוד זה לוקח את התמונה שהגדרנו ב-layout שנוכל להשתמש בה ולעשות עליה פעולות
        bigger(splashImage);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);

                ActivityOptions options = ActivityOptions.makeCustomAnimation(SplashScreenActivity.this, android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent, options.toBundle());
                finish();
            }
        }, 1450);
        //הקטע קוד הזה מעביר אחרי בערך שנייה וחצי מהדף הזה לעמוד הראשי
    }
    public void bigger( ImageView splashImage)
    {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 5.0f,
                1.0f, 5.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(1500);
        scaleAnimation.setFillAfter(true);
        splashImage.startAnimation(scaleAnimation);
        //הפונקציה יוצרת אנימציה של הגדלה על התמונה של הדף מעבר
    }
}