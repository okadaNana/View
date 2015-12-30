package com.owen.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.SeekBar;

import com.owen.view.R;
import com.owen.view.views.WeiBoLoadingView;

/**
 * Created by Owen on 2015/12/30.
 */
public class WeiBoLoadingViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_loading_view);

        final WeiBoLoadingView loadingView = (WeiBoLoadingView) findViewById(R.id.weibo_loading_view);

        AppCompatSeekBar seekBar = (AppCompatSeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                loadingView.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        loadingView.setProgress(seekBar.getProgress());
    }
}
