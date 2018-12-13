package yogi.property.alias;

import yogi.base.ObjectNotFoundException;
import yogi.base.relationship.index.IndexedManager;

public class PropertyAliasManager extends IndexedManager<PropertyAlias, String> {
	private static PropertyAliasManager propertyAliasManager = new PropertyAliasManager();

	protected PropertyAliasManager() {
		super();
	}

	public static PropertyAliasManager get() {
		return propertyAliasManager;
	}
	
	public PropertyAlias getPropertyAlias(String alias) throws ObjectNotFoundException
	{
		return getObject(alias);
	}
}
