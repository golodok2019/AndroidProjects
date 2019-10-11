package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_READ_PHONE_STATE = 10001;
    // объявляем разрешение, которое нам нужно получить
    private static final String READ_PHONE_STATE_PERMISSION = Manifest.permission.READ_PHONE_STATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textAndroid);
        textView.setText(String.format(Locale.getDefault(), "Android ID: %s", Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)));

        TextView textNameDevice = findViewById(R.id.textNameDevice);
        textNameDevice.setText(String.format(Locale.getDefault(), "Name: %s", getDeviceName()));

        TextView versionName = findViewById(R.id.textName);
        versionName.setText(String.format(Locale.getDefault(), "Name: %s", BuildConfig.VERSION_NAME));

        TextView versionCode = findViewById(R.id.textCode);
        versionCode.setText(String.format(Locale.getDefault(), "Code: %d", BuildConfig.VERSION_CODE));
    }

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    private String getId() {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String m_deviceId = "";
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermission(READ_PHONE_STATE_PERMISSION, REQUEST_READ_PHONE_STATE);
        }
        else {

        }
        return m_deviceId;

    }

    private void requestPermission(String permission, int requestCode) {
        // запрашиваем разрешение
        ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
    }

}