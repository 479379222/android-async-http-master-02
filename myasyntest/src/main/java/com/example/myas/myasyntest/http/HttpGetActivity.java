package com.example.myas.myasyntest.http;

import android.content.Context;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myas.myasyntest.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class HttpGetActivity extends AppCompatActivity {

    private Button btnAsyGet;
    private TextView txtView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_get);

        initView();

        btnAsyGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client = new AsyncHttpClient();

                client.get("https://www.baidu.com", getParames(), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        txtView.setText(new String(responseBody));
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
            }
        });
    }

    private RequestParams getParames() {
        RequestParams params = new RequestParams();
        params.put("user", "penngo");
        params.put("psw", "penngo");
        return params;
    }

    private void initView() {
        context = HttpGetActivity.this;
        btnAsyGet = (Button) findViewById(R.id.btnAsyGet);
        txtView = (TextView) findViewById(R.id.txtView);
    }
}
