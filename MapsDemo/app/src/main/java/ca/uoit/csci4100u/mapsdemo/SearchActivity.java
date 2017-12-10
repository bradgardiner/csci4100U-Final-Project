package ca.uoit.csci4100u.mapsdemo;

import android.Manifest;
import android.app.Activity;
//import android.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//import android.support.v4.app.Fragment;

public class SearchActivity extends FragmentActivity implements LocationListener, OnMapReadyCallback {
    private LocationManager locationManager;
    private GoogleMap mMap;


    private double latitude, longitude;
    private String locationName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // get access to geolocation services
        setupGeolocation();
    }

    private void setupGeolocation() {
        // check to ensure I have permission
        verifyGeolocationPermission();

        // check to ensure that geolocation is enabled

        // request updates

    }

    private static final int REQUEST_GEOLOCATION_PERMS = 1;

    private void verifyGeolocationPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // TODO:  Show the user rationale and ask for permission
            }

            String[] perms = new String[] { Manifest.permission.ACCESS_FINE_LOCATION };
            requestPermissions(perms, REQUEST_GEOLOCATION_PERMS);
        } else {
            // geolocation permission granted, so request location updates
            verifyGeolocationEnabled();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] perms,
                                           int[] results) {
        if (requestCode == REQUEST_GEOLOCATION_PERMS) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                // geolocation permission granted, so request location updates
                verifyGeolocationEnabled();
            }
        }
    }

    private void verifyGeolocationEnabled() {
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        // check if geolocation is enabled in settings
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // TODO:  Request location updates
            requestLocationUpdates();
        } else {
            // show the settings app to let the user enable it
            String locationSettings = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
            Intent enableGeoloc = new Intent(locationSettings);
            startActivity(enableGeoloc);

            // Note:  startActivityForResult() may be better here
        }
    }

    private void requestLocationUpdates() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);

        String recommendedProvider = locationManager.getBestProvider(criteria, true);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(recommendedProvider,
                    5000, 10, this);
            Log.i("MapsDemo", "requestLocationUpdates()");
        }
    }

    public void search(View source) {
        EditText txtLocation = (EditText)findViewById(R.id.txtLocation);
        String locationName = txtLocation.getText().toString();

        Log.i("MapsDemo", "search()::locationName == " + locationName);

        Intent showMapIntent = new Intent(this, MapsActivity.class);
        showMapIntent.putExtra("locationName", locationName);
        showMapIntent.putExtra("backupLatitude", latitude);
        showMapIntent.putExtra("backupLongitude", longitude);
        startActivity(showMapIntent);
    }

    private String geocode(double latitude, double longitude) {
        if (Geocoder.isPresent()) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            try {
                List<Address> results = geocoder.getFromLocation(latitude, longitude, 1);

                if (results.size() > 0) {
                    return results.get(0).getAddressLine(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private ArrayList<String> geoAll(double latitude, double longitude){
        ArrayList<String> geoResults = new ArrayList<String>();
        if (Geocoder.isPresent()) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            try {
                List<Address> results = geocoder.getFromLocation(latitude, longitude, 1);

                if (results.size() > 0) {
                    geoResults.add(results.get(0).getAddressLine(0));
                    geoResults.add(results.get(0).getAddressLine(1));
                    geoResults.add(results.get(0).getLocality());
                    geoResults.add(results.get(0).getAdminArea());
                    geoResults.add(results.get(0).getCountryName());
                    geoResults.add(results.get(0).getPostalCode());
                    geoResults.add(results.get(0).getPhone());
                    geoResults.add(results.get(0).getUrl());
                    return geoResults;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;

    }
    public void onLocationChanged(Location location) {
        Log.i("MapsDemo", "Location changed: " + location);

        // remember the coordinates for the map activity's backup location
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        ArrayList<String> geos = geoAll(location.getLatitude(), location.getLongitude());


        LatLng position = new LatLng(latitude, longitude);

        // centre the map around the specified location
        mMap.animateCamera(CameraUpdateFactory.newLatLng(position));

        // add a marker at the specified location
        MarkerOptions options = new MarkerOptions();
        mMap.addMarker(options.position(position).title(geos.toString()));

        // configure the map settings
        mMap.setTrafficEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // enable the zoom controls
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.setMinZoomPreference(15);



        // geocode the result - get the location name
//        String locationName = geocode(location.getLatitude(), location.getLongitude());
//
//        // show the location in the search UI
//        EditText txtLocation = (EditText)findViewById(R.id.txtLocation);
////        txtLocation.setText(locationName);

//        TextView address1 = (TextView) findViewById(R.id.address1);
//        TextView address2 = (TextView) findViewById(R.id.address2);
//        TextView city  = (TextView) findViewById(R.id.city);
//        TextView province = (TextView) findViewById(R.id.province);
//        TextView country = (TextView) findViewById(R.id.country);
//        TextView postal = (TextView) findViewById(R.id.postal);
//        TextView phone = (TextView) findViewById(R.id.phone);
//        TextView url = (TextView) findViewById(R.id.url);

//        if(!geos.isEmpty()){
//            address1.setText(geos.get(0));
//            address2.setText(geos.get(1));
//            city.setText(geos.get(2));
//            province.setText(geos.get(3));
//            country.setText(geos.get(4));
//            postal.setText(geos.get(5));
//            phone.setText(geos.get(6));
//            url.setText(geos.get(7));
//        }
//        else{
//            Log.i("EMPTY", "EMPTY*************");
//        }

    }

    public void onProviderEnabled(String provider) {
        Log.i("MapsDemo", "Provider enabled: " + provider);
    }

    public void onProviderDisabled(String provider) {
        Log.i("MapsDemo", "Provider disabled: " + provider);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i("MapsDemo", "Provider ("+provider+") status changed: " + status);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng position = new LatLng(latitude, longitude);

        // centre the map around the specified location
        mMap.animateCamera(CameraUpdateFactory.newLatLng(position));

        // add a marker at the specified location
        MarkerOptions options = new MarkerOptions();
        mMap.addMarker(options.position(position).title(locationName));

        // configure the map settings
        mMap.setTrafficEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // enable the zoom controls
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
    }
}
