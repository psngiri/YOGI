package yogi.server.gui.user.preferences.item;

import yogi.server.gui.record.item.RecordItem;


public class UserPreferencesItem extends RecordItem{
	

	private boolean domestic;
	private String[] actions;
	
	public UserPreferencesItem(int childId, boolean domestic, String[] actions) {
		super(childId);
		this.domestic = domestic;
		this.actions = actions;
	}

	public boolean isDomestic() {
		return domestic;
	}

	public String[] getActions() {
		return actions;
	}
	
}