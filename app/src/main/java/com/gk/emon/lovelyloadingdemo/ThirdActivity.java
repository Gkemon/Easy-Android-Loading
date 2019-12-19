package com.gk.emon.lovelyloadingdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gk.emon.lovelyloading.LoadingPopup;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        LoadingPopup.hideLoadingPopUp();

    }

}
