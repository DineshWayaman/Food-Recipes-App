package com.wcreation.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageView = (ImageView) findViewById(R.id.imgLogo);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.transtition);
        imageView.startAnimation(animation);
        final Intent i = new Intent(this,MainActivity.class);
        Thread timer = new Thread()
        {
            public void run()
            {
                try {
                    sleep(5000);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {

                    startActivity(i);
                    finish();

                }
            }
        };
        timer.start();

    }
}
