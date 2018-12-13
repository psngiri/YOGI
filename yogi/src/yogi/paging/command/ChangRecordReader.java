package yogi.paging.command;

import java.util.List;

import yogi.base.io.DefaultReader;
import yogi.base.io.FileRecordReader;
import yogi.base.io.resource.SystemResource;
import yogi.paging.changes.ChangeRecord;

public class ChangRecordReader extends DefaultReader<ChangeRecord, String> {
	public static boolean RUN = true;
	private FileRecordReader recordReader;
	public ChangRecordReader(SystemResource resource) {
		super();
		recordReader = new FileRecordReader(resource, 1);
		this.setRecordReader(recordReader);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new ChangeRecordProcessor());
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}
	
	public String[] getColumns(){
		List<String> header = this.recordReader.getHeader();
		return header.get(0).split(",");
	}
}
