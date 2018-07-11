package io.github.jamelouis.travel_mate.utilities;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.jamelouis.travel_mate.R;
import io.github.jamelouis.travel_mate.utils.Services;

import static io.github.jamelouis.travel_mate.utils.Constants.USER_NAME;
import static io.github.jamelouis.travel_mate.utils.Constants.USER_NUMBER;

public class ShareContact extends AppCompatActivity
            implements  View.OnClickListener {
    private static final int ACTIVITY_CREATE = 0, ACTIVITY_SCAN = 1, ACTIVITY_INSERT_CONTACT = 2;
    @BindView(R.id.create) Button create;
    @BindView(R.id.scan) Button scan;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_contact);

        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        create.setOnClickListener(this);
        scan.setOnClickListener(this);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(ACTIVITY_SCAN == requestCode && null != data && data.getExtras() != null) {
            String result = data.getExtras().getString(Services.RESULT);

            String[] x = Objects.requireNonNull(result).split("---");
            String r = "My name is" + x[1] + ". My phone number: " + x[0];

            addContact(x[1],x[0]);

            TextView resultTxt = findViewById(R.id.result);
            resultTxt.setText(r);
            resultTxt.setVisibility(View.VISIBLE);
        }

        if(ACTIVITY_CREATE == requestCode && null != data && null != data.getExtras()) {
            ImageView imgResult = findViewById(R.id.im);
            String qrCode = data.getExtras().getString(Services.RESULT);

            if(null == qrCode || 0 == qrCode.trim().length()) {
                Toast.makeText(ShareContact.this, R.string.msg_qr_not_saved, Toast.LENGTH_LONG).show();
                return ;
            }

            Toast.makeText(ShareContact.this,getString(R.string.msg_saved) + " " + qrCode, Toast.LENGTH_LONG).show();

            imgResult.setImageURI(Uri.parse(qrCode));
            imgResult.setVisibility(View.VISIBLE);
        }

        if(ACTIVITY_INSERT_CONTACT == requestCode) {
            if(Activity.RESULT_OK == resultCode) {
                Toast.makeText(this,"Added Contact", Toast.LENGTH_LONG).show();
            }
            if(Activity.RESULT_CANCELED == resultCode) {
                Toast.makeText(this,"Cancelled Added contact", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void addContact(String name, String phone) {
        Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
        contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        contactIntent
                .putExtra(ContactsContract.Intents.Insert.NAME, name)
                .putExtra(ContactsContract.Intents.Insert.PHONE, phone);

        startActivityForResult(contactIntent, ACTIVITY_INSERT_CONTACT);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent qrDroid;
        switch (view.getId()) {
            case R.id.scan:
                qrDroid = new Intent(Services.SCAN);
                qrDroid.putExtra(Services.COMPLETE,true);
                try{
                    startActivityForResult(qrDroid,ACTIVITY_SCAN);
                }catch (ActivityNotFoundException activity) {
                    Toast.makeText(this, "can't be generated. Need to download QR services", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.create:
                qrDroid = new Intent(Services.ENCODE);
                String mPhoneNumber = mSharedPreferences.getString(USER_NUMBER, "997112322");
                String name = mSharedPreferences.getString(USER_NAME, "Swati Garg");
                qrDroid.putExtra(Services.CODE, mPhoneNumber + "---" + name);
                Log.v("Sharing Contact", "Hey, My contact number is : " + mPhoneNumber);

                qrDroid.putExtra(Services.IMAGE, true);
                qrDroid.putExtra(Services.SIZE, 0);

                try{
                    startActivityForResult(qrDroid, ACTIVITY_CREATE);
                }catch (ActivityNotFoundException activity) {
                    Toast.makeText(ShareContact.this,"can't be generated. Need to download QR Services", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
