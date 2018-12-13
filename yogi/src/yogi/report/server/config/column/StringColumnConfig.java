package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.types.StringTableColumnConfig;
import yogi.report.compare.function.diff.config.StringDiffCompareFunctionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.config.StringInConditionConfig;
import yogi.report.condition.config.StringLikeConditionConfig;
import yogi.report.condition.config.StringNotInConditionConfig;
import yogi.report.server.config.ColumnConfig;

public  class StringColumnConfig<R> extends ColumnConfig<R, String> {

	private static final long serialVersionUID = 3468019700919515136L;

	public StringColumnConfig(String columnName, Evaluator<R, String> evaluator, Formatter<String> formatter, Comparator<String> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new StringInConditionConfig());
		this.addCondition(new StringNotInConditionConfig());
		this.addCondition(new StringLikeConditionConfig());
        this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
        this.addCompareFunction(new StringDiffCompareFunctionConfig());
	}

	public StringColumnConfig(String columnName, Evaluator<R, String> evaluator, Formatter<String> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}
	
	public StringColumnConfig(String columnName, Evaluator<R, String> evaluator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, null, tableColumnConfig, key);
	}
	public StringColumnConfig(String columnName, Evaluator<R, String> evaluator, String viewName, int width){
		this(columnName, evaluator,new StringTableColumnConfig(viewName, width, false),true);
	}
	
	public StringColumnConfig(String columnName, Evaluator<R, String> evaluator, int width){
		this(columnName, evaluator,columnName, width);
	}
	
	public StringColumnConfig(String columnName, Evaluator<R, String> evaluator){
		this(columnName, evaluator, 120);
	}
}
