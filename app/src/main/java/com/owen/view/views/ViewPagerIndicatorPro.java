package com.owen.view.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.owen.view.R;

import java.util.List;

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
    private int tabTextColor;

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

        if (position >= (mVisibleTabCount - 1) &&
                positionOffset > 0 && getChildCount() > mVisibleTabCount) {
            if (mVisibleTabCount != 1) {
                scrollTo((position - (mVisibleTabCount - 1)) * mTabWidth + (int) (positionOffset * mTabWidth), 0);
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

    private ViewPager mViewPager;

    public void setTabTextColor(int position) {
        TextView tvTab = (TextView) getChildAt(position);
        tvTab.setTextColor(PRESS_TAB_TEXT_COLOR);
    }

    public interface OnPageChangeListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
        void onPageSelected(int position);
        void onPageScrollStateChanged(int state);
    }

    private ViewPagerIndicatorPro.OnPageChangeListener mOnPagerChangeListener;

    public void setOnPagerChangeListener(ViewPagerIndicatorPro.OnPageChangeListener listener) {
        mOnPagerChangeListener = listener;
    }

    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;

        if (mViewPager != null) {
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    scroll(position, positionOffset);
                    if (mOnPagerChangeListener != null) {
                        mOnPagerChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    resetTabTextColor();
                    setTabTextColor(position);
                    if (mOnPagerChangeListener != null) {
                        mOnPagerChangeListener.onPageSelected(position);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (mOnPagerChangeListener != null) {
                        mOnPagerChangeListener.onPageScrollStateChanged(state);
                    }
                }
            });
        }
    }

    private void resetTabTextColor() {
        for (int i = 0; i < getChildCount(); i++) {
            TextView tvTab = (TextView) getChildAt(i);
            tvTab.setTextColor(NORMAL_TAB_TEXT_COLOR);
        }
    }

    private static final int NORMAL_TAB_TEXT_COLOR = 0x77FFFFFF;
    private static final int PRESS_TAB_TEXT_COLOR = 0xFFFFFFFF;

    public void setTabTitles(List<String> titles) {
        int screenWidth = getScreenWidth();

        for (int i = 0; i < titles.size(); i++) {
            TextView tvTab = new TextView(getContext());
            tvTab.setText(titles.get(i));
            tvTab.setTextColor(NORMAL_TAB_TEXT_COLOR);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            lp.width = screenWidth / mVisibleTabCount;
            tvTab.setLayoutParams(lp);
            tvTab.setGravity(Gravity.CENTER);
            tvTab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

            final int j = i;
            tvTab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(j);
                }
            });

            addView(tvTab);
        }
    }

    public void setVisibleTabCount(int visibleTabCount) {
        mVisibleTabCount = visibleTabCount;
    }

}
