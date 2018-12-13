package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.types.DoubleRoundedTableColumnConfig;
import yogi.report.compare.function.average.config.DoubleAverageCompareFunctionConfig;
import yogi.report.compare.function.diff.config.DoubleDiffCompareFunctionConfig;
import yogi.report.condition.config.DoubleGreaterThanConditionConfig;
import yogi.report.condition.config.DoubleGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.DoubleInConditionConfig;
import yogi.report.condition.config.DoubleLessThanConditionConfig;
import yogi.report.condition.config.DoubleLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.DoubleNotInConditionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.function.average.config.DoubleAverageFunctionConfig;
import yogi.report.function.max.config.MaxDoubleFunctionConfig;
import yogi.report.function.min.config.MinDoubleFunctionConfig;
import yogi.report.function.sum.config.DoubleSumFunctionConfig;
import yogi.report.function.sumhalf.config.DoubleSumHalfFunctionConfig;
import yogi.report.server.config.ColumnConfig;

public class DoubleColumnConfig<R> extends ColumnConfig<R, Double> {

	private static final long serialVersionUID = 3821799954357130923L;

	public DoubleColumnConfig(String columnName, Evaluator<R, Double> evaluator, Formatter<Double> formatter, Comparator<Double> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new DoubleInConditionConfig());
		this.addCondition(new DoubleNotInConditionConfig());
		this.addCondition(new DoubleGreaterThanConditionConfig());
		this.addCondition(new DoubleGreaterThanOrEqualsConditionConfig());
		this.addCondition(new DoubleLessThanConditionConfig());
		this.addCondition(new DoubleLessThanOrEqualsConditionConfig());
        this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
        this.addFunction(new MinDoubleFunctionConfig());
		this.addFunction(new MaxDoubleFunctionConfig());
        this.addFunction(new DoubleAverageFunctionConfig());
        this.addFunction(new DoubleSumFunctionConfig());
        this.addFunction(new DoubleSumHalfFunctionConfig());
        this.addCompareFunction(new DoubleDiffCompareFunctionConfig());
        this.addCompareFunction(new DoubleAverageCompareFunctionConfig());
        
	}

	public DoubleColumnConfig(String columnName, Evaluator<R, Double> evaluator, Formatter<Double> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}

	public DoubleColumnConfig(String columnName, Evaluator<R, Double> evaluator, TableColumnConfig<?> tableColumnConfig) {
		this(columnName, evaluator, null, tableColumnConfig, false);
	}
	public DoubleColumnConfig(String columnName, Evaluator<R, Double> evaluator,Formatter<Double> formatter, int width){
		this(columnName, evaluator,formatter,null,new DoubleRoundedTableColumnConfig(columnName, width, false),false);
	}
	
	public DoubleColumnConfig(String columnName, Evaluator<R, Double> evaluator, String viewName, int width){
		this(columnName, evaluator,new DoubleRoundedTableColumnConfig(viewName, width, false));
	}
	public DoubleColumnConfig(String columnName, Evaluator<R, Double> evaluator,Formatter<Double> formatter){
		this(columnName, evaluator,formatter,null,new DoubleRoundedTableColumnConfig(columnName, 120, false),false);
	}
	public DoubleColumnConfig(String columnName, Evaluator<R, Double> evaluator){
		this(columnName, evaluator,columnName, 120);
	}

}
