package yogi.report.condition.date.test;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.period.date.EarliestDateCreator;
import yogi.period.date.io.BoundaryNamedDDMMMYYDateScanner;
import yogi.period.date.io.DDMMMYYDateScanner;
import yogi.report.condition.date.EarliestDateCoversCondition;

public class EarliestDateCoversConditionTest extends TestCase{
	
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
	
	public void testDateMatchesCondition() {
		assertTrue(new EarliestDateCoversCondition("01JAN14").satisfied(new DDMMMYYDateScanner().scan("01JAN14").create()));
		assertFalse(new EarliestDateCoversCondition("01JAN14").satisfied(new DDMMMYYDateScanner().scan("02JAN14").create()));
		
		assertTrue(new EarliestDateCoversCondition("01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("01MAR14").create()));
		assertTrue(new EarliestDateCoversCondition("01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("15MAR14").create()));
		assertTrue(new EarliestDateCoversCondition("01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("31MAR14").create()));
		assertFalse(new EarliestDateCoversCondition("01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("01JUN14").create()));
		
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("01JAN14").create()));
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("01MAR14").create()));
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("15MAR14").create()));
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("31MAR14").create()));
		assertFalse(new EarliestDateCoversCondition("01JAN14, 01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("01JUN14").create()));
		
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("01JAN14").create()));
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("01FEB14").create()));
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("01MAR14").create()));
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("15MAR14").create()));
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("31MAR14").create()));
		assertFalse(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14").satisfied(new DDMMMYYDateScanner().scan("01JUN14").create()));
		
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14, 01APR14-30APR14").satisfied(new DDMMMYYDateScanner().scan("01JAN14").create()));
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14, 01APR14-30APR14").satisfied(new DDMMMYYDateScanner().scan("01FEB14").create()));
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14, 01APR14-30APR14").satisfied(new DDMMMYYDateScanner().scan("01MAR14").create()));
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14, 01APR14-30APR14").satisfied(new DDMMMYYDateScanner().scan("15MAR14").create()));
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14, 01APR14-30APR14").satisfied(new DDMMMYYDateScanner().scan("31MAR14").create()));
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14, 01APR14-30APR14").satisfied(new DDMMMYYDateScanner().scan("01APR14").create()));
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14, 01APR14-30APR14").satisfied(new DDMMMYYDateScanner().scan("15APR14").create()));
		assertTrue(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14, 01APR14-30APR14").satisfied(new DDMMMYYDateScanner().scan("30APR14").create()));
		assertFalse(new EarliestDateCoversCondition("01JAN14, 01FEB14, 01MAR14-31MAR14, 01APR14-30APR14").satisfied(new DDMMMYYDateScanner().scan("01JUN14").create()));
		
		assertTrue(new EarliestDateCoversCondition("BLANK").satisfied(new BoundaryNamedDDMMMYYDateScanner(EarliestDateCreator.creator).scan("BLANK").create()));
		assertTrue(new EarliestDateCoversCondition("BLANK").satisfied(new BoundaryNamedDDMMMYYDateScanner(EarliestDateCreator.creator).scan("BLANK").create()));
	}

}
