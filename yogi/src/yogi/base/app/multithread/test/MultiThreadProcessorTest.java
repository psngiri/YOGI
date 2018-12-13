package yogi.base.app.multithread.test;

import junit.framework.TestCase;

import yogi.base.app.ModuleAdapter;
import yogi.base.app.multithread.MultiThreadProcessor;

public class MultiThreadProcessorTest extends TestCase {

	public void test(){
		
		try {
			MultiThreadProcessor mtp = new MultiThreadProcessor();
			mtp.addProcessor(new ModuleAdapter());
			fail( "The test didn't throw the Expected Exception : We should not be able to add a module to multi thread processor" );
		} catch (RuntimeException e) {
			assertTrue(e instanceof RuntimeException);
			e.printStackTrace();
		}
	}
}
