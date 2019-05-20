package ru.naviwork.compass.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import ru.naviwork.compass.R;
import ru.naviwork.compass.databinding.AuthLoginFrBinding;

import static androidx.core.util.Preconditions.checkNotNull;

public class AuthSocialButtonFragment extends Fragment implements AuthUserActionListener {
    public static final String TAG = AuthSocialButtonFragment.class.getSimpleName();

    private AuthViewModel mViewModel;

    private AuthLoginFrBinding mViewBind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mViewBind == null)
            mViewBind = DataBindingUtil.
                    inflate(inflater, R.layout.auth_login_fr, container, false);

        if (mViewModel == null)
            mViewModel = AuthLoginActivity.obtainViewModel(checkNotNull(getActivity()));

        subscribeToNavigationActions();

        return mViewBind.getRoot();
    }

    private void subscribeToNavigationActions() {
        createNavOnClickListener(mViewBind.loginVk, R.id.authWebFragment);
        createNavOnClickListener(mViewBind.loginFacebook, R.id.authWebFragment);
        createNavOnClickListener(mViewBind.loginMicrosoft, R.id.authWebFragment);
        createNavOnClickListener(mViewBind.loginYandex, R.id.authWebFragment);
        createNavOnClickListener(mViewBind.loginGoogle, R.id.authWebFragment);
        createNavOnClickListener(mViewBind.loginOk, R.id.authWebFragment);
        createNavOnClickListener(mViewBind.loginTwitter, R.id.authWebFragment);
        createNavOnClickListener(mViewBind.loginMail, R.id.authWebFragment);
    }

    private void createNavOnClickListener(@NonNull View view, @IdRes final int resId) {
        view.setOnClickListener(Navigation.createNavigateOnClickListener(resId));
    }

}
