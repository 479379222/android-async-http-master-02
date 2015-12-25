package com.example.myas.myasyntest.http;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myas.myasyntest.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class JsonActivity extends AppCompatActivity {

    private Button btnJson;
    private Context context;
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        initView();
        AsyncHttpClient client = new AsyncHttpClient();

        JSONArray jsonArray=new JSONArray();
        client.get("http://10.0.2.2/MCMSysWeb/json.txt", new RequestParams("name", ""), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 200) {
                    List<String> objects = new ArrayList<>();
                    //存储数据变量
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            objects.add(object.getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    ArrayAdapter<String> adapter=new ArrayAdapter(context,android.R.layout.simple_list_item_1,objects);
                    lstView.setAdapter(adapter);

                }
            }
        });
    }

    private void initView() {
        context = JsonActivity.this;
        btnJson = (Button) findViewById(R.id.btnJson);
        lstView = (ListView) findViewById(R.id.lstView);
    }
}
