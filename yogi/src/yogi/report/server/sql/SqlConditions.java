package yogi.report.server.sql;

public enum SqlConditions {
	In("In"),
	NotIn("Not In"),
	Like("Like"),
//	Null("Blank"),
//	NotNull("Not Blank"),
	LessThan ("<"),
	LessThanOrEquals ("<="),
	GreaterThan (">"),
	GreaterThanOrEquals (">=");
	private String description;
	
	private SqlConditions(String description)
	{
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
