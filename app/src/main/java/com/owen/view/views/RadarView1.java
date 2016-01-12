package com.owen.view.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 雷达图
 * Created by Owen on 2016/1/12.
 */
public class RadarView1 extends View {

    private Paint mPaint;
    private Path mPath;
    private float mAngel = (float) (2 * Math.PI / 6);

    public RadarView1(Context context) {
        this(context, null);
    }

    public RadarView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(2F);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        canvas.translate(getWidth() / 2, getHeight() / 2);

        float r = 100F;

        mPath.moveTo(r, 0);
        for (int i = 1; i < 6; i++) {
            float x = (float) (Math.cos(mAngel * i) * r);
            float y =(float) (Math.sin(mAngel * i) * r);
            mPath.lineTo(x, y);
        }

        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }
}
