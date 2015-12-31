package com.owen.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.owen.view.R;
import com.owen.view.adapter.ModuleAdapter;
import com.owen.view.model.Module;
import com.owen.view.utils.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<Module> MODULE_LIST;

    static {
        MODULE_LIST = new ArrayList<>();

        MODULE_LIST.add(new Module("圆形进度条", CircleProgressActivity.class));
        MODULE_LIST.add(new Module("太极图", TaiJiActivity.class));
        MODULE_LIST.add(new Module("刷新进度条", RefreshActivity.class));
        MODULE_LIST.add(new Module("仿新浪微博进度条", WeiBoLoadingViewActivity.class));
        MODULE_LIST.add(new Module("画笔的 Style", PaintStyleActivity.class));
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
