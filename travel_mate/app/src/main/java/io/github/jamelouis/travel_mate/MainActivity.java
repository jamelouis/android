package io.github.jamelouis.travel_mate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import io.github.jamelouis.travel_mate.login.LoginActivity;
import io.github.jamelouis.travel_mate.travel.TravelFragment;
import io.github.jamelouis.travel_mate.utilities.EmergencyFragment;
import io.github.jamelouis.travel_mate.utilities.UtilitiesFragment;

import static io.github.jamelouis.travel_mate.utils.Constants.USER_TOKEN;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (id) {
            case R.id.nav_travel:
                fragment = new TravelFragment();
                fragmentManager.beginTransaction().replace(R.id.inc, fragment).commit();
                break;
            case R.id.nav_city:
                break;
            case R.id.nav_utility:
                fragment = new UtilitiesFragment();
                fragmentManager.beginTransaction().replace(R.id.inc,fragment).commit();
                break;
            case R.id.nav_emergency:
                fragment = new EmergencyFragment();
                fragmentManager.beginTransaction().replace(R.id.inc,fragment).commit();
                break;
            case R.id.nav_signout:
                ContextThemeWrapper crt = new ContextThemeWrapper(this, R.style.AlertDialog);
                AlertDialog.Builder builder = new AlertDialog.Builder(crt);
                builder.setMessage(R.string.signout_message)
                        .setPositiveButton(R.string.positive_button,
                                (dialog, which) -> {
                                    mSharedPreferences
                                            .edit()
                                            .putString(USER_TOKEN, null)
                                            .apply();
                                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    finish();
                                })
                        .setNegativeButton(R.string.negative_button,
                                ((dialog, which) -> {

                                }));
                builder.create().show();
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
