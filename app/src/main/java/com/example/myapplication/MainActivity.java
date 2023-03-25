package com.example.myapplication;
import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView coinImage = findViewById(R.id.coin_image);//קטע קוד זה לוקח את התמונה שהגדרנו בדף שנוכל להשתמש בה ולעשות עליה פעולות

        ObjectAnimator animator = ObjectAnimator.ofFloat(coinImage, "scaleX", 1f, -1f);
        animator.setDuration(1500);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(1000000);
        animator.start();
        //קטע קוד זה הוא גורם לאנימציה על התמונה שהגדרנו בדף וגורם לתמונה להסתובב כמו
        //מטבע כל סיבוב הוא בערך כשנייה וחצי והוא חוזר על עצמו מספר גדול של חזרות
        // כדי שנראה את האנימציה תמיד פועלת
    }
    public void DataPage(View view){
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        startActivity(intent);
        //הפונקציה הזאת גורם למעבר מהדף הנוכחי לדף של המידע
    }
    public void ControlPage(View view){
        Intent intent = new Intent(MainActivity.this, ControlActivity.class);
        startActivity(intent);
        //הפונקציה הזאת גורם למעבר מהדף הנוכחי לדף של השליטה הידנית
    }
}