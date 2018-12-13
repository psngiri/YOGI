package yogi.report.compare.template;

import java.util.ArrayList;
import java.util.List;

import yogi.base.io.MultiLineFormatter;
import yogi.report.compare.CompareGroup;
import yogi.report.template.TemplateUtil;

public class TemplateCompareGroupHeader<T> implements MultiLineFormatter<CompareGroup<T>> {
	private CompareReportTemplate<T> reportTemplate;
	private boolean drawLines = true;
	
	public TemplateCompareGroupHeader(CompareReportTemplate<T> reportTemplate) {
		super();
		this.reportTemplate = reportTemplate;
	}
	
	public List<String> format(CompareGroup<T> group) {
		int size = 0;
		if(drawLines) size = 1;
		List<String> rtnValue = new ArrayList<String>(size);
		if(drawLines) rtnValue.add(TemplateUtil.drawLine(reportTemplate.getWidth()));
		return rtnValue;
	}

}
