package yogi.report.compare.template;



public class SimpleCompareReportTemplate<T> extends BaseCompareReportTemplate<T>{
	public SimpleCompareReportTemplate() {
		super();
		this.setReportHeader(new TemplateCompareReportHeader<T>(this));
		this.setReportFooter(new TemplateCompareReportFooter<T>(this));
		this.setColumnSeparator(" | ");
	}
}
