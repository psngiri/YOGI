package yogi.report.server.config.column;

import yogi.base.evaluator.Evaluator;
import yogi.paging.column.types.IntegerTableColumnConfig;
import yogi.report.compare.function.average.config.IntegerAverageCompareFunctionConfig;
import yogi.report.compare.function.diff.config.IntegerDiffCompareFunctionConfig;
import yogi.report.function.count.config.CountFunctionConfig;
import yogi.report.server.config.ColumnConfig;
import yogi.report.server.tuple.evaluators.TupleRowDummyEvaluator;


public class CountColumnConfig<R> extends ColumnConfig<R, Integer> {

	private static final long serialVersionUID = -2787939807086850585L;
	private static final String COUNT = "Count";

	@SuppressWarnings("unchecked")
	public CountColumnConfig() {
		super(COUNT, (Evaluator<R, Integer>) new TupleRowDummyEvaluator<Integer>(COUNT), null, null, new IntegerTableColumnConfig(COUNT, 80, false), false);
		CountFunctionConfig function = new CountFunctionConfig();
		this.addFunction(function);
		this.addCompareFunction(new IntegerDiffCompareFunctionConfig());
		this.addCompareFunction(new IntegerAverageCompareFunctionConfig());
		this.setDefaultFunction(function.getName());
	}
}