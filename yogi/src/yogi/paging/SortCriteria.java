package yogi.paging;

import java.io.Serializable;
import java.util.List;

public class SortCriteria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3661722888413749776L;

	private List<SortConfig> sortData;
	
	public SortCriteria(List<SortConfig> sortData) {
		super();
		this.sortData = sortData;
	}

	public List<SortConfig> getSortData() {
		return sortData;
	}
}
