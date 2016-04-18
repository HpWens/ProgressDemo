package com.example.jms.circleprogressdemo;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.circleprogress.widget.BaseCircleProgress;
import com.example.circleprogress.widget.CircleProgressArc;

/**
 * Created by Administrator on 4/18 0018.
 */
public class ArcActivity extends AppCompatActivity {

    private CircleProgressArc cpa0;
    private CircleProgressArc cpa1;
    private CircleProgressArc cpa2;
    private CircleProgressArc cpa3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arc);
        cpa0 = (CircleProgressArc) findViewById(R.id.cpa0);
        cpa1 = (CircleProgressArc) findViewById(R.id.cpa1);
        cpa2 = (CircleProgressArc) findViewById(R.id.cpa2);
        cpa3 = (CircleProgressArc) findViewById(R.id.cpa3);

        cpa1.setBackgroundStyle(Paint.Style.STROKE);
        cpa1.setUnit(BaseCircleProgress.Unit.PERCENT);
        cpa1.setTextColor(Color.parseColor("#00FFFF"));
        cpa1.setArcColor(Color.parseColor("#FF9900"));
        cpa1.setText("idea");

        cpa2.setBackgroundStyle(Paint.Style.STROKE);
        cpa2.setArcStoreWidth(20);
        cpa2.setTextColor(Color.parseColor("#00FF00"));
        cpa2.setBackgroundColor(Color.parseColor("#003399"));
        cpa2.setTextColor(new Integer[]{Color.parseColor("#6699ff"), Color.parseColor("#00cc00")});

        cpa3.setBackgroundStyle(Paint.Style.STROKE);
        cpa3.setBackgroundStoreWidth(20);
    }
}
