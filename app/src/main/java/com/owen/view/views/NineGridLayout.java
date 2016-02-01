package com.owen.view.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.owen.view.model.ModelImage;
import com.owen.view.utils.DensityUtils;

import java.util.List;

/**
 * 九宫格布局控件
 *
 * Created by mike on 16/2/1.
 */
public class NineGridLayout extends ViewGroup {

    /**
     * 每个 item 之间的间隔
     */
    private int mGap;
    /**
     * 行数
     */
    private int mRow;
    /**
     * 列数
     */
    private int mColumn;
    /**
     * 九宫格控件的宽度
     */
    private int mWidth;

    /**
     * 数据源
     */
    private List<ModelImage> mDataSource;

    public NineGridLayout(Context context) {
        super(context);
    }

    public NineGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mWidth = DensityUtils.getInstance(context).getScreenWidth() - DensityUtils.getInstance(context).dip2px(80);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {}

    public void setDataSource(List<ModelImage> imageList) {
        if (imageList == null || imageList.isEmpty()) {
            return;
        }

        mDataSource = imageList;

        calculateRowAndColumn(mDataSource.size());
        addImageViews();
        layoutImageViews();
    }

    private void layoutImageViews() {
        int imageViewCount = mDataSource.size();

        int widthPerImageView = (mWidth - mGap * (3 - 1)) / 3;
        int heightPerImageView = widthPerImageView;

        // 根据子 View 的宽高,计算出控件的宽高
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = mRow * heightPerImageView + mGap * (mRow - 1);
        setLayoutParams(layoutParams);

        for (int i = 0; i < imageViewCount; i++) {
            ImageView imageView = (ImageView) getChildAt(i);

            Position position = getPositionOfImageView(i);
            int left = (widthPerImageView + mGap) * position.col;
            int top = (heightPerImageView + mGap) * position.row;
            int right = left + widthPerImageView;
            int bottom = top + heightPerImageView;

            imageView.layout(left, top, right, bottom);
        }

        /*
        for (int i = 0; i < childrenCount; i++) {
            CustomImageView childrenView = (CustomImageView) getChildAt(i);
            childrenView.setImageUrl(((Image) listData.get(i)).getUrl());
            int[] position = findPosition(i);
            int left = (singleWidth + gap) * position[1];
            int top = (singleHeight + gap) * position[0];
            int right = left + singleWidth;
            int bottom = top + singleHeight;

            childrenView.layout(left, top, right, bottom);
        }

         */

    }

    /**
     * 获取ImageView的位置(在哪一行,哪一列)
     */
    private Position getPositionOfImageView(int imageViewNum) {
        Position position = null;

        for (int row = 0; row < mRow; row++) {
            for (int col = 0; col < mColumn; col++) {
                if ((row * mColumn + col) == imageViewNum) {
                    position = new Position(row, col);
                }
            }
        }

        return position;
    }

    private static class Position {
        public int row;
        public int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private void addImageViews() {
        removeAllViews();

        for (int i = 0; i < mDataSource.size(); i++) {
            ImageView iv = generateImageView();
            addView(iv, generateDefaultLayoutParams());
        }
    }

    /**
     * 根据数据的个数,计算出需要的几行几列才能放下这些数据
     *
     * size   row  column
     *  1	  1  	1
     *  2	  1  	2
     *  3	  1  	3
     *  4	  2  	2
     *  5	  2  	3
     *  6	  2  	3
     *  7	  3  	3
     *  8	  3  	3
     *  9	  3  	3
     */
    private void calculateRowAndColumn(int size) {
        if (size <= 3) {
            mRow = 1;
            mColumn = size;
        } else if (size <= 6) {
            mRow = 2;
            mColumn = 3;

            if (size == 4) {
                mColumn = 2;
            }
        } else {
            mRow = 3;
            mColumn = 3;
        }
    }

    private ImageView generateImageView() {
        ImageView iv = new ImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击ImageView", Toast.LENGTH_SHORT).show();
            }
        });
        iv.setBackgroundColor(Color.parseColor("#f5f5f5"));
        return iv;
    }

}
