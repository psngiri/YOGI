package yogi.report.server.row;

import java.util.List;

import yogi.report.server.Query;

public interface RowExecutor {
	List<Row> execute(Query query, int queryIndex, String userid, int maxRows);
}
