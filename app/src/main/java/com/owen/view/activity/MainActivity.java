package com.owen.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.owen.view.R;
import com.owen.view.adapter.ModuleAdapter;
import com.owen.view.model.ModelModule;
import com.owen.view.utils.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<ModelModule> MODULE_LIST;

    static {
        MODULE_LIST = new ArrayList<>();

        MODULE_LIST.add(new ModelModule("圆形进度条", CircleProgressActivity.class));
        MODULE_LIST.add(new ModelModule("太极图", TaiJiActivity.class));
        MODULE_LIST.add(new ModelModule("刷新进度条", RefreshActivity.class));
        MODULE_LIST.add(new ModelModule("仿新浪微博进度条", WeiBoLoadingViewActivity.class));
        MODULE_LIST.add(new ModelModule("画笔的 Style", PaintStyleActivity.class));
        MODULE_LIST.add(new ModelModule("雷达", RadarViewActivity.class));
        MODULE_LIST.add(new ModelModule("饼图", PieChartViewActivity.class));
        MODULE_LIST.add(new ModelModule("朋友圈九宫格 -- 自定义 ViewGroup", MomentsActivity.class));
        MODULE_LIST.add(new ModelModule("CheckBox之 0 与 1 的艺术", CheckBoxActivity.class));
        MODULE_LIST.add(new ModelModule("View 的滑动 -- layout() getX()", DragViewActivity.class));
        MODULE_LIST.add(new ModelModule("View 的滑动 -- layout() getRawX()", DragView2Activity.class));
        MODULE_LIST.add(new ModelModule("View 的滑动 -- offsetLeftAndRight() offsetTopAndBottom()", DragView3Activity.class));
        MODULE_LIST.add(new ModelModule("View 的滑动 -- MarginLayoutParams", DragView4Activity.class));
        MODULE_LIST.add(new ModelModule("View 的滑动 -- getX() scrollBy()", DragView5Activity.class));
        MODULE_LIST.add(new ModelModule("View 的滑动 -- getRawX() scrollBy()", DragView6Activity.class));
        MODULE_LIST.add(new ModelModule("View 的事件分发", EventDispatchActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());

        ModuleAdapter adapter = new ModuleAdapter(this, MODULE_LIST);
        recyclerView.setAdapter(adapter);
    }

}
