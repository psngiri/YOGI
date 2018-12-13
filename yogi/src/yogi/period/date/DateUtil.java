package yogi.period.date;

public class DateUtil {
	public static final String[] Months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
	public static final String[] DaysOfWeek = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

	public static String getMonth0Base(int month)
	{
		return Months[month];
	}
	public static String getDayOfWeek0Base(int dayOfWeek)
	{
		return DaysOfWeek[dayOfWeek];
	}
	public static String getMonth(int month)
	{
		return Months[month-1];
	}
	public static String getDayOfWeek(int dayOfWeek)
	{
		return DaysOfWeek[dayOfWeek-1];
	}
	
	public static int getIntDayOfWeek(String dayOfWeek) {
		for (int i=0; i < DaysOfWeek.length; i++) {
			if (DaysOfWeek[i].equalsIgnoreCase(dayOfWeek)) {
				return i+1;
			}
		}
		return -1;
	}
	
	public static int getMonthIndex(String month) {
		int i = 0;
		for(String monthStr : Months) {
			if(monthStr.equalsIgnoreCase(month)) return i;
			i++;
		}
		return -1;
	}	
}
