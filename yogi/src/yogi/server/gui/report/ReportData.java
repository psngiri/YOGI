package yogi.server.gui.report;

import yogi.report.server.Query;
import yogi.server.gui.record.data.RecordData;

public class ReportData extends RecordData{
	
	private Query query;

	public ReportData(Query query) {
		super();
		this.query = query;
	}

	public Query getQuery() {
		return query;
	}
}