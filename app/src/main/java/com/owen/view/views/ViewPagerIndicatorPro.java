package com.owen.view.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.owen.view.R;

/**
 * Created by mike on 16/3/9.
 */
public class ViewPagerIndicatorPro extends LinearLayout {

    private static final int DEFAULT_VISIBLE_TAB_COUNT = 4;

    private Paint mPaint;
    private Path mPath;

    private int mInitTranslateX;
    private int mTranslateX;

    private int mTriangleWidth;
    private int mTriangleHeight;
    private float RADIO_TRIANGLE_BETWEEN_TAB = 1 / 6F;

    private int mTabWidth;
    private int mVisibleTabCount;

    public ViewPagerIndicatorPro(Context context) {
        this(context, null);
    }

    public ViewPagerIndicatorPro(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setPathEffect(new CornerPathEffect(3));

        TypedArray typedArray = null;
        try {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicatorPro);
            mVisibleTabCount = typedArray.getInt(R.styleable.ViewPagerIndicatorPro_visible_tab_count, DEFAULT_VISIBLE_TAB_COUNT);
            if (mVisibleTabCount < 0) {
                mVisibleTabCount = DEFAULT_VISIBLE_TAB_COUNT;
            }
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();

        canvas.translate(mInitTranslateX + mTranslateX, getHeight());
        canvas.drawPath(mPath, mPaint);

        canvas.restore();
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mTabWidth = w / mVisibleTabCount;

        mTriangleWidth = (int) (w / mVisibleTabCount * RADIO_TRIANGLE_BETWEEN_TAB);
        mTriangleHeight = mTriangleWidth / 2;
        mInitTranslateX = (w / mVisibleTabCount - mTriangleWidth) / 2;

        initTriangle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }

        int screenWidth = getScreenWidth();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LinearLayout.LayoutParams lp = (LayoutParams) childView.getLayoutParams();
            lp.weight = 0;
            lp.width = screenWidth / mVisibleTabCount;
            childView.setLayoutParams(lp);
        }
    }

    private void initTriangle() {
        mPath = new Path();

        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mPath.close();
    }

    public void scroll(int position, float positionOffset) {
        mTranslateX = (int) (mTabWidth * (position + positionOffset));

        if (position >= (mVisibleTabCount - 2) &&
                positionOffset > 0 && getChildCount() > mVisibleTabCount) {
            if (mVisibleTabCount != 1) {
                scrollTo((position - (mVisibleTabCount - 2)) * mTabWidth + (int) (positionOffset * mTabWidth), 0);
            } else {
                scrollTo(position * mTabWidth + (int) (positionOffset * mTabWidth), 0);
            }
        }
        invalidate();
    }

    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
