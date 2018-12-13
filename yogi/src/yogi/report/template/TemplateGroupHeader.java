package yogi.report.template;

import java.util.ArrayList;
import java.util.List;

import yogi.base.io.MultiLineFormatter;
import yogi.report.group.Group;

public class TemplateGroupHeader<T> implements MultiLineFormatter<Group<T>> {
	private ReportTemplate<T> reportTemplate;
	private boolean drawLines = true;
	
	public TemplateGroupHeader(ReportTemplate<T> reportTemplate) {
		super();
		this.reportTemplate = reportTemplate;
	}
	
	public List<String> format(Group<T> group) {
		int size = 0;
		if(drawLines) size = 1;
		List<String> rtnValue = new ArrayList<String>(size);
		if(drawLines) rtnValue.add(TemplateUtil.drawLine(reportTemplate.getWidth()));
		return rtnValue;
	}

}
