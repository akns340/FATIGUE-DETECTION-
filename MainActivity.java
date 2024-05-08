package com.example.hardik.automateddriverdrowsinesscontrol;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener {

    FrameLayout frame;
    Button agree, disagree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("SafeSteerIO");
        frame = findViewById(R.id.frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new MonitorMenu()).commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Schedule the alarm
        scheduleAlarm();
    }

    // Method to schedule the alarm
    private void scheduleAlarm() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        long predictedSleepOnsetTime = dbHelper.calculateAverageSleepOnsetTime();

        if (predictedSleepOnsetTime > 0) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, predictedSleepOnsetTime, pendingIntent);
            Toast.makeText(this, "Alarm scheduled for next sleep onset time", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No sleep data available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
