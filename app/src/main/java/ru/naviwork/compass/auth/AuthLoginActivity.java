package ru.naviwork.compass.auth;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import ru.naviwork.compass.R;
import ru.naviwork.compass.ViewModelFactory;
import ru.naviwork.compass.databinding.AuthLoginActBinding;

public class AuthLoginActivity extends AuthAccountAuthenticatorActivity {

    public static final String EXTRA_TOKEN_TYPE = "ru.naviwork.compass.EXTRA_TOKEN_TYPE";
    private AuthLoginActBinding mViewDataBinding;
    private AuthViewModel mViewModel;
    private NavController mNav;

    public static AuthViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(AuthViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mViewDataBinding == null)
            mViewDataBinding = DataBindingUtil.setContentView(
                    this, R.layout.auth_login_act);
        if (mViewModel == null) {
            mViewModel = AuthLoginActivity.obtainViewModel(this);
        }
        mViewDataBinding.setViewmodel(mViewModel);
        mNav = Navigation.findNavController(this, R.id.auth_host_fragment);


//        subscribeToNavigationChanges();

    }

    private void subscribeToNavigationChanges() {
//        mViewModel.getLoginClickEvent().observe(this, authLoginSocialServices ->
//                FragmentUtils.replace(getSupportFragmentManager(),
//                        new AuthSocialButtonFragment(), R.id.container));
        mViewModel.getLoginEvent().observe(this, result -> {
            setAccountAuthenticatorResult(result);
            setResult(RESULT_OK);
            finish();
        });
    }


}
