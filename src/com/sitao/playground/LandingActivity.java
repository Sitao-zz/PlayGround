package com.sitao.playground;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LandingActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {
	private static String TAG = "MainActivity";
	public static String MESSAGE_SENT = "com.sitao.playground.LandingActivity.messagesent";

	public final static int PROFILE = 1;
	public final static int HOME = 2;
	public final static int TOPICS = 3;
	public final static int FRIENDS = 4;
	public final static int HISTORY = 5;
	public final static int BADGES = 6;
	public final static int SETTINGS = 7;
	public final static int MESSAGE = 8;

	private static final String STATUS = "com.sitao.playground.LandingActivity.status";
	private int currentSection = HOME;

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landing);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		if (savedInstanceState == null) {
			onNavigationDrawerItemSelected(HOME - 1);
		}
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment;
		this.currentSection = ++position;
		switch (this.currentSection) {
		case HOME:
			fragment = new HomeFragment();
			break;
		case TOPICS:
			fragment = new TopicsFragment();
			break;
		case SETTINGS:
			fragment = new SettingsFragment();
			break;
		case MESSAGE:
			fragment = new MessageFragment();
			break;
		default:
			fragment = PlaceholderFragment.newInstance(this.currentSection);
			break;
		}
		fragmentManager.beginTransaction().replace(R.id.container, fragment)
				.commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case PROFILE:
			mTitle = getString(R.string.title_activity_profile);
			break;
		case HOME:
			mTitle = getString(R.string.title_activity_home);
			break;
		case TOPICS:
			mTitle = getString(R.string.title_activity_topics);
			break;
		case FRIENDS:
			mTitle = getString(R.string.title_activity_friends);
			break;
		case HISTORY:
			mTitle = getString(R.string.title_activity_history);
			break;
		case BADGES:
			mTitle = getString(R.string.title_activity_badges);
			break;
		case SETTINGS:
			mTitle = getString(R.string.title_activity_settings);
			break;
		case MESSAGE:
			mTitle = getString(R.string.title_activity_message);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.landing, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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
	public void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putInt(STATUS, this.currentSection);

		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		int section = savedInstanceState.getInt(STATUS);
		onNavigationDrawerItemSelected(section - 1);
	}

	public boolean showMessage(View view) {
		EditText editText = (EditText) findViewById(R.id.editMessage);
		String message = editText.getText().toString();
		Log.i(TAG, "Message=" + message);

		TextView textView = (TextView) findViewById(R.id.textView);
		textView.setText(message);

		Intent intent = new Intent(this, ShowMessageActivity.class);
		intent.putExtra(MESSAGE_SENT, message);
		startActivity(intent);
		return true;
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_landing,
					container, false);
			int number = getArguments().getInt(ARG_SECTION_NUMBER);

			RelativeLayout rl = (RelativeLayout) rootView
					.findViewById(R.id.container_landing);
			rl.setBackgroundColor(Color.YELLOW);

			TextView tv = (TextView) rootView.findViewById(R.id.text_landing);
			tv.setText("Section " + number);

			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((LandingActivity) activity).onSectionAttached(getArguments()
					.getInt(ARG_SECTION_NUMBER));
		}
	}

}
