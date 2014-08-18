package com.sitao.playground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	private static String TAG = "MainActivity";
	public static String MESSAGE_SENT = "com.sitao.playground.messagesent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public boolean showMessage(View view) {
    	EditText editText = (EditText)findViewById(R.id.editMessage);
    	String message = editText.getText().toString();
    	Log.i(TAG, "Message=" + message);
    	
    	TextView textView = (TextView)findViewById(R.id.textView);
    	textView.setText(message);
    	
    	Intent intent = new Intent(this, ShowMessageActivity.class);
    	intent.putExtra(MESSAGE_SENT, message);
    	startActivity(intent);
    	return true;
    }
}
