package com.example.circleprogress.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.circleprogress.unit.DensityUtil;

/**
 * Created by jms on 2016/4/15.
 */
public class CircleProgressBar extends BaseCircleProgress {

    private Paint backgroundPaint;  //背圆画笔
    private Paint barPaint;  //进度条画笔
    private Paint textPaint;  //进度文字画笔

    private int currentProgress;
    private int delayTime;
    private float textPercent;
    private int maxProgress;
    private boolean isAcc = true;//是否加速  默认加速

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init() {
        currentProgress = 0;
        textPercent = 0.5f;
        delayTime = DELAY_TIME;
        maxProgress = MAX_PROGRESS;

        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);   //抗锯齿
        backgroundPaint.setDither(true);    //防止抖动
        backgroundPaint.setColor(Color.parseColor("#cccccc"));
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setStrokeWidth(DensityUtil.dip2px(mContext, DEFAULT_STROKE_WIDTH));

        barPaint = new Paint();
        barPaint.setAntiAlias(true);   //抗锯齿
        barPaint.setDither(true);    //防止抖动
        barPaint.setColor(Color.parseColor("#4294EF"));
        barPaint.setStyle(Paint.Style.STROKE);
        barPaint.setStrokeWidth(DensityUtil.dip2px(mContext, DEFAULT_STROKE_WIDTH));

        textPaint = new Paint();
        textPaint.setAntiAlias(true);   //抗锯齿
        textPaint.setDither(true);    //防止抖动
        textPaint.setColor(Color.parseColor("#4294EF"));
        textPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);

        drawBar(canvas);

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
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    postInvalidate();
                }
            }, delayTime);
        }
    }

    /**
     * 画动态进度
     *
     * @param canvas
     */
    private void drawBar(Canvas canvas) {
        RectF f = new RectF(circleCenterX - circleRadius, circleCenterY - circleRadius, circleCenterX + circleRadius, circleCenterY + circleRadius);
        canvas.drawArc(f, 0f, ((float) currentProgress / MAX_PROGRESS) * ROUND_ANGLE, false, barPaint);
    }

    /**
     * 画文子
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
     * 设置是否加速
     *
     * @param mBool
     */
    public void setIsAcc(boolean mBool) {
        this.isAcc = mBool;
    }

    /**
     * 设置文字颜色
     *
     * @param color
     */
    public void setTextColor(int color) {
        textPaint.setColor(color);
    }

    /**
     * 设置最大进度
     *
     * @param maxProgress
     */
    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    /**
     * 设置最小进度
     *
     * @param minProgress
     */
    public void setMinProgress(int minProgress) {
        this.currentProgress = minProgress;
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
     * 设置背景空心圆的大小
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
     * 设置bar空心宽度大小
     *
     * @param width
     */
    public void setBarStoreWidth(int width) {
        barPaint.setStrokeWidth(DensityUtil.dip2px(mContext, width));
    }

    /**
     * 设置bar的颜色
     *
     * @param color
     */
    public void setBarColor(int color) {
        barPaint.setColor(color);
    }

    /**
     * 设置bar风格
     *
     * @param style
     */
    public void setBarStyle(Paint.Style style) {
        barPaint.setStyle(style);
    }
}
