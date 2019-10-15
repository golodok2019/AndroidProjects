package com.example.project1;

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

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //private CustomDialogFragment dialog;

    private static final int REQUEST_CODE_READ_PHONE = 1;
    private static boolean READ_PHONE_GRANTED = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TextView view = (TextView) findViewById (R.id.textAndroid);
        //dialog = new CustomDialogFragment();
        setContentView(R.layout.activity_main);

        TextView versionName = findViewById(R.id.textName);
        versionName.setText(String.format(Locale.getDefault(), "Name: %s", BuildConfig.VERSION_NAME));

        TextView versionCode = findViewById(R.id.textCode);
        versionCode.setText(String.format(Locale.getDefault(), "Model: %s", android.os.Build.MODEL));

        TextView view = (TextView) findViewById(R.id.textAndroid);

        int hasReadContactPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        // если устройство до API 23, устанавливаем разрешение
        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {
            READ_PHONE_GRANTED = true;
        } else {
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
            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE_READ_PHONE);
        }
        if (READ_PHONE_GRANTED) {
            TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            view.setText("Id "+TelephonyMgr.getDeviceId());
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        TextView view = (TextView) findViewById(R.id.textAndroid);
        switch (requestCode) {
            case REQUEST_CODE_READ_PHONE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    READ_PHONE_GRANTED = true;
                }
        }
        if (READ_PHONE_GRANTED) {
            TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED || READ_PHONE_GRANTED == true) {
                view.setText("Id "+TelephonyMgr.getDeviceId());
            }
        }
        else{
            view.setText("Need set permition");
        }
    }
}

