package com.example.appenglishnew.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appenglishnew.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProgressActivity  extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
//        findViewById(R.id.nav_home)
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_profile) {
                    Intent intent = new Intent(ProgressActivity.this, ProfileActivity.class);
                    startActivity(intent);
                } else if (id==R.id.nav_home) {
                    Intent intent = new Intent(ProgressActivity.this, HomeActivity.class);
                    startActivity(intent);


                }else if (id==R.id.nav_settings) {
                    Intent intent = new Intent(ProgressActivity.this, SettingsActivity.class);
                    startActivity(intent);


                }

                return true;
            });
        } else {
            Toast.makeText(this, "NavigationView không tồn tại trong layout!", Toast.LENGTH_SHORT).show();
        }
    }
}
