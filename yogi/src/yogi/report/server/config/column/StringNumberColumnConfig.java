package yogi.report.server.config.column;

import java.util.Comparator;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.types.StringNumberTableColumnConfig;
import yogi.report.compare.function.diff.config.StringDiffCompareFunctionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.condition.config.StringNumberInConditionConfig;
import yogi.report.condition.config.StringNumberLikeConditionConfig;
import yogi.report.condition.config.StringNumberNotInConditionConfig;
import yogi.report.server.config.ColumnConfig;

public class StringNumberColumnConfig<R> extends ColumnConfig<R, String> {

	private static final long serialVersionUID = -2504853361430092018L;

	public StringNumberColumnConfig(String columnName, Evaluator<R, String> evaluator, Formatter<String> formatter, Comparator<String> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new StringNumberInConditionConfig());
		this.addCondition(new StringNumberNotInConditionConfig());
		this.addCondition(new StringNumberLikeConditionConfig());
        this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
        this.addCompareFunction(new StringDiffCompareFunctionConfig());
	}

	public StringNumberColumnConfig(String columnName, Evaluator<R, String> evaluator, Formatter<String> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}
	
	public StringNumberColumnConfig(String columnName, Evaluator<R, String> evaluator, String viewName, int width){
		this(columnName, evaluator,null, new StringNumberTableColumnConfig(viewName, width, false),true);
	}
	
	public StringNumberColumnConfig(String columnName, Evaluator<R, String> evaluator){
		this(columnName, evaluator,columnName, 120);
	}

}
