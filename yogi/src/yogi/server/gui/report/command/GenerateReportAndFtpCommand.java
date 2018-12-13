package yogi.server.gui.report.command;

import yogi.base.app.Executor;
import yogi.remote.CommandException;
import yogi.remote.client.app.CommandAdapter;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.report.server.Query;
import yogi.report.server.command.GenerateAndFtpReportStringCommand;
import yogi.server.gui.report.Report;


public class GenerateReportAndFtpCommand extends CommandAdapter<Boolean> {

	private static final long serialVersionUID = -6849748273507429406L;
	private String name;
	private String ftpLocation;
	private String reportDataFileName;
	private String columnSeparator;
	private String partition;
	
	public GenerateReportAndFtpCommand(String name, String userId, String ftpLocation, String reportDataFileName, String columnSeparator, String partition) {
		super(userId);
		this.name = name;
		this.ftpLocation = ftpLocation;
		this.reportDataFileName = reportDataFileName;
		this.columnSeparator = columnSeparator;
		this.partition = partition;
	}


	@Override
	public Boolean execute() {
		ReportGetCommand reportGetCommand = new ReportGetCommand(name, getUserId(),partition);
			Report reportData = Executor.get().execute(reportGetCommand);
			Query query = reportData.getData().getQuery();
			GenerateAndFtpReportStringCommand command=new GenerateAndFtpReportStringCommand(query,reportDataFileName,ftpLocation,getUserId(),name,columnSeparator);
			try {
				MultiServerCommandExecutor.get().execute(query.getServerType(),command);
			} catch (CommandException e) {
				throw new RuntimeException(e);
			}
		
		return true;
	}

}