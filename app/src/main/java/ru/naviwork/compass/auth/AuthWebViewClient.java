package ru.naviwork.compass.auth;

import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

public class AuthWebViewClient extends WebViewClient {

//    TODO ПРоверить необходимость кода
//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        String host = Uri.parse(url).getHost();
//        if (host.equals(Const.TARGET_URL_PREFIX)) {
//            // This is my web site, so do not override; let my WebView load
//            // the page
//            if (mWebviewPop != null) {
//                mWebviewPop.setVisibility(View.GONE);
//                mContainer.removeView(mWebviewPop);
//                mWebviewPop = null;
//            }
//            return false;
//        }
//
//        if (host.equals("m.facebook.com")) {
//            return false;
//        }
//        return true;
//    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(request.getUrl().toString());
        return super.shouldOverrideUrlLoading(view, request);
    }
}