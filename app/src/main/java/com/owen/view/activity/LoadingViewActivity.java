package com.owen.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.owen.view.R;
import com.owen.view.views.LoadingView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mike on 16/3/3.
 */
public class LoadingViewActivity extends AppCompatActivity {

    private Timer mTimer;
    private LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingview);

        mLoadingView = (LoadingView) findViewById(R.id.loadingView);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mLoadingView.setProgress(mLoadingView.getProgress() + 1);
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
