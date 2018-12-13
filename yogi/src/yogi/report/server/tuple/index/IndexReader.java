package yogi.report.server.tuple.index;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.multithread.rdc.RDCCallable;
import yogi.base.app.multithread.rdc.RDCCaller;
import yogi.base.io.resource.FileResource;
import yogi.base.util.logging.Logging;
import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.FileChannelReader;

public class IndexReader{
	public static boolean RUN = true;
	public static int numberOfThreads = 2;
	public static long part = 50000000;
	private int recordLength;//this lenght includes newline characters also.
	private static Logger logger = Logging.getLogger(IndexReader.class);
	private FileResource fileResource;
	private Range<Integer> range;

	public IndexReader(int recordLength, FileResource fileResource, Range<Integer> range){
		this.recordLength = recordLength;
		this.fileResource = fileResource;
		this.range = range;
	}

	public int getRecordLength() {
		return recordLength;
	}

	protected int getNumberOfThreads(){
		return numberOfThreads;
	}
	
	public Map<Long,Long> read()
	{
		Map<Long,Long> rtnValue = new HashMap<Long,Long>();
		if(!open()) return rtnValue;
		try {
			FileChannelReader recordReader = getReader();
			if(!recordReader.open()) return rtnValue;
			List<Range<Long>> units = recordReader.getOffsets(part);
			recordReader.close();
			int threadCount = getNumberOfThreads();
			threadCount = Math.min(units.size(), threadCount);
			RDCCaller<Range<Long>, Map<Long,Long>, ReaderCallable> rdcCaller = new RDCCaller<Range<Long>, Map<Long,Long>, ReaderCallable>(threadCount);
			Collection<Map<Long,Long>> rlist = rdcCaller.call(units, ReaderCallable.class, fileResource,recordLength, range);
			for(Map<Long,Long> map: rlist){
				rtnValue.putAll(map);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			
			close();
		}
			return rtnValue;
	}

	protected FileChannelReader getReader() {
		return new FileChannelReader(fileResource,recordLength);
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
	public static class ReaderCallable implements RDCCallable<Range<Long>, Map<Long,Long>>
	{
		FileChannelReader recordReader;
		private Range<Integer> range;
		
		@SuppressWarnings("unchecked")
		public ReaderCallable(Object fileResource, Object recordLength, Object range) {
			setup((FileResource)fileResource, (Integer)recordLength, (Range<Integer>) range);
		}
		
		void setup(FileResource fileResource, int recordLength, Range<Integer> range) {
			this.recordReader = new FileChannelReader(fileResource,recordLength);
			this.range = range;
		}

		@Override
		public Map<Long,Long> call(Range<Long> part) {
			if(logger.isLoggable(Level.FINE)){
				logger.fine(String.format("working with offset %s in thread %s", part, Thread.currentThread().getId()));
			}
			Map<Long,Long> rtnValue = new HashMap<Long,Long>();
			try {
				long offset = part.getStart();
				while(offset < part.getEnd())
				{
					long read = recordReader.read(offset);
					Long key = Long.valueOf(new String(recordReader.getCharArray(getRange().getStart(), getRange().getEnd(), true)));
					offset = offset + read;
					long value = offset/read;
					rtnValue.put(key, value);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}finally{
			}
			return rtnValue;
		}

		private Range<Integer> getRange() {
			return range;
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
