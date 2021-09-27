package com.example.torch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnswitch;
    private Camera camera;
    private Camera.Parameters parameters;
    private  boolean isFlashon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (checkReadPermisson()){
            functionMain();


        }
        else{

            startActivity(new Intent(this,permisson.class));
        }


    }

    private void functionMain() {
        btnswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isFlashon){
                    turnOffFlash();
                }
                else{
                    turnOnFlash();
                }

            }
        });

        getCamera();
        toogleImage();
    }

    private void turnOffFlash() {
        if(isFlashon){
            if (camera==null|| parameters==null){
                return;
            }
            parameters=camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
            camera.stopPreview();
            isFlashon=false;
            toogleImage();
        }
    }
    private void turnOnFlash() {
        if(!isFlashon){
            if (camera==null|| parameters==null){
                return;
            }
            parameters=camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
            camera.startPreview();
            isFlashon=true;
            toogleImage();

    }}

    private void getCamera(){
        if (camera==null){
            try{

                camera=Camera.open();
                parameters=camera.getParameters();
            }
            catch (RuntimeException e){

            }
        }
    }
    private void toogleImage(){
        if(isFlashon){
            btnswitch.setBackgroundResource(R.drawable.on_button);
        }
        else{
            btnswitch.setBackgroundResource(R.drawable.off_button);
        }
    }

    private boolean checkReadPermisson() {

        int result= ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        return result==PackageManager.PERMISSION_GRANTED;
    }

}