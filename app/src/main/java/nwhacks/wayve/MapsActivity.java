package nwhacks.wayve;

import android.nfc.Tag;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Circle;

import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment supportMapFragment;
    private ArrayList<Marker> markersArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocationBox dialog = new LocationBox();
        dialog.show(getSupportFragmentManager(), "");

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ImageButton search = findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationBox dialog = new LocationBox();
                dialog.show(getSupportFragmentManager(), "");
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        mMap.setOnMapClickListener(new OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(latLng);

                markerOptions.title(latLng.latitude + " : " + latLng.longitude); // sets title  by clicking marker

                mMap.clear();

                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng)); // move map to where you click cursor
                Marker marker = mMap.addMarker(markerOptions);
                markersArray.add(marker);


                for (int i = 0; i < markersArray.size(); i++) {

                    createMarker(markersArray.get(i).getPosition().latitude,
                    markersArray.get(i).getPosition().longitude);

                    System.out.println("!!!" + markersArray.get(i).getPosition());
                }
            }


        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                System.out.println("testings" + marker.getTitle());

                marker.showInfoWindow();

                return true;
            }



        });


    }

    protected Marker createMarker(double latitude, double longitude) {

        return mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitude, longitude))
                        .anchor(0.5f, 0.5f));
//                .title(title)
//                .snippet(snippet)
//                .icon(BitmapDescriptorFactory.fromResource(iconResID)));
    }


}
