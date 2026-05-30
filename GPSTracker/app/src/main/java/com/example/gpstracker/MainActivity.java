package com.example.gpstracker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationClient;
    LocationCallback locationCallback;
    File csvFile;

    Button btnToggleTracking;
    boolean isTracking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // UI richtig einbetten mit Inset-Unterstützung
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisieren
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        csvFile = new File(getExternalFilesDir(null), "gps_data.csv");

        // Header schreiben, falls Datei neu
        if (!csvFile.exists()) {
            try (FileWriter writer = new FileWriter(csvFile, true)) {
                writer.append("Uhrzeit,Längengrad,Breitengrad,Höhe\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Button finden und Listener setzen
        btnToggleTracking = findViewById(R.id.btnToggleTracking);

        btnToggleTracking.setOnClickListener(v -> {
            if (!isTracking) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates();
                    btnToggleTracking.setText("Stop Tracking");
                    isTracking = true;
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
            } else {
                fusedLocationClient.removeLocationUpdates(locationCallback);
                btnToggleTracking.setText("Start Tracking");
                isTracking = false;
            }
        });
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(5000); // alle 5 Sekunden
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                Location location = locationResult.getLastLocation();
                if (location != null) {
                    String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                    double lat = location.getLatitude();
                    double lon = location.getLongitude();
                    double alt = location.getAltitude();

                    String line = time + "," + lon + "," + lat + "," + alt + "\n";

                    try (FileWriter writer = new FileWriter(csvFile, true)) {
                        writer.append(line);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(MainActivity.this, "Erfasst: " + lat + ", " + lon, Toast.LENGTH_SHORT).show();
                }
            }
        };

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isTracking) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }
}
