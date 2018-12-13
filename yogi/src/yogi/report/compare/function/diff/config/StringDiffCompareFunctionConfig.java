package yogi.report.compare.function.diff.config;


import yogi.report.compare.function.CompareFunction;
import yogi.report.compare.function.diff.StringDiffCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class StringDiffCompareFunctionConfig extends CompareFunctionConfig<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6434060238562094294L;

	public StringDiffCompareFunctionConfig() {
		super("Diff");
	}

	@Override
	public CompareFunction<String> getCompareFunction(int dataSet1, int dataSet2) {
		return new StringDiffCompareFunction(dataSet1, dataSet2);
	}

}
