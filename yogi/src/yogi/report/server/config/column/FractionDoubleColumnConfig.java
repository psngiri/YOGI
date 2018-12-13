package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.base.util.FractionDouble;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.types.DoubleRoundedTableColumnConfig;
import yogi.report.compare.function.diff.config.FractionDoubleDiffCompareFunctionConfig;
import yogi.report.condition.config.FractionDoubleGreaterThanConditionConfig;
import yogi.report.condition.config.FractionDoubleGreaterThanOrEqualsConditionConfig;
import yogi.report.condition.config.FractionDoubleInConditionConfig;
//import yogi.report.condition.config.FractionDoubleIsBlankConditionConfig;
//import yogi.report.condition.config.FractionDoubleIsNotBlankConditionConfig;
import yogi.report.condition.config.FractionDoubleLessThanConditionConfig;
import yogi.report.condition.config.FractionDoubleLessThanOrEqualsConditionConfig;
import yogi.report.condition.config.FractionDoubleNotInConditionConfig;
import yogi.report.function.average.config.FractionDoubleAverageFunctionConfig;
import yogi.report.function.max.config.MaxFractionDoubleFunctionConfig;
import yogi.report.function.min.config.MinFractionDoubleFunctionConfig;
import yogi.report.function.sum.config.FractionDoubleSumFunctionConfig;
import yogi.report.function.sumhalf.config.FractionDoubleSumHalfFunctionConfig;
import yogi.report.server.config.ColumnConfig;

public class FractionDoubleColumnConfig<R> extends ColumnConfig<R, FractionDouble> {

	private static final long serialVersionUID = 1L;

	public FractionDoubleColumnConfig(String columnName, Evaluator<R, FractionDouble> evaluator, Formatter<FractionDouble> formatter, Comparator<FractionDouble> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new FractionDoubleInConditionConfig());
		this.addCondition(new FractionDoubleNotInConditionConfig());
		this.addCondition(new FractionDoubleGreaterThanConditionConfig());
		this.addCondition(new FractionDoubleGreaterThanOrEqualsConditionConfig());
		this.addCondition(new FractionDoubleLessThanConditionConfig());
		this.addCondition(new FractionDoubleLessThanOrEqualsConditionConfig());	
   //   this.addCondition(new FractionDoubleIsBlankConditionConfig());
   //   this.addCondition(new FractionDoubleIsNotBlankConditionConfig());
		
        this.addFunction(new MinFractionDoubleFunctionConfig());
		this.addFunction(new MaxFractionDoubleFunctionConfig());
        this.addFunction(new FractionDoubleAverageFunctionConfig());
        this.addFunction(new FractionDoubleSumFunctionConfig());
        this.addFunction(new FractionDoubleSumHalfFunctionConfig());
        this.addCompareFunction(new FractionDoubleDiffCompareFunctionConfig());
        
	}

	public FractionDoubleColumnConfig(String columnName, Evaluator<R, FractionDouble> evaluator, Formatter<FractionDouble> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}
	public FractionDoubleColumnConfig(String columnName, Evaluator<R, FractionDouble> evaluator, TableColumnConfig<?> tableColumnConfig) {
		this(columnName, evaluator, null, tableColumnConfig, false);
	}	
	public FractionDoubleColumnConfig(String columnName, Evaluator<R, FractionDouble> evaluator, String viewName, int width){
		this(columnName, evaluator,new DoubleRoundedTableColumnConfig(viewName, width, false));
	}
	public FractionDoubleColumnConfig(String columnName, Evaluator<R, FractionDouble> evaluator){
		this(columnName, evaluator,columnName, 120);
	}
	public FractionDoubleColumnConfig(String columnName, Evaluator<R, FractionDouble> evaluator,Formatter<FractionDouble> formatter){
		this(columnName, evaluator,formatter,null,new DoubleRoundedTableColumnConfig(columnName, 120, false),false);
	}
}
