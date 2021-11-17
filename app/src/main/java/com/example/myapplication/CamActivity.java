package com.example.myapplication;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.naver.maps.map.LocationTrackingMode;

public class CamActivity extends AppCompatActivity {

    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1001;
    private static final String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
    };
    Camera camera;
    FrameLayout frameLayout;
    ShowCamera showCamera;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        frameLayout = (FrameLayout) findViewById(R.id.camera_preview);

        int permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if (permssionCheck != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "사진인식기능 사용을 위해 카메라 권한을 허용해주세요", Toast.LENGTH_LONG).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(this, "사진인식기능을 사용하려면 카메라 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
                Toast.makeText(this, "사진인식기능 사용이 가능합니다!", Toast.LENGTH_LONG).show();

            }
        }
        ActivityCompat.requestPermissions(this, PERMISSIONS, MY_PERMISSIONS_REQUEST_CAMERA );

    }

    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

        }
    };

    public void upload(View v) {
        if (camera != null) {
            camera.takePicture(null, null, mPictureCallback);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                camera = Camera.open();
                showCamera = new ShowCamera(this, camera);
                frameLayout.addView(showCamera);
            }
        }
    }
}
