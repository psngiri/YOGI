package yogi.report.server.command;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.server.ReportServerImpl;
import yogi.report.server.config.ReportConfig;


public class GetReportConfigurationCommand extends CommandAdapter<ReportConfig<?>>{

	private static final long serialVersionUID = -6849748273507429406L;
	private String reportName;
	
	public GetReportConfigurationCommand(String reportName, String userId) {
		super(userId);
		this.reportName = reportName;
	}

	@Override
	public ReportConfig<?> execute() {
//		Gson gson = new Gson();
//		String json = gson.toJson(reportName);
//		System.out.println("GetReportConfigurationCommand-->"+json);
		return  ReportServerImpl.get().getReportConfiguration(reportName);
	}


}
