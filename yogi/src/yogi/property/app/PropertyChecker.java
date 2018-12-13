package yogi.property.app;

import java.util.ArrayList;
import java.util.List;

import yogi.base.app.Checker;
import yogi.base.app.ErrorReporter;
import yogi.property.PropertyManager;


public class PropertyChecker implements Checker{
	
	private List<String> propertiesToCheck;
	public PropertyChecker() {
		super();
		propertiesToCheck = new ArrayList<String>();
	}

	public void addPropertiesToCheck(String... properties)
	{
		for(String property: properties)
		{
			addPropertyToCheck(property);
		}
	}
	
	public void addPropertyToCheck(String property)
	{
		propertiesToCheck.add(property);
	}
	
	public boolean check() {
		for(String property:propertiesToCheck)
		{
			try {
				Object propertyValue = PropertyManager.getPropertyValue(property);
				if(propertyValue == null)
				{
					ErrorReporter.get().error("Property value not set for property", property);
				}
			} catch (RuntimeException e) {
				ErrorReporter.get().error("Property value not set for property", property);
			}
		}
		return true;
	}

}
