package com.example.change_behavior_of_apps_based_on_user_preferences;

import android.app.Application;

public class UserSettings extends Application {

    //delcaring string
    public static final String PREFERENCES = "preferences";

    //Creating different themes
    public static final String   CUSTOM_THEME = "customTheme";
    public static final String LIGHT_THEME = "lightTheme";
    public static final String DARK_THEME = "darkTheme";

    private String customTheme;


    //creating getters and setters for customTheme
    public String getCustomTheme() {
        return customTheme;
    }

    public void setCustomTheme(String customTheme) {
        this.customTheme = customTheme;
    }
}
