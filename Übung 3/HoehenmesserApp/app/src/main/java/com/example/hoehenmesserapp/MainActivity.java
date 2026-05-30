package com.example.hoehenmesserapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editBezugswert;
    Button btnSpeichern;
    TextView textAnzeige;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editBezugswert = findViewById(R.id.editBezugswert);
        btnSpeichern = findViewById(R.id.btnSpeichern);
        textAnzeige = findViewById(R.id.textAnzeige);

        // Gespeicherten Wert beim Start laden
        SharedPreferences sharedPref = getSharedPreferences("hoehenmesser", Context.MODE_PRIVATE);
        float gespeicherterWert = sharedPref.getFloat("bezugswert", 0.0f);
        textAnzeige.setText("Gespeicherter Wert: " + gespeicherterWert);

        // Button: Wert speichern
        btnSpeichern.setOnClickListener(v -> {
            String eingabe = editBezugswert.getText().toString();
            if (!eingabe.isEmpty()) {
                float wert = Float.parseFloat(eingabe);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putFloat("bezugswert", wert);
                editor.apply();
                textAnzeige.setText("Gespeicherter Wert: " + wert);
            }
        });
    }
}
