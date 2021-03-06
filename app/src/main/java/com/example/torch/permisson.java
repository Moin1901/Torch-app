package com.example.torch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class permisson extends AppCompatActivity {
    Button btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisson);


        btnAccept=findViewById(R.id.btnAccept);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(permisson.this,new String[] {Manifest.permission.CAMERA},12);

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case 12:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();

                }
                else{
                    checkPermission();
                }


                break;
        }
    }
    public boolean checkPermission(){
        int result= ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        return result==PackageManager.PERMISSION_GRANTED;
    }
}