package yogi.period.date.io;

import yogi.base.Creator;
import yogi.period.date.Date;
import yogi.period.date.InfinityDateCreator;
import yogi.period.date.YMDDateCreator;

public class DDMMMYYYYDateScanner extends DDMMMYYDateScanner {
	
	private static final long serialVersionUID = -5775712812568785692L;

	public Creator<Date> scan(String record) {
		if(record.equals("00XXX0000")) return InfinityDateCreator.creator;
		if(record.length()==8 && record.charAt(0)!='0')
			record = "0" + record;
		YMDDateCreator dateCreator = setDayAndMonth(record);
		int year = Integer.parseInt(record.substring(5, 9));
		dateCreator.setYear(year);
		return dateCreator;
	}
}