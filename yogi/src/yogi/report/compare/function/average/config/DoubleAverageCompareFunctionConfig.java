package yogi.report.compare.function.average.config;


import yogi.report.compare.function.CompareFunction;
import yogi.report.compare.function.average.DoubleAverageCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class DoubleAverageCompareFunctionConfig extends CompareFunctionConfig<Double>{

	private static final long serialVersionUID = -6434060238562094294L;

	public DoubleAverageCompareFunctionConfig() {
		super("Average");
	}

	@Override
	public CompareFunction<Double> getCompareFunction(int dataSet1, int dataSet2) {
		return new DoubleAverageCompareFunction(dataSet1, dataSet2);
	}

}
