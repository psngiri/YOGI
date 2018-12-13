package yogi.period.date.io;

import yogi.base.Creator;
import yogi.period.date.Date;
import yogi.period.date.InfinityDateCreator;

public class BoundaryNamedDDMMMYYDateScanner extends YYMMMDDPartialDateScanner {
	private Creator<Date> boundaryDateCreator;
	
	public BoundaryNamedDDMMMYYDateScanner() {
		this(InfinityDateCreator.creator);
	}
	
	public BoundaryNamedDDMMMYYDateScanner(Creator<Date> boundaryDateCreator) {
		super();
		this.boundaryDateCreator = boundaryDateCreator;
	}
	
	public Creator<Date> scan(String record) {
		if(record.equals("BLANK")) return boundaryDateCreator;
		setEndDate(true);
		return super.scan(record);
	}
	
}
