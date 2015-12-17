package com.example.myas.myasyntest;

import android.database.Cursor;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplecursorActivity extends AppCompatActivity {

    private ListView lstview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplecursor);

        initView();
        Map<String, String> map = new HashMap<String, String>();

        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor != null) {

            startManagingCursor(cursor);

        }
        ListAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, cursor,
                new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME},
                new int[]{android.R.id.text1});
        lstview.setAdapter(adapter);
        cursor.close();

    }

    private void initView() {
        lstview = (ListView) findViewById(R.id.lstView);
    }

}
