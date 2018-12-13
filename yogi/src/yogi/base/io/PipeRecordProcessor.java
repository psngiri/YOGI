package yogi.base.io;

import java.util.ArrayList;
import java.util.List;


public class PipeRecordProcessor<T> implements RecordProcessor<String, T> {
	private static final ArrayList<String> EMPTY_LIST = new ArrayList<String>();
	private ObjectWriter<T> writer;
	
	public PipeRecordProcessor(ObjectWriter<T> writer) {
		super();
		this.writer = writer;
	}

	public int getValidRecordCount() {
		return 0;
	}

	public List<String> getObjectsCreated() {
		return EMPTY_LIST;
	}

	public boolean open() {
		return writer.open();
	}

	
	public boolean close() {
		return writer.close();
	}

	public String process(T record) {
		writer.write(record);
		return null;
	}

}
