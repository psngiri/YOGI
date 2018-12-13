package yogi.report.compare.function.diff.config;


import yogi.report.compare.function.CompareFunction;
import yogi.report.compare.function.diff.LongDiffCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class LongDiffCompareFunctionConfig extends CompareFunctionConfig<Long>{

	private static final long serialVersionUID = -6434060238562094294L;

	public LongDiffCompareFunctionConfig() {
		super("Diff");
	}

	@Override
	public CompareFunction<Long> getCompareFunction(int dataSet1, int dataSet2) {
		return new LongDiffCompareFunction(dataSet1, dataSet2);
	}

}
