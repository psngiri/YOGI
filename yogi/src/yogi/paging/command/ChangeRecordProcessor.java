package yogi.paging.command;

import java.util.ArrayList;
import java.util.List;

import yogi.base.io.RecordProcessor;
import yogi.paging.changes.ChangeRecord;

public class ChangeRecordProcessor implements RecordProcessor<ChangeRecord, String> {
	private List<ChangeRecord> changeRecords = new ArrayList<ChangeRecord>();
	@Override
	public ChangeRecord process(String record) {
		String[] split = record.split(",",-1);
		String[] newValue = new String[split.length];		
		for(int j = 0; j < newValue.length; j++) {						
		    newValue[j] = split[j];
		}
		ChangeRecord cr = new ChangeRecord(null, newValue);
		changeRecords.add(cr);
		return cr;
	}

	@Override
	public int getValidRecordCount() {
		return changeRecords.size();
	}

	@Override
	public List<ChangeRecord> getObjectsCreated() {
		return changeRecords;
	}

	@Override
	public boolean open() {
		return true;
	}

	@Override
	public boolean close() {
		return true;
	}

}
