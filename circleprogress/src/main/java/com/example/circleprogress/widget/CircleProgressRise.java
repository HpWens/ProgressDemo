package com.example.circleprogress.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.circleprogress.unit.DensityUtil;
import com.example.circleprogress.widget.BaseCircleProgress;

/**
 * Created by jms on 2016/4/15.
 */
public class CircleProgressRise extends BaseCircleProgress {

    private Paint backgroundPaint;  //背圆画笔
    private Paint risePaint;  //进度条画笔
    private Paint textPaint;  //进度文字画笔

    private int currentProgress;
    private int delayTime;
    private float textPercent;
    private int maxProgress;

    private float startAngle = 90;
    private float moveAngle = 0;
    private float everyMoveAngle = ROUND_ANGLE / 100 / 2;
    private boolean isAcc = true;//是否加速

    public CircleProgressRise(Context context) {
        this(context, null);
    }

    public CircleProgressRise(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressRise(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init() {
        delayTime = DELAY_TIME;
        currentProgress = 0;
        textPercent = 1 / 2f;
        maxProgress = MAX_PROGRESS;

        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);   //抗锯齿
        backgroundPaint.setDither(true);    //防止抖动
        backgroundPaint.setColor(Color.parseColor("#cccccc"));
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setStrokeWidth(DensityUtil.dip2px(mContext, DEFAULT_STROKE_WIDTH));

        risePaint = new Paint();
        risePaint.setAntiAlias(true);   //抗锯齿
        risePaint.setDither(true);    //防止抖动
        risePaint.setColor(Color.parseColor("#4294EF"));
        risePaint.setStyle(Paint.Style.FILL);
        risePaint.setStrokeWidth(DensityUtil.dip2px(mContext, DEFAULT_STROKE_WIDTH));

        textPaint = new Paint();
        textPaint.setAntiAlias(true);   //抗锯齿
        textPaint.setDither(true);    //防止抖动
        textPaint.setColor(Color.parseColor("#FFFFFF"));
        textPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);

        drawRise(canvas);

        drawText(canvas);

        updateProgress();

    }

    /**
     * 更新进度条
     */
    private void updateProgress() {
        if (currentProgress < maxProgress) {
            currentProgress++;
            if (isAcc) {
                delayTime--;
            }
            startAngle -= everyMoveAngle;
            moveAngle += everyMoveAngle * 2;
            if (moveAngle >= ROUND_ANGLE) {
                moveAngle = 359.99f;
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    postInvalidate();
                }
            }, delayTime);
        }
    }

    /**
     * 画文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent; //文字的高度
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(circleRadius * textPercent);
        canvas.drawText(currentProgress + mUnit, circleCenterX, circleCenterY + (fontHeight / 4), textPaint);
    }

    /**
     * @param canvas
     */
    private void drawRise(Canvas canvas) {
        Path mPath = new Path();
        RectF f = new RectF(circleCenterX - circleRadius, circleCenterY - circleRadius, circleCenterX + circleRadius, circleCenterY + circleRadius);
        mPath.arcTo(f, startAngle, moveAngle);
        canvas.drawPath(mPath, risePaint);
    }

    /**
     * 画背景
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {
        canvas.drawCircle(circleCenterX, circleCenterY, circleRadius, backgroundPaint);
    }

    /**
     * 每次移动的延迟时间
     *
     * @param delayTime
     */
    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    /**
     * 文字设置颜色
     *
     * @param color
     */
    public void setTextColor(int color) {
        textPaint.setColor(color);
    }

    /**
     * 设置最大的进度
     *
     * @param maxProgress
     */
    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }


    /**
     * 设置最小的进度
     *
     * @param minProgress
     */
    public void setMinProgress(int minProgress) {
        this.currentProgress = minProgress;
        this.startAngle -= minProgress * everyMoveAngle;
        moveAngle += minProgress * 2 * everyMoveAngle;
    }

    /**
     * percent  0到1
     *
     * @param percent
     */
    public void setTextSize(float percent) {
        this.textPercent = percent;
    }

    /**
     * 设置背景空心的大小
     *
     * @param width
     */
    public void setBackgroundStoreWidth(int width) {
        backgroundPaint.setStrokeWidth(DensityUtil.dip2px(mContext, width));
    }

    /**
     * 设置背景颜色
     *
     * @param color
     */
    public void setBackgroundColor(int color) {
        backgroundPaint.setColor(color);
    }

    /**
     * 设置背景风格
     *
     * @param style
     */
    public void setBackgroundStyle(Paint.Style style) {
        backgroundPaint.setStyle(style);
    }

    /**
     * 设置动圆的颜色
     *
     * @param color
     */
    public void setRiseColor(int color) {
        risePaint.setColor(color);
    }

    /**
     * 设置动圆的风格
     *
     * @param style
     */
    public void setRiseStyle(Paint.Style style) {
        risePaint.setStyle(style);
    }

    /***
     * 设置动圆空心大小
     *
     * @param width
     */
    public void setRiseStoreWidth(int width) {
        risePaint.setStrokeWidth(DensityUtil.dip2px(mContext, width));
    }

}
