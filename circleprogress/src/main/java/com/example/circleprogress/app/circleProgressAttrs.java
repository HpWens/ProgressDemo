package com.example.circleprogress.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import com.example.circleprogress.R;

/**
 * Created by Administrator on 4/19 0019.
 */
public class CircleProgressAttrs {

    private int maxProgress;
    private int minProgress;
    private int backgroundColor;
    private int progressColor;
    private int textColor;
    private long delayTime;
    private boolean isMove;//静止界面 还是运动界面
    private boolean isAcc;//是否加速  默认加速
    //开始的圆心角
    private float startAngle;
    //移动的圆心角
    private float moveAngele;

    //最大进度   默认值
    private final static int MAX_PROGRESS = 100;

    public CircleProgressAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.circleProgress, defStyleAttr, 0);
        maxProgress = ta.getInteger(R.styleable.circleProgress_maxProgress, MAX_PROGRESS);
        minProgress = ta.getInteger(R.styleable.circleProgress_minProgress, 0);
        backgroundColor = ta.getColor(R.styleable.circleProgress_backgroundColor, Color.parseColor("#4363ae"));
        progressColor = ta.getColor(R.styleable.circleProgress_progressColor, Color.parseColor("#47eaf4"));
        textColor = ta.getColor(R.styleable.circleProgress_textColor, Color.parseColor("#4294EF"));
        delayTime = (long) ta.getFloat(R.styleable.circleProgress_delayTime, 100f);
        isMove = ta.getBoolean(R.styleable.circleProgress_isMove, true);
        isAcc = ta.getBoolean(R.styleable.circleProgress_isAcc, false);
        startAngle = ta.getFloat(R.styleable.circleProgress_startAngle, 0f);
        moveAngele = ta.getFloat(R.styleable.circleProgress_moveAngele, 0f);
        ta.recycle();
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public int getMinProgress() {
        return minProgress;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getProgressColor() {
        return progressColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public boolean isMove() {
        return isMove;
    }

    public boolean isAcc() {
        return isAcc;
    }

    public float getStartAngle() {
        return startAngle;
    }

    public float getMoveAngele() {
        return moveAngele;
    }
}
