package ru.naviwork.compass.auth;

import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;

import androidx.databinding.BindingAdapter;

public class AuthBindingAdapters {

    @BindingAdapter("auth:web_view")
    public static void loadUrl(WebView webView, String url) {
        if (TextUtils.isEmpty(url)) {
            webView.setVisibility(View.GONE);
        } else {
            if (webView.getVisibility() == View.GONE)
                webView.setVisibility(View.VISIBLE);
            webView.loadUrl(url);
        }
    }

}
