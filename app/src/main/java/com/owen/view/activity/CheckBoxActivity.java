package com.owen.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.owen.view.R;

/**
 * http://blog.oceancx.com/2016/01/31/Android-CheckBox%E8%AF%A6%E8%A7%A3
 *
 * Created by Owen on 2016/2/3.
 */
public class CheckBoxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(CheckBoxActivity.this, "选中", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CheckBoxActivity.this, "消失", Toast.LENGTH_SHORT).show();
                }
            }
        });
        checkBox.setChecked(false);
    }
}
