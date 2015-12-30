package com.owen.view.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {

    private Paint mPaintCircle;

    private Paint mPaintText;
    private String mTextStr;
    private Rect mBoundText;
    private int mTextWidth;
    private int mTextHeight;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle.setColor(ContextCompat.getColor(context, android.R.color.holo_blue_light));

        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setColor(ContextCompat.getColor(context, android.R.color.black));
        mPaintText.setTextSize(40F);
        mTextStr = "正正正正正正正正";
        mBoundText = new Rect();
        mPaintText.getTextBounds(mTextStr, 0, mTextStr.length(), mBoundText);
        mTextWidth = mBoundText.width();
        mTextHeight = mBoundText.height();

        mPaintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintLine.setColor(Color.RED);
        mPaintLine.setStrokeWidth(1F);
    }

    private int mWidth;
    private int mHeight;
    private float mRadius;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getWidth();
        mHeight = getHeight();
        mRadius = Math.min(mWidth / 2, mHeight / 2);
    }

    private Paint mPaintLine;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mPaintCircle);
        canvas.drawText(mTextStr, (mWidth - mTextWidth) / 2, (mHeight + mTextHeight) / 2, mPaintText);

        canvas.drawLine(mWidth / 2, (getHeight() - mRadius * 2) / 2,
                mWidth / 2, getHeight() - (getHeight() - mRadius * 2) / 2,
                mPaintLine);
        canvas.drawLine(0, mHeight / 2,
                mWidth, mHeight / 2, mPaintLine);
    }

}
