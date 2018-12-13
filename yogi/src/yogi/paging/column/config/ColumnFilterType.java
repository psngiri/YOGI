package yogi.paging.column.config;

public enum ColumnFilterType {
	BOOLEAN ("boolean"),
	DATE ("date"),
	lIST ("list"),
	NUMERIC ("numeric"),
	STRING ("string"),
	BLANK ("");
	
	private String description;
	
	private ColumnFilterType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
