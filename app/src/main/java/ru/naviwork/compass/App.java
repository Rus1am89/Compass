package ru.naviwork.compass;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class App extends Application {
    public static final String TAG = App.class.getSimpleName();

    private static Context INSTANCE;

    private static String tempToken;

    public static Context getInstance() {
        return INSTANCE;
    }

    public static String getTempToken() {
        Log.d(TAG, "getTempToken: " + tempToken);
        if (tempToken != null) {
            String token = tempToken;
            tempToken = null;
            return token;
        }
        return null;
    }

    public static void setTempToken(String tempToken) {
        Log.d(TAG, "setTempToken: " + tempToken);
        App.tempToken = tempToken;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
