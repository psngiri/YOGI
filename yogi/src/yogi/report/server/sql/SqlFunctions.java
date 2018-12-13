package yogi.report.server.sql;

public enum SqlFunctions {
	Sum("Sum"),
	Avg("Avg"),
	Min ("Min"),
	Max("Max");
	private String description;
	
	private SqlFunctions(String description)
	{
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
