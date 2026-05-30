package com.example.javacalculatorapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultView;
    private String current = "";
    private String operator = "";
    private double firstNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultView = findViewById(R.id.resultView);

        int[] numBtns = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

        for (int id : numBtns) {
            Button btn = findViewById(id);
            btn.setOnClickListener(v -> {
                current += btn.getText().toString();
                resultView.setText(current);
            });
        }

        findViewById(R.id.btnAdd).setOnClickListener(v -> handleOp("+"));
        findViewById(R.id.btnSub).setOnClickListener(v -> handleOp("-"));
        findViewById(R.id.btnMul).setOnClickListener(v -> handleOp("*"));
        findViewById(R.id.btnDiv).setOnClickListener(v -> handleOp("/"));

        findViewById(R.id.btnEq).setOnClickListener(v -> {
            double second = current.isEmpty() ? 0 : Double.parseDouble(current);
            double result = 0;

            switch (operator) {
                case "+": result = firstNum + second; break;
                case "-": result = firstNum - second; break;
                case "*": result = firstNum * second; break;
                case "/": result = second != 0 ? firstNum / second : 0; break;
            }

            resultView.setText(String.valueOf(result));
            current = "";
            operator = "";
        });

        findViewById(R.id.btnC).setOnClickListener(v -> {
            current = "";
            operator = "";
            firstNum = 0;
            resultView.setText("0");
        });

        findViewById(R.id.btnCE).setOnClickListener(v -> {
            if (!current.isEmpty()) {
                current = current.substring(0, current.length() - 1);
                resultView.setText(current.isEmpty() ? "0" : current);
            }
        });
    }

    private void handleOp(String op) {
        if (!current.isEmpty()) {
            firstNum = Double.parseDouble(current);
            operator = op;
            current = "";
        }
    }
}
