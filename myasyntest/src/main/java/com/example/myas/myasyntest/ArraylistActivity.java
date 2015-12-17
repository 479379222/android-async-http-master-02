package com.example.myas.myasyntest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ArraylistActivity extends AppCompatActivity {

    private ListView lstView;
    private String[] mylistData = new String[]{"北京", "上海", "天津", "南京"};
    private static final String LOG_TAG=ArraylistActivity.class.getName();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arraylist);
        initView();
        lstView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylistData));

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,mylistData[position],Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        lstView = (ListView) findViewById(R.id.lstView);
        context=ArraylistActivity.this;
    }
}
