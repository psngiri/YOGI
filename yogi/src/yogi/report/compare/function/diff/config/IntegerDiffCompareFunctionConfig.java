package yogi.report.compare.function.diff.config;


import yogi.report.compare.function.CompareFunction;
import yogi.report.compare.function.diff.IntegerDiffCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class IntegerDiffCompareFunctionConfig extends CompareFunctionConfig<Integer>{


	private static final long serialVersionUID = -169837017059289874L;

	public IntegerDiffCompareFunctionConfig() {
		super("Diff");
	}

	@Override
	public CompareFunction<Integer> getCompareFunction(int dataSet1, int dataSet2) {
		return new IntegerDiffCompareFunction(dataSet1, dataSet2);
	}

}
