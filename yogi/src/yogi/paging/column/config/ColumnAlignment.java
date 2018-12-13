package yogi.paging.column.config;

public enum ColumnAlignment {
	LEFT ("left"),
	CENTER ("center"),
	RIGHT ("right"),
	BLANK ("");
	
	private String description;
	
	private ColumnAlignment(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
