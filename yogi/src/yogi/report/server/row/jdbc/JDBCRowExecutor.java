package yogi.report.server.row.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yogi.base.io.db.DBException;
import yogi.base.io.db.QueryExecutor;
import yogi.base.io.resource.db.DbResource;
import yogi.report.server.Column;
import yogi.report.server.Query;
import yogi.report.server.row.Row;
import yogi.report.server.row.RowExecutor;
import yogi.report.server.row.RowImpl;
import yogi.report.server.sql.SqlQueryUtil;

public class JDBCRowExecutor implements RowExecutor {

	public static final int fetchSize = 1000;
	private DbResource resource;
	private String tableName;

	public JDBCRowExecutor(DbResource resource, String tableName) {
		super();
		this.resource = resource;
		this.tableName = tableName;
	}

	@Override
	public List<Row> execute(Query query, int queryIndex, String userid, int maxRows) {
		Map<String, Integer> columnIndexMap = new HashMap<String, Integer>();
		List<Column> selectedColumns = query.getSelectedColumns();
		for(int i=0;i<selectedColumns.size();i++){
			columnIndexMap.put(selectedColumns.get(i).getName(), i) ;
		}
		String sqlQuery = SqlQueryUtil.buildSqlQuery(query);
		sqlQuery = sqlQuery.replaceFirst("TABLENAME", tableName);
		List<Row> rtnValue = new ArrayList<Row>();
		try {
			List<Object[]> rows = QueryExecutor.get().executeQuery(resource, sqlQuery, fetchSize);
			for(Object[] row: rows){
				rtnValue.add(new RowImpl(row, columnIndexMap));
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
		return rtnValue;
	}

}
