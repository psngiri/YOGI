package yogi.report.viewer;

import yogi.report.column.ColumnDefinition;
import yogi.report.group.Group;

public interface ReportData<T> extends ReportGroupData<T>{
	Group<T> getGroup();
	Object getValue(ColumnDefinition<T> columnDefinition);
}
