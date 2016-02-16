package com.owen.view.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mike on 16/2/16.
 */
public class DragView3 extends View {

    public DragView3(Context context) {
        super(context);
    }

    public DragView3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int mLastRawX;
    private int mLastRawY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastRawX = rawX;
                mLastRawY = rawY;
                break;

            case MotionEvent.ACTION_MOVE:
                int offsetRawX = rawX - mLastRawX;
                int offsetRawY = rawY - mLastRawY;

                offsetLeftAndRight(offsetRawX);
                offsetTopAndBottom(offsetRawY);

                mLastRawX = rawX;
                mLastRawY = rawY;
                break;

            default:
                break;
        }

        return true;
    }
}
