package yogi.report.compare.template;

import java.util.ArrayList;
import java.util.List;

import yogi.report.Header;
import yogi.report.template.TemplateUtil;

public class TemplateCompareReportHeader<T> implements Header {
	private CompareReportTemplate<T> reportTemplate;
	private CompareHeaderValuesGenerator<T> compareHeaderValuesGenerator;
	private boolean drawLines = true;
	
	public TemplateCompareReportHeader(CompareReportTemplate<T> reportTemplate) {
		super();
		this.reportTemplate = reportTemplate;
		compareHeaderValuesGenerator = new CompareHeaderValuesGenerator<T>(reportTemplate, true, true);
	}

	public List<String> getHeader() {
		List<List<String>> headerValues = compareHeaderValuesGenerator.getHeaderValues();
		List<String> rtnValue = toArray(headerValues);
		return rtnValue;
	}

	private List<String> toArray(List<List<String>> headerValues) {
		int size = headerValues.size();
		if(drawLines) size = size + 2;
		List<String> rtnValue = new ArrayList<String>(size);
		if(drawLines) rtnValue.add(TemplateUtil.drawLine(reportTemplate.getWidth()));
		String columnSeparator = reportTemplate.getColumnSeparator();
		for(List<String> values: headerValues)
		{
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(values.get(0));
			for(int i = 1; i < values.size(); i++)
			{
				stringBuilder.append(columnSeparator);
				stringBuilder.append(values.get(i));
			}
			rtnValue.add(stringBuilder.toString());
		}
		if(drawLines) rtnValue.add(TemplateUtil.drawLine(reportTemplate.getWidth()));
		return rtnValue;
	}
	
}
