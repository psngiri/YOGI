package yogi.server.gui.user.preferences;

import java.util.HashMap;
import java.util.Map;

import yogi.server.gui.record.data.RecordData;

public class UserPreferencesData extends RecordData {
	private Map<String, Object> properties;
	
	public UserPreferencesData() {
		properties = new HashMap<String, Object>();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProperty(String propertyName) {
		return (T) properties.get(propertyName);
	}

	public <T> void setProperty(String propertyName, T property) {
		properties.put(propertyName, property);
	}

}
