package yogi.base.util.test;

import java.io.File;

import yogi.base.util.LockManager;

import junit.framework.TestCase;

public class LockManagerTest extends TestCase {

	public void test()
	{
		LockManager lockManager = new LockManager("data/test", "myTest");
		lockManager.checkAndCreateLock();
		File lockFile = lockManager.getLockFile();
		assertTrue(lockFile.exists());
		assertTrue(lockFile.getName().startsWith("myTest."));
		assertTrue(lockFile.getName().endsWith(".lock"));
		lockManager.removeLock();
		assertTrue(!lockFile.exists());
	}
	
	public void test1()
	{
		LockManager lockManager = new LockManager("data/test", "myTest");
		lockManager.checkAndCreateLock();
		File lockFile = lockManager.getLockFile();
		try {
			lockManager.checkAndCreateLock();
			fail("Should throw a lock file exists exception");
		} catch (RuntimeException e) {
		}
		lockManager.removeLock();
		assertTrue(!lockFile.exists());
	}
	
	public void test2()
	{
		LockManager lockManager = new LockManager("data/test", "myTest");
		try {
			lockManager.run();
			fail("Should throw an exception");
		} catch (RuntimeException e) {
		}
		lockManager.setRunnable(new Runnable(){

			public void run() {
				throw new RuntimeException();
			}
			
		});
		try {
			lockManager.removeLock();
			fail("Should throw an exception");
		} catch (RuntimeException e) {
		}
	}
}
