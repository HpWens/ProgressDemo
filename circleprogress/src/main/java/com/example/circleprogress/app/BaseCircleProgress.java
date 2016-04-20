package com.example.circleprogress.app;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.example.circleprogress.callback.AddListener;
import com.example.circleprogress.unit.DensityUtil;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 4/19 0019.
 */
public class BaseCircleProgress extends View {

    protected CircleHandler handler;
    protected Context ctx;

    protected float circleSize;
    protected float circleCenterX;
    protected float circleCenterY;
    protected float circleRadius;

    protected String textUnit; //默认的是比分比符号
    protected int maxProgress;
    //当前的进度
    protected int currentProgress;
    protected int backgroundColor;
    protected int progressColor;
    protected int textColor;
    protected long delayTime;
    protected boolean isMove;//静止界面 还是运动界面
    protected boolean isAcc;//是否加速  默认加速
    //开始的圆心角
    protected float startAngle;
    //移动的圆心角
    protected float moveAngle;
    //每次移动的圆心角度
    protected float everyMoveAngle;

    protected Paint backgroundPaint;  //背圆画笔
    protected Paint progressPaint;  //进度条画笔
    protected Paint textPaint;  //进度文字画笔

    private final static String TEMP_UNIT = "℃";
    private final static String PERCENT_UNIT = "%";
    //默认圆大小为  长宽最小值的比例
    private final static float CIRCLE_SIZE = 0.618f;
    //一个整圆的圆心角度
    protected final static float ROUND_ANGLE = 360f;

    private CircleProgressAttrs circleProgressAttrs;

    public enum Unit {
        TEMP, PERCENT;
    }

    //最大进度 监听
    protected AddListener mAddListener = null;

    public BaseCircleProgress(Context context) {
        this(context, null);
    }

    public BaseCircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseCircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.circleProgressAttrs = new CircleProgressAttrs(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.ctx = context;
        this.handler = new CircleHandler(this);
        this.circleSize = CIRCLE_SIZE;
        this.textUnit = TEMP_UNIT;
        this.currentProgress = circleProgressAttrs.getCurrentProgress();
        this.maxProgress = circleProgressAttrs.getMaxProgress();
        this.backgroundColor = circleProgressAttrs.getBackgroundColor();
        this.progressColor = circleProgressAttrs.getProgressColor();
        this.textColor = circleProgressAttrs.getTextColor();
        this.delayTime = circleProgressAttrs.getDelayTime();
        this.isMove = circleProgressAttrs.isMove();
        this.isAcc = circleProgressAttrs.isAcc();
        this.startAngle = circleProgressAttrs.getStartAngle();
        this.initPaint();
    }

    private void initPaint() {
        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);   //抗锯齿
        backgroundPaint.setDither(true);    //防止抖动
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setStrokeJoin(Paint.Join.ROUND);//在画笔的连接处是圆滑的
        backgroundPaint.setStrokeCap(Paint.Cap.ROUND);//在画笔的起始处是圆滑的  笔触
        backgroundPaint.setStrokeWidth(DensityUtil.dip2px(ctx, 15));
        //backgroundPaint.setPathEffect(new CornerPathEffect(10));//画笔效果   转角处的圆滑程度

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);   //抗锯齿
        progressPaint.setDither(true);    //防止抖动
        progressPaint.setColor(progressColor);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeJoin(Paint.Join.ROUND);//在画笔的连接处是圆滑的
        progressPaint.setStrokeCap(Paint.Cap.ROUND);//在画笔的起始处是圆滑的  笔触
        progressPaint.setStrokeWidth(DensityUtil.dip2px(ctx, 15));
        //progressPaint.setPathEffect(new CornerPathEffect(10));//画笔效果   转角处的圆滑程度

        textPaint = new Paint();
        textPaint.setAntiAlias(true);   //抗锯齿
        textPaint.setDither(true);    //防止抖动
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(DensityUtil.dip2px(ctx, 30));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        circleCenterX = w / 2;
        circleCenterY = h / 2;
        circleRadius = (int) (Math.min(circleCenterX, circleCenterY) * circleSize);
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
            width = Math.min(heightSpecSize, Math.min(DensityUtil.getScreenSize(ctx)[0], DensityUtil.getScreenSize(ctx)[1]));
        } else if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.AT_MOST) {
            width = widthSpecSize;
            height = Math.min(widthSpecSize, Math.min(DensityUtil.getScreenSize(ctx)[0], DensityUtil.getScreenSize(ctx)[1]));
        } else if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            width = height = Math.min(DensityUtil.getScreenSize(ctx)[0], DensityUtil.getScreenSize(ctx)[1]);
        } else {
            width = widthSpecSize;
            height = heightSpecSize;
        }
        setMeasuredDimension(width, height);
    }

    /**
     * 设备背景颜色
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
     * @param width
     */
    public void setBackgroundStyle(Paint.Style style, float width) {
        backgroundPaint.setStyle(style);
        if (style == Paint.Style.STROKE) {
            backgroundPaint.setStrokeWidth(DensityUtil.dip2px(ctx, width));
        }
    }

    /**
     * 设置背景空心 宽度
     *
     * @param width
     */
    public void setBackgroundStrokeWidth(float width) {
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(DensityUtil.dip2px(ctx, width));
    }

    /**
     * 设置背景 的笔触类型
     *
     * @param cap
     */
    public void setBackgroundStrokeCap(Paint.Cap cap) {
        backgroundPaint.setStrokeCap(cap);
    }

    /**
     * 设置进度条 的笔触类型
     *
     * @param cap
     */
    public void setProgressStrokeCap(Paint.Cap cap) {
        progressPaint.setStrokeCap(cap);
    }

    /**
     * 设置进度条颜色
     *
     * @param color
     */
    public void setProgressColor(int color) {
        progressPaint.setColor(color);
    }

    /**
     * 设置进度条风格
     *
     * @param style
     * @param width
     */
    public void setProgressStyle(Paint.Style style, float width) {
        progressPaint.setStyle(style);
        if (style == Paint.Style.STROKE) {
            progressPaint.setStrokeWidth(DensityUtil.dip2px(ctx, width));
        }
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
     * 设置文字大小
     *
     * @param size
     */
    public void setTextSize(float size) {
        textPaint.setTextSize(DensityUtil.dip2px(ctx, size));
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
     * 设置当前进度
     *
     * @param currentProgress
     */
    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    /**
     * 设置开始角度
     *
     * @param angle
     */
    public void setStartAngle(float angle) {
        this.startAngle = angle;
    }


    /**
     * 是否是静止界面  还是动态界面
     *
     * @param isMove
     */
    public void setIsMove(boolean isMove) {
        this.isMove = isMove;
    }

    /**
     * 设置文本单位符号
     */
    public void setTextUnit(Unit mUnit) {
        switch (mUnit) {
            case TEMP:
                this.textUnit = TEMP_UNIT;
                break;
            case PERCENT:
                this.textUnit = PERCENT_UNIT;
                break;
        }
    }

    /**
     * @param size 0.0f~1.0f
     */
    public void setCircleSize(float size) {
        this.circleSize = size;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public static class CircleHandler extends Handler {
        private WeakReference<BaseCircleProgress> viewWeakReference;

        public CircleHandler(BaseCircleProgress circle) {
            viewWeakReference = new WeakReference<BaseCircleProgress>(circle);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseCircleProgress circle = viewWeakReference.get();
            if (circle == null) {
                return;
            }
        }
    }
}
