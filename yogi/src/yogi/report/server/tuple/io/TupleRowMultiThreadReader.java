package yogi.report.server.tuple.io;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.multithread.rdc.RDCCallable;
import yogi.base.app.multithread.rdc.RDCCaller;
import yogi.base.io.resource.FileResource;
import yogi.base.util.logging.Logging;
import yogi.base.util.range.Range;
import yogi.report.server.ColumnAndSelector;
import yogi.report.server.tuple.MutalbleTupleRow;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;

public class TupleRowMultiThreadReader extends TupleRowReader{
	public static boolean RUN = true;
	public static int numberOfThreads = 2;
	public static long part = 50000000;
	private static Logger logger = Logging.getLogger(TupleRowMultiThreadReader.class);
	private int recordLength;//this lenght includes newline characters also.
	protected ArrayList<Integer> recordLengths = new ArrayList<Integer>();

	/*
	 *  recordLength should be  larger than the max line size, otherwise it will not work.
	 */
	public TupleRowMultiThreadReader(int recordLength){
		this.recordLength = recordLength;
	}

	public int getRecordLength() {
		return recordLength;
	}

	protected int getNumberOfThreads(){
		return numberOfThreads;
	}
	
	public List<TupleRow> read()
	{
		List<TupleRow> rtnValue = new ArrayList<TupleRow>(0);
		if(!open()) return rtnValue;
		try {
			FileChannelReader recordReader = getReader();
			if(!recordReader.open()) return rtnValue;
			List<Range<Long>> units = recordReader.getOffsets(part);
			recordReader.close();
			int threadCount = getNumberOfThreads();
			threadCount = Math.min(units.size(), threadCount);
			Collection<List<TupleRow>> rlist = call(units, threadCount);
			for(List<TupleRow> rows: rlist){
				rtnValue.addAll(rows);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			
			close();
		}
			return rtnValue;
	}

	protected Collection<List<TupleRow>> call(List<Range<Long>> units, int threadCount) {
		RDCCaller<Range<Long>, List<TupleRow>, ReaderCallable> rdcCaller = new RDCCaller<Range<Long>, List<TupleRow>, ReaderCallable>(threadCount);
		return rdcCaller.call(units, ReaderCallable.class, getFileResource(),recordLength, recordLengths, fileResources,getColumnIndexMap(), getFilterColumnEvaluatorsMap(), getColumnEvaluatorsMap(), getColumnSelector());
	}

	protected FileChannelReader getReader() {
		return new FileChannelReader(getFileResource(),recordLength);
	}
	
	protected void setRecordLength(int index, int recordLength) {
		if(recordLengths.size() == index) this.recordLengths.add(recordLength);
		else if(recordLengths.size() > index) this.recordLengths.set(index, recordLength);
		else throw new RuntimeException("use successive indexes while setting recortLengths");
	}

	public int getRecordLength(int index) {
		return recordLengths.get(index);
	}

	private boolean close() {
		return true;
	}

	private boolean open() {
		return true;
	}

	public boolean isActivated() {
		return true;
	}
	public static class ReaderCallable implements RDCCallable<Range<Long>, List<TupleRow>>
	{
		FileChannelReader recordReader;
		private Map<String, Integer> columnIndexMap;
		private Map<String, TupleRowBaseEvaluator<?>> filterColumnEvaluatorsMap;
		private Map<String, TupleRowBaseEvaluator<?>> columnEvaluatorsMap;
		private ColumnAndSelector<TupleRow> columnSelector;
		
		protected ReaderCallable() {
			super();
		}

		@SuppressWarnings("unchecked")
		public ReaderCallable(Object fileResource, Object recordLength, Object recordLengths, Object fileResources, Object columnIndexMap, Object filterColumnEvaluatorsMap, Object columnEvaluatorsMap, Object columnSelector) {
			setup((FileResource)fileResource, (Integer)recordLength, (ArrayList<Integer>) recordLengths, (ArrayList<FileResource>) fileResources, (Map<String, Integer>)columnIndexMap, (Map<String, TupleRowBaseEvaluator<?>>)filterColumnEvaluatorsMap, (Map<String, TupleRowBaseEvaluator<?>>)columnEvaluatorsMap, (ColumnAndSelector<TupleRow>)columnSelector);
		}
		
		protected void setup(FileResource fileResource, int recordLength, ArrayList<Integer> recordLengths, ArrayList<FileResource> fileResources, Map<String, Integer> columnIndexMap, Map<String, TupleRowBaseEvaluator<?>> filterColumnEvaluatorsMap, Map<String, TupleRowBaseEvaluator<?>> columnEvaluatorsMap, ColumnAndSelector<TupleRow> columnSelector) {
			this.columnIndexMap = columnIndexMap;
			this.filterColumnEvaluatorsMap = filterColumnEvaluatorsMap;
			this.columnEvaluatorsMap = columnEvaluatorsMap;
			this.columnSelector = columnSelector;
			FileChannelReader[] fileChannelReaders = new FileChannelReader[recordLengths.size()];
			for(int i = 0; i < recordLengths.size(); i ++){
				fileChannelReaders[i] = getFileChannelReader(fileResources.get(i), recordLengths.get(i));
			}
			this.recordReader = getFileChannelReader(fileResource,recordLength, fileChannelReaders);
		}
		
		protected FileChannelReader getFileChannelReader(FileResource fileResource, int recordLength, FileChannelReader... fileChannelReaders){
			return new FileChannelReader(fileResource, recordLength, fileChannelReaders);
		}

		@Override
		public List<TupleRow> call(Range<Long> part) {
			if(logger.isLoggable(Level.FINE)){
				logger.fine(String.format("working with offset %s in thread %s", part, Thread.currentThread().getId()));
			}
			List<TupleRow> rtnValue = new ArrayList<TupleRow>(0);
			try {
				MutalbleTupleRow object = new MutalbleTupleRow(columnIndexMap, columnEvaluatorsMap);
				long offset = part.getStart();
				while(offset < part.getEnd())
				{
					long read = object.set(recordReader, offset);
					for(Entry<String, TupleRowBaseEvaluator<?>> entry: filterColumnEvaluatorsMap.entrySet()){
						object.getValue(entry.getKey(), (TupleRowBaseEvaluator<?>) entry.getValue());
					}
					if(columnSelector.select(object)){
						for(Entry<String, TupleRowBaseEvaluator<?>> entry: columnEvaluatorsMap.entrySet()){
							object.getValue(entry.getKey(), (TupleRowBaseEvaluator<?>) entry.getValue());
						}
						rtnValue.add(object.getTupleRow());
					}
					offset = offset + read;
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}finally{
			}
			return rtnValue;
		}

		@Override
		public boolean open() {
			if(logger.isLoggable(Level.INFO)){
				logger.info(String.format("Started Reading in thread %s", Thread.currentThread().getId()));
			}
			return recordReader.open();
		}

		@Override
		public boolean close() {
			if(logger.isLoggable(Level.INFO)){
				logger.info(String.format("Stoped Reading in thread %s", Thread.currentThread().getId()));
			}
			return recordReader.close();
		}
	}

}
