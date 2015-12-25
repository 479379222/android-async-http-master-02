package com.example.myas.myasyntest.http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myas.myasyntest.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;

public class CookieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie);

        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);

        BasicClientCookie newCookie = new BasicClientCookie("myCookie", "jason");
        newCookie.setVersion(1);
        newCookie.setDomain("www.fubosoft.com");
        newCookie.setPath("/");

        client.setCookieStore(myCookieStore);

    }
}
