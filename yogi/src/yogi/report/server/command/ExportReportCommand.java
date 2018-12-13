package yogi.report.server.command;

import java.util.ArrayList;
import java.util.List;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.LineWriter;
import yogi.report.compare.template.BaseCompareReportTemplate;
import yogi.report.compare.template.TemplateCompareGroupFormatterImpl;
import yogi.report.server.BaseReportDataItems;
import yogi.report.server.Query;
import yogi.report.server.config.ReportConfigProvider;
import yogi.report.template.TemplateGroupFormatterImpl;
import yogi.report.template.TemplateReportHeader;


public class ExportReportCommand extends CommandAdapter<List<String[]>> {

	private static final long serialVersionUID = -6849748273507429406L;
	private Query query;
	public transient static String columnSeparator=",";

	

	public ExportReportCommand(Query query, String userId) {
		super(userId);
		this.query = query;
	}
	
	@Override
	public List<String[]> execute() {
		BaseReportDataItems<Object> reportDataItems = new BaseReportDataItems<Object>(-1, getUserId(), new ReportConfigProvider<Object>(query.getReportName()));
		MyReportTemplate<Object> simpleCompareReportTemplate = new MyReportTemplate<Object>();
		simpleCompareReportTemplate.setColumnSeparator(columnSeparator);
		MyLineWriter lineWriter = new MyLineWriter();
		TemplateGroupFormatterImpl<Object> templateGroupFormatter = new TemplateGroupFormatterImpl<Object>(simpleCompareReportTemplate);
		TemplateCompareGroupFormatterImpl<Object> templateCompareGroupFormatter = new TemplateCompareGroupFormatterImpl<Object>(simpleCompareReportTemplate);
		reportDataItems.generateReport(query, simpleCompareReportTemplate, templateGroupFormatter, templateCompareGroupFormatter, lineWriter);
		return lineWriter.getReport();
	}

	class MyLineWriter implements LineWriter
	{
		ArrayList<String[]> report = new ArrayList<String[]>();
		public MyLineWriter() {
			super();
		}

		public void writeLine(String string) {
			report.add(string.split(columnSeparator));
		}
		
		public List<String[]> getReport(){
			return report;
		}
	}
	
	static class MyReportTemplate<T> extends BaseCompareReportTemplate<T>{
		public MyReportTemplate() {
			super();
			this.setReportHeader(new TemplateReportHeader<T>(this, false));
		}
	}

}
