package yogi.report.condition.in.test;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.period.frequency.io.MondayToSundayOptionalOffCharFrequencyScanner;
import yogi.report.condition.frequency.FrequencyCoversCondition;

public class FrequencyCoversConditionTest extends TestCase{
	
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
	
	public void testInCondition() {
		MondayToSundayOptionalOffCharFrequencyScanner scanner = new MondayToSundayOptionalOffCharFrequencyScanner(new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'});
		String value = "MTWQ, FJS, JS";
		FrequencyCoversCondition condition = new FrequencyCoversCondition(value, ',');
		assertTrue(condition.satisfied(scanner.scan("MTWQ").create()));
		assertTrue(condition.satisfied(scanner.scan("FJS").create()));
		assertTrue(condition.satisfied(scanner.scan("JS").create()));
		
		assertTrue(condition.satisfied(scanner.scan("TWQ").create()));
	}

	public void testInConditionForNoDayData() {
		MondayToSundayOptionalOffCharFrequencyScanner scanner = new MondayToSundayOptionalOffCharFrequencyScanner(new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'});
		String value = "MTWQ";
		FrequencyCoversCondition condition = new FrequencyCoversCondition(value, ',');
		assertTrue(condition.satisfied(scanner.scan("MTWQ").create()));
		assertFalse(condition.satisfied(scanner.scan("").create()));
	}

	public void testNoDayInCondition() {
		MondayToSundayOptionalOffCharFrequencyScanner scanner = new MondayToSundayOptionalOffCharFrequencyScanner(new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'});
		String value = ".,FJS";
		FrequencyCoversCondition condition = new FrequencyCoversCondition(value, ',');
		assertFalse(condition.satisfied(scanner.scan("MTWQ").create()));
		assertTrue(condition.satisfied(scanner.scan("FJS").create()));
		assertTrue(condition.satisfied(scanner.scan("FJ").create()));
		assertTrue(condition.satisfied(scanner.scan("F").create()));
		assertTrue(condition.satisfied(scanner.scan("").create()));
	}

}
