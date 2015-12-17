package com.example.myas.myasyntest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyserviceActivity extends AppCompatActivity {

    private ListView lstView;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myservice);

        initView();
        lstView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData()));
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.i("Test",sampleConfigs[position].title.toString());
                startActivity(new Intent(context, sampleConfigs[position].targetClass));
            }
        });
    }

    private static final SampleConfig[] sampleConfigs=new SampleConfig[]{
            new SampleConfig("Base Service",BaseserviceActivity.class)
    };

    private void initView(){
        context=MyserviceActivity.this;
        lstView=(ListView)findViewById(R.id.lstView);
    }

    private List<String> getData(){
        List<String> list=new ArrayList<>();
        for (SampleConfig config:sampleConfigs){
            list.add(config.title);
        }
        return list;
    }

    private static class SampleConfig {
        String title;
        Class targetClass;

        SampleConfig(String title,Class targetClass){
            this.title=title;
            this.targetClass=targetClass;
        }
    }

}
