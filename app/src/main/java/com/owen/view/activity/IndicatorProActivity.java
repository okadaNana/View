package com.owen.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.owen.view.R;
import com.owen.view.fragment.VpSimpleFragment;
import com.owen.view.views.ViewPagerIndicatorPro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mike on 16/3/9.
 */
public class IndicatorProActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ViewPagerIndicatorPro mIndicator;

    private List<String> mTitles;
    private List<VpSimpleFragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpagerindicator_pro);

        mIndicator = (ViewPagerIndicatorPro) findViewById(R.id.indicator);

        mTitles = new ArrayList<>(3);
        mTitles.add("短信1");
        mTitles.add("收藏2");
        mTitles.add("推荐3");
        mTitles.add("短信4");
        mTitles.add("收藏5");
        mTitles.add("推荐6");
        mTitles.add("短信7");
        mTitles.add("收藏8");
        mTitles.add("推荐9");

        mFragments = new ArrayList<>(mTitles.size());
        for (int i = 0; i < mTitles.size(); i++) {
            mFragments.add(VpSimpleFragment.newInstance(mTitles.get(i)));
        }

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicator.scroll(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

}
