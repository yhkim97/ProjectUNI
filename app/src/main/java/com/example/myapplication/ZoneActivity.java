package com.example.myapplication;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.text.IDNA;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.annotation.NonNull;

import androidx.core.app.ActivityCompat;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
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
    LatLng Loc1 = new LatLng(37.56641154248519, 126.97649456235365);
    LatLng Loc2 = new LatLng(37.566884, 126.977610);
    LatLng Loc3 = new LatLng(37.56532347115313, 126.9788652732633);
    Marker marker1 = new Marker();
    Marker marker2 = new Marker();
    Marker marker3 = new Marker();
    InfoWindow infoWindow = new InfoWindow();

    LocationManager locationManager;
    LocationListener locationListener;

    //여기까진 뻔하다
    ListView lvList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);

        // 지도를 출력할 프레그먼트 영역 인식
        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        // 지도 사용이 준비되면 onMapReady 콜백 함수 호출
        mapFragment.getMapAsync(this);

        lvList = (ListView)findViewById(R.id.lv_list);

        displayList();

    }


    void displayList() {
        //Dbhelper의 읽기모드 객체를 가져와 SQLiteDatabase에 담아 사용준비
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();

        //Cursor라는 그릇에 목록을 담아주기
        Cursor cursor = database.rawQuery("SELECT * FROM student", null);

        //리스트뷰에 목록 채워주는 도구인 adapter준비
        ListViewAdapter adapter = new ListViewAdapter();

        //목록의 개수만큼 순회하여 adapter에 있는 list배열에 add
        while (cursor.moveToNext()) {
            //num 행은 가장 첫번째에 있으니 0번이 되고, name은 1번
            adapter.addItemToList(cursor.getInt(0), cursor.getString(1));
        }

        //리스트뷰의 어댑터 대상을 여태 설계한 adapter로 설정
        lvList.setAdapter(adapter);
    }


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.MnaverMap = naverMap;

        marker1.setTag("숭례문");
        marker1.setPosition(Loc1);
        marker1.setMap(naverMap);
        marker1.setOnClickListener(overlay -> {
            infoWindow.open(marker1);
            return true;
        });
        marker2.setTag("보신각");
        marker2.setPosition(Loc2);
        marker2.setMap(naverMap);
        marker2.setOnClickListener(overlay -> {
            infoWindow.open(marker2);
            return true;
        });
        marker3.setTag("흥인지문");
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


//        //onItemClickListener를 추가
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(
//                        getApplicationContext(), // 현재화면의 제어권자
//                        PlaceActivity.class);
//
//            }
//        });



    }
}




