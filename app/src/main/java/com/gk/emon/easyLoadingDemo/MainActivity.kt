package com.gk.emon.easyLoadingDemo

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getColor
import com.gk.emon.lovelyLoading.LoadingPopup
import com.gk.emon.lovelyLoading.LoadingPopup.Companion.getInstance
import petrov.kristiyan.colorpicker.ColorPicker
import petrov.kristiyan.colorpicker.ColorPicker.OnChooseColorListener

class MainActivity : AppCompatActivity() {
    private lateinit var cbDelay: CheckBox
    private lateinit var cbBg: CheckBox
    private lateinit var cbOpacity: CheckBox
    private lateinit var etDelay: EditText
    private lateinit var etOpacity: EditText
    private lateinit var btnDefaultShow: View
    private lateinit var btnCustomShow: View
    private lateinit var btnColor: Button
    private lateinit var llOpacity: View
    private lateinit var llDelay: View

    @ColorRes
    private var bgColorSelected = android.R.color.transparent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        getInstance(this)
                .customLoading()
                .setCustomViewID(R.layout.layout_my_custom_loading, android.R.color.holo_red_dark)
                .doIntentionalDelay()
                .setDelayDurationInMillSec(5000)
                .setBackgroundOpacity(70)
                .build()


    }

    private fun doConfigureAndShowDefaultLovelyLoading() {

        if (bgColorSelected == android.R.color.transparent) {
            if (llOpacity.visibility == View.VISIBLE) {
                etOpacity.text.toString().toIntOrNull()?.let {
                    /**When only opacity is set*/
                    getInstance(this)
                            .defaultLovelyLoading()
                            .setBackgroundOpacity(it)
                            .build()
                }
            } else {
                /**When none of opacity and background color is set*/
                getInstance(this)
                        .defaultLovelyLoading()
                        .build()
            }

        } else {
            if (llOpacity.visibility == View.VISIBLE) {

                etOpacity.text.toString().toIntOrNull()?.let {
                    /**When both opacity and background color is set*/
                    getInstance(this)
                            .defaultLovelyLoading()
                            .setBackgroundColor(bgColorSelected)
                            .setBackgroundOpacity(it)
                            .build()
                }
            } else {
                /**When only background color is set*/
                getInstance(this)
                        .defaultLovelyLoading()
                        .setBackgroundColor(bgColorSelected)
                        .build()
            }
        }


        LoadingPopup.showLoadingPopUp()

        Handler(Looper.getMainLooper()).postDelayed({
            LoadingPopup.hideLoadingPopUp()
        }, DELAY)


    }

    private fun doConfigureAndShowCustomLovelyLoading() {

        val delayDurationStep: LoadingPopup.Companion.DelayDurationStep?
        var finalStep: LoadingPopup.FinalStep? = null


        val delayStep: LoadingPopup.Companion.DelayStep? = if (bgColorSelected != android.R.color.transparent)
            getInstance(this)
                    /**If bg color is set*/
                    .customLoading()
                    .setCustomViewID(R.layout.layout_my_custom_loading, bgColorSelected)
        else getInstance(this)
                /**If bg color is not set*/
                .customLoading()
                .setCustomViewID(R.layout.layout_my_custom_loading)

        if (llDelay.visibility == View.VISIBLE) {
            delayDurationStep = delayStep?.doIntentionalDelay()
            etDelay.text.toString().toLongOrNull()?.let {
                finalStep = delayDurationStep?.setDelayDurationInMillSec(it)
            }
        } else {
            finalStep = delayStep?.noIntentionalDelay()
        }

        if (llOpacity.visibility == View.VISIBLE) {
            etOpacity.text.toString().toIntOrNull()?.let {
                finalStep?.setBackgroundOpacity(it)
            }
        }

        if (btnColor.visibility == View.VISIBLE) {
            finalStep?.setBackgroundColor(bgColorSelected)
        }

        finalStep?.build()


        LoadingPopup.showLoadingPopUp()

        Handler(Looper.getMainLooper()).postDelayed({
            LoadingPopup.hideLoadingPopUp()
        }, DELAY)
    }

    private fun initView() {
        cbBg = findViewById(R.id.cb_bg)
        cbDelay = findViewById(R.id.cb_delay)
        cbOpacity = findViewById(R.id.cb_opacity)
        etDelay = findViewById(R.id.et_delay)
        etOpacity = findViewById(R.id.et_opacity)
        btnDefaultShow = findViewById(R.id.btn_default_show)
        btnCustomShow = findViewById(R.id.btn_custom_show)
        btnColor = findViewById(R.id.bt_color_select)
        llDelay = findViewById(R.id.ll_delay)
        llOpacity = findViewById(R.id.ll_opacity)

        btnCustomShow.setOnClickListener { doConfigureAndShowCustomLovelyLoading() }
        btnDefaultShow.setOnClickListener { doConfigureAndShowDefaultLovelyLoading() }
        cbBg.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) btnColor.visibility =
                    View.VISIBLE else {
                btnColor.visibility = View.GONE
                bgColorSelected = android.R.color.transparent
            }
        }
        cbDelay.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                llDelay.visibility = View.VISIBLE
            else {
                llDelay.visibility = View.GONE
            }
        }
        cbOpacity.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                llOpacity.visibility = View.VISIBLE
            else llOpacity.visibility = View.GONE
        }
        btnColor.setOnClickListener {
            val colorPicker = ColorPicker(this@MainActivity)
            colorPicker
                    .setColors(R.array.colors)
                    .setOnChooseColorListener(object : OnChooseColorListener {
                        override fun onChooseColor(position: Int, color: Int) {
                            val colors = intArrayOf(R.color.colorPrimary, R.color.colorPrimaryDark,
                                    R.color.colorAccent, R.color.colorGreen, R.color.colorRed,
                                    R.color.colorYellow, R.color.colorOrange, R.color.colorBlue,
                                    R.color.colorBlack)
                            bgColorSelected = colors[position]
                            btnColor.background.mutate().colorFilter = PorterDuffColorFilter(
                                    getColor(this@MainActivity, colors[position]), PorterDuff.Mode.SRC)
                        }

                        override fun onCancel() {}
                    }).show()
        }
    }

    companion object {
        private const val DELAY: Long = 10000
    }
}