package yogi.base.io;

import java.util.ArrayList;
import java.util.List;


public class DefaultReader<T, R> implements Reader<T>{
	private RecordReader<R> recordReader;
	private List<RecordProcessor<? extends T, R>> recordProcessors = new ArrayList<RecordProcessor<? extends T, R>>();
	
	public DefaultReader() {
		super();
	}
	
	public DefaultReader(RecordReader<R> recordReader) {
		super();
		setRecordReader(recordReader);
	}
	
	public DefaultReader(RecordReader<R> recordReader, RecordProcessor<? extends T, R> recordProcessor) {
		super();
		addRecordProcessor(recordProcessor);
		setRecordReader(recordReader);
	}
	
	public void setRecordReader(RecordReader<R> recordReader) {
		this.recordReader = recordReader;
	}

	public RecordReader<R> getRecordReader() {
		return recordReader;
	}

	public void addRecordProcessor(RecordProcessor<? extends T, R> recordProcessor)
	{
		recordProcessors.add(recordProcessor);
	}
	
	public List<T> read()
	{
		List<T> rtnValue = new ArrayList<T>(0);
		if(!open()) return rtnValue;
		try {
			while(recordReader.hasNext())
			{
				R record = recordReader.next();
				processRecord(record);
			}
			rtnValue = objectsRead();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			close();
		}
		return rtnValue;
	}

	private boolean close() {
		boolean rtnValue = recordReader.close();
		for(RecordProcessor<? extends T, R> recordProcessor: recordProcessors)
		{
			if(!recordProcessor.close()) return false;
		}
		return rtnValue;
	}

	private boolean open() {
		boolean rtnValue = recordReader.open();
		for(RecordProcessor<? extends T, R> recordProcessor: recordProcessors)
		{
			if(!recordProcessor.open()) return false;
		}
		return rtnValue;
	}

	private void processRecord(R record) {
		for(RecordProcessor<? extends T, R> recordProcessor: recordProcessors)
		{
			recordProcessor.process(record);
		}
	}

	private List<T> objectsRead()
	{
		List<T> objectsRead = new ArrayList<T>();
		for(RecordProcessor<? extends T, R> recordProcessor: recordProcessors)
		{
			objectsRead.addAll(recordProcessor.getObjectsCreated());
		}
		return objectsRead;
	}
	
	public boolean isActivated() {
		return true;
	}

}
