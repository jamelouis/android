package io.github.jamelouis.travel_mate.login;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.dd.processbutton.FlatButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.jamelouis.travel_mate.R;

/**
 * Created by ejoy on 2018/7/5.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.signup)  TextView signup;
    @BindView(R.id.login)   TextView login;
    @BindView(R.id.signup_layout)    LinearLayout sig;
    @BindView(R.id.loginlayout) LinearLayout log;
    @BindView(R.id.input_email_login)    EditText email_login;
    @BindView(R.id.input_pass_login) EditText pass_login;
    @BindView(R.id.input_email_signup) EditText email_signup;
    @BindView(R.id.input_pass_signup) EditText pass_signup;
    @BindView(R.id.input_confirm_pass_signup) EditText confirm_pass_signup;
    @BindView(R.id.input_name_signup) EditText name;
    @BindView(R.id.ok_login) FlatButton ok_login;
    @BindView(R.id.ok_signup) FlatButton ok_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        Window window = this.getWindow();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
        }

        ButterKnife.bind(this);

    }
}
