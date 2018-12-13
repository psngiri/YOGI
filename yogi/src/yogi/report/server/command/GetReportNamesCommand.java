package yogi.report.server.command;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.app.ReportProperties;
import yogi.server.gui.applicationpermissions.command.GetAuthorizedModulesCommand;

public class GetReportNamesCommand extends CommandAdapter<Map<String,String>> {

	private static final long serialVersionUID = -6849748273507429406L;
	public static LinkedHashMap<String,String> reports = new LinkedHashMap<String,String>();
	public GetReportNamesCommand(String userId) {
		super(userId);
	}

	@Override
	public Map<String, String> execute() {
		List<String> reportNames = new ArrayList<String>(reports.keySet());
		List<String> authorizedReportNames = new GetAuthorizedModulesCommand(ReportProperties.ApplicationName, reportNames, getUserId()).execute();
		LinkedHashMap<String,String> rtnValue = new LinkedHashMap<String,String>();
		for(String reportName: authorizedReportNames){
			rtnValue.put(reportName, reports.get(reportName));
		}
		return rtnValue;
	}

}
