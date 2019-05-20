package ru.naviwork.compass.auth;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import ru.naviwork.compass.R;
import ru.naviwork.compass.auth.util.WebUtils;
import ru.naviwork.compass.databinding.AuthLoginWebFrBinding;

public class AuthWebFragment extends Fragment {
    public static final String TAG = AuthWebFragment.class.getSimpleName();

    private AuthViewModel mViewModel;

    private AuthLoginWebFrBinding mViewBind;

    public AuthWebFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mViewBind == null)
            mViewBind = DataBindingUtil.
                    inflate(inflater, R.layout.auth_login_web_fr, container, false);

        if (mViewModel == null) {
            mViewModel = AuthLoginActivity.obtainViewModel(getActivity());
        }
        mViewBind.setViewmodel(mViewModel);

        setWebViewSettings(mViewBind.webview);

        mViewModel.initWebBrowser();

        return mViewBind.getRoot();
    }

    private void setWebViewSettings(WebView webView) {
        WebUtils.setAcceptCookie(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setSavePassword(true);
        webView.getSettings().setSaveFormData(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(mViewModel), "HTMLOUT");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mViewModel.setWebLoading();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mViewModel.setWebLoaded(url);
            }
        });
    }

    class MyJavaScriptInterface {

        private AuthViewModel authViewModel;

        MyJavaScriptInterface(AuthViewModel authViewModel) {
            this.authViewModel = authViewModel;
        }

        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processHTML(String html) {
            authViewModel.setWebLoadedHtml(html);
        }
    }

}
