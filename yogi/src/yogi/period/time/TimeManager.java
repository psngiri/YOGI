package yogi.period.time;

import yogi.base.Factory;
import yogi.base.Manager;
import yogi.period.time.io.HHMMTimeScanner;

public class TimeManager extends Manager<Time> {
	private static TimeManager timeManager = new TimeManager();
	public static final int MinTimeInMinutes = 0;
	public static final int MaxTimeInMinutes = 1440;
	public static final int MaxTimeInMinutesDiv2 = 720;
	public static final Time MinTime = timeManager.getTime(MinTimeInMinutes);
	public static final Time MaxTime = timeManager.getTime(MaxTimeInMinutes);
	public static final Time OverNightTime = new HHMMTimeScanner().scan("0300").create();
	
	TimeFactory timeFactory;
	
	
	public static TimeManager get() {
		return timeManager;
	}

	protected TimeManager() {
		super();
		timeFactory = new TimeFactory(this);
	}

	public Time getTime(int timeInMinutes)
	{
		if(timeInMinutes > MaxTimeInMinutes) timeInMinutes = timeInMinutes - MaxTimeInMinutes;
		if(timeInMinutes < MinTimeInMinutes) timeInMinutes = timeInMinutes + MaxTimeInMinutes;
		return timeFactory.getTime(timeInMinutes);
	}
	
	public Time getTime(String timeInMinutes) 
	{
		try {
			return getTime(Integer.parseInt(timeInMinutes));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
		
	@Override
	public void setFactory(Factory<? extends Time> factory) {
		super.setFactory(factory);
	}

}
