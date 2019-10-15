package com.lord.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project1.BuildConfig;
import com.example.project1.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView versionName = findViewById(R.id.textName);
        versionName.setText(String.format(Locale.getDefault(), "Name: %s", BuildConfig.VERSION_NAME));

        TextView versionCode = findViewById(R.id.textCode);
        versionCode.setText(String.format(Locale.getDefault(), "Model: %s", android.os.Build.MODEL));

        TextView view =  findViewById(R.id.textAndroid);

        int hasReadContactPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (hasReadContactPermission != PackageManager.PERMISSION_GRANTED) {
             AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Alert")
                  .setMessage("Need youre permision to show phone Id!")
                  .setCancelable(false)
                  .setNegativeButton("OK",
                    new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int id) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                      dialog.cancel();
                }
            });
             builder.show();
        }
        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            view.setText("Id "+TelephonyMgr.getDeviceId());
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        int hasReadContactPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        TextView view =  findViewById(R.id.textAndroid);

        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED){
            TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            view.setText("Id "+TelephonyMgr.getDeviceId());
        }
        else{
            view.setText("Need set permition");
        }
    }
}

