package com.sitao.playground;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsFragment extends Fragment {

	private LayoutInflater inflater;
	private ViewGroup container;
	private View rootView;

	public SettingsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.container = container;
		this.rootView = inflater.inflate(R.layout.fragment_settings, container,
				false);

		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((LandingActivity) activity)
				.onSectionAttached(LandingActivity.SETTINGS);
	}
}
