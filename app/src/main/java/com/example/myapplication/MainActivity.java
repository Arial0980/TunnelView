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
    private boolean alreadyAutoControlled=false;
    private DatabaseReference myRefVideo;
    private FirebaseDatabase database;
    private String video="";
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

        database = FirebaseDatabase.getInstance();
        myRefVideo = database.getReference("video");
        myRefVideo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                video = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + video);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        WebView cam = findViewById(R.id.cam);
        String ip=video;
        cam.setWebViewClient(new WebViewClient());
        cam.loadUrl(ip);
        cam.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings= cam.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setSupportMultipleWindows(true);
        String newUA="Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
        webSettings.setUserAgentString(newUA);
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