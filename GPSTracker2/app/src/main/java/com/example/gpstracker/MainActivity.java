package com.example.gpstracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private EditText referenceValueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referenceValueEditText = findViewById(R.id.referenceValueEditText);
        Button saveButton = findViewById(R.id.saveButton);

        float referenceValue = loadReferenceValue(this);
        referenceValueEditText.setText(String.valueOf(referenceValue));

        saveButton.setOnClickListener((View v) -> {
            try {
                float newValue = Float.parseFloat(referenceValueEditText.getText().toString());
                saveReferenceValue(this, newValue);
                Toast.makeText(this, "Gespeichert!", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Bitte geben Sie eine gültige Zahl ein.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveReferenceValue(Context context, float referenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AltimeterPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("ReferenceValue", referenceValue);
        editor.apply();
    }

    public float loadReferenceValue(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AltimeterPreferences", Context.MODE_PRIVATE);
        return sharedPreferences.getFloat("ReferenceValue", 0.0f);
    }
}
