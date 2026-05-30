package com.example.hoehenmesser;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private TextView textViewHeight;
    private EditText editTextCalib;
    private float basePressure = 1013.25f;
    private float currentPressure = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHeight = findViewById(R.id.textView_height);
        editTextCalib = findViewById(R.id.editText_calibration);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        if (pressureSensor == null) {
            Toast.makeText(this, "Kein Luftdrucksensor vorhanden", Toast.LENGTH_LONG).show();
        }

        // Kalibrierung über Eingabefeld
        editTextCalib.setOnEditorActionListener((v, actionId, event) -> {
            try {
                float enteredHeight = Float.parseFloat(editTextCalib.getText().toString());
                if (currentPressure > 0) {
                    basePressure = (float) (currentPressure / Math.pow(1 - (enteredHeight / 44330.0), 5.255));
                    Toast.makeText(this, "Kalibriert auf " + enteredHeight + " m", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Ungültige Eingabe", Toast.LENGTH_SHORT).show();
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pressureSensor != null) {
            sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            currentPressure = event.values[0];

            double altitude = 44330.0 * (1.0 - Math.pow(currentPressure / basePressure, 1 / 5.255));
            textViewHeight.setText(String.format("Höhe: %.2f m", altitude));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
