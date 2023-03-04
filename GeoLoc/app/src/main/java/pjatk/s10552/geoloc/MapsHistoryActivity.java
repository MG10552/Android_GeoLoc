package pjatk.s10552.geoloc;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import pjatk.s10552.geoloc.db.DatabaseHelper;

public class MapsHistoryActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseHelper db;
    private LocationManager locman = null;
    private String provider;
    private LatLng currentSearchLocation;
    private static final String ACTION_PROXIMITY_ALERT = "Proximity Alert ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_history);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        db = new DatabaseHelper(this);

        locman = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria cr = new Criteria();
        provider = locman.getBestProvider(cr, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("AAAAAAA", "onMapReady: nie jest ");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
//            return;
        }
        Location loc = locman.getLastKnownLocation(provider);
        Log.i("_loc_test", "---------------------Provider: "+loc.getProvider());
        Log.i("_loc_test", "---------------------Latitude:  "+loc.getLatitude());
        Log.i("_loc_test", "---------------------Longitude: "+loc.getLongitude());

        currentSearchLocation = new LatLng(loc.getLatitude(), loc.getLongitude());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        Intent intent = new Intent(ACTION_PROXIMITY_ALERT);
        //PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);

        Cursor data = db.getAll();
        while(data.moveToNext()){
            LatLng location = new LatLng(data.getDouble(2), data.getDouble(3));
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(location);
            markerOptions.title("Position");
            mMap.addMarker(markerOptions);
            // if_
                        }
        data.close();

    }
}