package yogi.server.gui.record.item;


public abstract class RecordItem  {


	private int childId;
	
	public RecordItem() {
		super();
	}
	
	public RecordItem(int childId) {
		super();
		this.childId = childId;
	}

	public int getChildId(){
		return childId;
	}
	
}
