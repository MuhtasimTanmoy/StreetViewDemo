package com.example.t.map;


import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * This shows how to create a simple activity with streetview
 */
public class MapsActivity extends FragmentActivity implements OnStreetViewPanoramaReadyCallback {

    private Handler handler;
    private Runnable updatePosTask;
    private StreetViewPanorama panorama;
    List<LatLng> positionList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        positionList = new ArrayList<>();
        positionList.add(new LatLng(23.724619, 90.395509));
        positionList.add(new LatLng(23.724545, 90.396110));
        positionList.add(new LatLng(23.724487, 90.396644));
        positionList.add(new LatLng(23.724418, 90.397102));
        positionList.add(new LatLng(23.724354, 90.397531));
        positionList.add(new LatLng(23.724261, 90.398035));
        positionList.add(new LatLng(23.724197, 90.398571));





        this.panorama = null;
        this.handler = new Handler();
        this.updatePosTask = createUpdateTask();


        StreetViewPanoramaFragment streetViewPanoramaFragment =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.map);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
    }

    @Override
    public void onStreetViewPanoramaReady(final StreetViewPanorama panorama) {
        this.panorama = panorama;
        this.panorama.setPosition(new LatLng(23.756959, 90.389930));
        handler.postDelayed(updatePosTask, 4000);
        //panorama.setStreetNamesEnabled(false);
    }

    private void updateCameraPosition() {
        this.panorama.setPosition(this.positionList.get(0));

        //this.panorama.animateTo();
        this.positionList.remove(0);
    }

    private Runnable createUpdateTask() {
        return new Runnable() {
            @Override
            public void run() {
                if (positionList.size() == 0) {
                    return;
                }

                if (panorama != null) {
                    updateCameraPosition();
                }

                handler.postDelayed(this, 4000);
            }
        };
    }

}

