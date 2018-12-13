package yogi.server.gui.report.command;

import yogi.server.gui.command.TypeSaveCommand;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.report.Report;
import yogi.server.gui.report.ReportCreator;
import yogi.server.gui.report.ReportData;
import yogi.server.gui.report.ReportFactory;
import yogi.server.gui.report.ReportManager;
import yogi.server.gui.report.ReportValidator;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.report.key.ReportKeyManager;

public class ReportSaveCommand extends TypeSaveCommand<ReportKey, ReportData,Report, ReportCreator> {

	private static final long serialVersionUID = -3338090347106604543L;
	private transient ReportCreator reportCreator;
	private static final ReportValidator Report_VALIDATOR = new ReportValidator();
	
	public ReportSaveCommand(String name, String userId,boolean global,String description,String comments, ReportData reportData) {
		super(name, userId,global,description,comments,reportData);
	}

	@Override
	public ReportValidator getValidator() {
		return Report_VALIDATOR;
	}

	@Override
	public ReportKeyManager getKeyManager() {
		return ReportKeyManager.get();
	}
	
	@Override
	public RecordManager<ReportKey,ReportData, Report> getRecordManager() {
		return  ReportManager.get();
	}

	@Override
	public ReportFactory getFactory() {
		return ReportFactory.get();
	}

	@Override
	protected ReportCreator getCreator() {
		if(reportCreator == null) reportCreator  = new ReportCreator();
		return reportCreator;
	}

	@Override
	public void setChildKeys(ReportCreator recordCreator) {
		super.setChildKeys(recordCreator);
	}


}