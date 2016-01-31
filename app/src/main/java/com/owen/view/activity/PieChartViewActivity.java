package com.owen.view.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.owen.view.R;
import com.owen.view.views.PieChart;

/**
 * Created by mike on 16/1/31.
 */
public class PieChartViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        final PieChart pie = (PieChart) this.findViewById(R.id.Pie);
        pie.addItem("Agamemnon", 2, ContextCompat.getColor(this, R.color.seafoam));
        pie.addItem("Bocephus", 3.5f, ContextCompat.getColor(this, R.color.chartreuse));
        pie.addItem("Calliope", 2.5f, ContextCompat.getColor(this, R.color.emerald));
        pie.addItem("Daedalus", 3, ContextCompat.getColor(this, R.color.bluegrass));
        pie.addItem("Euripides", 1, ContextCompat.getColor(this, R.color.turquoise));
        pie.addItem("Ganymede", 3, ContextCompat.getColor(this, R.color.slate));

        findViewById(R.id.Reset).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pie.setCurrentItem(0);
            }
        });
    }
}
