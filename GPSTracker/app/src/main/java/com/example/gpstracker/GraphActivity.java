package com.example.gpstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lineChart = findViewById(R.id.lineChart);

        List<Entry> entries = new ArrayList<>();
        File csvFile = new File(getExternalFilesDir(null), "gps_data.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean first = true;
            int index = 0;

            while ((line = reader.readLine()) != null) {
                if (first) {
                    first = false;
                    continue; // Header überspringen
                }

                String[] tokens = line.split(",");
                if (tokens.length >= 4) {
                    float height = Float.parseFloat(tokens[3]);
                    entries.add(new Entry(index++, height));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LineDataSet dataSet = new LineDataSet(entries, "Höhenverlauf");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setLineWidth(2f);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.getDescription().setText("Höhe über Zeit");
        lineChart.invalidate(); // Zeichne neu
    }
}
