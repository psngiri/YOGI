package yogi.report.server.tuple.index.test;

import java.util.List;

import yogi.base.app.testing.TestErrorReporter;
import yogi.base.io.resource.FileResource;
import yogi.report.server.tuple.index.LineOffsetReader;

import junit.framework.TestCase;

public class LineOffsetReaderTest extends TestCase {

	
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
//		LineReader r = new LineReader(new FileResource("C:/git/JAF/data/exampleForecatingTestData/marketInfo2.dat"));
		LineOffsetReader r = new LineOffsetReader(new FileResource("C:/git/JAF/data/exampleForecatingTestData/patterns.csv"));
		List<Long> read = r.read();
		System.out.println(read);
	}
}
