package yogi.server.action;


public enum Action {
	Cancel ("Cancel (Delete)", 1),
	Add ("Add (New)", 2),
	Update ("Update (Change)", 3);
	
	private String description;
	private int value;
	
	private Action(String description, int value)
	{
		this.description = description;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
	
}
