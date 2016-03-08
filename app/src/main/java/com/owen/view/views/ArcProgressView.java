package com.owen.view.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.owen.view.utils.DensityUtils;

/**
 * Created by mike on 16/3/8.
 */
public class ArcProgressView extends View {

    /** 画笔 */
    private Paint mPaint;
    private RectF mOval;

    /** 进度背景色 */
    private int mUnfinishedColor;
    /** 进度前景色 */
    private int mFinishedColor;
    /** 画笔宽度 */
    private float mStrokeWidth;

    /** 控件宽度 */
    private int mWidth;
    /** 控件高度 */
    private int mHeight;

    /** 进度 */
    private int mProgress;
    /** 最大进度 */
    private int mMaxProgress = 100;
    /** 进度的最大扫过角度 */
    private float mArcAngle = 360 * 0.8F;

    public ArcProgressView(Context context) {
        this(context, null);
    }

    public ArcProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(Context context) {
        mUnfinishedColor = Color.rgb(72, 106, 176);
        mFinishedColor = Color.WHITE;
        mStrokeWidth = DensityUtils.getInstance(context).sp2px(4F);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mOval = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = (int) (w - mStrokeWidth / 2);
        mHeight = (int) (h - mStrokeWidth / 2);

        mOval.set(mStrokeWidth / 2, mStrokeWidth / 2, mWidth - mStrokeWidth / 2, mHeight - mStrokeWidth / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float startAngle = 270 - mArcAngle / 2f;
        float mFinishedSeepAngle = mProgress / (float) mMaxProgress * mArcAngle;

        mPaint.setColor(mUnfinishedColor);
        canvas.drawArc(mOval, startAngle, mArcAngle, false, mPaint);

        mPaint.setColor(mFinishedColor);
        canvas.drawArc(mOval, startAngle, mFinishedSeepAngle, false, mPaint);
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        mProgress = progress;

        if (mProgress > mMaxProgress) {
            mProgress %= mMaxProgress;
        }

        invalidate();
    }

}
