package yogi.report.server;

import java.io.Serializable;

public class ReportDataIndex implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7529687656719910472L;
	private int startIndex;
	private int endIndex;
	public ReportDataIndex(int startIndex, int endIndex) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	@Override
	public String toString() {
		return "ReportDataIndex [startIndex=" + startIndex + ", endIndex="
				+ endIndex + "]";
	}
}