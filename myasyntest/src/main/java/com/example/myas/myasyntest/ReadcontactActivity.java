package com.example.myas.myasyntest;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadcontactActivity extends AppCompatActivity {

    private ListView lstview;
    private Context context;
    /**
     * 联系人显示名称
     **/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;
    /**
     * 电话号码
     **/
    private static final int PHONES_NUMBER_INDEX = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readcontact);

        initView();
        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.listitem_contact,
                new String[]{"phone","names"},
                new int[]{R.id.txtTel,R.id.txtName});
        lstview.setAdapter(adapter);
    }

    private void initView() {
        lstview = (ListView) findViewById(R.id.lstView);
        context = ReadcontactActivity.this;
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        int contactIdIndex = 0;
        int nameIndex = 0;
        if(cursor.getCount() > 0) {
            contactIdIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        }
        while (cursor.moveToNext()) {
            //得到手机号码 wangbf
            String contactId = cursor.getString(contactIdIndex);
            String phonenumber="";
            if (TextUtils.isEmpty(contactId))
                continue;
            //得到联系人姓名
            String contactname = cursor.getString(nameIndex);
            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
                    null, null);
            int phoneIndex = 0;
            if(phones.getCount() > 0) {
                phoneIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            }
            while(phones.moveToNext()) {
                phonenumber = phones.getString(phoneIndex);
            }

            map = new HashMap<>();
            map.put("phone", phonenumber);
            map.put("names", contactname);
            list.add(map);

        }

        return list;
    }

}
