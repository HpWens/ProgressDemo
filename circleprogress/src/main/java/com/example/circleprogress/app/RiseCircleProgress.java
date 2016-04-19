package com.example.circleprogress.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by Administrator on 4/19 0019.
 */
public class RiseCircleProgress extends BaseCircleProgress {

    private boolean isRoundCircle;

    public RiseCircleProgress(Context context) {
        this(context, null);
    }

    public RiseCircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RiseCircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        startAngle = 90f;
        progressPaint.setStyle(Paint.Style.FILL);
        progressPaint.setStrokeCap(Paint.Cap.BUTT);
        isRoundCircle = false;
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
                startAngle -= everyMoveAngle;
                moveAngle += everyMoveAngle * 2;
                isRoundCircle = false;
                if (moveAngle >= ROUND_ANGLE) {
                    isRoundCircle = true;
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
        if (isRoundCircle) {
            canvas.drawCircle(circleCenterX, circleCenterY, circleRadius, progressPaint);
        } else {
            Path mPath = new Path();
            RectF rectF = new RectF(circleCenterX - circleRadius, circleCenterY - circleRadius, circleCenterX + circleRadius, circleCenterY + circleRadius);
            mPath.arcTo(rectF, startAngle, moveAngle);
            canvas.drawPath(mPath, progressPaint);
        }
    }

    private void drawBackground(Canvas canvas) {
        canvas.drawCircle(circleCenterX, circleCenterY, circleRadius, backgroundPaint);
    }
}
