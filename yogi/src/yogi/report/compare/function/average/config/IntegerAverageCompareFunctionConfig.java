package yogi.report.compare.function.average.config;


import yogi.report.compare.function.CompareFunction;
import yogi.report.compare.function.average.IntegerAverageCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class IntegerAverageCompareFunctionConfig extends CompareFunctionConfig<Integer>{

	private static final long serialVersionUID = -6434060238562094294L;

	public IntegerAverageCompareFunctionConfig() {
		super("Average");
	}

	@Override
	public CompareFunction<Integer> getCompareFunction(int dataSet1, int dataSet2) {
		return new IntegerAverageCompareFunction(dataSet1, dataSet2);
	}

}
