package yogi.report.compare.function.diff.config;


import yogi.base.util.FractionDouble;
import yogi.report.compare.function.CompareFunction;
import yogi.report.compare.function.diff.FractionDoubleDiffCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class FractionDoubleDiffCompareFunctionConfig extends CompareFunctionConfig<FractionDouble>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6434060238562094294L;

	public FractionDoubleDiffCompareFunctionConfig() {
		super("Diff");
	}

	@Override
	public CompareFunction<FractionDouble> getCompareFunction(int dataSet1, int dataSet2) {
		return new FractionDoubleDiffCompareFunction(dataSet1, dataSet2);
	}

}
