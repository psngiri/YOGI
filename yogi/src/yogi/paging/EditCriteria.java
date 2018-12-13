package yogi.paging;

import java.io.Serializable;
import java.util.List;

public class EditCriteria implements Serializable{
	private static final long serialVersionUID = -6420864600625199181L;

	private List<Integer> rows;
	private List<EditConfig> editData;
	private boolean selectAll;
	
	public EditCriteria(List<Integer> rows, List<EditConfig> editData, boolean selectAll) {
		super();
		this.rows = rows;
		this.editData = editData;
		this.selectAll = selectAll;
	}

	public List<EditConfig> getEditData() {
		return editData;
	}

	public List<Integer> getRows() {
		return rows;
	}
	
	public boolean isAllSelected() {
		return selectAll;
	}
}
