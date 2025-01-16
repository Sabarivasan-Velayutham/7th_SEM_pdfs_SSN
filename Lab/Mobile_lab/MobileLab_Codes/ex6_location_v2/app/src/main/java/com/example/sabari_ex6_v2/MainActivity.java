package com.example.sabari_ex6_v2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.location.Address;
import android.location.Geocoder;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private TextView latitudeText;
    private TextView longitudeText;
    private Button getLocationButton;
    private FusedLocationProviderClient fusedLocationClient;
    private EditText placeEditText;
    private Button geocodeButton;
    private TextView latitudeText2;
    private TextView longitudeText2;
    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitudeText = findViewById(R.id.latitudeText);
        longitudeText = findViewById(R.id.longitudeText);
        getLocationButton = findViewById(R.id.getLocationButton);

        getLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLocationPermission();
            }
        });

        placeEditText = findViewById(R.id.placeEditText);
        geocodeButton = findViewById(R.id.geocodeButton);
        latitudeText2 = findViewById(R.id.latitudeText2);
        longitudeText2 = findViewById(R.id.longitudeText2);
        geocoder = new Geocoder(this, Locale.getDefault());

        geocodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geocodePlace();
            }
        });

        // Initialize the FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permission already granted
            getLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            }
        }
    }

    private void getLocation() {
        // Check if permission is granted before accessing location
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                double latitude = location.getLatitude();
                                double longitude = location.getLongitude();

                                latitudeText.setText("Latitude: " + latitude);
                                longitudeText.setText("Longitude: " + longitude);
                            }
                        }
                    });
        }
    }
    private void geocodePlace() {
        String placeName = placeEditText.getText().toString().trim();

        try {
            List<Address> addresses = geocoder.getFromLocationName(placeName, 1);

            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                double latitude = address.getLatitude();
                double longitude = address.getLongitude();

                latitudeText2.setText("Latitude: " + latitude);
                longitudeText2.setText("Longitude: " + longitude);
            } else {
                latitudeText2.setText("Latitude: N/A");
                longitudeText2.setText("Longitude: N/A");
            }
        } catch (IOException e) {
            e.printStackTrace();
            latitudeText2.setText("Latitude: N/A");
            longitudeText2.setText("Longitude: N/A");
        }
    }
}
