package yogi.report.compare.template;

import yogi.base.io.Formatter;
import yogi.base.io.MultiLineFormatter;
import yogi.report.compare.CompareGroup;

public class TemplateCompareGroupFormatterAdapter<T> implements TemplateCompareGroupFormatter<T> {
	private CompareReportTemplate<T> reportTemplate;
	
	public TemplateCompareGroupFormatterAdapter(CompareReportTemplate<T> reportTemplate) {
		super();
		this.reportTemplate = reportTemplate;
	}

	public CompareReportTemplate<T> getReportTemplate() {
		return reportTemplate;
	}

	public MultiLineFormatter<CompareGroup<T>> getGroupObjectFormatter() {
		return null;
	}

	public Formatter<CompareGroup<T>> getGroupTotalFormatter() {
		return null;
	}

	public MultiLineFormatter<CompareGroup<T>> getGroupHeader() {
		return null;
	}

	public MultiLineFormatter<CompareGroup<T>> getGroupFooter() {
		return null;
	}

	public MultiLineFormatter<CompareGroup<T>> getGroupTotalHeader() {
		return null;
	}

	public MultiLineFormatter<CompareGroup<T>> getGroupTotalFooter() {
		return null;
	}

}
