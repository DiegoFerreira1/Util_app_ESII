package com.example.utilapp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainSplash extends AppCompatActivity {

    ImageView img_logo;
    TextView txt_util, txt_util2;
    Animation top, bottom;

    private static final int SPLASH_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_splash);

        img_logo = findViewById(R.id.id_img_logo);
        txt_util = findViewById(R.id.id_text_util);
        txt_util2 = findViewById(R.id.id_text_social);

        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);

        img_logo.setAnimation(top);
        txt_util.setAnimation(bottom);
        txt_util2.setAnimation(bottom);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainSplash.this, MainLogin.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);

    }
}