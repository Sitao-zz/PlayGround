package com.sitao.playground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ShowMessageActivity extends ActionBarActivity implements
		OnClickListener {
	private static final String TAG = "ShowMessageActivity";

	private static final int MY_EDITTEXT = 1000;
	private static final int MY_BUTTON = 9000;
	private static final String INPUT = "TextInput";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_message);

		updateView();
		setupSoftKeyboard(findViewById(R.id.parent));
	}

	@Override
	protected void onPostResume() {
		super.onPostResume();
	}

	private void updateView() {
		Intent intent = getIntent();
		String message = intent.getStringExtra(LandingActivity.MESSAGE_SENT);

		LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout2);

		// add text view
		TextView tv = new TextView(this);
		tv.setText(message);
		ll.addView(tv);

		// add edit text
		EditText et = new EditText(this);
		et.setHint(R.string.ShowMessageActivity_editTextHint);
		et.setMinLines(1);
		et.setMaxLines(3);
		et.setId(MY_EDITTEXT);
		ll.addView(et);

		// add button
		Button b = new Button(this);
		b.setText(R.string.ShowMessageActivity_button);
		b.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		b.setId(MY_BUTTON);
		b.setOnClickListener(this);
		ll.addView(b);

		// add checkboxes
		for (int i = 0; i < 5; i++) {
			CheckBox cb = new CheckBox(this);
			cb.setText("Checkbox " + i);
			cb.setId(i + 10);
			ll.addView(cb);
		}

		// add radio buttons
		final RadioButton[] rb = new RadioButton[5];
		// create the RadioGroup
		RadioGroup rg = new RadioGroup(this);
		// or RadioGroup.VERTICAL
		rg.setOrientation(RadioGroup.VERTICAL);
		// the RadioButtons are added to the radioGroup instead of the layout
		for (int i = 0; i < 5; i++) {
			rb[i] = new RadioButton(this);
			rb[i].setText("Radio Button " + i);
			rb[i].setId(i);
			rg.addView(rb[i]);
		}
		// add the whole RadioGroup to the layout
		ll.addView(rg);

		// add Toggle button
		ToggleButton tb = new ToggleButton(this);
		tb.setTextOn("Toggle Button - ON");
		tb.setTextOff("Toggle Button - OFF");
		tb.setChecked(true);
		tb.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		ll.addView(tb);
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

	@Override
	public void onClick(View view) {
		Toast toast;
		Log.i(TAG, "View Id: " + view.getId());
		switch (view.getId()) {
		case MY_BUTTON:
			toast = Toast
					.makeText(this, "Answer submitted!", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.TOP, 25, 400);
			toast.show();
			saveAnswers();
			break;
		// More buttons go here (if any) ...
		}
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		EditText et = (EditText) findViewById(MY_EDITTEXT);
		String message = et.getText().toString();
		savedInstanceState.putString(INPUT, message);

		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		String message = savedInstanceState.getString(INPUT);
		EditText et = (EditText) findViewById(MY_EDITTEXT);
		et.setText(message);
	}

	public void saveAnswers() {
		// or whatever your root control is
		LinearLayout root = (LinearLayout) findViewById(R.id.linearLayout2);
		loopQuestions(root);
	}

	private void loopQuestions(ViewGroup parent) {
		for (int i = 0; i < parent.getChildCount(); i++) {
			View child = parent.getChildAt(i);
			if (child instanceof RadioGroup) {
				// Support for RadioGroups
				RadioGroup radio = (RadioGroup) child;
				storeAnswer(radio.getId(), radio.getCheckedRadioButtonId());
			} else if (child instanceof CheckBox) {
				// Support for Checkboxes
				CheckBox cb = (CheckBox) child;
				int answer = cb.isChecked() ? 1 : 0;
				storeAnswer(cb.getId(), answer);
			} else if (child instanceof EditText) {
				// Support for EditText
				EditText et = (EditText) child;
				Log.i(TAG, "EdiText: " + et.getText());
			} else if (child instanceof ToggleButton) {
				// Support for ToggleButton
				ToggleButton tb = (ToggleButton) child;
				Log.i(TAG, "Toggle: " + tb.getText());
			} else {
				// Support for other controls
			}

			if (child instanceof ViewGroup) {
				// Nested Q&A
				ViewGroup group = (ViewGroup) child;
				loopQuestions(group);
			}
		}
	}

	private void storeAnswer(int question, int answer) {
		Log.i(TAG, "Question: " + String.valueOf(question) + " * " + "Answer: "
				+ String.valueOf(answer));

		Toast toast = Toast.makeText(this, String.valueOf(question) + " * "
				+ "Answer: " + String.valueOf(answer), Toast.LENGTH_LONG);
		toast.setGravity(Gravity.TOP, 25, 400);
		toast.show();
	}

	public void setupSoftKeyboard(View view) {

		// Set up touch listener for non-text box views to hide keyboard.
		if (!(view instanceof EditText)) {

			view.setOnTouchListener(new OnTouchListener() {

				public boolean onTouch(View v, MotionEvent event) {
					hideSoftKeyboard(ShowMessageActivity.this);
					return false;
				}

			});
		}

		// If a layout container, iterate over children and seed recursion.
		if (view instanceof ViewGroup) {

			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

				View innerView = ((ViewGroup) view).getChildAt(i);

				setupSoftKeyboard(innerView);
			}
		}
	}

	public static void hideSoftKeyboard(Activity activity) {
		InputMethodManager inputMethodManager = (InputMethodManager) activity
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
				.getWindowToken(), 0);
	}
}
