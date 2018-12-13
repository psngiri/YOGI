package yogi.report.compare.template;

import yogi.base.io.Formatter;
import yogi.base.io.MultiLineFormatter;
import yogi.report.compare.CompareGroup;

public class TemplateCompareGroupFormatterImpl<T> extends TemplateCompareGroupFormatterAdapter<T> {
	public TemplateCompareGroupFormatterImpl(CompareReportTemplate<T> reportTemplate) {
		super(reportTemplate);
	}

	public MultiLineFormatter<CompareGroup<T>> getGroupObjectFormatter() {
		return new TemplateCompareGroupObjectFormatter<T>(getReportTemplate());
	}

	public Formatter<CompareGroup<T>> getGroupTotalFormatter() {
		return new TemplateCompareGroupTotalFormatter<T>(getReportTemplate());
	}

	public MultiLineFormatter<CompareGroup<T>> getGroupHeader() {
		return new TemplateCompareGroupHeader<T>(getReportTemplate());
	}

	public MultiLineFormatter<CompareGroup<T>> getGroupFooter() {
		return new TemplateCompareGroupFooter<T>(getReportTemplate());
	}

	public MultiLineFormatter<CompareGroup<T>> getGroupTotalHeader() {
		return new TemplateCompareGroupTotalHeader<T>(getReportTemplate());
	}

	public MultiLineFormatter<CompareGroup<T>> getGroupTotalFooter() {
		return new TemplateCompareGroupTotalFooter<T>(getReportTemplate());
	}

}
