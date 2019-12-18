package com.gk.emon.lovelyloadingdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.gk.emon.lovelyloading.LoadingPopup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LoadingPopup.getCustomBuilder(this)
                .setDuration(1)
                .autoCancleable()
                .init();


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingPopup.showLoadingPopUp(MainActivity.this);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LoadingPopup.hideLoadingPopUp();
                    }
                },3000);
                //startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingPopup.hideLoadingPopUp();
                //startActivity(new Intent(MainActivity.this,ThirdActivity.class));
            }
        });
    }
}
