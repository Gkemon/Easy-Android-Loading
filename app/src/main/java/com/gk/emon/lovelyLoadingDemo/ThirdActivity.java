package com.gk.emon.lovelyLoadingDemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gk.emon.lovelyLoading.LoadingPopup;


public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        LoadingPopup.hideLoadingPopUp();

    }

}
