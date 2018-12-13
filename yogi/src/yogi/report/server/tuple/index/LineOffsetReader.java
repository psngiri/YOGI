package yogi.report.server.tuple.index;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.multithread.rdc.RDCCallable;
import yogi.base.app.multithread.rdc.RDCCaller;
import yogi.base.app.multithread.rdc.RDCList;
import yogi.base.io.resource.FileResource;
import yogi.base.util.logging.Logging;
import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.FileChannelReader;
/**
 * LineOffset Reader reads the line offsets for each line in a file.Using the return values you know the offset location
 * of each line, which allows to get to the line quickly.
 * @author 549915
 *
 */
public class LineOffsetReader{
	private static byte newLineByte = (byte) '\n';
	public static boolean RUN = true;
	public static int numberOfThreads = 2;
	public static long part = 50000000;
	public static int bufferSize = 10000;
	private static Logger logger = Logging.getLogger(LineOffsetReader.class);
	private FileResource fileResource;

	public LineOffsetReader(FileResource fileResource){
		this.fileResource = fileResource;
	}

	protected int getNumberOfThreads(){
		return numberOfThreads;
	}
	
	public List<Long> read()
	{
		List<Long> rtnValue = new ArrayList<Long>();
		rtnValue.add(0l);
		Iterator<List<Long>> returnValuesIterator = readLines();
		while(returnValuesIterator.hasNext()){
			rtnValue.addAll(returnValuesIterator.next());
		}
		return rtnValue;
	}
	
	protected Iterator<List<Long>> readLines()
	{
		if(!open()) return null;
		try {
			FileChannelReader recordReader = getReader();
			if(!recordReader.open()) return null;
			List<Range<Long>> units = recordReader.getOffsets(part);
			recordReader.close();
			int threadCount = getNumberOfThreads();
			threadCount = Math.min(units.size(), threadCount);
			RDCCaller<Range<Long>, List<Long>, ReaderCallable> rdcCaller = new RDCCaller<Range<Long>, List<Long>, ReaderCallable>(threadCount);
			RDCList<Range<Long>, List<Long>> rlist = rdcCaller.execute(units, ReaderCallable.class, fileResource,bufferSize);
			return  rlist.getReturnValuesIterator();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			
			close();
		}
	}

	protected FileChannelReader getReader() {
		return new FileChannelReader(fileResource,bufferSize);
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
	public static class ReaderCallable implements RDCCallable<Range<Long>, List<Long>>
	{
		FileChannelReader recordReader;
		
		public ReaderCallable(Object fileResource, Object bufferSize) {
			setup((FileResource)fileResource, (Integer)bufferSize);
		}
		
		void setup(FileResource fileResource, int bufferSize) {
			this.recordReader = new FileChannelReader(fileResource,bufferSize);
		}

		@Override
		public List<Long> call(Range<Long> part) {
			if(logger.isLoggable(Level.FINE)){
				logger.fine(String.format("working with offset %s in thread %s", part, Thread.currentThread().getId()));
			}
			List<Long> rtnValue = new ArrayList<Long>();
			try {
				long offset = part.getStart();
				while(offset < part.getEnd())
				{
					long read = recordReader.read(offset);
					for(int i = 0; i < read; i++){
						byte myByte = recordReader.getByte(i);
						if(myByte==newLineByte){
							rtnValue.add(offset+i+1);
						}
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
