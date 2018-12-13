package yogi.report.template;

import java.util.Comparator;
import java.util.List;

import yogi.base.Selector;
import yogi.report.Footer;
import yogi.report.Header;
import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnGroupBy;
import yogi.report.group.Group;

public interface ReportTemplate<T> {
	Header getReportHeader();
	Footer getReportFooter();
	List<ColumnDefinition<T>> getColumns();
	String getColumnSeparator();
	int getColumnSeparatorWidth();
	int getWidth();
	List<ColumnGroupBy<T>> getGroupBys();
	Comparator<T> getComparator();
	Comparator<Group<T>> getGroupComparator();
	Selector<Group<T>> getGroupSelector();
}
