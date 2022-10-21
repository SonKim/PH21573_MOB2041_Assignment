package com.example.ass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ass.fragment.DTFragment;
import com.example.ass.fragment.QLLSFragment;
import com.example.ass.fragment.QLPMFragment;
import com.example.ass.fragment.QLSFragment;
import com.example.ass.fragment.QLTVFragment;
import com.example.ass.fragment.Top10Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer_layout;
    NavigationView navigation_view;
    QLPMFragment qlpmFragment = new QLPMFragment();
    QLLSFragment qllsFragment = new QLLSFragment();
    QLSFragment qlsFragment = new QLSFragment();
    QLTVFragment qltvFragment = new QLTVFragment();
    Top10Fragment top10Fragment = new Top10Fragment();
    DTFragment dtFragment = new DTFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer_layout = findViewById(R.id.drawer_layout);
        navigation_view = findViewById(R.id.navigation_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.myFrameLayout,qlpmFragment).commit();
        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.itQLPM:
                        getSupportFragmentManager().beginTransaction().replace(R.id.myFrameLayout,qlpmFragment).commit();
                        break;
                    case R.id.itQLLS:
                        getSupportFragmentManager().beginTransaction().replace(R.id.myFrameLayout,qllsFragment).commit();
                        break;
                    case R.id.itQLS:
                        getSupportFragmentManager().beginTransaction().replace(R.id.myFrameLayout,qlsFragment).commit();
                        break;
                    case R.id.itQLTV:
                        getSupportFragmentManager().beginTransaction().replace(R.id.myFrameLayout,qltvFragment).commit();
                        break;
                    case R.id.itTop10:
                        getSupportFragmentManager().beginTransaction().replace(R.id.myFrameLayout,top10Fragment).commit();
                        break;
                    case R.id.itDT:
                        getSupportFragmentManager().beginTransaction().replace(R.id.myFrameLayout,dtFragment).commit();
                        break;
                    case R.id.itDMK:
                        Intent intent1 = new Intent(MainActivity.this,DoiMatKhauActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.itDX:
                        Intent intent = new Intent(MainActivity.this,DangNhapActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                }
                drawer_layout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}