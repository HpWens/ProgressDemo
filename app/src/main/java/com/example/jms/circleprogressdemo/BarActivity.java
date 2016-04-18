package com.example.jms.circleprogressdemo;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.circleprogress.widget.BaseCircleProgress;
import com.example.circleprogress.widget.CircleProgressBar;

/**
 * Created by Administrator on 4/18 0018.
 */
public class BarActivity extends AppCompatActivity {

    private CircleProgressBar cpb0;
    private CircleProgressBar cpb1;
    private CircleProgressBar cpb2;
    private CircleProgressBar cpb3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar);
        cpb0 = (CircleProgressBar) findViewById(R.id.cpb0);
        cpb1 = (CircleProgressBar) findViewById(R.id.cpb1);
        cpb2 = (CircleProgressBar) findViewById(R.id.cpb2);
        cpb3 = (CircleProgressBar) findViewById(R.id.cpb3);

        cpb1.setBackgroundStyle(Paint.Style.STROKE);
        cpb1.setUnit(BaseCircleProgress.Unit.PERCENT);
        cpb1.setTextColor(Color.parseColor("#00FFFF"));
        cpb1.setBarColor(Color.parseColor("#FF9900"));

        cpb2.setBackgroundStyle(Paint.Style.STROKE);
        cpb2.setBarStoreWidth(20);
        cpb2.setTextColor(Color.parseColor("#00FF00"));
        cpb2.setBackgroundColor(Color.parseColor("#003399"));

        cpb3.setBackgroundStyle(Paint.Style.STROKE);
        cpb3.setBackgroundStoreWidth(20);
    }
}
