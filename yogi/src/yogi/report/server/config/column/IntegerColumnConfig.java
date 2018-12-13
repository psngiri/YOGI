package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.types.IntegerTableColumnConfig;
import yogi.report.compare.function.average.config.IntegerAverageCompareFunctionConfig;
import yogi.report.compare.function.diff.config.IntegerDiffCompareFunctionConfig;
import yogi.report.condition.config.IntegerGreaterThanConditionConfig;
import yogi.report.condition.config.IntegerGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.IntegerInConditionConfig;
import yogi.report.condition.config.IntegerLessThanConditionConfig;
import yogi.report.condition.config.IntegerLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.IntegerNotInConditionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.function.average.config.IntegerAverageFunctionConfig;
import yogi.report.function.max.config.MaxIntegerFunctionConfig;
import yogi.report.function.min.config.MinIntegerFunctionConfig;
import yogi.report.function.sum.config.IntegerSumFunctionConfig;
import yogi.report.function.sumhalf.config.IntegerSumHalfFunctionConfig;
import yogi.report.server.config.ColumnConfig;

public class IntegerColumnConfig<R> extends ColumnConfig<R, Integer> {

	private static final long serialVersionUID = -2787939807086850585L;

	public IntegerColumnConfig(String columnName, Evaluator<R, Integer> evaluator, Formatter<Integer> formatter, Comparator<Integer> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new IntegerInConditionConfig());
		this.addCondition(new IntegerNotInConditionConfig());
		this.addCondition(new IntegerGreaterThanConditionConfig());
		this.addCondition(new IntegerGreaterThanOrEqualsConditionConfig());
		this.addCondition(new IntegerLessThanConditionConfig());
		this.addCondition(new IntegerLessThanOrEqualsConditionConfig());
		this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
		this.addFunction(new MinIntegerFunctionConfig());
		this.addFunction(new MaxIntegerFunctionConfig());
		this.addFunction(new IntegerSumFunctionConfig());
		this.addFunction(new IntegerSumHalfFunctionConfig());
		this.addFunction(new IntegerAverageFunctionConfig());
		if(!key){
			this.addCompareFunction(new IntegerDiffCompareFunctionConfig());
			this.addCompareFunction(new IntegerAverageCompareFunctionConfig());
		}
	}

	public IntegerColumnConfig(String columnName, Evaluator<R, Integer> evaluator, Formatter<Integer> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}

	public IntegerColumnConfig(String columnName, Evaluator<R, Integer> evaluator, TableColumnConfig<?> tableColumnConfig) {
		this(columnName, evaluator, null, tableColumnConfig, false);
	}
	public IntegerColumnConfig(String columnName, Evaluator<R, Integer> evaluator, String viewName, int width){
		this(columnName, evaluator,new IntegerTableColumnConfig(viewName, width, false));
	}
	public IntegerColumnConfig(String columnName, Evaluator<R, Integer> evaluator){
		this(columnName, evaluator,columnName, 120);
	}
}
