package yogi.optimize.expr;


public enum EqualityType {
	LESS_THAN ("Less Than", "L"),
	EQUAL_TO ("Equal To", "E"),
	GREATER_THAN ("Greater Than", "G");
	
	private String description;
	private String code;
	
	private EqualityType(String description, String code)
	{
		this.description = description;
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public String getCode() {
		return code;
	}
}