package com.owen.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.owen.view.model.Module;

import java.util.List;

/**
 * Created by Owen on 2015/12/28.
 */
public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Module> mModuleList;


    public ModuleAdapter(Context context, List<Module> moduleList) {
        mModuleList = moduleList;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Module module = mModuleList.get(position);

        holder.mTvName.setText(module.getName());
        holder.mTvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, module.getClazz());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mModuleList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvName = (TextView) itemView;
        }
    }

}
