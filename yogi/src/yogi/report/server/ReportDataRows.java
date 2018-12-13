package yogi.report.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReportDataRows implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<ReportData> rows = new ArrayList<ReportData>();
	

	public ReportDataRows(List<ReportData> rows) {
		super();
		this.rows = rows;
	}
	public List<ReportData> getRows() {
		return rows;
	}
	
}
