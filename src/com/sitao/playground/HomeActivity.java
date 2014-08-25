package com.sitao.playground;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeActivity extends ActionBarActivity {
	private static int CARDCOUNT_S2 = 14;
	private static int CARDCOUNT_S3 = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		setupSection2View();
		setupSection3View();
	}

	private void setupSection2View() {
		LinearLayout section2 = (LinearLayout) findViewById(R.id.section2);
		LayoutInflater layoutInflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		List<Integer> colorList = new ArrayList<Integer>();
		colorList.add(Color.GREEN);
		colorList.add(Color.YELLOW);
		colorList.add(Color.BLUE);
		colorList.add(Color.RED);
		colorList.add(Color.CYAN);

		for (int i = 0; i < CARDCOUNT_S2; i++) {
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(0, 0, R.dimen.margin_m, 0);

			RelativeLayout item = new RelativeLayout(this);
			item.setLayoutParams(lp);
			View view = layoutInflater.inflate(R.layout.tile, item, false);

			ImageView iv = (ImageView) view.findViewById(R.id.image);
			iv.setBackgroundColor(colorList.get(i % colorList.size()));
			TextView tv1 = (TextView) view.findViewById(R.id.line1);
			tv1.setText("Player " + (i + 1));
			TextView tv2 = (TextView) view.findViewById(R.id.line2);
			tv2.setText("Result " + (i + 1));

			section2.addView(view, i, lp);
		}
	}

	private void setupSection3View() {
		LinearLayout section2 = (LinearLayout) findViewById(R.id.section3);
		LayoutInflater layoutInflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		List<Integer> colorList = new ArrayList<Integer>();
		colorList.add(Color.GREEN);
		colorList.add(Color.YELLOW);
		colorList.add(Color.BLUE);
		colorList.add(Color.RED);
		colorList.add(Color.CYAN);

		for (int i = 0; i < CARDCOUNT_S3; i++) {
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(0, 0, R.dimen.margin_m, 0);

			RelativeLayout item = new RelativeLayout(this);
			item.setLayoutParams(lp);
			View view = layoutInflater.inflate(R.layout.tile, item, false);

			ImageView iv = (ImageView) view.findViewById(R.id.image);
			iv.setBackgroundColor(colorList.get(i % colorList.size()));
			TextView tv1 = (TextView) view.findViewById(R.id.line1);
			tv1.setText("Quiz " + (i + 1));
			TextView tv2 = (TextView) view.findViewById(R.id.line2);
			tv2.setText("Details " + (i + 1));

			section2.addView(view, i, lp);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
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
