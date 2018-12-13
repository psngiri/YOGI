package yogi.paging.column.config;

public enum ColumnFieldType {
	TEXTFIELD ("textfield"),
	UPPERCASETEXTFIELD ("uppercasetextfield"),
	CHECKBOXFIELD ("checkboxfield"),
	COMBOBOX ("combobox"),
	CUSTOM ("custom"),
	TIMESTAMP ("timestamp"),
	DATEFIELD ("datefield"),
	BLANK ("");
	
	private String description;
	
	private ColumnFieldType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
