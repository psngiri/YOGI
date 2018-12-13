package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.types.UCStringTableColumnConfig;
import yogi.report.compare.function.diff.config.StringDiffCompareFunctionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.config.UCStringInConditionConfig;
import yogi.report.condition.config.UCStringLikeConditionConfig;
import yogi.report.condition.config.UCStringNotInConditionConfig;
import yogi.report.server.config.ColumnConfig;

public  class UCStringColumnConfig<R> extends ColumnConfig<R, String> {

	private static final long serialVersionUID = -849343928127887042L;

	public UCStringColumnConfig(String columnName, Evaluator<R, String> evaluator, Formatter<String> formatter, Comparator<String> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new UCStringInConditionConfig());
		this.addCondition(new UCStringNotInConditionConfig());
		this.addCondition(new UCStringLikeConditionConfig());
        this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
        this.addCompareFunction(new StringDiffCompareFunctionConfig());
	}

	public UCStringColumnConfig(String columnName, Evaluator<R, String> evaluator, Formatter<String> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}
	
	public UCStringColumnConfig(String columnName, Evaluator<R, String> evaluator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, null, tableColumnConfig, key);
	}
	public UCStringColumnConfig(String columnName, Evaluator<R, String> evaluator, String viewName, int width){
		this(columnName, evaluator,new UCStringTableColumnConfig(viewName, width, false),true);
	}
	
	public UCStringColumnConfig(String columnName, Evaluator<R, String> evaluator){
		this(columnName, evaluator,columnName, 120);
	}
}
