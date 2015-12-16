package com.example.myas.myasyntest;

import android.content.Context;
import android.database.Cursor;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplelistActivity extends AppCompatActivity {

    private ListView lstView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplelist);

        initView();
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.listitem_city,
                new String[]{"logo", "title", "subtitle"},
                new int[]{R.id.imgLogo, R.id.txtTitle, R.id.txtSubtitle});
        lstView.setAdapter(adapter);

    }

    private void initView() {
        context = SimplelistActivity.this;
        lstView = (ListView) findViewById(R.id.lstView);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<>();
        map.put("logo", R.mipmap.music);
        map.put("title", "三星");
        map.put("subtitle", "三星手机是Android");
        list.add(map);

        map = new HashMap<>();
        map.put("logo", R.mipmap.music);
        map.put("title", "华为");
        map.put("subtitle", "华为手机是Android");
        list.add(map);

        return list;

    }

}
