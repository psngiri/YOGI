package yogi.period.dow;

public enum DayOfWeek {
	SUN("Sun"),
	MON("Mon"),
	TUE("Tue"),
	WED("Wed"),
	THU("Thu"),
	FRI("Fri"),
	SAT("Sat"),
	BLANK("BLANK");

	private String description;
	
	private DayOfWeek(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
