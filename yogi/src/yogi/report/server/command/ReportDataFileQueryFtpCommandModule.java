package yogi.report.server.command;

import yogi.base.app.BaseModule;

public class ReportDataFileQueryFtpCommandModule extends BaseModule {
	public static boolean RUN = true;
	private String queryFileName;
	private int subNbr;
	private String ftpLocation;
	private String reportDataFileName;

	public ReportDataFileQueryFtpCommandModule(String queryFileName,int subNbr,String ftpLocation,String reportDataFileName) {
		super();
		this.queryFileName = queryFileName;
		this.subNbr = subNbr;
		this.ftpLocation = ftpLocation;
		this.reportDataFileName = reportDataFileName;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		this.addProcessor(new ReportDataFileQueryCommandProcessor(queryFileName,subNbr,ftpLocation,reportDataFileName));
	}
}
