package yogi.tools.viewers.capacity;

import yogi.base.relationship.types.capacity.Capacity;
import yogi.base.util.range.Range;
import yogi.tools.swingx.treetable.TreeTableDataHelper;

public class CapacityTreeTableDataHelper implements TreeTableDataHelper<Capacity> {

	public Object getValueAt(Capacity capacity, int column) {
		switch(column)
		{
		case 0: return capacity.getKlass().getName();
		case 1: 
			return getStartIndex(capacity);
		case 2: return capacity.getInitialCapacity();
		case 3: return getStartIndex(capacity) + capacity.getInitialCapacity();
		}
		return null;
	}

	private int getStartIndex(Capacity capacity) {
		int startIndex = 0;
		Range<Integer> range = capacity.getRange();
		if(range != null) startIndex = range.getStart();
		return startIndex;
	}

	public int getColumnCount() {
		return 4;
	}

	public boolean isEditable(Capacity capacity, int column) {
		return false;
	}

	public void setValueAt(Capacity capacity, Object aValue, int column) {
		
	}

	public String getColumnName(int column) {
		switch(column)
		{
		case 0: return "Name";
		case 1: return "Parents Relationships";
		case 2: return "My Relationships";
		case 3: return "Total Relationships";
		}
		return "";
	}

}
