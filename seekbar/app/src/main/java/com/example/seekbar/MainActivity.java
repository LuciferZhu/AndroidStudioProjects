package com.example.seekbar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private TextView tv_one = null;
    private TextView tv_two = null;
    private SeekBar sb_one = null;
    private SeekBar sb_two = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sb_one.setOnSeekBarChangeListener(this);
        sb_two.setOnSeekBarChangeListener(this);
    }

    private void initView() {
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        sb_one = (SeekBar) findViewById(R.id.sb_one);
        sb_two = (SeekBar) findViewById(R.id.sb_two);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (seekBar.getId() == R.id.sb_one) {
            tv_one.setText("拖动条1当前的进度:"+progress);
        } else {
            tv_two.setText("拖动条2当前的进度:"+progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.sb_one) {
            tv_one.setTextColor(Color.GREEN);
        } else {
            tv_two.setTextColor(Color.GREEN);
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.sb_one) {
            tv_one.setTextColor(Color.GRAY);
        } else {
            tv_two.setTextColor(Color.GRAY);
        }
    }
}
