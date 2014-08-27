package com.sitao.playground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class MessageFragment extends Fragment {

	private LayoutInflater inflater;
	private ViewGroup container;
	private View rootView;
	private LandingActivity activity;

	public MessageFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.container = container;
		this.rootView = inflater.inflate(R.layout.fragment_message, container,
				false);
		this.activity = (LandingActivity) getActivity();

		return rootView;
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
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((LandingActivity) activity).onSectionAttached(LandingActivity.MESSAGE);
	}
}
