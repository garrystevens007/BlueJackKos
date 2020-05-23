package com.example.firstprojectever.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.firstprojectever.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class google_api extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap gmap;
    String lat,lng,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_api);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        name = getIntent().getExtras().getString("name");
        lat = getIntent().getExtras().getString("lat");
        lng = getIntent().getExtras().getString("lng");
        Log.i("Current passing ", "onMapReady: namakos "+name+" lat : " + lat + " lng : " + lng);
        double fix_lat = Double.parseDouble(lat);
        double fix_lng = Double.parseDouble(lng);
        LatLng curr = new LatLng(fix_lat,fix_lng);
        gmap.addMarker(new MarkerOptions().position(curr).title(name));
        gmap.moveCamera(CameraUpdateFactory.newLatLng(curr));
    }
}
