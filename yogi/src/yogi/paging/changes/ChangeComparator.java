package yogi.paging.changes;

import java.util.Comparator;

import yogi.paging.ChangeType;

public class ChangeComparator implements Comparator<Change>{
	
	@Override
	public int compare(Change c1, Change c2) {
	    	int rowIndex1 = c1.getRowDataIndex();
	    	int rowIndex2 = c2.getRowDataIndex();
		
		int result = Integer.valueOf(rowIndex1).compareTo(Integer.valueOf(rowIndex2));
		if(result != 0) return result;
		
		ChangeType action1 = c1.getChangeType();
		ChangeType action2 = c2.getChangeType();
		return compareTypes(action1, action2);		
	}

	private int compareTypes(ChangeType action1, ChangeType action2) {
	    int result = 0;
	    switch(action1) {
	    	case DEL :
	    	case ADD :
	    	case MOD :
	    		if(action2.ordinal() < action1.ordinal()) {
	    		    result = 1;
	    		} else if(action2.ordinal() > action1.ordinal()) {
	    		    result = -1;
	    		} else {
	    		    result = 0;
	    		}
	    		break;					
	    	default:
	    		break;
	    }				
	    return result;
	}
	
}