package com.example.activities_comm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    EditText et = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        et = (EditText) findViewById(R.id.et);
    }

    public void onclick_addfinish(View v) {
        Intent intent = getIntent();
        intent.putExtra("EditText", et.getText().toString());
        setResult(1, intent);
        finish();
    }

}
