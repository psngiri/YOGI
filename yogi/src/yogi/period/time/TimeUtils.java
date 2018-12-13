package yogi.period.time;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class TimeUtils {
	public static String getHoursComponent(Time time) {
		return getHoursComponent(time.getTime());
	}

	public static String getHoursComponent(int timeInMinutes) {
		int hours = getHours(timeInMinutes);
		if(hours > 9) return String.valueOf(hours);
		else return "0" + String.valueOf(hours);
	}
	
	public static int getHours(Time time) {
		return getHours(time.getTime());
	}

	public static int getHours(int timeInMinutes) {
		return timeInMinutes/60;
	}

	public static String getMinutesComponent(Time time) {
		return getMinutesComponent(time.getTime());
	}

	public static String getMinutesComponent(int timeInMinutes) {
		int minutes = getMinutes(timeInMinutes);
		if(minutes > 9) return String.valueOf(minutes);
		else return "0" + String.valueOf(minutes);
	}

	public static int getMinutes(Time time) {
		return getMinutes(time.getTime());
	}

	public static int getMinutes(int timeInMinutes) {
		return timeInMinutes%60;
	}
	
	/**
	 * Gets the relative distance between from and to using from as the anchor
	 * @param from
	 * @param to
	 * @return relative distance of to from from
	 */
	public static int getDistance(Time from, Time to)
	{
		int distance = to.getTime() - from.getTime();
		if(distance < -TimeManager.MaxTimeInMinutesDiv2) return distance + TimeManager.MaxTimeInMinutes;
		if(distance > TimeManager.MaxTimeInMinutesDiv2) return distance -TimeManager.MaxTimeInMinutes;
		return distance;
	}
	/**
	 Sorts times using the comparator. Uses the first time in times as an anchor and puts the rest with
	 reference to that. Does a good job as long as the times are within 720 minutes from the anchor.
	 * @param <T>
	 * @param times
	 * @param comparator
	 * @return sorted list
	 */
	public static <T> List<T> sort(List<T> times, TimeComparator<T> comparator)
	{
		if(times.isEmpty()) return times;
		comparator.setAnchorObject(times.get(0));
		Collections.sort(times, comparator);
		return times;
	}
	
	public static int getTimeInMinutes(String record, char separator) {
		int indexOf = record.indexOf(separator);
		int hours = 0;
		if(indexOf != -1)
		{
			String hr = record.substring(0,indexOf).trim();
			if(hr.length()> 0)
			{
				hours = Integer.parseInt(hr);
			}
		}
		int minutes = 0;
		String mm = record.substring(indexOf+1).trim();
		if(mm.length()> 0)
		{
			minutes = Integer.parseInt(mm);
		}
		int timeInMinutes = hours*60 + minutes;
		return timeInMinutes;
	}
	
	public static int getTimeInMinutes(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);		
		int minutes = calendar.get(Calendar.MINUTE);			
		int timeInMinutes = hours*60 + minutes;
		return timeInMinutes;
	}
}
