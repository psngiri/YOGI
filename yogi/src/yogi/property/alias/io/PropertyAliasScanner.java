package yogi.property.alias.io;

import yogi.base.Creator;
import yogi.property.alias.PropertyAlias;
import yogi.property.alias.PropertyAliasCreator;

public class PropertyAliasScanner implements
		yogi.base.io.Scanner<PropertyAlias, String> {
	private PropertyAliasCreator creator = new PropertyAliasCreator();

	public Creator<PropertyAlias> scan(String record) {
		String[] s2 = record.split("=");
		if(s2.length != 2) 
			 throw new RuntimeException("= ambiguosly placed =: " + record);
		String value = s2[1];
		String splitChar = null;
		if(s2[0].contains(":"))
		{
			splitChar = ":";
			creator.setField(true);
		}else if(s2[0].contains(";"))
		{
			splitChar = ";";
			creator.setField(false);
		}else throw new RuntimeException("Excpects either : or ; separating the ClassName and the field or method name respectively: " + s2[0]);
		String name = extractClassName(s2[0], splitChar);
		creator.setName(name.trim());
		creator.setValue(value.trim());
		return creator;
	}
	
	private String extractClassName(String string, String splitBy) {
		String[] s1 = string.split(splitBy);
		if(s1.length != 2) throw new RuntimeException(splitBy + " ambiguosly placed =: " + string);
		String className = s1[0];
		String rest = s1[1];
		creator.setClassName(className.trim());
		return rest;
	}
}
