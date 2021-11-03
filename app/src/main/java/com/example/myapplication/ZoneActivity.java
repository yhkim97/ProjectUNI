package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.icu.text.IDNA;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.annotation.NonNull;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.view.View;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.Arrays;

public class ZoneActivity extends FragmentActivity implements OnMapReadyCallback {

    private FusedLocationSource mLocationSource;
    private NaverMap MnaverMap;
    LatLng prev_LOC = null;
    LatLng curr_LOC;
    LatLng Loc1 = new LatLng(37.47568,126.61738);
    LatLng Loc2 = new LatLng(37.43940,126.68724);
    LatLng Loc3 = new LatLng(37.71341,126.45151);
    Marker marker1 = new Marker();
    Marker marker2 = new Marker();
    Marker marker3 = new Marker();
    InfoWindow infoWindow = new InfoWindow();

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);

        // 지도를 출력할 르레그먼트 영역 인식
        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        // 지도 사용이 준비되면 onMapReady 콜백 함수 호출
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.MnaverMap = naverMap;

        marker1.setTag("인천 차이나타운");
        marker1.setPosition(Loc1);
        marker1.setMap(naverMap);
        marker1.setOnClickListener(overlay -> {
            infoWindow.open(marker1);
            return true;
        });
        marker2.setTag("인천 향교");
        marker2.setPosition(Loc2);
        marker2.setMap(naverMap);
        marker2.setOnClickListener(overlay -> {
            infoWindow.open(marker2);
            return true;
        });
        marker3.setTag("인천 강화도");
        marker3.setPosition(Loc3);
        marker3.setMap(naverMap);
        marker3.setOnClickListener(overlay -> {
            infoWindow.open(marker3);
            return true;
        });
    infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
        @NonNull
        @Override
        public CharSequence getText(@NonNull InfoWindow infoWindow) {
            return (CharSequence)infoWindow.getMarker().getTag();
        }
    });

    }
}




