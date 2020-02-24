package com.example.belisolbokregistratieapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity
{

    CheckBox check_1,check_2,check_3,check_4, check_7;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();


        check_1  = (CheckBox) findViewById(R.id.check_1);
        check_2  = (CheckBox) findViewById(R.id.check_2);
        check_3  = (CheckBox) findViewById(R.id.check_3);
        check_4  = (CheckBox) findViewById(R.id.check_4);

        //idle
        check_7  = (CheckBox) findViewById(R.id.check_7);

        TableControllerLogTypes mySettings = new TableControllerLogTypes(getApplicationContext());

        mySettings.messageType = 1;
        check_1.setChecked(mySettings.isActive());

        mySettings.messageType = 2;
        check_2.setChecked(mySettings.isActive());

        mySettings.messageType = 3;
        check_3.setChecked(mySettings.isActive());

        mySettings.messageType = 4;
        check_4.setChecked(mySettings.isActive());


        //load LogOffWhenIdle
        //deze naar de load brengen
        SharedPreferences prefs = getSharedPreferences("USER_PREFERENCES", MODE_PRIVATE);
        check_7.setChecked(prefs.getBoolean("LogOffWhenIdle",true));


    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        }
    }

    public void onCheckboxClicked(View view)
    {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        String configname = getResources().getResourceEntryName(view.getId());
        configname = configname.replace("check_","");


        TableControllerLogTypes mySettings = new TableControllerLogTypes(getApplicationContext());
        mySettings.create();

        mySettings.messageType = Integer.parseInt(configname);
        mySettings.messageValue = checked;
        mySettings.update();


    }

    public void onCheckboxClickedPreferences(View view)
    {

        SharedPreferences.Editor editor = getSharedPreferences("USER_PREFERENCES", MODE_PRIVATE).edit();

        editor.putBoolean("LogOffWhenIdle",((CheckBox) view).isChecked());
        editor.apply();
    }


}