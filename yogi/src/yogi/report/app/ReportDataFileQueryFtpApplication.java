package yogi.report.app;

import yogi.base.app.standard.StandardApplication;
import yogi.report.server.command.ReportDataFileQueryFtpCommandModule;



public class ReportDataFileQueryFtpApplication extends StandardApplication {
	public static boolean RUN = true;
	private String queryFileName;
	private int subNbr;
	private String ftpLocation;
	private String reportDataFileName;
	
	
	public ReportDataFileQueryFtpApplication(String dataLocation, String outputLocation,String queryFileName, String subNbr,String ftpLocation,String reportDataFileName) {
		super(dataLocation, outputLocation);
		this.queryFileName = queryFileName;
		this.subNbr = Integer.parseInt(subNbr);
		this.ftpLocation= ftpLocation;
		this.reportDataFileName = reportDataFileName;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}
	
	public void setup() {
		super.setup();
		this.addModule(new ReportDataFileQueryFtpCommandModule(queryFileName,subNbr,ftpLocation,reportDataFileName));
	}
	
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Usage: ReportDataFileQueryFtpApplication worksetLocation queryName [ftpLocation reportDataFileName subNbr]");
			System.out.println("[outputLocation] is optional");
			return;
		}
		String outputLocation = null;
		if(args.length == 4) {
			outputLocation = args[1];
		}
		ReportDataFileQueryFtpApplication reportDataFileQueryFtpApplication = new ReportDataFileQueryFtpApplication(args[0],outputLocation,args[2],args[3],args[4],args[5]);
		reportDataFileQueryFtpApplication.execute();
	}

}
