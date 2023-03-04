package pjatk.s10552.geoloc;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String LATITUDE_STRING = "latitude";
    public static final String LONGITUDE_STRING = "longitude";
    private GoogleMap mMap;
    private LatLng currentSearchLocation;
    public static final String NO_PREVIOUS_SEARCH_ERROR = "In order to save you need to find location first.";
    private LocationManager locman = null;
    private String provider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
        Log.i("_loc_test", "Provider: "+loc.getProvider());
        Log.i("_loc_test", "Latitude:  "+loc.getLatitude());
        Log.i("_loc_test", "Longitude: "+loc.getLongitude());

        currentSearchLocation = new LatLng(loc.getLatitude(), loc.getLongitude());

        final Button saveButton = (Button) findViewById(R.id.save_location_button);

        final Intent addLocationActivityIntent = new Intent(this, AddLocationActivity.class);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentSearchLocation!=null){
                    addLocationActivityIntent.putExtra(LATITUDE_STRING, currentSearchLocation.latitude);
                    addLocationActivityIntent.putExtra(LONGITUDE_STRING, currentSearchLocation.longitude);
                    startActivity(addLocationActivityIntent);
                } else {
                    Toast.makeText(MapsActivity.this, NO_PREVIOUS_SEARCH_ERROR, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentSearchLocation);
        markerOptions.title("Current Position");
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentSearchLocation,11));

    }
}