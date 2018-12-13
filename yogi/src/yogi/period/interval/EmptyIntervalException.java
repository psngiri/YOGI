package yogi.period.interval;

public class EmptyIntervalException extends Exception {

	private Interval interval;
	/**
	 * 
	 */
	private static final long serialVersionUID = -7196306773917188859L;

	public EmptyIntervalException(Interval interval) {
		super("Empty interval : " + interval);
		this.interval = interval;
	}

	public Interval getInterval() {
		return interval;
	}
	
}
