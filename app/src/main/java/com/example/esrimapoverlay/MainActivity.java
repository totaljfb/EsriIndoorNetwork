package com.example.esrimapoverlay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.esri.arcgisruntime.layers.ArcGISMapImageLayer;
import com.esri.arcgisruntime.layers.WmsLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MapView mMapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMapView = findViewById(R.id.mapView);
        Basemap.Type basemapType = Basemap.Type.STREETS_VECTOR;
        double latitude = 38.92907534;
        double longitude = -77.23981402;
        int levelOfDetail = 20;
        ArcGISMap map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
        mMapView.setMap(map);
        //addSFILayer();

        // hold a list of uniquely-identifying WMS layer names to display
        List<String> wmsLayerNames = Arrays.asList("1","2","3","4","5","7","8","9","10","11");

        // create a new WMS layer displaying the specified layers from the service
        WmsLayer wmsLayer = new WmsLayer("http://sfi.gotdns.com:6080/arcgis/services/dev/SFI_VIEW/MapServer/WMSServer", wmsLayerNames);
        map.getOperationalLayers().add(wmsLayer);
        mMapView.setMap(map);
    }

    @Override
    protected void onPause() {
        if (mMapView != null) {
            mMapView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mMapView != null) {
            mMapView.dispose();
        }
        super.onDestroy();
    }

//    private void addSFILayer() {
//        ArcGISMapImageLayer mapImageLayer = new ArcGISMapImageLayer("http://spatial-desktop:6080/arcgis/rest/services/NetworkServices/SFI_Data/MapServer");
//        map.getOperationalLayers().add(mapImageLayer);
//        mMapView.setMap(map);
//    }
}
