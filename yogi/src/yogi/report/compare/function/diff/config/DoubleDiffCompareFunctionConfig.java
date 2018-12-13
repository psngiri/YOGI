package yogi.report.compare.function.diff.config;


import yogi.report.compare.function.CompareFunction;
import yogi.report.compare.function.diff.DoubleDiffCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class DoubleDiffCompareFunctionConfig extends CompareFunctionConfig<Double>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6434060238562094294L;

	public DoubleDiffCompareFunctionConfig() {
		super("Diff");
	}

	@Override
	public CompareFunction<Double> getCompareFunction(int dataSet1, int dataSet2) {
		return new DoubleDiffCompareFunction(dataSet1, dataSet2);
	}

}
