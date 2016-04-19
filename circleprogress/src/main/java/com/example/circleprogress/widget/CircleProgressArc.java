package com.example.circleprogress.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.circleprogress.unit.DensityUtil;

/**
 * Created by jms on 2016/4/16.
 */
public class CircleProgressArc extends BaseCircleProgress {

    private Paint backgroundPaint;  //背圆画笔
    private Paint arcPaint;  //进度条画笔
    private Paint textPaint;  //进度文字画笔
    private Paint progressPaint;

    private int currentProgress;
    private int delayTime;
    private int maxProgress;
    private float progressSpacing;
    private float maxMoveAngle;
    private float startAngle;
    private String arcText = "temp";

    private final static float TEXT_PERCENT = 0.4f;
    private final static float PROGRESS_PERCENT = 0.7f;

    public final static float START_ANGLE = 120f;
    public final static float MAX_MOVE_ANGLE = 300f;

    public CircleProgressArc(Context context) {
        this(context, null);
    }

    public CircleProgressArc(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressArc(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void init() {
        delayTime = DELAY_TIME;
        currentProgress = 0;
        maxProgress = MAX_PROGRESS;
        startAngle = START_ANGLE;
        maxMoveAngle = MAX_MOVE_ANGLE;

        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);   //抗锯齿
        backgroundPaint.setDither(true);    //防止抖动
        backgroundPaint.setColor(Color.parseColor("#4363ae"));
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(DensityUtil.dip2px(mContext, DEFAULT_STROKE_WIDTH));
        backgroundPaint.setStrokeJoin(Paint.Join.ROUND);//在画笔的连接处是圆滑的
        backgroundPaint.setStrokeCap(Paint.Cap.ROUND);//在画笔的起始处是圆滑的  笔触
        backgroundPaint.setPathEffect(new CornerPathEffect(DEFAULT_STROKE_WIDTH));//画笔效果   转角处的圆滑程度

        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);   //抗锯齿
        arcPaint.setDither(true);    //防止抖动
        arcPaint.setColor(Color.parseColor("#47eaf4"));
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(DensityUtil.dip2px(mContext, DEFAULT_STROKE_WIDTH));
        arcPaint.setStrokeJoin(Paint.Join.ROUND);//在画笔的连接处是圆滑的
        arcPaint.setStrokeCap(Paint.Cap.ROUND);//在画笔的起始处是圆滑的  笔触
        arcPaint.setPathEffect(new CornerPathEffect(DEFAULT_STROKE_WIDTH));//画笔效果   转角处的圆滑程度

        textPaint = new Paint();
        textPaint.setAntiAlias(true);   //抗锯齿
        textPaint.setDither(true);    //防止抖动
        textPaint.setColor(Color.parseColor("#4294EF"));
        textPaint.setStyle(Paint.Style.FILL);

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);   //抗锯齿
        progressPaint.setDither(true);    //防止抖动
        progressPaint.setColor(Color.parseColor("#00FFFF"));
        progressPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);

        drawArc(canvas);

        drawText(canvas);

        updateProgress();

    }

    private void updateProgress() {
        if (currentProgress < maxProgress) {
            currentProgress++;
            delayTime--;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    postInvalidate();
                }
            }, delayTime);
        }else {
            if (mAddListener != null) {
                mAddListener.OnProgressEndListener();
            }
        }
    }

    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent; //文字的高度
        textPaint.setTextSize(circleRadius * TEXT_PERCENT);
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(arcText, circleCenterX, circleCenterY + circleRadius, textPaint);

        progressPaint.setTextAlign(Paint.Align.CENTER);
        progressPaint.setTextSize(circleRadius * PROGRESS_PERCENT);
        canvas.drawText(currentProgress + "", circleCenterX, circleCenterY + (fontHeight / 2), progressPaint);

        if (currentProgress < 10) {
            progressSpacing = circleRadius * PROGRESS_PERCENT * 0.40f;
        } else if (currentProgress >= 10 && currentProgress < MAX_PROGRESS) {
            progressSpacing = circleRadius * PROGRESS_PERCENT * 0.65f;
        } else {
            progressSpacing = circleRadius * PROGRESS_PERCENT * 0.90f;
        }
        canvas.drawText(mUnit, circleCenterX + progressSpacing, circleCenterY, textPaint);
    }

    private void drawArc(Canvas canvas) {
        RectF mRectF = new RectF(circleCenterX - circleRadius, circleCenterY - circleRadius, circleCenterX + circleRadius, circleCenterY + circleRadius);
        if (currentProgress != 0) {
            canvas.drawArc(mRectF, startAngle, (maxMoveAngle / MAX_PROGRESS) * currentProgress, false, arcPaint);
        }
    }

    private void drawBackground(Canvas canvas) {
        RectF mRectF = new RectF(circleCenterX - circleRadius, circleCenterY - circleRadius, circleCenterX + circleRadius, circleCenterY + circleRadius);
        canvas.drawArc(mRectF, startAngle, maxMoveAngle, false, backgroundPaint);
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
     * 设置字体颜色
     *
     * @param colorArr
     */
    public void setTextColor(Integer... colorArr) {
        if (colorArr != null) {
            if (colorArr.length == 1) {
                textPaint.setColor(colorArr[0]);
            } else if (colorArr.length >= 2) {
                textPaint.setColor(colorArr[0]);
                progressPaint.setColor(colorArr[1]);
            }
        }
    }

    /**
     * 设置文字
     *
     * @param text
     */
    public void setText(String text) {
        this.arcText = text;
    }

    /**
     * 最大进度
     *
     * @param maxProgress
     */
    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public void setMinProgress(int minProgress) {
        this.currentProgress = minProgress;
    }

    public void setBackgroundStoreWidth(int width) {
        backgroundPaint.setStrokeWidth(DensityUtil.dip2px(mContext, width));
    }

    public void setBackgroundColor(int color) {
        backgroundPaint.setColor(color);
    }

    public void setBackgroundStyle(Paint.Style style) {
        backgroundPaint.setStyle(style);
    }

    public void setArcStoreWidth(int width) {
        arcPaint.setStrokeWidth(DensityUtil.dip2px(mContext, width));
    }

    public void setArcColor(int color) {
        arcPaint.setColor(color);
    }

    public void setArcStyle(Paint.Style style) {
        arcPaint.setStyle(style);
    }


}
