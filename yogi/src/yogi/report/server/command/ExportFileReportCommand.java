package yogi.report.server.command;

import yogi.base.app.Executor;
import yogi.remote.client.app.CommandAdapter;
import yogi.report.app.GenerateQueryReportProcessor;
import yogi.report.server.Query;


public class ExportFileReportCommand extends CommandAdapter<String> {

	private static final long serialVersionUID = -6849748273507429406L;
	private Query query;
	public transient static String columnSeparator=",";
	public static String ExportDataLocation = "/tmp/Export/";
	private static int id = 0;

	public ExportFileReportCommand(Query query, String userId) {
		super(userId);
		this.query = query;
	}
	
	@Override
	public String execute() {
		String reportName = this.getUserId()+"."+ id++ +".csv";
		String reportFileName = ExportDataLocation+reportName;
		GenerateQueryReportProcessor generateQueryReportProcessor = new GenerateQueryReportProcessor(query, reportFileName, this.getUserId());
		Executor.get().execute(generateQueryReportProcessor);
		return reportName;
	}

}
