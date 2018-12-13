package yogi.period.time;

import yogi.base.Creator;

public class TimeCreator implements Creator<Time> {
	private int time;
	public Time create() {
		return TimeManager.get().getTime(time);
	}
	
	public int getTime() {
		return time;
	}
	public TimeCreator setTime(int time) {
		this.time = time;
		return this;
	}
	public TimeCreator setTime(int hours, int minutes) {
		this.time = hours*60 + minutes;
		return this;
	}

}
