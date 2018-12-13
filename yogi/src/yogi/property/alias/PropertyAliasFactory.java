package yogi.property.alias;

import yogi.base.Factory;

public class PropertyAliasFactory extends Factory<PropertyAlias> {
	private static PropertyAliasFactory propertyAliasFactory = new PropertyAliasFactory(
			PropertyAliasManager.get());

	protected PropertyAliasFactory(PropertyAliasManager manager) {
		super(manager);
	}

	public static PropertyAliasFactory get() {
		return propertyAliasFactory;
	}

}
