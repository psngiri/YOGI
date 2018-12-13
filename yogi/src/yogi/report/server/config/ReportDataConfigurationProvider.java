package yogi.report.server.config;

import java.util.Comparator;
import java.util.List;

import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.paging.TableActionConfig;
import yogi.paging.column.TableColumnConfig;
import yogi.report.compare.function.CompareFunction;
import yogi.report.condition.Condition;
import yogi.report.function.Function;
import yogi.report.server.Column;
import yogi.report.server.Filter;
import yogi.report.server.ReportDataItemsProvider;

public interface ReportDataConfigurationProvider<R> {
	Function<Object> getFunction(Column column);
	Formatter<Object> getFormatter(Column column);
	Evaluator<R, Object> getEvaluator(Column column);
	Condition<Object> getCondition(Filter aliasFilter);
	ReportDataItemsProvider<R> getReportDataItemsProvider();
	boolean validate(String[] Value);
	List<CompareFunction<?>> getCompareFunctions(Column column, int numberOfDataSets);
	TableColumnConfig<?> getTableColumnConfig(String columnName);
	List<TableActionConfig> getTableActionConfigs();
	Comparator<Object> getComparator(Column column);
	ColumnConfig<? super R, ?> getColumnConfig(Column column);
	String getDefaultFindColumnName();
	boolean isDefaultEditSelectAllValue();
}
