package com.example.activities_comm;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv = null;
    final static int req_code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
    }

    public void onclick_add(View e) {
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        startActivityForResult(intent, req_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == req_code && requestCode == 1) {
            tv.setText(tv.getText().toString() + data.getExtras().getString("EditText"));
        }
    }
}
