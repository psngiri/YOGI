package yogi.base.app.testing;

import yogi.base.app.ApplicationProperties;
import yogi.base.relationship.types.capacity.io.CapacityReader;
import yogi.base.relationship.types.capacity.io.CapacityWriter;
import yogi.base.relationship.types.io.RelationshipTypeWriter;
import yogi.base.util.LockManager;
import yogi.base.util.logging.LoggingPropertiesFileReader;
import yogi.property.alias.io.PropertyAliasReader;

public abstract class ModuleAcceptenceTestCase extends ApplicationAcceptenceTestCase {
	private TestApplication testApplication;

	protected ModuleAcceptenceTestCase(String workset) {
		super(workset);
		testApplication = new TestApplication();
	}

	@Override
	protected void setProperties() {
		super.setProperties();
		ApplicationProperties.InputDataLocation = "data/" + getWorkset();
		ApplicationProperties.OutputLocation = "data/" + getWorkset() + "/testOutput";
		PropertyAliasReader.RUN = false;
		CapacityReader.RUN = false;
		CapacityWriter.RUN = false;
		RelationshipTypeWriter.RUN = false;
		LoggingPropertiesFileReader.RUN = true;
		LockManager.Disable = true;
	}

	public TestApplication getTestApplication() {
		return testApplication;
	}

	@Override
	public int executeApplication(String[] args) {
		ApplicationProperties.InputDataLocation = args[0];
		ApplicationProperties.OutputLocation = args[1];
		testApplication.execute();
		return 0;
	}
}
