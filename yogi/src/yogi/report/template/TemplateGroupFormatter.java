package yogi.report.template;

import yogi.report.GroupFormatter;
import yogi.report.group.Group;

public interface TemplateGroupFormatter<T> extends GroupFormatter<Group<T>> {
	ReportTemplate<T> getReportTemplate();
}
