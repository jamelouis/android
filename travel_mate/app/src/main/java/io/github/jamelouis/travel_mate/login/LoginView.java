package io.github.jamelouis.travel_mate.login;


interface LoginView {
    void rememberUserInfo(String token, String email);
    void startMainActivity();
    void showError();
    void showLoadingDialog();
    void dismissLoadingDialog();
    void getRunTimePermissions();
    void checkUserSession();
    void openSignUp();
    void openLogin();
    void setLoginEmail(String email);
    void showMessage(String message);
}
