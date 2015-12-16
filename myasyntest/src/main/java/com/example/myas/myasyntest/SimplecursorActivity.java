package com.example.myas.myasyntest;

import android.database.Cursor;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

public class SimplecursorActivity extends AppCompatActivity {

    private ListView lstview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplecursor);

        initView();
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        startManagingCursor(cursor);

        ListAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1,
                cursor,
                new String[]{Contacts.People.NAME},
                new int[]{android.R.id.text1});

        lstview.setAdapter(listAdapter);

    }

    private void initView() {
        lstview = (ListView) findViewById(R.id.lstView);
    }

}
