package yogi.report.server.test;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.io.FileToStringReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.base.io.resource.SystemResource;
import yogi.base.util.JsonAssistant;
import yogi.report.server.CompoundFilter;

import junit.framework.TestCase;

public class CompoundFilterTest  extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		TestErrorReporter.end();
		Factory.clearAllFactories();
		super.tearDown();
		
	}
	

	public void testFromJsonFileQuery() {
		SystemResource resource = new ClassPathResource("compoundFilter.txt", this.getClass());
		FileToStringReader fileToStringReader = new FileToStringReader(resource, 0);
		String read = fileToStringReader.read();
		CompoundFilter query = JsonAssistant.get().fromJson(read, CompoundFilter.class);
		String json = JsonAssistant.get().toJson(query);
		System.out.println(json);
		assertEquals(read, json);
	}

}
