package com.sitao.playground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ShowMessageActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		Intent intent = getIntent();
//		String message = intent.getStringExtra(MainActivity.MESSAGE_SENT);
//		
//		TextView viewer = new TextView(this);
//		viewer.setWidth(100);
//		viewer.setHeight(40);
//		viewer.setTextSize(40);
//		viewer.setBackgroundColor(android.graphics.Color.WHITE);
//		viewer.setTextColor(android.graphics.Color.BLACK);
//		viewer.setText(message);
		
		setContentView(R.layout.activity_show_message);
	}
	
	@Override
	protected void onPostResume() {
		super.onPostResume();
		
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.MESSAGE_SENT);
		TextView viewer = (TextView)findViewById(R.id.textView);
		viewer.setText(message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_message, menu);
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
}
