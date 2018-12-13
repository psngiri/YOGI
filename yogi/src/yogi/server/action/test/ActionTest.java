package yogi.server.action.test;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.server.action.Action;
import yogi.server.action.ActionAssistant;

public class ActionTest extends TestCase {
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	protected void tearDown() throws Exception {
		TestErrorReporter.end();
		Factory.clearAllFactories();
		super.tearDown();
	}

	public void test() {
		Action type=ActionAssistant.get().getAction(1);
		System.out.println(type.toString()+"\t"+type.getDescription());
	}
}
