package ru.naviwork.compass.auth;

public interface AuthUserActionListener {

    interface WebAuthCallback {

        void onLoginOk();

        void onLoginError();

    }

}
