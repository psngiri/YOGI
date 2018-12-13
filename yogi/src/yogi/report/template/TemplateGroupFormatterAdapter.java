package yogi.report.template;

import yogi.base.io.Formatter;
import yogi.base.io.MultiLineFormatter;
import yogi.report.group.Group;

public class TemplateGroupFormatterAdapter<T> implements TemplateGroupFormatter<T> {
	private ReportTemplate<T> reportTemplate;
	
	public TemplateGroupFormatterAdapter(ReportTemplate<T> reportTemplate) {
		super();
		this.reportTemplate = reportTemplate;
	}

	public ReportTemplate<T> getReportTemplate() {
		return reportTemplate;
	}

	public MultiLineFormatter<Group<T>> getGroupObjectFormatter() {
		return null;
	}

	public Formatter<Group<T>> getGroupTotalFormatter() {
		return null;
	}

	public MultiLineFormatter<Group<T>> getGroupHeader() {
		return null;
	}

	public MultiLineFormatter<Group<T>> getGroupFooter() {
		return null;
	}

	public MultiLineFormatter<Group<T>> getGroupTotalHeader() {
		return null;
	}

	public MultiLineFormatter<Group<T>> getGroupTotalFooter() {
		return null;
	}

}
