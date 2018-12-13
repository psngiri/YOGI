package yogi.paging.launch;

import java.util.HashMap;
import java.util.Map;

public class LaunchParameters {
	private String appName;
	private Map<String, Object> parameters;
	
	public LaunchParameters(String appName, Map<String, Object> parameters) {
		super();
		this.appName = appName;
		this.parameters = parameters;
	}

	public LaunchParameters(String appName) {
		super();
		this.appName = appName;
		this.parameters = new HashMap<String, Object>(1);
	}
	
	public LaunchParameters addParameter(String key, Object value)
	{
		parameters.put(key, value);
		return this;
	}

	public String getAppName() {
		return appName;
	}
}
