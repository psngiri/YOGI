package yogi.report.server;

import java.util.List;

public interface ReportDataItemsProvider<R> {
	List<List<R>> getItems(Query query, String userId, int maxOutput);
	ColumnAndSelector<R> enhance(ColumnAndSelector<R> columnSelector);
}
