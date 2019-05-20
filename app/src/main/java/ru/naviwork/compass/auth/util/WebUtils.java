package ru.naviwork.compass.auth.util;

import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.WebView;

import org.jetbrains.annotations.NotNull;

public class WebUtils {

    public static void setAcceptCookie(@NotNull WebView webView) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
    }
}
