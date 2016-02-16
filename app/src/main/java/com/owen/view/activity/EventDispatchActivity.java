package com.owen.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.owen.view.R;

/**
 * Created by mike on 16/2/15.
 */
public class EventDispatchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("EventDispatchActivity", "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("EventDispatchActivity", "onTouchEvent");
        return super.onTouchEvent(event);
    }

}
