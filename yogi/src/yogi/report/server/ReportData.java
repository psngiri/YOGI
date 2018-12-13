package yogi.report.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ReportData implements Serializable{

	private static final long serialVersionUID = -4853345382216250255L;
	List<ReportDataIndex> indexes;
	boolean group;
	Object[] values;

	public ReportData(int startIndex, int endIndex, boolean group, Object[] objects) {
		super();
		indexes = new ArrayList<ReportDataIndex>(1);
		indexes.add(new ReportDataIndex(startIndex, endIndex));
		this.group = group;
		this.values = objects;
	}
	
	public ReportData(List<ReportDataIndex> indexes, boolean group, Object[] objects) {
		super();
		this.indexes = indexes;
		this.group = group;
		this.values = objects;
	}

	public List<ReportDataIndex> getIndexes() {
		return indexes;
	}

	public Object[] getValues() {
		return values;
	}

	public Object getValue(int index) {
		return values[index];
	}
	public boolean isGroup()
	{
		return group;
	}
	
	public int columnCount() {
	    return values.length;
	}
	
	@Override
	public String toString() {
		return "ReportData [indexes=" + indexes + ", group=" + group
				+ ", values=" + Arrays.toString(values) + "]";
	}
	
}