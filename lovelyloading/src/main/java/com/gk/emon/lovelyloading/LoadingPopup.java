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


public class  LoadingPopup extends Dialog {

    public static LoadingPopup dialog;
    public  boolean isAutoCancelable;
    public static Activity activity;
    private int duration =3;
    //If anyone wants to custom the view of loading then
    // can set the XML resource ID of that layout
    private int customLayoutID;


    public LoadingPopup setCustomViewID(int customLayoutID){
        this.customLayoutID=customLayoutID;
        return this;
    }
    public LoadingPopup setDuration(int second){
        this.duration = second;
        return this;
    }
    public LoadingPopup autoCancleable(){
        this.isAutoCancelable = false;
        return this;
    }
    public LoadingPopup setActivity(Activity activity){
        this.activity = activity;
        return this;
    }
    public void init(){
        getInstance(getOwnerActivity());
    }

    public LoadingPopup(@NonNull Context context) {
        super(context);
    }
    public static LoadingPopup getCustomBuilder(Activity activity){
        return new LoadingPopup(activity);
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




    @Override
    public void show() {
        super.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadingPopup.this.setCancelable(true);
            }
        }, duration*1000);

        if(isAutoCancelable){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hideLoadingPopUp();
                }
            }, duration*1000);
        }

    }

    public static void hideLoadingPopUp(){

        if(dialog!=null)
        {
            dialog.dismiss();
            dialog=null;
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(customLayoutID==0)
        setContentView(R.layout.dialog_lottie_loading_popup);
        else setContentView(customLayoutID);

        if(getWindow()!=null)
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    public static LoadingPopup getInstance(Activity activity){

        if(dialog==null){
            dialog=new LoadingPopup(activity);
            return dialog;
        }
        else return dialog;
    }


}
