package com.owen.view.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 可以旋转的太极图
 * <p/>
 * Created by Owen on 2015/12/28.
 */
public class TaiJiView extends View {

    /**
     * 黑色画笔
     */
    private Paint mPaintBlack;

    /**
     * 白色画笔
     */
    private Paint mPaintWhite;

    /**
     * 半径
     */
    private float mRadius;

    /**
     * 圆弧的外轮廓矩形区域
     */
    private RectF mOval;

    /**
     * 圆心点
     */
    private Point mPointCenter;

    public TaiJiView(Context context) {
        this(context, null);
    }

    public TaiJiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaintBlack = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBlack.setColor(Color.BLACK);

        mPaintWhite = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintWhite.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getWidth();
        int height = getHeight();
        mRadius = Math.min(width / 2, height / 2) - 100;

        mPointCenter = new Point(width / 2, height / 2);

        mOval = new RectF(-mRadius, -mRadius, mRadius, mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mPointCenter.x, mPointCenter.y);
        canvas.drawColor(Color.GRAY);
        canvas.rotate(mDegree);

        canvas.drawArc(mOval, 90F, 180F, true, mPaintBlack);
        canvas.drawArc(mOval, -90F, 180F, true, mPaintWhite);

        canvas.drawCircle(0, -mRadius / 2, mRadius / 2, mPaintBlack);
        canvas.drawCircle(0, mRadius / 2, mRadius / 2, mPaintWhite);


        canvas.drawCircle(0, -mRadius / 2, mRadius / 8, mPaintWhite);
        canvas.drawCircle(0, mRadius / 2, mRadius / 8, mPaintBlack);

        mDegree += 2;
        invalidate();
    }

    /**
     * 旋转角度
     */
    private float mDegree;

}
