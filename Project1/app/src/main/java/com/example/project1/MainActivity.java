package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textAndroid);
        textView.setText(String.format(Locale.getDefault(), "Android: %s (%d)", Build.VERSION.RELEASE, Build.VERSION.SDK_INT));

        TextView versionName = findViewById(R.id.textName);
        versionName.setText(String.format(Locale.getDefault(),"Name: %s",BuildConfig.VERSION_NAME));

        TextView versionCode = findViewById(R.id.textCode);
        versionCode.setText(String.format(Locale.getDefault(),"Code: %d",BuildConfig.VERSION_CODE));
    }
}
