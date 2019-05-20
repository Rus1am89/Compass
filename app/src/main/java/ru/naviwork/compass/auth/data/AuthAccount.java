package ru.naviwork.compass.auth.data;

import android.accounts.Account;

public class AuthAccount extends Account {

    public static final String TYPE = "ru.naviwork.compass";

    public static final String TOKEN_FULL_ACCESS = "ru.naviwork.compass.TOKEN_FULL_ACCESS";

    public AuthAccount(String name) {
        super(name, TYPE);
    }
}
