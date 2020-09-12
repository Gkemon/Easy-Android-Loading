package com.gk.emon.lovelyLoading

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import com.airbnb.lottie.LottieAnimationView

class  LoadingPopup(context: Context) : Dialog(context) {
    val TAG: String = LoadingPopup::class.java.simpleName
    private var intentionalDelayInMillSec: Long = 0
    @ColorInt
    var backgroundColor: Int = Color.TRANSPARENT
    private var opacity = 50
    private var isQueued = false

    @LayoutRes
    private var customLayoutID = 0
    fun setIntentionalDelayInMillSec(millSec: Long) {
        intentionalDelayInMillSec = millSec
    }

    fun setCustomViewID(customLayoutID: Int, backgroundColor: Int) {
        this.customLayoutID = customLayoutID
        this.backgroundColor = backgroundColor
    }

    fun setOpacity(opacity: Int) {
        this.opacity = opacity
    }

    fun autoCancelable() {
        setCancelable(true)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureResources()
    }

    private fun configureResources() {
        if (customLayoutID == 0) {
            setContentView(R.layout.dialog_lottie_loading_popup)
            val lottieAnimationView: LottieAnimationView = findViewById(R.id.v_lottie)
            lottieAnimationView.setAnimation("Loading.json")
            if (window != null) window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        } else {
            val vContent = layoutInflater.inflate(customLayoutID, null)

            /**defaultBackgroundColor not black means user set a default color as watermark
             * background.If default color is black that means a default color is not set.
             * So then it should be get the background color from the inflated view from layout
             * resource id which will be shown as the water mark. If all of these approach get any exception
             * then black color with 50% opacity (default opacity) will be set*/
            if(backgroundColor==Color.TRANSPARENT)
                vContent.setBackgroundColor(Color.parseColor(ColorTransparentUtils
                        .transparentColor(getBackgroundColor(vContent), opacity)))
            else vContent.setBackgroundColor(Color.parseColor(ColorTransparentUtils
                    .transparentColor(backgroundColor, opacity)))

            setContentView(vContent)
        }
    }

    private fun getBackgroundColor(view: View): Int {
        val drawable = view.background
        if (drawable is ColorDrawable) {
            return drawable.color
        }
        return backgroundColor
    }

    override fun show() {
        if (intentionalDelayInMillSec != 0L) {
            if (!isQueued) {
                isQueued = true
                super.show()
                Handler(Looper.getMainLooper()).postDelayed({
                    hideLoadingPopUp()
                    isQueued = false
                }, intentionalDelayInMillSec)
            }
        } else {
            super.show()
        }
    }

    interface TypeStep {
        fun defaultLoading(): FinalStep
        fun customLoading(): CustomLayoutStep
    }

    interface CustomLayoutStep {
        fun setCustomViewID(@LayoutRes customLayoutID: Int): DelayStep
        fun setCustomViewID(@LayoutRes customLayoutID: Int, @ColorRes backgroundColor: Int): DelayStep
    }

    interface DelayStep {
        fun doIntentionalDelay(): DelayDurationStep
        fun noIntentionalDelay(): FinalStep
    }

    interface DelayDurationStep {
        fun setDelayDurationInMillSec(millSec: Long): FinalStep
    }

    interface FinalStep {
        fun autoCancelable(): FinalStep
        fun setBackgroundOpacity(opacity: Int): FinalStep
        fun build()
    }

    class LoadingBuilder(activity: Activity) : FinalStep, TypeStep, DelayDurationStep, DelayStep, CustomLayoutStep {
        private var isAutoCancelable = false
        private var intentionalDelayInMillSec: Long = 0
        var dialog: LoadingPopup

        @LayoutRes
        private var customLayoutID = 0
        private var opacity = 0

        @ColorRes
        private var backgroundColor = 0
        override fun setCustomViewID(customLayoutID: Int): DelayStep {
            this.customLayoutID = customLayoutID
            return this
        }

        override fun setCustomViewID(customLayoutID: Int, backgroundColor: Int): DelayStep {
            this.customLayoutID = customLayoutID
            this.backgroundColor = backgroundColor
            return this
        }

        override fun autoCancelable(): FinalStep {
            isAutoCancelable = true
            return this
        }

        override fun setBackgroundOpacity(opacity: Int): FinalStep {
            this.opacity = opacity
            return this
        }

        override fun build() {
            dialog.setIntentionalDelayInMillSec(intentionalDelayInMillSec)
            if (isAutoCancelable) dialog.autoCancelable()
            dialog.setCustomViewID(customLayoutID, backgroundColor)
            dialog.setOpacity(opacity)
            dialog.configureResources()
        }

        override fun defaultLoading(): FinalStep {
            return this
        }

        override fun customLoading(): CustomLayoutStep {
            return this
        }

        override fun doIntentionalDelay(): DelayDurationStep {
            return this
        }

        override fun noIntentionalDelay(): FinalStep {
            return this
        }

        override fun setDelayDurationInMillSec(millSec: Long): FinalStep {
            this.intentionalDelayInMillSec = millSec
            return this
        }

        private fun registerActivityChangeListener(activity: Activity?) {
            if (activity != null && activity.application != null) {
                activity.application
                        .registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}
                            override fun onActivityStarted(activity: Activity) {}
                            override fun onActivityResumed(activity: Activity) {
                                dialog = LoadingPopup(activity)
                            }

                            override fun onActivityPaused(activity: Activity) {}
                            override fun onActivityStopped(activity: Activity) {}
                            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
                            override fun onActivityDestroyed(activity: Activity) {}
                        })
            }
        }

        init {
            dialog = LoadingPopup(activity)
            registerActivityChangeListener(activity)
        }
    }

    companion object {
        private lateinit var loadingBuilder: LoadingBuilder

        @JvmStatic
        fun showLoadingPopUp() = try {
            loadingBuilder.dialog.show()
        } catch (e: Exception) {
            Log.e(TAG, "Error in loading popup: " + e.message)
        }

        @JvmStatic
        fun hideLoadingPopUp() {
            loadingBuilder.dialog.hide()
        }

        @JvmStatic
        @Synchronized
        fun getInstance(activity: Activity): TypeStep {
            return LoadingBuilder(activity).also {
                loadingBuilder = it
            }
        }
        private val TAG: String = LoadingPopup::class.java.simpleName
    }
}