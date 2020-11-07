package com.example.phonecallapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    EditText txt_phoneNumber;
    Button btn_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_phoneNumber = findViewById(R.id.txt_number);
        btn_call = findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();             
            }
        });
    }

    private void makePhoneCall() {
        String number = txt_phoneNumber.getText().toString();
        if (number.trim().length()>0){
            if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }else{
                String dial = "tel:"+number;
                startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
            }
        }else{
            Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else{
                Toast.makeText(this, "Permission Denided", Toast.LENGTH_SHORT).show();
            }
        }
    }
}