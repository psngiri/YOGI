package yogi.paging;

import java.io.Serializable;
import java.util.List;

public class FilterCriteria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3648928538740672733L;

	private List<FilterConfig> filterData;
	
	public FilterCriteria(List<FilterConfig> filterData) {
		super();
		this.filterData = filterData;
	}

	public List<FilterConfig> getFilterData() {
		return filterData;
	}
}
