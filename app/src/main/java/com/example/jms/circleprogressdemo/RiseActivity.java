package com.example.jms.circleprogressdemo;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.circleprogress.app.ArcCircleProgress;
import com.example.circleprogress.app.RiseCircleProgress;

/**
 * Created by Administrator on 4/18 0018.
 */
public class RiseActivity extends AppCompatActivity {

    private RiseCircleProgress acp0;
    private RiseCircleProgress acp1;
    private RiseCircleProgress acp2;
    private RiseCircleProgress acp3;
    private RiseCircleProgress acp4;
    private RiseCircleProgress acp5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rise);

        acp0 = (RiseCircleProgress) findViewById(R.id.cpa0);
        acp1 = (RiseCircleProgress) findViewById(R.id.cpa1);
        acp2 = (RiseCircleProgress) findViewById(R.id.cpa2);
        acp3 = (RiseCircleProgress) findViewById(R.id.cpa3);
        acp4 = (RiseCircleProgress) findViewById(R.id.cpa4);
        acp5 = (RiseCircleProgress) findViewById(R.id.cpa5);

        acp1.setIsMove(false);
        acp1.setCurrentProgress(60);

        acp2.setMaxProgress(200);
        acp2.setTextUnit(ArcCircleProgress.Unit.PERCENT);
        acp2.setBackgroundColor(Color.parseColor("#ee33cc"));
        acp2.setProgressStyle(Paint.Style.STROKE,20f);

        acp3.setBackgroundColor(Color.parseColor("#0ff00f"));
        acp3.setBackgroundStrokeWidth(20f);
        acp3.setStartAngle(60);

        acp4.setProgressColor(Color.parseColor("#ff0033"));
        acp4.setProgressStrokeCap(Paint.Cap.ROUND);

        acp5.setStartAngle(60);
        acp5.setProgressStyle(Paint.Style.STROKE,20);
        acp5.setProgressStrokeCap(Paint.Cap.ROUND);

    }
}
