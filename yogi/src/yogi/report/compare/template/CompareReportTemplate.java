package yogi.report.compare.template;

import yogi.base.util.immutable.ImmutableList;
import yogi.report.column.ColumnDefinition;
import yogi.report.compare.function.CompareFunction;
import yogi.report.template.ReportTemplate;

public interface CompareReportTemplate<T> extends ReportTemplate<T>{
	ImmutableList<CompareFunction<?>> getCompareFunctions(int columnIndex);
	int getNumberOfDataSets();
	int getCompareColumnsWidth(ColumnDefinition<T> columnDefinition);
	String getDataSetName(int i);
}
