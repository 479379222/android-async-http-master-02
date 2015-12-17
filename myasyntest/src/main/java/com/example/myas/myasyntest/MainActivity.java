package com.example.myas.myasyntest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private Button btnHttpTest,btnLstTest,btnService;
    private TextView txtResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();
        btnHttpTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client = new AsyncHttpClient();
                /*client.get("https://www.baidu.com", new AsyncHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        Toast.makeText(MainActivity.this,"OnStart",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(MainActivity.this,"OnSuccess",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(MainActivity.this,"OnFailure",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        super.onRetry(retryNo);
                        Toast.makeText(MainActivity.this,"OnRetry",Toast.LENGTH_SHORT).show();
                    }
                });*/

                client.get("https://www.baidu.com", getParames(), new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        //Toast.makeText(MainActivity.this,responseString,Toast.LENGTH_SHORT).show();
                        txtResponse.setText(responseString);
                    }
                });
            }
        });

        btnLstTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyListActivity.class));
            }
        });

        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyserviceActivity.class));
            }
        });
    }

    private RequestParams getParames(){
        RequestParams params = new RequestParams();
        params.put("user", "penngo");
        params.put("psw", "penngo");
        return params;
    }


    private void initView(){
        btnHttpTest=(Button)findViewById(R.id.btnHttpTest);
        btnLstTest=(Button)findViewById(R.id.btnTest);
        btnService=(Button)findViewById(R.id.btnService);
        txtResponse=(TextView)findViewById(R.id.txtResponse);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
