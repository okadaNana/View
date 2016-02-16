package com.owen.view.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mike on 16/2/16.
 */
public class DragView4 extends View {

    public DragView4(Context context) {
        super(context);
    }

    public DragView4(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int mLastX;
    private int mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                int offsetX = x - mLastX;
                int offsetY = y - mLastY;

                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
                marginLayoutParams.leftMargin = getLeft() + offsetX;
                marginLayoutParams.topMargin = getTop() + offsetY;

                setLayoutParams(marginLayoutParams);
                break;

            default:
                break;
        }

        return true;
    }
}
