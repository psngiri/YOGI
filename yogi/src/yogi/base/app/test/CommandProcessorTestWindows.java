package yogi.base.app.test;


import junit.framework.TestCase;

import yogi.base.app.CommandProcessor;
import yogi.base.app.Executor;

public class CommandProcessorTestWindows extends TestCase {

	public void test()
	{
		CommandProcessor cp = new CommandProcessor();
		cp.setCommand("data\\test\\test.bat ");
		Executor.get().execute(cp);
	}
}
