package test.com.appdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity {
    private GoogleMap googleMap;
    double latitude;
    double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i=getIntent();
       System.out.println( i.getDoubleExtra("latitude", 0));
        latitude=i.getDoubleExtra("latitude", 0);
        longitude=i.getDoubleExtra("longitude", 0);

        System.out.println("Latitude==>>"+latitude+"  Longitude==>>"+longitude);
        try {
            // Loading map
            initilizeMap();
            // Changing map type
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            // Showing / hiding your current location
            googleMap.setMyLocationEnabled(false);
            // Enable / Disable zooming controls
            googleMap.getUiSettings().setZoomControlsEnabled(false);
            // Enable / Disable Rotate gesture
            googleMap.getUiSettings().setRotateGesturesEnabled(false);
            // Enable / Disable zooming functionality
            googleMap.getUiSettings().setZoomGesturesEnabled(true);
            MarkerOptions a = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.iconmap))
                    .position(new LatLng(latitude,longitude));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(latitude,longitude))      // Sets the center of the map to location user
                    .zoom(14)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            //addMarkerWithCameraZooming(MainActivity.this,googleMap,latitude,longitude,"Hiii",true);
            //animateMarker(m,lt,true);
            googleMap.addMarker(a);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map_this)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();



        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
