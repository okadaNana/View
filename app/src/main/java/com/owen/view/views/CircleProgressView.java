package com.owen.view.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * 圆形进度条
 * <p/>
 * Created by Owen on 2015/12/24.
 */
public class CircleProgressView extends View {

    private Paint mPaintInner;

    private Paint mPaintOuter;

    private Paint mPaintText;
    private String mTextStr;
    private float mTextHeight;
    private float mTextWidth;

    private Paint mPaintHorizontalLine;

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaintOuter = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintOuter.setStyle(Paint.Style.FILL);
        mPaintOuter.setColor(ContextCompat.getColor(context, android.R.color.holo_orange_light));

        mPaintInner = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintInner.setStyle(Paint.Style.STROKE);
        mPaintInner.setStrokeWidth(20F);
        mPaintInner.setColor(ContextCompat.getColor(context, android.R.color.holo_blue_light));

        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setTextSize(40F);
        mTextStr = "国国国国国国国";
        Rect boundText = new Rect();
        mPaintText.getTextBounds(mTextStr, 0, mTextStr.length(), boundText);
        mTextHeight = boundText.height();
        mTextWidth = boundText.width();

        mPaintHorizontalLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintHorizontalLine.setColor(ContextCompat.getColor(context, android.R.color.holo_red_light));
        mPaintHorizontalLine.setStrokeWidth(1F);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int center = getWidth() / 2;
        canvas.drawCircle(center, center, 200F, mPaintOuter);

        canvas.drawCircle(center, center, 200F, mPaintInner);

        canvas.drawLine(0F, center, getWidth(), center, mPaintHorizontalLine);

        canvas.drawText(mTextStr, center - mTextWidth / 2, center + mTextHeight / 2, mPaintText);
    }

}
