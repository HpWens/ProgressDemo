package com.example.circleprogress.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.example.circleprogress.unit.DensityUtil;

import java.lang.ref.WeakReference;

/**
 * Created by jms on 2016/4/15.
 */
public abstract class BaseCircleProgress extends View {

    protected Context mContext;
    protected MyHandler handler;

    protected int circleCenterX;
    protected int circleCenterY;
    protected int circleRadius;

    protected String mUnit = TEMP_UNIT;

    private final static float CIRCLE_PERCENT = 0.618f;
    protected final static float ROUND_ANGLE = 360f;
    protected final static int MAX_PROGRESS = 100;
    protected final static int DELAY_TIME = 100;
    protected final static String TEMP_UNIT = "℃";
    protected final static String PERCENT_UNIT = "%";
    protected final static float DEFAULT_STROKE_WIDTH = 10;

    public BaseCircleProgress(Context context) {
        this(context, null);
    }

    public BaseCircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseCircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        handler = new MyHandler(this);
        init();
    }

    public abstract void init();

    public enum Unit {
        TEMP, PERCENT;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width;
        int height;
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.EXACTLY) {
            height = heightSpecSize;
            width = Math.min(heightSpecSize, Math.min(DensityUtil.getScreenSize(mContext)[0], DensityUtil.getScreenSize(mContext)[1]));
        } else if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.AT_MOST) {
            width = widthSpecSize;
            height = Math.min(widthSpecSize, Math.min(DensityUtil.getScreenSize(mContext)[0], DensityUtil.getScreenSize(mContext)[1]));
        } else if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            width = height = Math.min(DensityUtil.getScreenSize(mContext)[0], DensityUtil.getScreenSize(mContext)[1]);
        } else {
            width = widthSpecSize;
            height = heightSpecSize;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        circleCenterX = w / 2;
        circleCenterY = h / 2;
        circleRadius = (int) (Math.min(circleCenterX, circleCenterY) * CIRCLE_PERCENT);
    }

    public void setCircleRadius(int radius) {
        this.circleRadius = radius;
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    protected static class MyHandler extends Handler {
        private WeakReference<BaseCircleProgress> activityWeakReference;

        public MyHandler(BaseCircleProgress circle) {
            activityWeakReference = new WeakReference<BaseCircleProgress>(circle);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseCircleProgress circle = activityWeakReference.get();
            if (circle == null) {
                return;
            }
        }
    }

    /**
     * 设置符号
     */
    public void setUnit(Unit mUnit) {
        switch (mUnit) {
            case TEMP:
                this.mUnit = TEMP_UNIT;
                break;
            case PERCENT:
                this.mUnit = PERCENT_UNIT;
                break;
        }
    }
}
