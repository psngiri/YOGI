package yogi.period.date;

import java.io.Serializable;

import yogi.base.Creator;

public class YMDDateCreator implements Creator<Date>, Serializable {
	private static final long serialVersionUID = -8790807814060058377L;
	private int year;
	private int month;
	private int day;
	public Date create() {
		return DateManager.get().getDate(year, month, day);
	}
	public YMDDateCreator() {
		super();
	}
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
}
