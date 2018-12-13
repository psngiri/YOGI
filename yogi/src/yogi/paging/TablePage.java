package yogi.paging;

import java.util.ArrayList;
import java.util.List;

public class TablePage extends TableResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2794298548661728814L;
	
	private List<TableRecord> data;
	private PageChanges pageChanges;
		
	public TablePage(List<String[]> records, int tableSize, Integer tableDataSetId, PageChanges pageChanges) {
		super(tableSize, tableDataSetId);
		
		this.data = new ArrayList<TableRecord>(records.size());
		for(int i = 0; i < records.size(); i++){
			data.add(i, new TableRecord(records.get(i)));
		}
		this.pageChanges = pageChanges;
	}

	public PageChanges getPageChanges() {
	    return pageChanges;
	}

	public List<TableRecord> getData() {
		return data;
	}	
	
}
