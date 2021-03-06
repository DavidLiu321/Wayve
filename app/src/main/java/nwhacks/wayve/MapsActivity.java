package nwhacks.wayve;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.location.*;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;


import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private SupportMapFragment supportMapFragment;
    private ArrayList<Marker> markersArray = new ArrayList<>();

    private LocationManager locationManager;

    static TextView res;

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


        ImageButton search = findViewById(R.id.dismiss);

        res = findViewById(R.id.result);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationBox dialog = new LocationBox();
                dialog.show(getSupportFragmentManager(), "");
            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        // check the network provider is enabled
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    //instantiate class, LatLng
                    LatLng latLng = new LatLng(latitude, longitude);
                    Geocoder  geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality()+ ",";
                        str += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }
        else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    //instantiate class, LatLng
                    LatLng latLng = new LatLng(latitude, longitude);
                    Geocoder  geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str;
                        str = "User Current Location, ";
                        if (!addressList.isEmpty()) {
                            if (addressList.get(0).getLocality() != null) {
                                str = addressList.get(0).getLocality() + ",";
                            }
                            str += addressList.get(0).getCountryName();
                        } else {
                            str = "No Localities Nearby";
                        }
                        //mMap.clear();
                        Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        markersArray.add(marker);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }

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

        LatLng vancouver = new LatLng(49.2827, -123.1207);
        Marker marker = mMap.addMarker(new MarkerOptions().position(vancouver).title("Marker for Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vancouver));
        markersArray.add(marker);


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(latLng);

                mMap.clear();

                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng)); // move map to where you click cursor
                mMap.addMarker(markerOptions);
                Marker marker = mMap.addMarker(markerOptions);
                markersArray.add(marker);

                Rating dialog = new Rating();
                dialog.show(getSupportFragmentManager(), "");

                for (int i = 0; i < markersArray.size()-1; i++) {

                    createMarker(markersArray.get(i).getPosition().latitude,
                    markersArray.get(i).getPosition().longitude);

                    System.out.println("!!!" + markersArray.get(i).getPosition());
                }
            }


        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                System.out.println("testings");

                Result dialog = new Result();
                dialog.show(getSupportFragmentManager(), "");

                marker.showInfoWindow();

                return true;
            }


        });


    }

    protected Marker createMarker(double latitude, double longitude) {

        return mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitude, longitude)));
//                .title(title)
//                .snippet(snippet)
//                .icon(BitmapDescriptorFactory.fromResource(iconResID)));
    }


}
