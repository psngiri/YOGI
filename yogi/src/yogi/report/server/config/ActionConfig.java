package yogi.report.server.config;

import java.io.Serializable;

public class ActionConfig implements Serializable {	
	
	private static final long serialVersionUID = 8966889173350543085L;
	
	private String buttonName;
	private String commandName;
	private int activeTabIndex;	
	private String[] buttonStates;
	private boolean defaultState;		
	
	public ActionConfig(String buttonName, String commandName,
			int activeTabName, String[] buttonStates, boolean defaultState) {
		super();
		this.buttonName = buttonName;
		this.commandName = commandName;
		this.activeTabIndex = activeTabName;
		this.buttonStates = buttonStates;
		this.defaultState = defaultState;
	}
	
	public String getButtonName() {
		return buttonName;
	}
	public String getCommandName() {
		return commandName;
	}
	public int getActiveTabNames() {
		return activeTabIndex;
	}
	public String[] getButtonStates() {
		return buttonStates;
	}
	public boolean isDefaultState() {
		return defaultState;
	}
}
