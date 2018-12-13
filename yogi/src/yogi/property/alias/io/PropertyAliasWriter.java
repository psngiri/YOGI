package yogi.property.alias.io;

import yogi.base.Selector;
import yogi.base.io.FactoryWriter;
import yogi.base.io.FileWriter;
import yogi.property.alias.PropertyAlias;
import yogi.property.alias.PropertyAliasFactory;

public class PropertyAliasWriter extends FactoryWriter<PropertyAlias> {
	public PropertyAliasWriter(String fileName, Selector<PropertyAlias> selector) {
		super(PropertyAliasFactory.get(), new FileWriter<PropertyAlias>(
				fileName, new PropertyAliasFormatter()), selector);
	}

	public PropertyAliasWriter(String fileName) {
		this(fileName, null);
	}
}
