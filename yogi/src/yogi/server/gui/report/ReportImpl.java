package yogi.server.gui.report;

import yogi.server.action.Action;
import yogi.server.gui.record.RecordImpl;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.user.User;

public class ReportImpl extends RecordImpl<ReportKey,ReportData,Report> implements Report {
	
	protected ReportImpl(ReportKey key, long timeStamp, String description, String comments, ReportData reportData, Action action, User modifiedByUser) {
		super(key, timeStamp, description, comments, reportData, action, modifiedByUser);
	}

	

}
