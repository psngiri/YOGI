package yogi.report.template;

import java.util.ArrayList;
import java.util.List;

import yogi.report.Footer;

public class TemplateReportFooter<T> implements Footer {
	private ReportTemplate<T> reportTemplate;
	private boolean drawLines = true;
	
	public TemplateReportFooter(ReportTemplate<T> reportTemplate) {
		this(reportTemplate, true);
	}
	
	public TemplateReportFooter(ReportTemplate<T> reportTemplate,
			boolean drawLines) {
		super();
		this.reportTemplate = reportTemplate;
		this.drawLines = drawLines;
	}

	public List<String> getFooter() {
		int size = 0;
		if(drawLines) size = 1;
		List<String> rtnValue = new ArrayList<String>(size);
		if(drawLines) rtnValue.add(TemplateUtil.drawLine(reportTemplate.getWidth()));
		return rtnValue;
	}
}
