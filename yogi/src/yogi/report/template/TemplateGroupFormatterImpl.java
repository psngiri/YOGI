package yogi.report.template;

import yogi.base.io.Formatter;
import yogi.base.io.MultiLineFormatter;
import yogi.report.group.Group;

public class TemplateGroupFormatterImpl<T> extends TemplateGroupFormatterAdapter<T> {
	public TemplateGroupFormatterImpl(ReportTemplate<T> reportTemplate) {
		super(reportTemplate);
	}

	public MultiLineFormatter<Group<T>> getGroupObjectFormatter() {
		return new TemplateGroupObjectFormatter<T>(getReportTemplate());
	}

	public Formatter<Group<T>> getGroupTotalFormatter() {
		return new TemplateGroupTotalFormatter<T>(getReportTemplate());
	}

	public MultiLineFormatter<Group<T>> getGroupHeader() {
		return new TemplateGroupHeader<T>(getReportTemplate());
	}

	public MultiLineFormatter<Group<T>> getGroupFooter() {
		return new TemplateGroupFooter<T>(getReportTemplate());
	}

	public MultiLineFormatter<Group<T>> getGroupTotalHeader() {
		return new TemplateGroupTotalHeader<T>(getReportTemplate());
	}

	public MultiLineFormatter<Group<T>> getGroupTotalFooter() {
		return new TemplateGroupTotalFooter<T>(getReportTemplate());
	}

}
