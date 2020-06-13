package com.example.progressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar pgb = null;
    Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void onClick_update(View v) {
        int progress = pgb.getProgress();
        progress += 10;
        pgb.setProgress(progress);

        if (progress == 100) {
            Toast.makeText(MainActivity.this, "进度条更新完毕!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        pgb = (ProgressBar) findViewById(R.id.progress_One);
        btn = (Button) findViewById(R.id.button_one);
    }

    protected void on
}
