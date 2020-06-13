package com.example.rythmic;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.EditText;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private EditText downEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void clickCommit(View v) {
        final String input = downEdit.getText().toString();
        final String answer = "如今坟草三丈五";

        if (input.equals(answer)) {
            //downEdit.setText(Html.fromHtml("<font color=\"green\">"+input+"</font>"));
            downEdit.setTextColor(Color.GREEN);
        } else {
            //downEdit.setText(Html.fromHtml("<font color=\"red\">"+input+"</font>"));
            downEdit.setTextColor(Color.RED);
        }
    }

    public void initView() {
        downEdit = (EditText) findViewById(R.id.edit_downsnt);
    }
}
