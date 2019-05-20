package ru.naviwork.compass.auth.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.text.TextUtils;

import ru.naviwork.compass.App;
import ru.naviwork.compass.auth.data.AuthAccount;

public class AuthUtils {

    public static String getToken() {
        String token = App.getTempToken();
        if (TextUtils.isEmpty(token)) {
            AccountManager accountManager = AccountManager.get(App.getInstance());
            Account[] accounts = accountManager.getAccountsByType(AuthAccount.TYPE);
            return accountManager.peekAuthToken(accounts[0], AuthAccount.TYPE);
        }
        return token;
    }

}
