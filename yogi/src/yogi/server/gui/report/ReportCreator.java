package yogi.server.gui.report;

import yogi.server.gui.record.RecordCreator;
import yogi.server.gui.report.key.ReportKey;

public class ReportCreator extends RecordCreator<ReportKey, ReportData, Report> {
	public Report create() {
		ReportData data = this.getData();
		return new ReportImpl(getKey(), getTimeStamp(), getDescription(), getComments(), new ReportData(data.getQuery()), getAction(), getModifiedByUser());
	}

}