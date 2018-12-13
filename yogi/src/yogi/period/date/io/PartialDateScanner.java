package yogi.period.date.io;

import yogi.base.io.Scanner;
import yogi.period.date.Date;
import yogi.period.date.DateAssistant;

public abstract class PartialDateScanner implements Scanner<Date, String> {
	private boolean endDate;
	private Date referenceDate; 
	
	public PartialDateScanner() {
		super();
		referenceDate = DateAssistant.get().getCurrentDate();
	}

	public boolean isEndDate() {
		return endDate;
	}

	public PartialDateScanner setEndDate(boolean endDate) {
		this.endDate = endDate;
		return this;
	}

	public Date getReferenceDate() {
		return referenceDate;
	}

	public PartialDateScanner setReferenceDate(Date referenceDate) {
		this.referenceDate = referenceDate;
		return this;
	}
	
}
