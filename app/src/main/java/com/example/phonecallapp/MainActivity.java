package com.example.phonecallapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
                Intent intentCall = new Intent(Intent.ACTION_CALL);
                String number = txt_phoneNumber.getText().toString();
                if (number.trim().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Your Phone Number", Toast.LENGTH_SHORT).show();
                }else{
                    intentCall.setData(Uri.parse("tel"+number));
                }
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "Please Grant Permission", Toast.LENGTH_SHORT).show();
                    requestionPermission();
                }else{
                    startActivity(intentCall);
                }
            }
        });
    }
    private  void requestionPermission(){
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);

    }
}