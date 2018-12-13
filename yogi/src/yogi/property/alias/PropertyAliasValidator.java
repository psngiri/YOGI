package yogi.property.alias;

import yogi.base.app.ErrorReporter;
import yogi.base.validation.ValidatorAdapter;

public class PropertyAliasValidator extends ValidatorAdapter<PropertyAlias> {
	@Override
	public boolean validate(PropertyAlias propertyAlias) {
		if(propertyAlias.getPropertyName() == null)
		{
			ErrorReporter.get().error("Null PropertyName", propertyAlias);
			return false;
		}
		if(propertyAlias.getAlias() == null)
		{
			ErrorReporter.get().error("Null Alias", propertyAlias);
			return false;
		}
		return true;
	}
}
