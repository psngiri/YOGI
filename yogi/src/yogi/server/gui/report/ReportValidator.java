package yogi.server.gui.report;

import yogi.base.app.ErrorReporter;
import yogi.server.gui.record.RecordValidator;
import yogi.server.gui.report.key.ReportKey;

public class ReportValidator extends RecordValidator<ReportKey,ReportData,Report> {

	@Override
	protected boolean validate(ReportData data) {
		if(data.getQuery()==null || data.getQuery().getReportName()==null){
			ErrorReporter.get().warning("ReportName not found for Report, Ignoring : ", data);
			return false;
		}
		return true;
	}
	
}
