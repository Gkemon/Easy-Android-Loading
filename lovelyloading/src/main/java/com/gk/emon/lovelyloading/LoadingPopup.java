package com.gk.emon.lovelyloading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.NonNull;

import com.airbnb.lottie.LottieAnimationView;


public class  LoadingPopup extends Dialog {

    public static LoadingPopup dialog;
    public int duration;
    private boolean isQueued;//This is for handle random click to show popup
    //If anyone wants to custom the view of loading then
    // can set the XML resource ID of that layout
    private int customLayoutID;

    public static class Builder{

        public  boolean isAutoCancelable;
        public  Activity activity;
        public int duration;
        private int customLayoutID;

        public Builder setCustomViewID(int customLayoutID){
            this.customLayoutID=customLayoutID;
            return this;
        }
        public Builder setDuration(int second){
            this.duration = second;
            return this;
        }
        public Builder autoCancleable(){
            this.isAutoCancelable = true;
            return this;
        }

        public Builder (Activity activity){
            this.activity=activity;
        }
        public static Builder  getCustomLoadingBuilder(Activity activity)
        {
            return new Builder(activity);
        }
        public void init(){
            dialog=getInstance(activity);
            dialog.setDuration(duration);
            if(isAutoCancelable)dialog.autoCancleable();
            dialog.setCustomViewID(customLayoutID);
        }

    }

    public LoadingPopup(@NonNull Context context) {
        super(context);
    }

    public static void  showLoadingPopUp(Activity activity) {
           try {
               dialog.show();
           } catch (Exception e) {
               Log.e("Error","Error in loading popup: "+e.getMessage());
               dialog = null;
               getInstance(activity).show();
           }
    }

    public void setCustomViewID(int customLayoutID){
        this.customLayoutID=customLayoutID;
    }
    public void setDuration(int second){
        this.duration = second;
    }
    public void autoCancleable(){
        setCancelable(true);
    }


    @Override
    public void show() {
        super.show();

             Handler handler = new Handler();


            if(duration!=0)
                if(!isQueued)
                {
                    isQueued=true;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hideLoadingPopUp();
                            isQueued=false;
                        }
                    }, duration*1000);
                }

    }

    public static void hideLoadingPopUp(){
       if(dialog!=null)dialog.dismiss();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(customLayoutID==0)
        setContentView(R.layout.dialog_lottie_loading_popup);
        else setContentView(customLayoutID);

        LottieAnimationView lottieAnimationView = findViewById(R.id.v_lottie);
        lottieAnimationView.setAnimation("cheers.json");

        if(getWindow()!=null)
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    public synchronized static LoadingPopup getInstance(Activity activity){

        if(dialog==null){
            dialog=new LoadingPopup(activity);
            return dialog;
        }
        else return dialog;
    }


}
