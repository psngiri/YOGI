package yogi.optimize.expr.xpress.bp.test;

import yogi.optimize.expr.xpress.bp.XpressOptimizer;
import junit.framework.TestCase;

public class XpressOptimizerTest extends TestCase {
	private XpressOptimizer optimizer;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		optimizer = new XpressOptimizer(getClass().getName());
	}

	public void testShouldSetCorrectAlgorithmIndicatorForBarrier() {
		optimizer.useBarrierAlgorithm();

		assertEquals("b", optimizer.getAlgorithmType());
	}

	public void testShouldSetCorrectAlgorithmIndicatorForPrimalSimplex() {
		optimizer.usePrimalSimplexAlgorithm();

		assertEquals("p", optimizer.getAlgorithmType());
	}

	public void testShouldSetCorrectAlgorithmIndicatorForBarrierMixedInteger() {
		optimizer.useBarrierAlgorithmMixedInteger();

		assertEquals("bg", optimizer.getAlgorithmType());
	}
}
