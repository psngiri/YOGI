package yogi.base.util.test;

import yogi.base.util.Timer;

import junit.framework.TestCase;

public class TimerTest extends TestCase {

	public void test() throws InterruptedException
	{
		Timer myTimer = new Timer(){

			@Override
			protected long getIntervalTime() {
				return 30000;
			}

		};
		assertTrue(myTimer.process());
		assertTrue(!myTimer.process());
		assertTrue(!myTimer.process());
		assertTrue(!myTimer.process());
		Thread.sleep(100);
		assertTrue(!myTimer.process());
		Thread.sleep(30000);
		assertTrue(myTimer.process());
		assertTrue(!myTimer.process());
		myTimer.reset();
		assertTrue(myTimer.process());
		assertTrue(!myTimer.process());
	}
	
	public void testWithDontShowMessageFirstTime() throws InterruptedException
	{
		Timer myTimer = new Timer(true){

			@Override
			protected long getIntervalTime() {
				return 30000;
			}

		};
		assertTrue(!myTimer.process());
		assertTrue(!myTimer.process());
		assertTrue(!myTimer.process());
		assertTrue(!myTimer.process());
		Thread.sleep(100);
		assertTrue(!myTimer.process());
		Thread.sleep(30000);
		assertTrue(myTimer.process());
		assertTrue(!myTimer.process());
		Thread.sleep(30000);
		myTimer.reset();
		assertTrue(!myTimer.process());
		assertTrue(!myTimer.process());
	}
	
	public void testIsActivated() throws InterruptedException
	{
		Timer myTimer = new Timer(){

			@Override
			protected long getIntervalTime() {
				return 30000;
			}

			@Override
			protected boolean isActivated() {
				return false;
			}

		};
		assertTrue(!myTimer.process());
		assertTrue(!myTimer.process());
		assertTrue(!myTimer.process());
		assertTrue(!myTimer.process());
		Thread.sleep(100);
		assertTrue(!myTimer.process());
		Thread.sleep(30000);
		assertTrue(!myTimer.process());
		assertTrue(!myTimer.process());
	}

}
