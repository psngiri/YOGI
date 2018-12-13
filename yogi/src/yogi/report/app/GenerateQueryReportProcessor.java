package yogi.report.app;

import java.io.File;

import yogi.base.app.BaseProcessor;
import yogi.base.io.FileWriter;
import yogi.base.io.Formatter;
import yogi.report.LineWriter;
import yogi.report.compare.CompareGroup;
import yogi.report.compare.template.BaseCompareReportTemplate;
import yogi.report.compare.template.CompareReportTemplate;
import yogi.report.compare.template.TemplateCompareGroupFormatter;
import yogi.report.compare.template.TemplateCompareGroupFormatterAdapter;
import yogi.report.compare.template.TemplateCompareGroupFormatterImpl;
import yogi.report.compare.template.TemplateCompareGroupTotalFormatter;
import yogi.report.group.Group;
import yogi.report.server.BaseReportDataItems;
import yogi.report.server.Query;
import yogi.report.server.config.ReportConfigProvider;
import yogi.report.template.ReportTemplate;
import yogi.report.template.TemplateGroupFormatter;
import yogi.report.template.TemplateGroupFormatterAdapter;
import yogi.report.template.TemplateGroupFormatterImpl;
import yogi.report.template.TemplateGroupTotalFormatter;
import yogi.report.template.TemplateReportHeader;

public class GenerateQueryReportProcessor extends BaseProcessor {

	public  static boolean RUN = true;
	private Query query;
	private String reportFileName;
	private String columnSeparator;
	private String userId;
	
	public GenerateQueryReportProcessor(String reportFileName) {
		super();
		this.reportFileName = reportFileName;
		columnSeparator = ",";
	}
	
	public GenerateQueryReportProcessor(Query query,String reportFileName, String userId) {
		this(reportFileName);
		this.query = query;
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}

	public String getColumnSeparator() {
		return columnSeparator;
	}

	public void setColumnSeparator(String columnSeparator) {
		this.columnSeparator = columnSeparator;
	}

	protected void setQuery(Query query) {
		this.query = query;
	}

	private FileWriter<String> getFileWriter(String reportFileName) {
		File file = new File(reportFileName);
		FileWriter<String> fileWriter = new FileWriter<String>(file);
		return fileWriter;
	}

	static class MyLineWriter implements LineWriter
	{
		FileWriter<String> fileWriter;
		
		public MyLineWriter(FileWriter<String> fileWriter) {
			super();
			this.fileWriter = fileWriter;
		}

		public void writeLine(String string) {
			fileWriter.write(string);
		}
		
	}

	@Override
	public void run() {
		BaseReportDataItems<Object> reportDataItems = new BaseReportDataItems<Object>(-1, getUserId(), new ReportConfigProvider<Object>(query.getReportName()));
		MyReportTemplate<Object> simpleCompareReportTemplate = new MyReportTemplate<Object>();
		simpleCompareReportTemplate.setColumnSeparator(columnSeparator);
		FileWriter<String> fileWriter = getFileWriter(reportFileName);
		fileWriter.open();
		MyLineWriter lineWriter = new MyLineWriter(fileWriter);
		TemplateGroupFormatter<Object> templateGroupFormatter = new TemplateGroupFormatterImpl<Object>(simpleCompareReportTemplate);
		TemplateCompareGroupFormatter<Object> templateCompareGroupFormatter = new TemplateCompareGroupFormatterImpl<Object>(simpleCompareReportTemplate);
		if(query.isSingleGrouping()&&query.isGroupBy()){
			templateGroupFormatter = new TemplateSingleGroupFormatterImpl<Object>(simpleCompareReportTemplate);
			templateCompareGroupFormatter = new TemplateSingleCompareGroupFormatterImpl<Object>(simpleCompareReportTemplate);
		}
		reportDataItems.generateReport(query, simpleCompareReportTemplate, templateGroupFormatter, templateCompareGroupFormatter, lineWriter);
		fileWriter.close();
	}


	@Override
	public boolean isActivated() {
		return RUN;
	}
	
	static class MyReportTemplate<T> extends BaseCompareReportTemplate<T>{
		public MyReportTemplate() {
			super();
			this.setReportHeader(new TemplateReportHeader<T>(this, false));
		}
	}
	static class TemplateSingleGroupFormatterImpl<T> extends TemplateGroupFormatterAdapter<T> {
		public TemplateSingleGroupFormatterImpl(ReportTemplate<T> reportTemplate) {
			super(reportTemplate);
		}

		public Formatter<Group<T>> getGroupTotalFormatter() {
			return new TemplateGroupTotalFormatter<T>(getReportTemplate());
		}

	}
	static class TemplateSingleCompareGroupFormatterImpl<T> extends TemplateCompareGroupFormatterAdapter<T> {
		public TemplateSingleCompareGroupFormatterImpl(CompareReportTemplate<T> reportTemplate) {
			super(reportTemplate);
		}

		public Formatter<CompareGroup<T>> getGroupTotalFormatter() {
			return new TemplateCompareGroupTotalFormatter<T>(getReportTemplate());
		}

	}

}
