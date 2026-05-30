package com.example.javatemperatureconverterv2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputField;
    TextView resultText;
    Button btnCtoF, btnFtoC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.inputField);
        resultText = findViewById(R.id.resultText);
        btnCtoF = findViewById(R.id.btnCtoF);
        btnFtoC = findViewById(R.id.btnFtoC);

        btnCtoF.setOnClickListener(v -> {
            String input = inputField.getText().toString();
            try {
                double celsius = Double.parseDouble(input);
                double fahrenheit = celsius * 9 / 5 + 32;
                resultText.setText(celsius + " °C = " + String.format("%.2f", fahrenheit) + " °F");
            } catch (NumberFormatException e) {
                resultText.setText("Ungültige Eingabe");
            }
        });

        btnFtoC.setOnClickListener(v -> {
            String input = inputField.getText().toString();
            try {
                double fahrenheit = Double.parseDouble(input);
                double celsius = (fahrenheit - 32) * 5 / 9;
                resultText.setText(fahrenheit + " °F = " + String.format("%.2f", celsius) + " °C");
            } catch (NumberFormatException e) {
                resultText.setText("Ungültige Eingabe");
            }
        });
    }
}
