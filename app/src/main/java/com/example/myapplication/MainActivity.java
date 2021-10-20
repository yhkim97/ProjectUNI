package com.example.myapplication;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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



    }
}
