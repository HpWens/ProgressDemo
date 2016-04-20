package com.example.jms.circleprogressdemo;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.circleprogress.app.ArcCircleProgress;
import com.example.circleprogress.app.BarCircleProgress;

/**
 * Created by Administrator on 4/18 0018.
 */
public class BarActivity extends AppCompatActivity {

    private BarCircleProgress acp0;
    private BarCircleProgress acp1;
    private BarCircleProgress acp2;
    private BarCircleProgress acp3;
    private BarCircleProgress acp4;
    private BarCircleProgress acp5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar);

        acp0 = (BarCircleProgress) findViewById(R.id.bcp0);
        acp1 = (BarCircleProgress) findViewById(R.id.bcp1);
        acp2 = (BarCircleProgress) findViewById(R.id.bcp2);
        acp3 = (BarCircleProgress) findViewById(R.id.bcp3);
        acp4 = (BarCircleProgress) findViewById(R.id.bcp4);
        acp5 = (BarCircleProgress) findViewById(R.id.bcp5);

        acp1.setIsMove(false);
        acp1.setCurrentProgress(60);

        acp2.setMaxProgress(200);
        acp2.setTextUnit(ArcCircleProgress.Unit.PERCENT);
        acp2.setBackgroundColor(Color.parseColor("#ee33cc"));
        acp2.setProgressStyle(Paint.Style.STROKE,20f);

        acp3.setBackgroundStrokeWidth(20f);
        acp3.setBackgroundColor(Color.parseColor("#0ff00f"));

        acp4.setProgressColor(Color.parseColor("#ff0033"));
        acp4.setProgressStrokeCap(Paint.Cap.ROUND);


        acp5.setProgressStrokeCap(Paint.Cap.ROUND);
        acp5.setProgressStyle(Paint.Style.FILL, 0);

    }
}
