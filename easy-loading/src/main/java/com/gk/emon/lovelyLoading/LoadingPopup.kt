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
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView

class LoadingPopup(context: Context) : Dialog(context) {
    private var intentionalDelayInMillSec: Long = 0

    @ColorRes
    var backgroundColor: Int = android.R.color.transparent

    @IntRange(from = 0, to = 100)
    private var opacity = DEFAULT_OPACITY
    private var isQueued = false
    private lateinit var vContent: View

    @LayoutRes
    private var customLayoutID = 0
    fun setIntentionalDelayInMillSec(millSec: Long) {
        intentionalDelayInMillSec = millSec
    }

    fun setCustomViewID(customLayoutID: Int, @ColorRes backgroundColor: Int) {
        this.customLayoutID = customLayoutID
        this.backgroundColor = backgroundColor
    }

    fun setOpacity(opacity: Int) {
        this.opacity = opacity
    }

    fun setCancelable() {
        setCancelable(true)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureResources()
    }

    private fun setupBackgroundColorWithOpacity() {
        /**defaultBackgroundColor not black means user set a default color as watermark
         * background.If default color is black that means a default color is not set.
         * So then it should be get the background color from the inflated view from layout
         * resource id which will be shown as the water mark. If all of these approach get any exception
         * then black color with 50% opacity (default opacity) will be set*/
        if (backgroundColor == android.R.color.transparent)
            vContent.setBackgroundColor(Color.parseColor(ColorTransparentUtils
                    .transparentColor(getBackgroundColor(vContent), opacity)))
        else {
            vContent.setBackgroundColor(Color.parseColor(ColorTransparentUtils
                    .transparentColor(ContextCompat.getColor(context, backgroundColor), opacity)))
        }
    }

    private fun configureResources() {
        /** customLayoutID==0 means no custom layout is set. So it should show default layout. */
        if (customLayoutID == 0) {
            vContent = layoutInflater.inflate(R.layout.dialog_lottie_loading_popup, null)
            setupBackgroundColorWithOpacity();
            setContentView(vContent)
            val lottieAnimationView: LottieAnimationView = findViewById(R.id.v_lottie)
            lottieAnimationView.setAnimation("Loading.json")
        } else {
            vContent = layoutInflater.inflate(customLayoutID, null)
            setupBackgroundColorWithOpacity()
            setContentView(vContent)
        }
        window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            it.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }


    }

    private fun getBackgroundColor(view: View): Int {
        val drawable = view.background
        if (drawable is ColorDrawable) {
            return drawable.color
        }
        return Color.TRANSPARENT
    }

    override fun hide() {
        if (intentionalDelayInMillSec != 0L) {
            Handler(Looper.getMainLooper()).postDelayed({
               super.hide()
            }, intentionalDelayInMillSec)
        } else {
            super.hide()
        }

    }

    override fun show() {
        if (!isShowing) {
            super.show()
        }
    }


    interface FinalStep {
        fun cancelable(): FinalStep
        fun setBackgroundOpacity(@IntRange(from = 0, to = 100) opacity: Int): FinalStep
        fun setBackgroundColor(@ColorRes colorId: Int): FinalStep
        fun build()
    }

    class LoadingBuilder(activity: Activity) : FinalStep, TypeStep, DelayDurationStep, DelayStep, CustomLayoutStep {
        private var isAutoCancelable = false
        private var intentionalDelayInMillSec: Long = 0
        var dialog: LoadingPopup

        @LayoutRes
        private var customLayoutID = 0
        private var opacity = DEFAULT_OPACITY

        @ColorRes
        private var backgroundColor = android.R.color.transparent
        override fun setCustomViewID(customLayoutID: Int): DelayStep {
            this.customLayoutID = customLayoutID
            return this
        }

        override fun setCustomViewID(customLayoutID: Int, @ColorRes backgroundColor: Int): DelayStep {
            this.customLayoutID = customLayoutID
            this.backgroundColor = backgroundColor
            return this
        }

        override fun cancelable(): FinalStep {
            isAutoCancelable = true
            return this
        }

        override fun setBackgroundOpacity(opacity: Int): FinalStep {
            this.opacity = opacity
            return this
        }

        override fun setBackgroundColor(colorId: Int): FinalStep {
            this.backgroundColor = colorId;
            return this
        }

        override fun build() {
            dialog.setIntentionalDelayInMillSec(intentionalDelayInMillSec)
            if (isAutoCancelable) dialog.setCancelable()
            dialog.setCustomViewID(customLayoutID, backgroundColor)
            dialog.setOpacity(opacity)
            dialog.configureResources()
        }

        override fun defaultLovelyLoading(): FinalStep {
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
                                build()
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

        interface TypeStep {
            fun defaultLovelyLoading(): FinalStep
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
        private const val DEFAULT_OPACITY = 30
    }
}