package com.example.myas.myasyntest.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myas.myasyntest.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;

import cz.msebera.android.httpclient.Header;

public class UploadingActivity extends AppCompatActivity {

    private Button btnUpload;
    private ImageView imgView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploading);

        initView();
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client=new AsyncHttpClient();
                client.get("http://www.bz55.com/uploads/allimg/130328/1-13032Q15S7.jpg", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        BitmapFactory factory=new BitmapFactory();
                        Bitmap bitmap=factory.decodeByteArray(responseBody,0,responseBody.length);
                        imgView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });

            }
        });
    }

    private void initView() {
        context = UploadingActivity.this;
        btnUpload = (Button) findViewById(R.id.btnUpload);
        imgView = (ImageView) findViewById(R.id.imgView);
    }
}
