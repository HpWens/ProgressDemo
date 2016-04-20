package com.example.circleprogress.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.example.circleprogress.unit.DensityUtil;

/**
 * Created by Administrator on 4/19 0019.
 */
public class ArcCircleProgress extends BaseCircleProgress {

    private float maxAngle;
    private String arcText;
    private float progressAndUnitSpacing;
    private float arcStartAngle;

    private int arcUnitColor;
    private int arcTextColor;
    private int arcProgressColor;

    public ArcCircleProgress(Context context) {
        this(context, null);
    }

    public ArcCircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcCircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        backgroundPaint.setStyle(Paint.Style.STROKE);
        arcText = "temp";
        arcUnitColor = arcTextColor = arcProgressColor = textPaint.getColor();
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
        if (isMove)
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
            } else {
                if (mAddListener != null) {
                    mAddListener.OnProgressEndListener();
                }
            }
    }

    /**
     * 需要注意的是方法里面数字比较多
     * <p/>
     * 主要是测试合适字体的大小
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent; //文字的高度
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(arcTextColor);
        textPaint.setTextSize(DensityUtil.dip2px(ctx, circleRadius / 6));
        canvas.drawText(arcText, circleCenterX, circleCenterY + circleRadius, textPaint);

        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(arcProgressColor);
        textPaint.setTextSize(DensityUtil.dip2px(ctx, circleRadius / 4));
        canvas.drawText(currentProgress + "", circleCenterX, circleCenterY + (fontHeight / 2), textPaint);

        if (currentProgress < 10) {  //小于10
            progressAndUnitSpacing = circleRadius / 4;  //半径的1/4
        } else if (currentProgress >= 10 && currentProgress < 100) { //大于10并且小于100
            progressAndUnitSpacing = circleRadius / 2; //半径的1/2
        } else {
            progressAndUnitSpacing = circleRadius / 3 * 2;//半径的2/3
        }
        textPaint.setColor(arcUnitColor);
        textPaint.setTextSize(DensityUtil.dip2px(ctx, circleRadius / 12));
        canvas.drawText(textUnit, circleCenterX + progressAndUnitSpacing, circleCenterY - (fontHeight / 2), textPaint);
    }

    private void drawArc(Canvas canvas) {
        RectF mRectF = new RectF(circleCenterX - circleRadius, circleCenterY - circleRadius, circleCenterX + circleRadius, circleCenterY + circleRadius);
        if (currentProgress != 0) {
            canvas.drawArc(mRectF, arcStartAngle, (maxAngle / maxProgress) * currentProgress, false, progressPaint);
        }
    }

    private void drawBackground(Canvas canvas) {
        arcStartAngle = startAngle + 130f;  //130测试最佳距离
        maxAngle = ROUND_ANGLE - Math.abs(arcStartAngle - ROUND_ANGLE / 4) * 2;
        RectF mRectF = new RectF(circleCenterX - circleRadius, circleCenterY - circleRadius, circleCenterX + circleRadius, circleCenterY + circleRadius);
        canvas.drawArc(mRectF, arcStartAngle, maxAngle, false, backgroundPaint);
    }

    /**
     * 设置文字
     *
     * @param text
     */
    public void setArcText(String text) {
        this.arcText = text;
    }

    /**
     * 设置文字颜色集合
     * 0 文字颜色
     * 1 进度条数字颜色
     * 2 单位颜色
     *
     * @param colors
     */
    public void setTextColorArr(int... colors) {
        if (colors != null) {
            if (colors.length == 1) {
                arcTextColor = colors[0];
            } else if (colors.length == 2) {
                arcTextColor = colors[0];
                arcProgressColor = colors[1];
            } else {
                arcTextColor = colors[0];
                arcProgressColor = colors[1];
                arcUnitColor = colors[2];
            }
        }
    }

}
