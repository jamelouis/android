package io.github.jamelouis.travel_mate.login;


import android.os.Handler;

public class LoginPresenter {
    private LoginView mView;

    public void bind(LoginView view){
        this.mView = view;
    }

    public void unbind() {
        mView = null;
    }

    public void signUp() {
        mView.openSignUp();
    }

    public void ok_signUp(final String name, final String email, String pass, final Handler mhandler) {
        mView.showLoadingDialog();

        mView.openLogin();
        mView.setLoginEmail(email);
        mView.showMessage("signup succeeded! please login");
    }

    public void login() {
        mView.openLogin();
    }

    public void ok_login(final String email, String pass, final Handler mhandler) {
        mView.showLoadingDialog();

        mView.rememberUserInfo(email,email);
        mView.startMainActivity();
    }
}
