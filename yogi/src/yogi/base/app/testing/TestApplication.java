package yogi.base.app.testing;

import yogi.base.Creator;
import yogi.base.app.BaseApplication;
import yogi.base.app.BaseModule;
import yogi.base.app.Processor;
import yogi.base.relationship.types.capacity.io.CapacityReader;
import yogi.base.relationship.types.capacity.io.CapacityWriter;
import yogi.base.relationship.types.io.RelationshipTypeWriter;
import yogi.base.util.LockManager;
import yogi.base.util.logging.LoggingPropertiesFileReader;
import yogi.property.Property;
import yogi.property.PropertyManager;
import yogi.property.alias.PropertyAliasAssistant;
import yogi.property.alias.io.PropertyAliasReader;
import yogi.property.io.PropertyScanner;



public class TestApplication extends BaseApplication{

	
	public TestApplication() {
		super();
		PropertyAliasReader.RUN = false;
		CapacityReader.RUN = false;
		CapacityWriter.RUN = false;
		RelationshipTypeWriter.RUN = false;
		LoggingPropertiesFileReader.RUN = true;
		LockManager.Disable = true;
	}
	
	@Override
	protected void setupManagementMBeans() {
	}

	public void setup() {		
	}

	public TestApplication addProcessor(final Processor processor)
	{
		this.addModule(new BaseModule(){

			public void setup() {
				this.addProcessor(processor);
			}
			
		});
		return this;
	}
	
	public TestApplication setPropertyAlias(String aliasName, Class<?> klass, String name, boolean isField)
	{
		PropertyAliasAssistant.get().setAlias(aliasName, klass, name, isField);
		return this;
	}
	
	public TestApplication setPropertyAlias(String aliasName, String className, String name, boolean isField)
	{
		PropertyAliasAssistant.get().setAlias(aliasName, className, name, isField);
		return this;
	}
	
	public TestApplication setProperty(String propertyString)
	{
		PropertyScanner propertyScanner = new PropertyScanner();
		Creator<Property> creator = propertyScanner.scan(propertyString);
		Property property = creator.create();
		PropertyManager.assignProperty(property);
		return this;
	}
}
