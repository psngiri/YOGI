package yogi.base.io.db;

import java.util.ArrayList;
import java.util.List;


public abstract class MultiRowDbFormatterAdapter<T> implements MultiRowDbFormatter<T> {
	private List<ObjectDbRecord> objectDbRecordsStore;
	private List<DbRecord> objectDbRecords = new ArrayList<DbRecord>();
	private int size;
	private int index = 0;
	
	public MultiRowDbFormatterAdapter(int size) {
		super();
		this.size = size;
		objectDbRecordsStore  = new ArrayList<ObjectDbRecord>();
	}
	public List<DbRecord> getObjectDbRecords()
	{
		index = 0;
		objectDbRecords.clear();
		return objectDbRecords;
	}
	public ObjectDbRecord getNextObjectDbRecord() {
		if(objectDbRecordsStore.size()== index)
		{
			objectDbRecordsStore.add(new ObjectDbRecord(size));
		}
		ObjectDbRecord objectDbRecord = objectDbRecordsStore.get(index);
		index++;
		objectDbRecord.clear();
		return objectDbRecord;
	}

}
