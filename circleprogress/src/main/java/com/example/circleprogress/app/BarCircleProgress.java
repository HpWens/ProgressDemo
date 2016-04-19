package com.example.circleprogress.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by Administrator on 4/19 0019.
 */
public class BarCircleProgress extends BaseCircleProgress {

    public BarCircleProgress(Context context) {
        this(context, null);
    }

    public BarCircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarCircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        progressPaint.setStrokeCap(Paint.Cap.BUTT);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);

        drawProgress(canvas);

        drawText(canvas);

        updateProgress();
    }

    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent; //文字的高度
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(currentProgress + textUnit, circleCenterX, circleCenterY + (fontHeight / 4), textPaint);
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

    private void drawProgress(Canvas canvas) {
        RectF rect = new RectF(circleCenterX - circleRadius, circleCenterY - circleRadius, circleCenterX + circleRadius, circleCenterY + circleRadius);
        canvas.drawArc(rect, startAngle, (currentProgress / (float) maxProgress) * ROUND_ANGLE, false, progressPaint);
    }

    private void drawBackground(Canvas canvas) {
        canvas.drawCircle(circleCenterX, circleCenterY, circleRadius, backgroundPaint);
    }
}
