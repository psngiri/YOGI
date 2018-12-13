package yogi.report.server;

import java.io.Serializable;

public class Column implements Serializable {

	private static final long serialVersionUID = 9216459031224425807L;
	private String name;
	private String displayName;
	private String functionName;
	private String[] compareFunctionNames;
	private boolean groupBy;
	private boolean hidden = false;
	private int sortOrder;
	private int groupSortOrder;
	
	public Column(String name, String displayName, String functionName, boolean groupBy, int sortOrder,	int groupSortOrder) {
		super();
		this.name = name;
		this.displayName = displayName;
		this.functionName = functionName;
		this.groupBy = groupBy;
		this.sortOrder = sortOrder;
		this.groupSortOrder = groupSortOrder;
	}
	
	public Column(String name, String displayName, String functionName, String[] compareFunctionNames, boolean groupBy, int sortOrder, int groupSortOrder) {
		super();
		this.name = name;
		this.displayName = displayName;
		this.functionName = functionName;
		this.compareFunctionNames = compareFunctionNames;
		this.groupBy = groupBy;
		this.sortOrder = sortOrder;
		this.groupSortOrder = groupSortOrder;
	}

	public String getName() {
		return name;
	}

	public String getFunction() {
		return functionName;
	}

	public String[] getCompareFunctionNames() {
		return compareFunctionNames;
	}

	public boolean isGroupBy() {
		return groupBy;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public int getGroupSortOrder() {
		return groupSortOrder;
	}

	public boolean isHidden() {
		return hidden;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String toString() {
		return "Column [name=" + name + ", functionName=" + functionName
				+ ", groupBy=" + groupBy + ", sortOrder=" + sortOrder
				+ ", groupSortOrder=" + groupSortOrder + "]";
	}
	
}
