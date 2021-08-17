package com.danicode.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private Switch swNotification;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.swNotification = findViewById(R.id.swNotification);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // cargar las preferencias => getBoolean(key, defaultValue)
        boolean notification = this.sharedPreferences.getBoolean("notification", true);
        this.swNotification.setChecked(notification);

        this.swNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    sharedPreferences.edit().putBoolean("notification", true).apply();
                } else {
                    sharedPreferences.edit().putBoolean("notification", false).apply();
                }
            }
        });
    }

    public void irAVideo(View view) {
        Intent intent = new Intent(MainActivity.this, Video.class);
        startActivity(intent);
    }

    public void irAToolbar(View view) {
        Intent intent = new Intent(MainActivity.this, ToolbarActivity.class);
        startActivity(intent);
    }
}