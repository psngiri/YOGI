package yogi.report.function.frequency.test;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;
import yogi.remote.CommandException;
import yogi.report.function.frequency.FrequencyIntersectionFunction;

public class FrequencyIntersectionFunctionTest extends TestCase {
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
		byte freq1 = Frequency.SUNDAY;		
		byte freq2 = Frequency.SUNDAY;
		
		FrequencyIntersectionFunction intersectFrequencies = new FrequencyIntersectionFunction();

		intersectFrequencies.process(FrequencyManager.get().getFrequency(freq1), 0);
		intersectFrequencies.process(FrequencyManager.get().getFrequency(freq2), 0);
		assertEquals(FrequencyManager.get().getFrequency(freq1 & freq2), intersectFrequencies.getValue());
	}
}
