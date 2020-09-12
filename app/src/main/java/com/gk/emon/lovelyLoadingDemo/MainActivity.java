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
                .customLoading()
                .setCustomViewID(R.layout.dialog_lottie_loading_popup)
                .doIntentionalDelay()
                .setDelayDurationInMillSec(3000)
                .build();

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
