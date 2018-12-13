package yogi.property.io;

import yogi.base.Creator;
import yogi.base.app.ErrorReporter;
import yogi.property.Property;
import yogi.property.PropertyAssistant;
import yogi.property.PropertyCreator;
import yogi.property.PropertyReplacer;


public class PropertyScanner implements yogi.base.io.Scanner<Property, String> {
	private static PropertyCreator creator = new PropertyCreator();
	private static PropertyReplacer propertyReplacer = new PropertyReplacer();
	public Creator<Property> scan(String record) {
		String[] s2 = record.split("=", 2);
		if(s2.length != 2) ErrorReporter.get().error("= ambiguosly placed", record);
		String value = s2[1];
		String propertyName = s2[0];
		return scan(propertyName, value);
	}

	public static PropertyCreator scan(String propertyName, String value) {
		creator.setValue(propertyReplacer.replaceVariables(value.trim()));
		creator.setPropertyName(PropertyAssistant.getPropertyname(propertyName));
		return creator;
	}

}
