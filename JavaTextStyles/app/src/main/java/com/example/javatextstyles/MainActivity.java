package com.example.javatextstyles;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Eigene Schriftart setzen
        TextView customFontText = findViewById(R.id.customFontText);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/lobster.ttf");
        customFontText.setTypeface(customFont);
    }
}
