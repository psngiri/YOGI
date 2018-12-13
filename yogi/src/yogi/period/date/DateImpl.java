package yogi.period.date;

import yogi.period.date.io.DDMMMYYYYDateFormatter;

class DateImpl implements Date {

	private static DDMMMYYYYDateFormatter formatter = new DDMMMYYYYDateFormatter();
	private long value;
	
	protected DateImpl(long value) {
		super();
		this.value = value;
	}

	public long getValue() {
		return value;
	}

	public Long getIndex() {
		return getValue();
	}

	public int compareTo(Date o) {
		if(getValue() < o.getValue()) return -1;
		else if(getValue() > o.getValue()) return 1;
		else return 0;
	}

	@Override
	public String toString() {
		return formatter.format(this);
	}

}
