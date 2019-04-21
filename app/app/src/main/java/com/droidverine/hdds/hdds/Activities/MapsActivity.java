package com.droidverine.hdds.hdds.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.droidverine.hdds.hdds.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    GoogleMap mMap;
    String lat;
    String longit;
    MapStyleOptions mapStyleOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent=getIntent();
         lat= intent.getStringExtra("lat");
         longit= intent.getStringExtra("longi");

        Toast.makeText(getApplicationContext(),intent.getStringExtra("title"),Toast.LENGTH_LONG).show();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        double latitude = Double.parseDouble(lat);
        double longitude = Double.parseDouble(longit);
        mapStyleOptions = MapStyleOptions.loadRawResourceStyle(MapsActivity.this, R.raw.mapstyle_aubergine);
        mMap.setMapStyle(mapStyleOptions);
        // Add a marker in Sydney and move the camera
        LatLng nerul = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(nerul).title("SIES GST Nerul"));
//		mMap.animateCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16.0f));

    }
}
