package com.example.gpstracker3;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private TextView gpsValueTextView, utmValueTextView;
    private ImageView routeImageView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gpsValueTextView = findViewById(R.id.tvGPSValue);
        utmValueTextView = findViewById(R.id.tvUTMValue);
        routeImageView = findViewById(R.id.imageView);
        updateLocationDisplay(52.5200, 13.4050, "33U 392000 5812000");
        drawRouteOnCanvas();
    }
    private void updateLocationDisplay(double latitude, double longitude, String utm) {
        gpsValueTextView.setText(String.format("GPS: %f, %f", latitude, longitude));
        utmValueTextView.setText("UTM: " + utm);
    }
    private void drawRouteOnCanvas() {
        Bitmap bitmap = Bitmap.createBitmap(1000, 700, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        int cellSize = 100;
        for (int x = 0; x <= bitmap.getWidth(); x += cellSize) {
            canvas.drawLine(x, 0, x, bitmap.getHeight(), paint);
        }
        for (int y = 0; y <= bitmap.getHeight(); y += cellSize) {
            canvas.drawLine(0, y, bitmap.getWidth(), y, paint);
        }
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        int[][] routePoints = {
                {100, 650}, {200, 600}, {300, 550}, {400, 500},
                {500, 450}, {600, 400}, {700, 350}, {800, 300}
        };
        for (int i = 0; i < routePoints.length - 1; i++) {
            canvas.drawLine(routePoints[i][0], routePoints[i][1],
                    routePoints[i + 1][0], routePoints[i + 1][1], paint);
        }
        routeImageView.setImageBitmap(bitmap);
    }}