package examples.server.nonstopop.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.base.io.resource.FileResource;

import examples.server.nonstopop.NonStopOpIndexAssistant;
import junit.framework.TestCase;

public class NonStopOpIndexAssistantTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		TestErrorReporter.end();
	}


	public void test(){
		FileResource fileResource = new FileResource("./src/examples/server/nonstopop/test/549915/Forecast1/NonStopOp");
		for(long i =0; i< 10; i++){
			System.out.println(NonStopOpIndexAssistant.get().getValue(fileResource, i));
		}
	}
	
}
