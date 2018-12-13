package yogi.period.time;

import java.util.Comparator;

public abstract class TimeComparator<T> implements Comparator<T> {
	Time anchor;
	
	public void setAnchor(Time time) {
		this.anchor = time;
	}

	public void setAnchorObject(T object) {
		this.anchor = getTime(object);
	}

	public int compare(T o1, T o2) {
		return TimeUtils.getDistance(anchor, getTime(o1))-TimeUtils.getDistance(anchor, getTime(o2));
	}

	protected abstract Time getTime(T object); 

}
