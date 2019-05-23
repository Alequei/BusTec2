package com.example.bustec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Inicio extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar=findViewById(R.id.procesbar);
        textView=findViewById(R.id.porcentaje);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();
    }
    public  void  progressAnimation(){
        ProccessBar amin=new ProccessBar(this,progressBar,textView,0f,100f);
        amin.setDuration(8000);
        progressBar.setAnimation(amin);
    }

}
