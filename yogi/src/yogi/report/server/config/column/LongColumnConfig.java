package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.types.LongTableColumnConfig;
import yogi.report.compare.function.average.config.LongAverageCompareFunctionConfig;
import yogi.report.compare.function.diff.config.LongDiffCompareFunctionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.config.LongGreaterThanConditionConfig;
import yogi.report.condition.config.LongGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.LongInConditionConfig;
import yogi.report.condition.config.LongLessThanConditionConfig;
import yogi.report.condition.config.LongLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.LongNotInConditionConfig;
import yogi.report.function.average.config.LongAverageFunctionConfig;
import yogi.report.function.max.config.MaxLongFunctionConfig;
import yogi.report.function.min.config.MinLongFunctionConfig;
import yogi.report.function.sum.config.LongSumFunctionConfig;
import yogi.report.function.sumhalf.config.LongSumHalfFunctionConfig;
import yogi.report.server.config.ColumnConfig;

public class LongColumnConfig<R> extends ColumnConfig<R, Long> {

	private static final long serialVersionUID = -2787939807086850585L;

	public LongColumnConfig(String columnName, Evaluator<R, Long> evaluator, Formatter<Long> formatter, Comparator<Long> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new LongInConditionConfig());
		this.addCondition(new LongNotInConditionConfig());
		this.addCondition(new LongGreaterThanConditionConfig());
		this.addCondition(new LongGreaterThanOrEqualsConditionConfig());
		this.addCondition(new LongLessThanConditionConfig());
		this.addCondition(new LongLessThanOrEqualsConditionConfig());
		this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
		this.addFunction(new MinLongFunctionConfig());
		this.addFunction(new MaxLongFunctionConfig());
		this.addFunction(new LongSumFunctionConfig());
		this.addFunction(new LongSumHalfFunctionConfig());
		this.addFunction(new LongAverageFunctionConfig());
		if(!key){
			this.addCompareFunction(new LongDiffCompareFunctionConfig());
			this.addCompareFunction(new LongAverageCompareFunctionConfig());
		}
	}

	public LongColumnConfig(String columnName, Evaluator<R, Long> evaluator, Formatter<Long> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}

	public LongColumnConfig(String columnName, Evaluator<R, Long> evaluator, TableColumnConfig<?> tableColumnConfig) {
		this(columnName, evaluator, null, tableColumnConfig, false);
	}
	
	public LongColumnConfig(String columnName, Evaluator<R, Long> evaluator, String viewName, int width){
		this(columnName, evaluator,new LongTableColumnConfig(viewName, width, false));
	}
	
	public LongColumnConfig(String columnName, Evaluator<R, Long> evaluator, Formatter<Long> formatter){
		this(columnName, evaluator,formatter, new LongTableColumnConfig(columnName, 120, formatter), false);
	}
	
	public LongColumnConfig(String columnName, Evaluator<R, Long> evaluator, int width){
		this(columnName, evaluator,columnName, width);
	}
	
	public LongColumnConfig(String columnName, Evaluator<R, Long> evaluator){
		this(columnName, evaluator,120);
	}
}
