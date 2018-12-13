package yogi.server.gui.user.preferences.level;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yogi.server.gui.user.preferences.UserPreferencesData;

public class UserPreferencesLevel {
	private List<UserPreferencesData> updLevels;
	private transient Map<String, Object> properties;
	
	public UserPreferencesLevel(List<UserPreferencesData> updLevels) {
		super();
		this.updLevels = updLevels;
	}
	
	public Object getProperty(String propertyName) {
		Object property = getProperties().get(propertyName);
		if(!properties.containsKey(propertyName)){
			property = getDefinedProperty(propertyName);
			getProperties().put(propertyName, property);
		}
		return property;		
	}

	private Object getDefinedProperty(String name) {
		Object rtnValue = getPropertyValue(name);
		if(rtnValue != null) return rtnValue;
		int indexOf = name.indexOf('.');
		if(indexOf != -1) {
			String appName = name.substring(0, indexOf);
			name = name.substring(indexOf+1);
			indexOf = name.indexOf('.');
			if(indexOf != -1) {
				String moduleName = name.substring(0, indexOf);
				name = name.substring(indexOf+1);
				String wildCard = "*";
				rtnValue = getDefinedProperty(appName, wildCard, name);
				if(rtnValue != null) return rtnValue;
				rtnValue = getDefinedProperty(wildCard, moduleName, name);
				if(rtnValue != null) return rtnValue;
				rtnValue = getDefinedProperty(wildCard, wildCard, name);
			}
		}
		return rtnValue;
	}
	
	private Object getDefinedProperty(String appName, String moduleName, String propertyName) {
		return getPropertyValue(appName+ "."+ moduleName+ "."+  propertyName);
	}

	private Object getPropertyValue(String propertyName) {
		Object property = null;
		for(UserPreferencesData upd: updLevels){
			if(upd == null) continue;
			property = upd.getProperty(propertyName);
			if(property != null) break;
		}
		return property;
	}

	private Map<String, Object> getProperties() {
		if(properties == null) properties = new HashMap<String, Object>();
		return properties;
	}

	public List<UserPreferencesData> getUpdLevels() {
		return updLevels;
	}
}
