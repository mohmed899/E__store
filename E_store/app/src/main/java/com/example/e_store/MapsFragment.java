package com.example.e_store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.LogWriter;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class MapsFragment extends Fragment {
    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    Geocoder geocoder;
    String address;
    View view;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.0604466, 31.2274268),8));

            locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    Log.i("xxx", " ");

                    mMap.clear();
                    LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(position)).setDraggable(true);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position,10));
                }
            };
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
            else {
                try {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0.1f, locationListener);

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                LatLng place = null;
                if (lastKnownLocation != null) {
                    place = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                    Log.i("my location ", place.longitude +"  "+ place.latitude);

                } else {
                    // Add a marker in Sydney and move the camera
                    place = new LatLng(30.0604466, 31.2274268);

                }
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(place).title("ur location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 16));
            }


            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    Log.i(" location ", latLng.latitude+ "  "+ latLng.longitude);
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(latLng).title("ur location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                    getAddress(latLng.latitude, latLng.longitude);
                }
            });

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_maps, container, false);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    void getAddress(double Latitude,double Longitude){
        geocoder = new Geocoder(getContext().getApplicationContext(), Locale.getDefault());

        try {
            ArrayList<Address> addresses = (ArrayList<Address>) geocoder.getFromLocation(Latitude, Longitude, 1);

            if (addresses != null && addresses.size()>0)
            {
                if (addresses.get(0).getThoroughfare() != null)
                {

                Toast.makeText(getContext(),addresses.get(0).getThoroughfare() , Toast.LENGTH_SHORT).show();
                    //address =(addresses.get(0).getAddressLine(0));
                    address =addresses.get(0).getThoroughfare();

                    MapsFragmentDirections.ActionMapsFragmentToSummaryFragment  action = MapsFragmentDirections.actionMapsFragmentToSummaryFragment() ;
                    action.setAdsress(address);
                    Navigation.findNavController(view).navigate(action);
                }
             //   Log.i("addd",address);

            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}