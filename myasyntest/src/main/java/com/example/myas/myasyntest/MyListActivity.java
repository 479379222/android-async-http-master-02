package com.example.myas.myasyntest;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyListActivity extends ListActivity {

    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        initView();
        lstView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getListData()));
        setContentView(lstView);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    private void initView() {
        lstView = (ListView) findViewById(R.id.lstView);
    }

    private List<String> getListData(){
        List<String> listdata=new ArrayList<>();
        listdata.add("北京");
        listdata.add("上海");
        listdata.add("天津");
        return listdata;
    }

}
