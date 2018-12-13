package yogi.report.compare.function.diff.config;


import yogi.base.util.PrecisionFloat;
import yogi.report.compare.function.CompareFunction;
import yogi.report.compare.function.diff.PrecisionFloatDiffCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class PrecisionFloatDiffCompareFunctionConfig extends CompareFunctionConfig<PrecisionFloat>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6434060238562094294L;

	public PrecisionFloatDiffCompareFunctionConfig() {
		super("Diff");
	}

	@Override
	public CompareFunction<PrecisionFloat> getCompareFunction(int dataSet1, int dataSet2) {
		return new PrecisionFloatDiffCompareFunction(dataSet1, dataSet2);
	}

}
