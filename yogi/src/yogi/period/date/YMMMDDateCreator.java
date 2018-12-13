package yogi.period.date;

import java.util.HashMap;
import java.util.Map;

public class YMMMDDateCreator extends YMDDateCreator {
	private static final long serialVersionUID = 1L;
	private static Map<String,Integer> months = null;
	
	private synchronized void populateMonths(){
		if(months !=null) return;
		months = new HashMap<String,Integer>();
		int i = 0;
		for(String month:DateUtil.Months)
		{
			months.put(month, i++);
		}
	}
	
	public YMMMDDateCreator() {
		super();
	}
	public void setMonth(String monthMMM) {
		
		setMonth(getMonths().get(monthMMM.toUpperCase()));
	}
	
	private Map<String, Integer> getMonths() {
		if(months == null) {
			populateMonths();
		}
		return months;
	}
}
