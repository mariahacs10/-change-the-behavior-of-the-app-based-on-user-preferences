package com.example.change_behavior_of_apps_based_on_user_preferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity {

    /** DATE STARTED 8/15/2022

     Date fixed the problem : 8/16/2022

     The fix was a easy and simple i think its because his video was going to fast and i couldn't see it
     but when i rewatched the video i saw him quickly add it to the loadSharedPreferences()

     Video linK:
     https://www.youtube.com/watch?v=-u63b5X2NqE

     **/

    //Declare a couple of views
    private View parentView;
    private SwitchMaterial themeSwitch;

    //Now declare your 2 text views
    private TextView themeTV, titleTV;

    //Now create another variable called settings
    //that is going to implement the settings the users input
    private UserSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //(he didn't explain what this meant)
        settings = (UserSettings) getApplication();

        //Now create a function (aka. a method) call initWidget()
        //This method is going to find all the views by there ids
        initWidget();
        //cast it to the user settings class
        loadSharedPreferences();
        //This might mean when u switch between different modes?
        initSwitchListener();
    }

    //Thid method is taking all the ids from the activity-main
    private void initWidget() {
        themeTV = findViewById(R.id.themeTV);
        titleTV = findViewById(R.id.titleTV);
        themeSwitch = findViewById(R.id.themeSwitch);
        parentView = findViewById(R.id.parentView);
    }

    //This method is going to load any shared preferences
    //or any user settings if they exist otherwise were going
    //to load a default
    private void loadSharedPreferences() {
        //Setting the preferences
        SharedPreferences sharedPreferences = getSharedPreferences(UserSettings.PREFERENCES, MODE_PRIVATE);

        //Setting what the default / Custom theme is going to be
        String theme = sharedPreferences.getString(UserSettings.CUSTOM_THEME, UserSettings.LIGHT_THEME);
        //Then setting it
        settings.setCustomTheme(theme);
        /** IN ORDER FOR THIS TO WORK YOU NEED TO PUT THE updateViews() METHOD IN BOTH initSwitchListner() AND loadSharedPreferences()
         WITHOUT PUTTING THIS IN IT WONT SAVE YOUR DATA **/
        updateView();
    }

    private void initSwitchListener() {

        //This is going to determine whether or not the switch
        //is turned on or off
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                //Checking if the user is using dark theme
                if(checked)
                    settings.setCustomTheme(UserSettings.DARK_THEME);

                //Otherwise it is going to have light theme (the default)
                else
                    settings.setCustomTheme(UserSettings.LIGHT_THEME);

                //This is going to make sure this is going
                //to get edited
                SharedPreferences.Editor editor = getSharedPreferences(UserSettings.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(UserSettings.CUSTOM_THEME, settings.getCustomTheme());
                //Now you need to apply the edit
                editor.apply();
                //Now you need to create a updateView()
                //to update your views
                updateView();

            }
        });
    }

    private void updateView() {
        //This is declaring a variable black and white
        final int black = ContextCompat.getColor(this, R.color.black);
        final int white = ContextCompat.getColor(this, R.color.white);

       //This is going to check if are theme is dark or white theme
       if(settings.getCustomTheme().equals(UserSettings.DARK_THEME))
       {
           //This is going to be if its dark
           titleTV.setTextColor(white);
           themeTV.setTextColor(white);
           themeTV.setText("Dark");
           parentView.setBackgroundColor(black);
           themeSwitch.setChecked(true);
       }
       //This is going to check else white
       else
       {
           titleTV.setTextColor(black);
           themeTV.setTextColor(black);
           themeTV.setText("Light");
           parentView.setBackgroundColor(white);
           themeSwitch.setChecked(false);
       }

    }
}