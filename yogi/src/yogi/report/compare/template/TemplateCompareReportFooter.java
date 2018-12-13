package yogi.report.compare.template;

import java.util.ArrayList;
import java.util.List;

import yogi.report.Footer;
import yogi.report.template.TemplateUtil;

public class TemplateCompareReportFooter<T> implements Footer {
	private CompareReportTemplate<T> reportTemplate;
	private boolean drawLines = true;
	
	public TemplateCompareReportFooter(CompareReportTemplate<T> reportTemplate) {
		super();
		this.reportTemplate = reportTemplate;
	}
	
	public List<String> getFooter() {
		int size = 0;
		if(drawLines) size = 1;
		List<String> rtnValue = new ArrayList<String>(size);
		if(drawLines) rtnValue.add(TemplateUtil.drawLine(reportTemplate.getWidth()));
		return rtnValue;
	}
}
