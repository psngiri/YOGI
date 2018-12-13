package yogi.report.server.config.column;


import java.util.Comparator;
import java.util.List;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.column.TableColumnConfig;
import yogi.report.condition.config.IntegerListInConditionConfig;
import yogi.report.condition.config.IntegerListNotInConditionConfig;
import yogi.report.condition.config.IsNotNullConditionConfig;
import yogi.report.condition.config.IsNullConditionConfig;
import yogi.report.function.integerlist.config.IntegerListIntersectionFunctionConfig;
import yogi.report.function.integerlist.config.IntegerListUnionFunctionConfig;
import yogi.report.server.config.ColumnConfig;


public class IntegerListColumnConfig<R> extends ColumnConfig<R, List<Integer>> {

	private static final long serialVersionUID = 408540737574249968L;

	public IntegerListColumnConfig(String columnName, Evaluator<R, List<Integer>> evaluator, Formatter<List<Integer>> formatter, Comparator<List<Integer>> comparator, TableColumnConfig<?> tableColumnConfig, boolean key) {
		super(columnName, evaluator, formatter, comparator, tableColumnConfig, key);
		this.addCondition(new IntegerListInConditionConfig());
		this.addCondition(new IntegerListNotInConditionConfig());
		this.addCondition(new IsNullConditionConfig());
        this.addCondition(new IsNotNullConditionConfig());
        this.addFunction(new IntegerListUnionFunctionConfig());
		this.addFunction(new IntegerListIntersectionFunctionConfig());		
	}
	
	public IntegerListColumnConfig(String columnName, Evaluator<R, List<Integer>> evaluator, Formatter<List<Integer>> formatter, TableColumnConfig<?> tableColumnConfig, boolean key) {
		this(columnName, evaluator, formatter, null, tableColumnConfig, key);
	}
}
