package yogi.paging.changes;

import java.io.Serializable;
import java.util.List;

public class Changes implements Serializable {
	private static final long serialVersionUID = -4722525612105053455L;
	
	private List<Change> changesHistory;
	private boolean groupBoundaryToBeMarked;
		
	public Changes(List<Change> changesHistory, boolean groupBoundaryToBeMarked) {
		this(changesHistory);
		this.groupBoundaryToBeMarked = groupBoundaryToBeMarked;
	}
	
	public Changes(List<Change> changesHistory) {
		super();
		this.changesHistory = changesHistory;
	}
	public List<Change> getChangesHistory(){
		return changesHistory;
	}
	
	public boolean isChangesExisting() {
		if(changesHistory == null) {
			return false;
		} else {
			return changesHistory.size() != 0;
		}
	}
	
	public boolean isGroupBoundaryToBeMarked() {
		return groupBoundaryToBeMarked;
	}
	
}
