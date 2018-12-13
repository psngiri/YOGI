package yogi.report.server.command;

import java.io.PrintWriter;
import java.io.StringWriter;

import yogi.remote.client.app.CommandAdapter;
import yogi.report.LineWriter;
import yogi.report.compare.template.SimpleCompareReportTemplate;
import yogi.report.compare.template.TemplateCompareGroupFormatterImpl;
import yogi.report.server.BaseReportDataItems;
import yogi.report.server.Query;
import yogi.report.server.config.ReportConfigProvider;
import yogi.report.template.TemplateGroupFormatterImpl;


public class GenerateReportStringCommand extends CommandAdapter<String> {

	private static final long serialVersionUID = -6849748273507429406L;
	private Query query;
			
	public GenerateReportStringCommand(Query query, String userId) {
		super(userId);
		this.query = query;
	}
	
	public String execute() {
			BaseReportDataItems<Object> reportDataItems = new BaseReportDataItems<Object>(-1, getUserId(), new ReportConfigProvider<Object>(query.getReportName()));
			SimpleCompareReportTemplate<Object> simpleCompareReportTemplate = new SimpleCompareReportTemplate<Object>();
			simpleCompareReportTemplate.setColumnSeparator(",");
			MyLineWriter lineWriter = new MyLineWriter();
			TemplateGroupFormatterImpl<Object> templateGroupFormatter = new TemplateGroupFormatterImpl<Object>(simpleCompareReportTemplate);
			TemplateCompareGroupFormatterImpl<Object> templateCompareGroupFormatter = new TemplateCompareGroupFormatterImpl<Object>(simpleCompareReportTemplate);
			reportDataItems.generateReport(query, simpleCompareReportTemplate, templateGroupFormatter, templateCompareGroupFormatter, lineWriter);
			return lineWriter.getReport();		
	}
	
	static class MyLineWriter implements LineWriter
	{
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		
		public void writeLine(String string) {
			printWriter.println(string);
		}
		
		public String getReport(){
			return stringWriter.toString();
		}
	}
	
	

}
