package com.example.myas.myasyntest.http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myas.myasyntest.R;

public class DeleteActivity extends AppCompatActivity {

    private Button btnDelete;
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        initView();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initView() {
        btnDelete = (Button) findViewById(R.id.btnDelete);
        txtView = (TextView) findViewById(R.id.txtView);
    }
}
