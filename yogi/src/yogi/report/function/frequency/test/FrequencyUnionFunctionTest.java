package yogi.report.function.frequency.test;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;
import yogi.remote.CommandException;
import yogi.report.function.frequency.FrequencyUnionFunction;

public class FrequencyUnionFunctionTest extends TestCase {
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception
	{
		TestErrorReporter.end();
		Factory.clearAllFactories();
		super.tearDown();
	}
		
	public void test() throws CommandException {
		byte freq1 = Frequency.MONDAY;		
		byte freq2 = Frequency.THURSDAY;
		
		FrequencyUnionFunction addFrequencies = new FrequencyUnionFunction();

		addFrequencies.process(FrequencyManager.get().getFrequency(freq1), 0);
		addFrequencies.process(FrequencyManager.get().getFrequency(freq2), 0);
		assertEquals(FrequencyManager.get().getFrequency(freq1 | freq2), addFrequencies.getValue());
	}
}
