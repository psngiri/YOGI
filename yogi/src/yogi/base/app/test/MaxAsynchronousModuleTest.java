package yogi.base.app.test;

import yogi.base.app.MaxAsynchronousModule;
import yogi.base.app.Processor;

import junit.framework.TestCase;

public class MaxAsynchronousModuleTest extends TestCase {

	public void test(){
		long time = 3000;
		boolean value = true;
		test(time, value);
	}
	
	public void test1(){
		long time = 13000;
		boolean value = false;
		test(time, value);
	}

	private void test(final long time, boolean value) {
		Processor processor = new Processor(){

			@Override
			public boolean isActivated() {
				return true;
			}

			@Override
			public void run() {
				System.out.println("Started execution");
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("completed execution");
			}
			
		};
		MaxAsynchronousModule mam = new MaxAsynchronousModule(processor, 10000);
		mam.setup();
		mam.run();
		assertEquals(value, mam.isCompleted());
	}
}
