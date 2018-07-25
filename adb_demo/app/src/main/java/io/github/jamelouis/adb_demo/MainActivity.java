package io.github.jamelouis.adb_demo;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handleIntent(this.getIntent());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
    }

    protected void handleIntent(Intent intent) {
        Log.d("adb_demo", "handle inent" + intent);
        Bundle extras = intent.getExtras();
        if(extras!=null) {
            Log.d("adb_demo", "extras " + extras);
            String value1 = extras.getString("key1");
            String value2 = extras.getString("key2");
            if(value1 != null && value2 != null )
                Log.d("adb_demo", "value1: " + value1 + " value2: " + value2);
        }

    }

    @Override
    protected void onNewIntent(Intent intent){
        handleIntent(intent);
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
