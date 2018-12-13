package yogi.period.time;



public class Times{
	
	public static Time add(Time t1, Time t2)
	{
		short time2 = t2.getTime();
		return add(t1, time2);
	}

	// t2 is in minutes
	public static Time add(Time t1, int t2) {
		int time = t1.getTime()+t2;
		return TimeManager.get().getTime(time);
	}
	
	public static Time subtract(Time t1, Time t2)
	{
		short time2 = t2.getTime();
		return subtract(t1, time2);
	}

	public static Time subtract(Time t1, int t2) {
		return add(t1, -1*t2);
	}
	
	public static boolean isMidNight(Time time)
	{
		short timeInMinutes = time.getTime();
		return timeInMinutes == TimeManager.MaxTimeInMinutes || timeInMinutes == TimeManager.MinTimeInMinutes;
	}

	public static Time getGmtTime(Time localTime, int offset)
	{
		return Times.subtract(localTime, offset);
	}
	
	public static Time getLocalTime(Time GmtTime, int offset)
	{
		return Times.add(GmtTime, offset);
	}
	
	public static boolean isContigous(Time time1, Time time2)
	{
		return (getDistance(time1, time2) == 1);
	}
	
	public static long getDistance(Time time1, Time time2)
	{
		return Math.abs(time1.getTime() - time2.getTime());
	}
}
