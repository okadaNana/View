package com.owen.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.owen.view.R;
import com.owen.view.adapter.MomentsAdapter;
import com.owen.view.model.ModelImage;
import com.owen.view.model.ModelMoment;

import java.util.ArrayList;
import java.util.List;

/**
 * 朋友圈
 *
 * Created by Owen on 2016/2/2.
 */
public class MomentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninegridlayout);

        ListView listView = (ListView) findViewById(R.id.listView);

        List<ModelMoment> modelMoments = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            ModelMoment moment = new ModelMoment();
            List<ModelImage> modelImages = new ArrayList<>();
            for (int k = 0; k <= i; k++) {
                modelImages.add(new ModelImage());
            }
            moment.setmImages(modelImages);

            modelMoments.add(moment);
        }

        listView.setAdapter(new MomentsAdapter(this, modelMoments));
    }
}
