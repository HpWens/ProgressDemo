package com.example.jms.circleprogressdemo;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.circleprogress.app.ArcCircleProgress;

/**
 * Created by Administrator on 4/18 0018.
 */
public class ArcActivity extends AppCompatActivity {

    private ArcCircleProgress acp0;
    private ArcCircleProgress acp1;
    private ArcCircleProgress acp2;
    private ArcCircleProgress acp3;
    private ArcCircleProgress acp4;
    private ArcCircleProgress acp5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arc);

        acp0 = (ArcCircleProgress) findViewById(R.id.cpa0);
        acp1 = (ArcCircleProgress) findViewById(R.id.cpa1);
        acp2 = (ArcCircleProgress) findViewById(R.id.cpa2);
        acp3 = (ArcCircleProgress) findViewById(R.id.cpa3);
        acp4 = (ArcCircleProgress) findViewById(R.id.cpa4);
        acp5 = (ArcCircleProgress) findViewById(R.id.cpa5);

        acp1.setIsMove(false);
        acp1.setCurrentProgress(60);
        acp1.setArcText("love");
        acp1.setTextColorArr(Color.parseColor("#0000ff"),Color.parseColor("#00ff00"),Color.parseColor("#aabbcc"));

        acp2.setMaxProgress(200);
        acp2.setTextUnit(ArcCircleProgress.Unit.PERCENT);
        acp2.setBackgroundColor(Color.parseColor("#ee33cc"));
        acp2.setProgressStyle(Paint.Style.STROKE,20f);

        acp3.setBackgroundStrokeWidth(20f);

        acp4.setProgressColor(Color.parseColor("#ff0033"));
        acp4.setProgressStrokeCap(Paint.Cap.ROUND);
        acp4.setStartAngle(60);

        acp5.setProgressStrokeCap(Paint.Cap.ROUND);
        acp5.setProgressStyle(Paint.Style.FILL, 0);
    }
}
