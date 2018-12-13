package yogi.report.compare.function.diff.config;


import yogi.report.compare.function.CompareFunction;
import yogi.report.compare.function.diff.FloatDiffCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class FloatDiffCompareFunctionConfig extends CompareFunctionConfig<Float>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6434060238562094294L;

	public FloatDiffCompareFunctionConfig() {
		super("Diff");
	}

	@Override
	public CompareFunction<Float> getCompareFunction(int dataSet1, int dataSet2) {
		return new FloatDiffCompareFunction(dataSet1, dataSet2);
	}

}
