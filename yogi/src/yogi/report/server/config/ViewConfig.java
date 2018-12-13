package yogi.report.server.config;

import java.io.Serializable;

public class ViewConfig implements Serializable {	
	
	private static final long serialVersionUID = 5345002674327638901L;
	
	private String tabName;
	private String componentName;
	
	public ViewConfig(String tabName, String componentName) {
		super();
		this.tabName = tabName;
		this.componentName = componentName;
	}
	
	public String getTabName() {
		return tabName;
	}
	public String getComponentName() {
		return componentName;
	}
}
