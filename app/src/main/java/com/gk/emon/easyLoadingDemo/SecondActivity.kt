package com.gk.emon.easyLoadingDemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.gk.emon.lovelyLoading.LoadingPopup

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Handler(Looper.getMainLooper()).postDelayed({
            LoadingPopup.hideLoadingPopUp()
        }, 2000)

    }
}