package yogi.paging;


public class PagingCommandResponse extends TableResponse {

    	private static final long serialVersionUID = -2794298548661728814L;
	
	private int startRowIndex;
	private String message = "";
		
	public PagingCommandResponse(int tableSize, Integer tableDataSetId, int startRowIndex) {
		super(tableSize, tableDataSetId);
		this.startRowIndex = startRowIndex;
		if(startRowIndex == -1) {
		    this.message = "Search parameter not found";
		}
	}

	public int getStartRowIndex() {
		return startRowIndex;
	}
	
	public String getMessage() {
		return message;
	}	
	
}
