package com.pushwoosh.richmediadelegatetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pushwoosh.Pushwoosh;
import com.pushwoosh.richmedia.RichMedia;
import com.pushwoosh.richmedia.RichMediaManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Pushwoosh.getInstance().registerForPushNotifications();

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                TestingApp.setIsLoggedIn(true);
                if(TestingApp.getIsLoggedIn()){
                    Log.d("SplashScreen","logged in");
                }
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

                RichMedia richMedia = TestingApp.getTestingApp().get_richMedia();
                if (richMedia !=null){
                    RichMediaManager.present(richMedia);
                }
            }
        });
    }
}
