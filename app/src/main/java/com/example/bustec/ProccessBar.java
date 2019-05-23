package com.example.bustec;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProccessBar extends Animation {
    private Context context;
    private ProgressBar progressBar;
    private TextView textView;
    private float from;
    private  float flo;


    public ProccessBar(Context context , ProgressBar progressBar, TextView textView, float from, float flo){
        this.context=context;
        this.progressBar=progressBar;
        this.textView=textView;
        this.from=from;
        this.flo=flo;

    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        float value=from+(flo-from)*interpolatedTime;
        progressBar.setProgress((int) value);
        textView.setText((int)value+"%");

        if (value == flo){
            context.startActivity(new Intent(context,Login.class));
        }
    }
}
