package yogi.property.alias.io;

import yogi.base.io.Formatter;
import yogi.property.alias.PropertyAlias;

public class PropertyAliasFormatter implements Formatter<PropertyAlias> {
	public String format(PropertyAlias propertyAlias) {
		return propertyAlias.toString();
	}
}
