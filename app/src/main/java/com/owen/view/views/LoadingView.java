package com.owen.view.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.owen.view.utils.DensityUtils;

/**
 * Created by mike on 16/3/3.
 */
public class LoadingView extends View {

    private Paint mCirclePaint;
    private Paint mLoadingProgressPaint;
    private Paint mTextPaint;

    private int mWidth;
    private int mHeight;
    private int mRadius;
    private RectF mOval;

    private int mProgress;
    private int mMaxProgress = 100;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark));
        mCirclePaint.setStrokeWidth(2F);

        mLoadingProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLoadingProgressPaint.setStyle(Paint.Style.FILL);
        mLoadingProgressPaint.setColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark));

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(DensityUtils.getInstance(context).sp2px(25));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
        mRadius = Math.min(mWidth, mHeight) / 2 - 4;
        mOval = new RectF(-mRadius, -mRadius, mRadius, mRadius);
    }

    private String mTextStr = "90%";

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.drawCircle(0F, 0F, mRadius, mCirclePaint);

        float startAngel = (float) (90 - 1.0 * mProgress / mMaxProgress * 180);  // 这里需要先乘以 1.0，转换成 float 类型
        float swipeAngel = (float) ((1.0 * mProgress / mMaxProgress * 180) * 2);
        canvas.drawArc(mOval, startAngel, swipeAngel, false, mLoadingProgressPaint);

        canvas.restore();

        float textHeight = mTextPaint.descent() + mTextPaint.ascent();
        canvas.drawText(mTextStr, (mWidth - mTextPaint.measureText(mTextStr)) / 2.0F,
                (mWidth - textHeight) / 2.0F, mTextPaint);
    }

    public void setProgress(int progress) {
        mProgress = progress;

        if (mProgress > mMaxProgress) {
            mProgress %= mMaxProgress;
        }

        invalidate();
    }

    public int getProgress() {
        return mProgress;
    }

}
