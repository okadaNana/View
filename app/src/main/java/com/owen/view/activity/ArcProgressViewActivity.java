package com.owen.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.owen.view.R;
import com.owen.view.views.ArcProgressView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mike on 16/3/8.
 */
public class ArcProgressViewActivity extends AppCompatActivity {

    private Timer mTimer;
    private ArcProgressView mArcProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcprogressview);

        mArcProgressView = (ArcProgressView) findViewById(R.id.arcProgressView);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mArcProgressView.setProgress(mArcProgressView.getProgress() + 1);
                    }
                });
            }
        }, 1000, 100);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mTimer != null) {
            mTimer.cancel();
        }
    }
}
