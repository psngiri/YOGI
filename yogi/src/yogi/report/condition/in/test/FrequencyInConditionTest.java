package yogi.report.condition.in.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.period.frequency.io.MondayToSundayOptionalOffCharFrequencyScanner;
import yogi.report.condition.frequency.FrequencyContainsCondition;
import yogi.report.condition.frequency.FrequencyInCondition;
import yogi.report.condition.frequency.FrequencyNotInCondition;
import yogi.report.condition.frequency.MondayToSundayOneToSevenFrequencyScanner;

import junit.framework.TestCase;

public class FrequencyInConditionTest extends TestCase{
	
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
		FrequencyInCondition condition = new FrequencyInCondition(value);
		assertTrue(condition.satisfied(scanner.scan("MTWQ").create()));
		assertTrue(condition.satisfied(scanner.scan("FJS").create()));
		assertTrue(condition.satisfied(scanner.scan("JS").create()));
		
		assertFalse(condition.satisfied(scanner.scan("TWQ").create()));
	}

	public void testInConditionForNoDayData() {
		MondayToSundayOptionalOffCharFrequencyScanner scanner = new MondayToSundayOptionalOffCharFrequencyScanner(new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'});
		String value = "MTWQ";
		FrequencyInCondition condition = new FrequencyInCondition(value);
		assertTrue(condition.satisfied(scanner.scan("MTWQ").create()));
		assertFalse(condition.satisfied(scanner.scan("").create()));
	}

	public void testAllDayInCondition() {
		MondayToSundayOptionalOffCharFrequencyScanner scanner = new MondayToSundayOptionalOffCharFrequencyScanner(new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'});
		String value = "MTWQFJS";
		FrequencyInCondition condition = new FrequencyInCondition(value);
		assertFalse(condition.satisfied(scanner.scan("MTWQ").create()));
		assertFalse(condition.satisfied(scanner.scan("FJS").create()));
		assertFalse(condition.satisfied(scanner.scan("").create()));
	}

	public void testNoDayInCondition() {
		MondayToSundayOptionalOffCharFrequencyScanner scanner = new MondayToSundayOptionalOffCharFrequencyScanner(new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'});
		String value = ".,FJS";
		FrequencyInCondition condition = new FrequencyInCondition(value);
		assertFalse(condition.satisfied(scanner.scan("MTWQ").create()));
		assertTrue(condition.satisfied(scanner.scan("FJS").create()));
		assertFalse(condition.satisfied(scanner.scan("FJ").create()));
		assertFalse(condition.satisfied(scanner.scan("F").create()));
		assertTrue(condition.satisfied(scanner.scan("").create()));
	}

	public void testNotInCondition() {
		MondayToSundayOptionalOffCharFrequencyScanner scanner = new MondayToSundayOptionalOffCharFrequencyScanner(new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'});
		String value = "MTWQ, FJS, JS";
		FrequencyNotInCondition condition = new FrequencyNotInCondition(value);
		assertFalse(condition.satisfied(scanner.scan("MTWQ").create()));
		assertFalse(condition.satisfied(scanner.scan("FJS").create()));
		assertFalse(condition.satisfied(scanner.scan("JS").create()));
		
		assertTrue(condition.satisfied(scanner.scan("TWQ").create()));
	}
	
	public void testCharChanges()
	{
		MondayToSundayOneToSevenFrequencyScanner scanner = new MondayToSundayOneToSevenFrequencyScanner();
		String value = "1234";
		FrequencyContainsCondition condition = new FrequencyContainsCondition(value,scanner);
		assertTrue(condition.satisfied(scanner.scan("1234").create()));
		assertFalse(condition.satisfied(scanner.scan("567").create()));
		assertFalse(condition.satisfied(scanner.scan("67").create()));
		assertFalse(condition.satisfied(scanner.scan("234").create()));
	}
}