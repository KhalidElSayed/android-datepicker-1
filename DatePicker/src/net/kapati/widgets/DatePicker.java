package net.kapati.widgets;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class DatePicker extends EditText implements DatePickerDialog.OnDateSetListener {
	public interface OnDateSetListener {
		public void onDateSet(DatePicker view, int year, int month, int day);
	}
	
	protected int year;
	protected int month;
	protected int day;
	protected OnDateSetListener onDateSetListener = null;
	
	public OnDateSetListener getOnDateSetListener() {
		return onDateSetListener;
	}

	public void setOnDateSetListener(OnDateSetListener onDateSetListener) {
		this.onDateSetListener = onDateSetListener;
	}
	
	public DatePicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
		setFocusable(false);
	}
	
	public boolean hasDate() {
		return (year != 0) && (month != 0) && (day != 0);
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
		updateText();
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
		updateText();
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
		updateText();
	}
	
	public void setDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
		
		updateText();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN)
			showDatePicker();
		
		return super.onTouchEvent(event);
	}

	protected void showDatePicker() {
		DatePickerDialog datePickerDialog;
		
		if(hasDate()) {
			datePickerDialog = new DatePickerDialog(
					getContext(),
					this,
					getYear(),
					getMonth(),
					getDay());
		} else {
			datePickerDialog = new DatePickerDialog(
					getContext(),
					this,
					Calendar.getInstance().get(Calendar.YEAR),
					Calendar.getInstance().get(Calendar.MONTH),
					Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		}
		
		datePickerDialog.show();
	}

	@Override
	public void onDateSet(android.widget.DatePicker view, int year,
			int month, int day) {
		setDate(year, month, day);
		clearFocus();
		
		if(onDateSetListener != null)
			onDateSetListener.onDateSet(this, year, month, day);
	}
	
	public void updateText() {
		Calendar cal = new GregorianCalendar(getYear(), getMonth(), getDay());
		setText(DateFormat.getDateFormat(getContext()).format(cal.getTime()));
	}
}
