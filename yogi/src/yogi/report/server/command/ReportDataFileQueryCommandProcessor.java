package yogi.report.server.command;

import yogi.base.app.BaseProcessor;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.FileResource;
import yogi.base.io.resource.SystemResource;
import yogi.base.util.JsonAssistant;
import yogi.remote.CommandException;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.report.server.Query;
import yogi.report.server.command.GenerateAndFtpReportStringCommand;

public class ReportDataFileQueryCommandProcessor extends BaseProcessor 
{
	public static boolean RUN = true;
	private String queryFileName;
	
	QueryReader queryReader=null;
	private String ftpLocation;
	private String reportDataFileName;
	public static String ColumnSeparator = ",";

	public ReportDataFileQueryCommandProcessor(String queryFileName,int sub,String ftpLocation,String reportDataFileName) {
		super();
		this.queryFileName =queryFileName;
		this.ftpLocation = ftpLocation;
		this.reportDataFileName = reportDataFileName;
		this.getQueryReader().addVariable("Sub", String.valueOf(sub));
	
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
		@Override
	public void run() {
		Query query = JsonAssistant.get().fromJson(getQuery(), Query.class);
		String serverType = query.getServerType();
		try {
			MultiServerCommandExecutor.get().execute(serverType, new GenerateAndFtpReportStringCommand(query,reportDataFileName,ftpLocation,"",queryFileName,ColumnSeparator));
		} catch (CommandException e) {
			throw new RuntimeException(e);
		}
	}

		
	protected SystemResource getQueryResource() {
			FileResource fileResource =  new FileResource(queryFileName);
			return fileResource;
	}

	protected String getQuery() {
		return getQueryReader().read();
	}

	protected QueryReader getQueryReader() {
		if(queryReader == null){
			queryReader = new QueryReader(getQueryResource());
		}
		return queryReader;
		}
}