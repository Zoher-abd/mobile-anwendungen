package com.example.gpsreceiver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private String currentProvider = LocationManager.GPS_PROVIDER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView_location);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                double alt = location.getAltitude();
                float speed = location.getSpeed();

                String info = "Provider: " + currentProvider +
                        "\nBreite: " + lat +
                        "\nLänge: " + lon +
                        "\nHöhe: " + alt + " m" +
                        "\nGeschwindigkeit: " + speed + " m/s";

                textView.setText(info);
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
                Toast.makeText(MainActivity.this, "Provider aktiviert: " + provider, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                Toast.makeText(MainActivity.this, "Provider deaktiviert: " + provider, Toast.LENGTH_SHORT).show();
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            startLocationUpdates(currentProvider);
        }
    }

    private void startLocationUpdates(String provider) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(provider, 5000, 5, locationListener);
        }
    }

    private void changeProvider(String provider) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.removeUpdates(locationListener);
            currentProvider = provider;
            startLocationUpdates(provider);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates(currentProvider);
        } else {
            Toast.makeText(this, "Standortzugriff verweigert", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_gps) {
            Toast.makeText(this, "GPS Provider ausgewählt", Toast.LENGTH_SHORT).show();
            changeProvider(LocationManager.GPS_PROVIDER);
            return true;
        } else if (id == R.id.item_network) {
            Toast.makeText(this, "Network Provider ausgewählt", Toast.LENGTH_SHORT).show();
            changeProvider(LocationManager.NETWORK_PROVIDER);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
