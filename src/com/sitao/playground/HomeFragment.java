package com.sitao.playground;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeFragment extends Fragment {
	private static int CARDCOUNT_S2 = 14;
	private static int CARDCOUNT_S3 = 10;

	private LayoutInflater inflater;
	private ViewGroup container;
	private View rootView;

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.container = container;
		this.rootView = inflater.inflate(R.layout.fragment_home, container, false);

		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();

		setupSection2View(this.inflater, this.rootView);
		setupSection3View(this.inflater, this.rootView);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((LandingActivity) activity).onSectionAttached(LandingActivity.HOME);
	}

	private void setupSection2View(LayoutInflater inflater, View root) {
		LinearLayout section2 = (LinearLayout) root.findViewById(R.id.section2);

		List<Integer> colorList = new ArrayList<Integer>();
		colorList.add(Color.GREEN);
		colorList.add(Color.YELLOW);
		colorList.add(Color.BLUE);
		colorList.add(Color.RED);
		colorList.add(Color.CYAN);

		for (int i = 0; i < CARDCOUNT_S2; i++) {
			if (i != 0) {
				LinearLayout space = new LinearLayout(getActivity());
				LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
						getResources().getDimensionPixelSize(R.dimen.margin_m),
						LinearLayout.LayoutParams.WRAP_CONTENT);
				space.setLayoutParams(lp2);

				section2.addView(space);
			}

			View view = inflater.inflate(R.layout.tile, null);
			ImageView iv = (ImageView) view.findViewById(R.id.image);
			iv.setBackgroundColor(colorList.get(i % colorList.size()));
			TextView tv1 = (TextView) view.findViewById(R.id.line1);
			tv1.setText("Player " + (i + 1));
			TextView tv2 = (TextView) view.findViewById(R.id.line2);
			tv2.setText("Result " + (i + 1));

			section2.addView(view);
		}
	}

	private void setupSection3View(LayoutInflater inflater, View root) {
		LinearLayout section3 = (LinearLayout) root.findViewById(R.id.section3);

		List<Integer> colorList = new ArrayList<Integer>();
		colorList.add(Color.GREEN);
		colorList.add(Color.YELLOW);
		colorList.add(Color.BLUE);
		colorList.add(Color.RED);
		colorList.add(Color.CYAN);

		for (int i = 0; i < CARDCOUNT_S3; i++) {
			if (i != 0) {
				LinearLayout space = new LinearLayout(getActivity());
				LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(
						getResources().getDimensionPixelSize(R.dimen.margin_m),
						LinearLayout.LayoutParams.WRAP_CONTENT);
				space.setLayoutParams(lp3);

				section3.addView(space);
			}

			View view = inflater.inflate(R.layout.tile, null);
			ImageView iv = (ImageView) view.findViewById(R.id.image);
			iv.setBackgroundColor(colorList.get(i % colorList.size()));
			TextView tv1 = (TextView) view.findViewById(R.id.line1);
			tv1.setText("Quiz " + (i + 1));
			TextView tv2 = (TextView) view.findViewById(R.id.line2);
			tv2.setText("Details " + (i + 1));

			section3.addView(view);
		}
	}
}
