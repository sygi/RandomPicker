//Jakub Sygnowski
//20.11.2012
//Obrazek wzialem z wikipedii http://commons.wikimedia.org/wiki/File:10_sided_die.svg
package com.example.randompicker;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	int scian = 2, rzutow = 1;

	long getVal() {
		long a = 0;
		for (int i = 0; i < rzutow; ++i) {
			a += 1 + r.nextInt(scian);
		}
		return a;
	}

	Activity link;
	Random r;

	void makeDialog(String msg){
		AlertDialog.Builder build = new AlertDialog.Builder(link);
		build.setMessage(msg)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialog, int id) {
								dialog.cancel();
							}
						}).show();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		r = new Random(System.currentTimeMillis());
		setContentView(R.layout.activity_main);
		Button b = (Button) this.findViewById(R.id.button1);
		final TextView t = (TextView) this.findViewById(R.id.editText2);
		final TextView t2 = (TextView) this.findViewById(R.id.editText1);
		link = this;
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (t.getText().toString().equals("")) {
					makeDialog("Type number of sides");
					return;
				}
				if (t.getText().toString().length() > 9 || t2.getText().toString().length() > 6){ //problem z dlugoscia
					makeDialog("Integer too long");
					return;
				}
				scian = Integer.parseInt(t.getText().toString());
				if (t2.getText().toString().equals(""))
					rzutow = 1;
				else {
					rzutow = Integer.parseInt(t2.getText().toString());
				}
				makeDialog(Long.toString(getVal()));
			}
		});
		Button exit = (Button) this.findViewById(R.id.button2);
		exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				link.finish();
			}
		});
	}

}
