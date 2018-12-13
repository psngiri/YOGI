package yogi.report.template;


public class SimpleReportTemplate<T> extends BaseReportTemplate<T>{
	public SimpleReportTemplate() {
		super();
		this.setReportHeader(new TemplateReportHeader<T>(this));
		this.setReportFooter(new TemplateReportFooter<T>(this));
		this.setColumnSeparator(" | ");
	}
}
