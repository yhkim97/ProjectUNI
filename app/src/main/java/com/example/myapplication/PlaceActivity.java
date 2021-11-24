package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PlaceActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        TextView main = (TextView)findViewById(R.id.text_main);
        Intent intentmain = getIntent();
        String datamain = intentmain.getStringExtra("c_name");
        main.setText(datamain);

        TextView sub = (TextView)findViewById(R.id.text_sub);
        Intent intentsub = getIntent();
        String datasub = intentsub.getStringExtra("info");
        sub.setText(datasub);

    }
}
