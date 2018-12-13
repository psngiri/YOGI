package yogi.report;

import java.util.Iterator;
import java.util.List;

import yogi.base.io.Formatter;
import yogi.base.io.MultiLineFormatter;
import yogi.base.util.node.Node;

public class ReportBuilder<T, G>{
	private GroupFormatter<G> groupFormatter;
	private Header reportHeader;
	private Footer reportFooter;
	private String lastLine = null;
	private boolean ignoreConsecutiveSameLines = true;
	private LineWriter lineWriter;

	
	public boolean isIgnoreConsecutiveSameLines() {
		return ignoreConsecutiveSameLines;
	}

	public void setIgnoreConsecutiveSameLines(boolean ignoreConsecutiveSameLines) {
		this.ignoreConsecutiveSameLines = ignoreConsecutiveSameLines;
	}

	public String getLastLine() {
		return lastLine;
	}

	public void setLastLine(String lastLine) {
		this.lastLine = lastLine;
	}

	public LineWriter getLineWriter() {
		return lineWriter;
	}

	public void setLineWriter(LineWriter lineWriter) {
		this.lineWriter = lineWriter;
	}

	public Footer getReportFooter() {
		return reportFooter;
	}

	public void setReportFooter(Footer reportFooter) {
		this.reportFooter = reportFooter;
	}

	public Header getReportHeader() {
		return reportHeader;
	}

	public void setReportHeader(Header reportHeader) {
		this.reportHeader = reportHeader;
	}

	public void setGroupFormatter(GroupFormatter<G> groupFormatter) {
		this.groupFormatter = groupFormatter;
	}

	public GroupFormatter<G> getGroupFormatter() {
		if(groupFormatter == null) throw new RuntimeException("GroupFormatter not set, Please set the GroupFormatter before running the report.");
		return groupFormatter;
	}

	public void build(Node<G> nodes) {
		writerHeader();
		writeGroup(nodes);
		writerFooter();
	}

	private void writerFooter() {
		if(reportFooter == null) return;
		write(reportFooter.getFooter());	
	}

	private void writerHeader() {
		if(reportHeader == null) return;
		write(reportHeader.getHeader());	
	}

	private void writeGroup(Node<G> node) {
		G data = node.getData();
		
		if(!isRoot(node)) writeGroupHeader(data);
		writeBody(node);
		if(!isRoot(node)) writeGroupTotal(data);
		if(!isRoot(node)) writeGroupFooter(data);
	}

	
	private boolean isRoot(Node<G> node) {
		return node.getParent() == null;
	}

	private void writeChildGroups(Node<G> node) {
		Iterator<Node<G>> children = node.children();
		while(children.hasNext())
		{
			writeGroup(children.next());
		}
	}

	private void writeGroupTotal(G data) {
		writeGroupTotalHeader(data);
		Formatter<G> groupTotalFormatter = getGroupFormatter().getGroupTotalFormatter();
		if(groupTotalFormatter != null) write(groupTotalFormatter.format(data));	
		writeGroupTotalFooter(data);
	}

	private void writeGroupTotalFooter(G data) {
		MultiLineFormatter<G> groupTotalFooter = getGroupFormatter().getGroupTotalFooter();
		if(groupTotalFooter == null) return;
		write(groupTotalFooter.format(data));
	}

	private void writeGroupTotalHeader(G data) {
		MultiLineFormatter<G> groupTotalHeader = getGroupFormatter().getGroupTotalHeader();
		if(groupTotalHeader == null) return;
		write(groupTotalHeader.format(data));
	}

	private void writeGroupHeader(G data) {
		MultiLineFormatter<G> groupHeader = getGroupFormatter().getGroupHeader();
		if(groupHeader == null) return;
		write(groupHeader.format(data));	
	}

	private void writeGroupFooter(G data) {
		MultiLineFormatter<G> groupFooter = getGroupFormatter().getGroupFooter();
		if(groupFooter == null) return;
		write(groupFooter.format(data));	
	}

	private void writeBody(Node<G> groupNode) {
		if(groupNode.isLeaf())
		{
			writeItems(groupNode.getData());
		}else
		{
			writeChildGroups(groupNode);
		}
	}

	private void writeItems(G data) {
		MultiLineFormatter<G> groupObjectFormatter = getGroupFormatter().getGroupObjectFormatter();
		if(groupObjectFormatter == null) return;
		write(groupObjectFormatter.format(data));
	}

	private void write(List<String> strings) {
		if(strings == null) return;
		for(String string: strings)
		{
			write(string);
		}
	}
	
	private void write(String string) {
		if(string == null) return;
		if(ignoreConsecutiveSameLines && string.equals(lastLine)) return;
		writeLine(string);
		lastLine = string;
	}
	
	private void writeLine(String line)
	{
		lineWriter.writeLine(line);
	}
}
