package yogi.report.server.command;

import yogi.base.app.ApplicationProperties;
import yogi.base.app.Executor;
import yogi.remote.client.app.CommandAdapter;
import yogi.report.app.GenerateQueryReportProcessor;
import yogi.report.app.ScriptExecuteProcessor;
import yogi.report.server.Query;
import yogi.report.server.config.UserQueryAssistant;


public class GenerateAndFtpReportStringCommand extends CommandAdapter<Boolean> {

	private static final long serialVersionUID = -6849748273507429406L;
	private Query query;
	private String reportDataFileName;
	private String ftpLocation;
	private String queryName;
	private String columnSeparator;
	
	public GenerateAndFtpReportStringCommand(Query query,String reportDataFileName, String ftpLocation, String userId, String queryName, String columnSeparator) {
		super(userId);
		this.query = query;
		this.reportDataFileName = reportDataFileName;
		this.ftpLocation = ftpLocation;
		this.queryName = queryName;
		this.columnSeparator = columnSeparator;
	}

	@Override
	public Boolean execute(){
		String reportFileName = ApplicationProperties.OutputLocation+UserQueryAssistant.ReportQueriesLocation+"/"+getUserId()+queryName;
		GenerateQueryReportProcessor generateQueryReportProcessor = new GenerateQueryReportProcessor(query, reportFileName, getUserId());
		if(columnSeparator != null)generateQueryReportProcessor.setColumnSeparator(columnSeparator);
		Executor.get().execute(generateQueryReportProcessor);
		ScriptExecuteProcessor scriptExecuteProcessor = new ScriptExecuteProcessor(ftpLocation,reportFileName, reportDataFileName);
		Executor.get().execute(scriptExecuteProcessor);
		return true;
	}


}
