package com.owen.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.owen.view.R;
import com.owen.view.model.ModelMoment;
import com.owen.view.views.NineGridLayout;

import java.util.List;

/**
 * 朋友圈
 *
 * Created by Owen on 2016/2/2.
 */
public class MomentsAdapter extends BaseAdapter {

    private Context mContext;
    private List<ModelMoment> mModelMoments;
    private LayoutInflater mInflater;

    public MomentsAdapter(Context context, List<ModelMoment> modelMoments) {
        mContext = context;
        mModelMoments = modelMoments;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mModelMoments == null ? 0 : mModelMoments.size();
    }

    @Override
    public Object getItem(int position) {
        return mModelMoments == null ? null : mModelMoments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_moment, parent, false);

            holder = new ViewHolder();
            holder.mTvText = (TextView) convertView.findViewById(R.id.tv_text);
            holder.mNineGridLayoutImages = (NineGridLayout) convertView.findViewById(R.id.nine_iv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ModelMoment modelMoment = mModelMoments.get(position);

        holder.mTvText.setText(modelMoment.getText());
        holder.mNineGridLayoutImages.setDataSource(modelMoment.getmImages());

        return convertView;
    }

    private static class ViewHolder {
        TextView mTvText;
        NineGridLayout mNineGridLayoutImages;
    }

}
