package com.example.jms.circleprogressdemo;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.circleprogress.widget.BaseCircleProgress;
import com.example.circleprogress.widget.CircleProgressRise;

/**
 * Created by Administrator on 4/18 0018.
 */
public class RiseActivity extends AppCompatActivity {

    private CircleProgressRise cpr0;
    private CircleProgressRise cpr1;
    private CircleProgressRise cpr2;
    private CircleProgressRise cpr3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rise);
        cpr0 = (CircleProgressRise) findViewById(R.id.cpr0);
        cpr1 = (CircleProgressRise) findViewById(R.id.cpr1);
        cpr2 = (CircleProgressRise) findViewById(R.id.cpr2);
        cpr3 = (CircleProgressRise) findViewById(R.id.cpr3);

        cpr1.setBackgroundStyle(Paint.Style.STROKE);
        cpr1.setUnit(BaseCircleProgress.Unit.PERCENT);
        cpr1.setTextColor(Color.parseColor("#00FFFF"));
        cpr1.setRiseColor(Color.parseColor("#FF9900"));

        cpr2.setBackgroundStyle(Paint.Style.STROKE);
        cpr2.setRiseStyle(Paint.Style.STROKE);
        cpr2.setRiseStoreWidth(20);
        cpr2.setTextColor(Color.parseColor("#00FF00"));
        cpr2.setBackgroundColor(Color.parseColor("#003399"));

        cpr3.setBackgroundStyle(Paint.Style.STROKE);
        cpr3.setBackgroundStoreWidth(20);
        cpr3.setRiseStyle(Paint.Style.STROKE);

    }
}
