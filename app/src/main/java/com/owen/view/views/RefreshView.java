package com.owen.view.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Owen on 2015/12/28.
 */
public class RefreshView extends View {

    public RefreshView(Context context) {
        this(context, null);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private Paint mPaint;

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(ContextCompat.getColor(getContext(), android.R.color.holo_red_light));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5F);

        mPaintReferenceLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintReferenceLine.setColor(Color.BLACK);
        mPaintReferenceLine.setStrokeWidth(1F);
    }

    private float mRadius;
    private Paint mPaintReferenceLine;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getWidth();
        int height = getHeight();

        mRadius = Math.min(width / 2, height / 2) - 80;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.drawColor(Color.WHITE);

        // 垂直参考线
        canvas.drawLine(0, -mRadius, 0, mRadius, mPaintReferenceLine);
        // 水平参考线
        canvas.drawLine(-mRadius, 0, mRadius, 0, mPaintReferenceLine);

        canvas.drawCircle(0, 0, mRadius, mPaint);


    }
}
