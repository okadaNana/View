package com.owen.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mike on 16/3/9.
 */
public class VpSimpleFragment extends Fragment {

    private static final String BUNDLE_TITLE = "title";

    public static VpSimpleFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);

        VpSimpleFragment fragment = new VpSimpleFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String title = bundle.getString(BUNDLE_TITLE);

        TextView tvTitle = new TextView(getContext());
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tvTitle.setLayoutParams(lp);
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        tvTitle.setText(title);

        return tvTitle;
    }

}
