package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;
        import androidx.cursoradapter.widget.SimpleCursorAdapter;

        import android.Manifest;
        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.ContentValues;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 문화재 검색 버튼클릭시 액티비티 전환
        Button search_cult = (Button) findViewById(R.id.search_cult);
        search_cult.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), PlaceActivity.class);
                startActivity(intent);
            }
        });

        //위치 검색 버튼 클릭시 액티비티 전환
        Button search_site = (Button) findViewById(R.id.search_site);
        search_site.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), ZoneActivity.class);
                startActivity(intent);
            }
        });

        //커뮤니티 버튼 클릭시 액티비티 전환
        Button search_cum = (Button) findViewById(R.id.search_cum);
        search_cum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CumActivity.class);
                startActivity(intent);
            }
        });







        //DB 생성 및 불러오기
//        DBHelper helper;
//        SQLiteDatabase db;
//        helper = new DBHelper(MainActivity.this, "InfoDB.db", null, 1);
//        db = helper.getWritableDatabase();
//        helper.onCreate(db);
//
//
//        ContentValues values = new ContentValues();
//        values.put("name", "숭례문");
//        values.put("place", "서울");
//        values.put("information1", "숭례문(崇禮門) 또는 서울 숭례문은 조선의 수도였던 한양의 4대문 중의 하나로 남쪽의 대문이다. 흔히 남대문이라고도 부른다. 남대문이라는 명칭을 일제강점기에 일본이 붙인 것이라는 설이 있는데, 이는 사실이 아니다. 조선왕조실록에도 숭례문을 남대문이라 칭한 기사가 다수 실려 있다. 서울 4대문 및 보신각의 이름은 오행사상을 따라 지어졌는데, 이런 명칭은 인, 의, 례, 지, 신의 5덕을 표현한 것이었으며, 숭례문의 '례'는 여기서 유래한 것이다.[3] 숭례문의 편액은 지봉유설에 따르면 양녕대군이 썼다고 알려져 있으나 이설이 많다. 1396년에 최유경이축성하였다. 1447년과 1479년 고쳐 지었다");
//        values.put("information2", "2006년 3월 3일, 숭례문이 도로에 의해 고립되어 버리는 것을 막고 시민들에게 문화재를 가까이 하기 위하여 서울특별시에서 숭례문의 중앙통로를 일반인에게 개방하였다. 2008년 2월 10일 오후 8시 40분 전후 방화범 채종기의 방화로 인해 불타기 시작해 약 5시간 후인 11일 오전 1시 54분, 목조 건물 일부와 석축 기반을 남기고 2층 누각이 모두 붕괴되어 소실되었다. 서울 중구청에서 외부인의 접근을 통제하기 위한 가림막을 설치했고, 기온이나 강수, 바람 등 기상요인에 의한 추가 붕괴 방지를 위하여 덮개를 씌웠다. 문화재청에서는 소실된 숭례문을 원형에 가깝게 복구할 계획을 발표하였다. 2013년 4월 29일 완공되어, 2013년 5월 4일 복원 완료를 기념하는 완공식이 거행되었다.\n");
//        db.insert("InfoDB.db", null, values);

//        ListView listView = (ListView) findViewById(R.id.listview);
//
//        helper = new DBHelper(MainActivity.this, "InfoDB.db", null, 1);
//        db = helper.getWritableDatabase();
//        helper.onCreate(db);
//
//        String sql = "select * from InfoTable;";
//        Cursor c = db.rawQuery(sql, null);
//        String[] strs = new String[]{"name"};
//        int[] ints = new int[]{R.id.listview_txt};
//
//        SimpleCursorAdapter adapter = null;
//        adapter = new SimpleCursorAdapter(listView.getContext(), R.layout.activity_zone, c, strs, ints, 0);
//
//        listView.setAdapter(adapter);

    }

}
