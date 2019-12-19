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


        LoadingPopup.Builder.getCustomLoadingBuilder(this)
                .setDuration(3)
                .autoCancleable()
                .init();


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingPopup.showLoadingPopUp(MainActivity.this);
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
