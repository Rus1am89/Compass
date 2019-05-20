package ru.naviwork.compass.auth;

import android.accounts.AccountManager;
import android.app.Application;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

import com.blankj.utilcode.util.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.naviwork.compass.App;
import ru.naviwork.compass.Const;
import ru.naviwork.compass.SingleLiveEvent;
import ru.naviwork.compass.auth.data.AuthAccount;
import ru.naviwork.compass.auth.data.AuthSocialServices;
import ru.naviwork.compass.auth.data.AuthToken;
import ru.naviwork.compass.auth.data.User;
import ru.naviwork.compass.auth.data.source.remote.CompassRestClient;
import ru.naviwork.compass.auth.util.StringUtil;

public class AuthViewModel extends AndroidViewModel {
    public static final String TAG = AuthViewModel.class.getSimpleName();

    private final SingleLiveEvent<AuthSocialServices> mLoginClickEvent = new SingleLiveEvent<>();

    private final SingleLiveEvent<Bundle> mLoginEvent = new SingleLiveEvent<>();
    public ObservableBoolean mWebLoading = new ObservableBoolean();
    public ObservableField<String> mUrl = new ObservableField<>();

    public AuthViewModel(@NonNull Application application) {
        super(application);
    }

    public SingleLiveEvent<AuthSocialServices> getLoginClickEvent() {
        return mLoginClickEvent;
    }

    public SingleLiveEvent<Bundle> getLoginEvent() {
        return mLoginEvent;
    }

    void setUrl(String url) {
        mUrl.set(url);
    }

    void initWebBrowser() {
        mUrl.set(Const.BASE_URL + Const.REST_FACEBOOK);
    }

    void setWebLoading() {
        mWebLoading.set(true);
    }

    /* This call inject JavaScript into the page which just finished loading. */
    void setWebLoaded(String url) {
        mWebLoading.set(false);

        String host = Uri.parse(url).getHost();
        if (host != null && host.equals(Const.TARGET_URL_PREFIX)) {
            mUrl.set("javascript:window.HTMLOUT.processHTML" +
                    "('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
        }

    }

    void setWebLoadedHtml(String html) {
        mUrl.set(null);
        // TODO Изменить проверку вхождения
        if (!html.isEmpty() && html.contains("access_token")) {
            String json = StringUtil.removeHtmlAtribute(html);
            addAccount(StringUtil.getAuthToken(json));
        }
    }
` `

    private void addAccount(AuthToken authToken) {
        App.setTempToken(authToken.getAccessToken());

        CompassRestClient.getInstance().getCurrentUserAsync(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    ToastUtils.showLong("onResponse: " + response.body().toString());
                    AuthAccount account = new AuthAccount(user.getName());

                    final AccountManager am = AccountManager.get(getApplication().getApplicationContext());
                    final Bundle result = new Bundle();
                    if (am.addAccountExplicitly(account, "", new Bundle())) {
                        result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
                        result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
                        result.putString(AccountManager.KEY_AUTHTOKEN, authToken.getAccessToken());
                        am.setAuthToken(account, account.type, authToken.getAccessToken());
                    } else {
                        result.putString(AccountManager.KEY_ERROR_MESSAGE, "account_already_exists");
                    }
                } else
                    ToastUtils.showLong("onResponse: error");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                ToastUtils.showLong("onFailure: error");
            }
        });

//        CompassApi.compassClient.getCurrentUser().enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if (response.body() != null) {
//                    ToastUtils.showLong("onResponse: " + response.body().toString());
//                    AuthAccount authAccount = new AuthAccount();
//
//                    final AccountManager am = AccountManager.get(getApplication().getApplicationContext());
//                    final Bundle result = new Bundle();
//                    if (am.addAccountExplicitly(account, password, new Bundle())) {
//                        result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
//                        result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
//                        result.putString(AccountManager.KEY_AUTHTOKEN, token);
//                        am.setAuthToken(account, account.type, token);
//                    } else {
//                        result.putString(AccountManager.KEY_ERROR_MESSAGE, getString(R.string.account_already_exists));
//                    }
//                } else
//                    ToastUtils.showLong("onResponse: error");
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                ToastUtils.showLong("onFailure: error");
//            }
//        });


//        mLoginEvent.setValue(result);
    }
}
