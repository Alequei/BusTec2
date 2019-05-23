package com.example.bustec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        RecyclerView recyclerView=findViewById(R.id.listaRecy);
        List<item> mlist=new ArrayList<>();
        mlist.add(new item(R.drawable.bus3,"Carlos",R.drawable.photo,"Placa:2000"));
        mlist.add(new item(R.drawable.bus2,"Maria",R.drawable.photo,"Placa:2000"));
        mlist.add(new item(R.drawable.bus1,"Juan",R.drawable.photo,"Placa:2000"));
        mlist.add(new item(R.drawable.bus3,"Marcelo",R.drawable.photo,"Placa:2000"));
        mlist.add(new item(R.drawable.bus5,"Alex",R.drawable.photo,"Placa:2000"));
        mlist.add(new item(R.drawable.bus6,"Maricarmen",R.drawable.photo,"Placa:2000"));
        Adapter adapter=new Adapter(this,mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
