package net.kapati.widgets.datepickerdemo;

import net.kapati.widgets.DatePicker;
import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;

public class DemoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);
		
		((DatePicker) findViewById(R.id.short_date)).setDateFormat(DateFormat.getDateFormat(this));
		((DatePicker) findViewById(R.id.long_date)).setDateFormat(DateFormat.getLongDateFormat(this));
	}
}
