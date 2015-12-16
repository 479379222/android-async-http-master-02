package com.example.myas.myasyntest;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyListActivity extends AppCompatActivity {

    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        initView();
        lstView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getTitlesList()));
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MyListActivity.this, sampleConfig[position].targetClass));
            }
        });

    }

    private void initView() {
        lstView = (ListView) findViewById(R.id.lstView);
    }

    private static final SampleConfig[] sampleConfig = new SampleConfig[]{
            new SampleConfig(R.string.arraylist, ArraylistActivity.class)
    };

    private List<String> getTitlesList() {
        List<String> titlel = new ArrayList<>();
        for (SampleConfig config : sampleConfig) {
            titlel.add(getString(config.titleID));
        }
        return titlel;
    }

    private static class SampleConfig {
        int titleID;
        Class targetClass;

        SampleConfig(int titleID, Class targetClass) {
            this.titleID = titleID;
            this.targetClass = targetClass;
        }
    }

}
