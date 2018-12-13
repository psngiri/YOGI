package yogi.report.compare.template;

import yogi.report.GroupFormatter;
import yogi.report.compare.CompareGroup;

public interface TemplateCompareGroupFormatter<T> extends GroupFormatter<CompareGroup<T>> {
	CompareReportTemplate<T> getReportTemplate();
}
