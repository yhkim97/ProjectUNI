package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.ArrayList;
import java.util.List;

public class ZoneActivity extends MainActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ListViewAdapterData>arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    LatLng Loc1 =  new LatLng(37.57113098, 127.0096586 );
    LatLng Loc2 = new LatLng( 37.5710694,126.9886346);
    LatLng Loc3 =  new LatLng(37.562919, 126.948715);
    LatLng Loc4 = new LatLng(37.52404138,126.9803502);
    LatLng Loc5 =  new LatLng(37.589116, 127.0182146 );
    LatLng Loc6 = new LatLng(37.589116,127.0182146);
    LatLng Loc7 =  new LatLng(37.589116, 127.0182146 );
    LatLng Loc8 = new LatLng(37.589116,127.0182146);
    LatLng Loc9 =  new LatLng(37.57937947, 126.9911 );
    LatLng Loc10 = new LatLng(37.579201,126.975569);
    LatLng Loc11 = new LatLng(37.578748,126.995008);
    public ArrayList<LatLng> latLngs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);

        arrayList = new ArrayList<>();
        latLngs.add(Loc1);
        latLngs.add(Loc2);
        latLngs.add(Loc3);
        latLngs.add(Loc4);
        latLngs.add(Loc5);
        latLngs.add(Loc6);
        latLngs.add(Loc7);
        latLngs.add(Loc8);
        latLngs.add(Loc9);
        latLngs.add(Loc10);
        latLngs.add(Loc11);

        locationSource =
                new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        // onMapReady에서 NaverMap 객체를 받음
        mapFragment.getMapAsync(this);

        // 카메라 인식 버튼 클릭시 액티비티 전환
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CamActivity.class);
                startActivity(intent);
            }



        });



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("Apidata");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    int i = 0;
                    ListViewAdapterData listViewAdapterData = snapshot.getValue(ListViewAdapterData.class);
                    arrayList.add(i,listViewAdapterData);
                    // 담은 데이터들을 배열리스트에 넣고 리스트뷰에 보낼 준비
                    i++;
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ZoneActivity",String.valueOf(error.toException()));
            }
        });

        adapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);



    }



    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);
        ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE);
        for(int i = 0;i<latLngs.size();i++) {
            LatLng loc = latLngs.get(i);
            Marker mk = new Marker();
            mk.setVisible(true);
            mk.setPosition(loc);
            mk.setMap(naverMap);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,  @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // request code와 권한획득 여부 확인
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }
        }
    }
}
