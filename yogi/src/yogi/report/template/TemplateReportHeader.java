package yogi.report.template;

import java.util.ArrayList;
import java.util.List;

import yogi.report.Header;
import yogi.report.column.ColumnDefinition;

public class TemplateReportHeader<T> implements Header {
	private ReportTemplate<T> reportTemplate;
	private boolean drawLines = true;
	
	public TemplateReportHeader(ReportTemplate<T> reportTemplate) {
		this(reportTemplate, true);
	}

	public TemplateReportHeader(ReportTemplate<T> reportTemplate,
			boolean drawLines) {
		super();
		this.reportTemplate = reportTemplate;
		this.drawLines = drawLines;
	}

	public List<String> getHeader() {
		List<ColumnDefinition<T>> columns = reportTemplate.getColumns();
		String columnSeparator = reportTemplate.getColumnSeparator();
		int headerHeight = getHeaderHeight();
		List<StringBuilder> headerStringBuilders = initializeHeaders(headerHeight);
		appendColumn(columns.get(0), headerStringBuilders);
		for(int i = 1; i < columns.size(); i ++)
		{
			appendSeparator(headerStringBuilders, columnSeparator);
			appendColumn(columns.get(i), headerStringBuilders);
		}
		List<String> rtnValue = toArray(headerStringBuilders);
		return rtnValue;
	}

	private List<String> toArray(List<StringBuilder> headerStringBuilders) {
		int size = headerStringBuilders.size();
		if(drawLines) size = size + 2;
		List<String> rtnValue = new ArrayList<String>(size);
		if(drawLines) rtnValue.add(TemplateUtil.drawLine(reportTemplate.getWidth()));
		for(StringBuilder stringBuilder: headerStringBuilders)
		{
			rtnValue.add(stringBuilder.toString());
		}
		if(drawLines) rtnValue.add(TemplateUtil.drawLine(reportTemplate.getWidth()));
		return rtnValue;
	}
	
	private List<StringBuilder> initializeHeaders(int headerHeight) {
		List<StringBuilder> headerStringBuilders = new ArrayList<StringBuilder>(headerHeight);
		for(int i = 0; i < headerHeight; i ++)
		{
			headerStringBuilders.add(new StringBuilder());
		}
		return headerStringBuilders;
	}

	private void appendSeparator(List<StringBuilder> headerStringBuilders, String columnSeparator)
	{
		for(StringBuilder stringBuilder: headerStringBuilders)
		{
			stringBuilder.append(columnSeparator);
		}
	}
	
	private int getHeaderHeight()
	{
		int rtnValue = 1;
		for(ColumnDefinition<T> columnDefinition: reportTemplate.getColumns())
		{
			rtnValue = Math.max(columnDefinition.getHeading().length, rtnValue);
		}
		return rtnValue;
	}
	private void appendColumn(ColumnDefinition<T> columnDefinition, List<StringBuilder> headerStringBuilders) {
		String[] heading = columnDefinition.getHeading();
		for(int i = 0; i < headerStringBuilders.size(); i ++)
		{
			String name = "";
			if(i < heading.length) name = heading[i];
			headerStringBuilders.get(i).append(TemplateUtil.format(name, columnDefinition.getWidth()));
		}
	}
	
}
