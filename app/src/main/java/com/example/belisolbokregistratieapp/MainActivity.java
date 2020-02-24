package com.example.belisolbokregistratieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.material.navigation.NavigationView;

import java.util.Timer;
import java.util.TimerTask;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    private Timer timer;
    int idleTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen, R.string.drawerClose);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        //set idle
        SharedPreferences prefs = getSharedPreferences("USER_PREFERENCES", MODE_PRIVATE);
        boolean setidleTime = prefs.getBoolean("LogOffWhenIdle",true);
        if (setidleTime)
        {
            idleTime = 5 * 60 * 1000; //zet op 5min
        }
        else
        {
            idleTime = 60 * 60 * 1000 * 8; //zet op een werkdag
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        timer = new Timer();
        Log.i("Main", "Invoking logout timer");
        LogOutTimerTask logoutTimeTask = new LogOutTimerTask();
        timer.schedule(logoutTimeTask, idleTime); //auto logout in 5 minutes
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null) {
            timer.cancel();
            Log.i("Main", "cancel timer");
            timer = null;
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        Intent myIntent;

        switch(menuItem.getItemId())
        {
            case R.id.ItemDelivery:
                //Toast.makeText(MainActivity.this,"Expeditie", Toast.LENGTH_SHORT).show();
                myIntent = new Intent(MainActivity.this, Registration.class);
                MainActivity.this.startActivity(myIntent);
                break;
            case R.id.ItemSearch:
                //Toast.makeText(MainActivity.this,"Zoeken", Toast.LENGTH_SHORT).show();
                myIntent = new Intent(MainActivity.this, ListActivity.class);
                MainActivity.this.startActivity(myIntent);

                break;
            case R.id.ItemConfiguration:
                myIntent = new Intent(MainActivity.this, SettingsActivity.class);
                MainActivity.this.startActivity(myIntent);
                break;

            //case R.id.ItemConfigurationLookup:
            //    myIntent = new Intent(MainActivity.this, ExpeditionRegistrationListActivity.class);
            //    MainActivity.this.startActivity(myIntent);
            //    break;
        }
        return false;
    }

    private class LogOutTimerTask extends TimerTask {

        @Override
        public void run() {

            //redirect user to login screen
            Intent i = new Intent(MainActivity.this, Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
    }
}
