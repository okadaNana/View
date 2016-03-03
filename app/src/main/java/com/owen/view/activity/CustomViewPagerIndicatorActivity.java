package com.owen.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.owen.view.R;
import com.owen.view.fragment.FirstFragment;
import com.owen.view.fragment.SecondFragment;
import com.owen.view.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mike on 16/3/3.
 */
public class CustomViewPagerIndicatorActivity extends AppCompatActivity {

    private View mIndicator;
    private ViewPager mViewPager;

    private int mScreenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_viewpager_indicator);

        initTabText();
        calculateScreenWidth();
        setIndicatorWidth();
        initFragments();
    }

    private void initFragments() {
        final List<Fragment> fragments = new ArrayList<Fragment>(3);
        fragments.add(new FirstFragment());
        fragments.add(new SecondFragment());
        fragments.add(new ThirdFragment());

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        };
        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("TAG", "position=[" + position + "] positionOffset=[" + positionOffset + "] positionOffsetPixels=[" + positionOffsetPixels + "]");

                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mIndicator.getLayoutParams();
                lp.leftMargin = (int) ((position + positionOffset) * (mScreenWidth / 3));
                mIndicator.setLayoutParams(lp);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setIndicatorWidth() {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mIndicator.getLayoutParams();
        lp.width = mScreenWidth / 3;
        mIndicator.setLayoutParams(lp);
    }

    private void initTabText() {
        mIndicator = findViewById(R.id.view_indicator);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void calculateScreenWidth() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mScreenWidth = metric.widthPixels;
    }

}
