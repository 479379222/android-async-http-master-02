package com.example.myas.myasyntest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MyserviceActivity extends AppCompatActivity {

    private ListView lstView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myservice);



    }

    private void initView(){
        lstView=(ListView)findViewById(R.id.lstView);
    }
}
