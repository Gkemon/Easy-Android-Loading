package com.gk.emon.lovelyLoadingDemo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gk.emon.lovelyLoading.LoadingPopup;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LoadingPopup.getInstance(this)
                .defaultLoading()
                .build();


//        LoadingPopup.getInstance(this)
//                .customLoading()
//                .setCustomViewID(R.layout.activity_second,android.R.color.holo_red_dark)
//                .doIntentionalDelay()
//                .setDelayDurationInMillSec(5000)
//                .setBackgroundOpacity(70)
//                .build();

//        LoadingPopup.getInstance(this)
//                .customLoading()
//                .setCustomViewID(R.layout.activity_second,android.R.color.holo_red_dark)
//                .doIntentionalDelay()
//                .setDelayDurationInMillSec(5000)
//                .setBackgroundOpacity(70)
//                .build();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingPopup.showLoadingPopUp();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingPopup.hideLoadingPopUp();
            }
        });
    }
}
