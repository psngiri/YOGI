package yogi.report.condition.in.test;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.config.StringLikeConditionConfig;

public class StringLikeConditionTest extends TestCase{
	
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
	
	public void testStringMatchesCondition() {
		assertTrue(new StringLikeConditionConfig().getCondition("ATL").satisfied("ATL"));
		assertTrue(new StringLikeConditionConfig().getCondition("%UP%").satisfied("DUPE"));
		assertFalse(new StringLikeConditionConfig().getCondition("~DAL").satisfied("DAL"));
		assertTrue(new StringLikeConditionConfig().getCondition("D%").satisfied("DFW"));
		assertTrue(new StringLikeConditionConfig().getCondition("DF?").satisfied("DFW"));
		assertTrue(new StringLikeConditionConfig().getCondition("DF%").satisfied("DFW"));
		assertTrue(new StringLikeConditionConfig().getCondition("DF%,CH?").satisfied("CHI"));
		assertTrue(new StringLikeConditionConfig().getCondition("DF%,CH?,ATL").satisfied("ATL"));
		assertFalse(new StringLikeConditionConfig().getCondition("DF%,CH?,ATL").satisfied("XYZ"));
		
		assertTrue(new StringLikeConditionConfig().getCondition("DF%,CH?,ATL,~DAL").satisfied("DFW"));
		assertTrue(new StringLikeConditionConfig().getCondition("DF%,CH?,ATL,~DAL").satisfied("CHI"));
		assertTrue(new StringLikeConditionConfig().getCondition("DF%,CH?,ATL,~DAL").satisfied("ATL"));
		assertFalse(new StringLikeConditionConfig().getCondition("DF%,CH?,ATL,~DAL").satisfied("DAL"));
		assertFalse(new StringLikeConditionConfig().getCondition("DF%,CH?,ATL,~DAL").satisfied("XYZ"));
		assertTrue(new StringLikeConditionConfig().getCondition("F%,~F%A").satisfied("F"));
		assertTrue(new StringLikeConditionConfig().getCondition("F%,~F%A").satisfied("FA2AAZN"));
		assertTrue(new StringLikeConditionConfig().getCondition("H%,V%").satisfied("HGFRAY"));
		assertTrue(new StringLikeConditionConfig().getCondition("H%,V%").satisfied("VREMI"));
		
		assertTrue(new StringLikeConditionConfig().getCondition("?%").satisfied("ABC"));
		assertTrue(new StringLikeConditionConfig().getCondition("?%").satisfied("AB"));
		assertTrue(new StringLikeConditionConfig().getCondition("?%").satisfied("A"));
		assertTrue(new StringLikeConditionConfig().getCondition("~?%").satisfied(""));
	}

}
