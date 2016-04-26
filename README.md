# ProgressDemo

![bar](https://github.com/HpWens/ProgressDemo/blob/master/app/src/main/assets/a.gif)

![rise](https://github.com/HpWens/ProgressDemo/blob/master/app/src/main/assets/b.gif)

##Usage

###gradle构建

```
dependencies {
    compile 'com.github.ws.circleprogress:circleprogress:2.1.5'
}
```

###Maven

```
<dependency>
  <groupId>com.github.ws.circleprogress</groupId>
  <artifactId>circleprogress</artifactId>
  <version>2.1.5</version>
  <type>pom</type>
</dependency>
```

###eclipse开发工具

请下载源码，导入circleprogress包

###xml布局中申明

```
            <com.example.circleprogress.app.BarCircleProgress
             xmlns:app="http://schemas.android.com/apk/res-auto"
                android:id="@+id/bcp"
                app:backgroundColor="#0ff000" 
                android:layout_width="200dp"
                android:layout_height="200dp" />
```

###Activity文件

```
 bcp= (BarCircleProgress) findViewById(R.id.bcp);
```

![rise](https://github.com/HpWens/ProgressDemo/blob/master/app/src/main/assets/c.png)

静止的进度条：

```
    <com.example.circleprogress.app.BarCircleProgress
        android:id="@+id/bcp"
        android:layout_width="200dp"
        android:layout_height="200dp"
        app:backgroundColor="#0ff000"
        app:currentProgress="20"
        app:isMove="false" />
```

##Attribute

```
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
```

更多分享，请关注我的博客http://blog.csdn.net/u012551350/article/details/51179957




