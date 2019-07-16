package com.example.android.coderunner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String LOG_TEXT_KEY = "log text key";
    private TextView mLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLog = (TextView) findViewById(R.id.log);
        mLog.setMovementMethod(new ScrollingMovementMethod());
        if(savedInstanceState != null && savedInstanceState.containsKey(LOG_TEXT_KEY)){
            mLog.setText(savedInstanceState.getString(LOG_TEXT_KEY));

        }
        else {
            mLog.setText("");
        }

        logMessage("on create!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logMessage("on pause!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logMessage("on resume!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logMessage("on stop!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        logMessage("on start!");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        String log_text=mLog.getText().toString();
        outState.putString(LOG_TEXT_KEY,log_text);
        super.onSaveInstanceState(outState);
    }

    private void logMessage(String message) {
//      Output message to logcat console
        Log.i(TAG, message);

//      Output message to TextView
        mLog.append(message + "\n");

//      Adjust scroll position to make last line visible
        Layout layout = mLog.getLayout();
        if (layout != null) {
            int scrollAmount = layout.getLineTop(mLog.getLineCount()) - mLog.getHeight();
            mLog.scrollTo(0, scrollAmount > 0 ? scrollAmount : 0);
        }
    }
}
