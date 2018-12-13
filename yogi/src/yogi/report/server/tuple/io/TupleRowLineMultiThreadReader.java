package yogi.report.server.tuple.io;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import yogi.base.app.multithread.rdc.RDCCaller;
import yogi.base.io.resource.FileResource;
import yogi.base.util.range.Range;
import yogi.report.server.ColumnAndSelector;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;

public class TupleRowLineMultiThreadReader extends TupleRowMultiThreadReader{
	private byte separator = (byte) ',';
	private int numOfHeaderLines;

	/*
	 *  recordLength should be  larger than the max line size, otherwise it will not work.
	 */
	public TupleRowLineMultiThreadReader(int recordLength) {
		super(recordLength);
	}

	/*
	 *  recordLength should be  larger than the max line size, otherwise it will not work.
	 */
	public TupleRowLineMultiThreadReader(int recordLength, byte separator) {
		super(recordLength);
		this.separator = separator;
	}

	public int getNumOfHeaderLines() {
		return numOfHeaderLines;
	}

	public void setNumOfHeaderLines(int numOfHeaderLines) {
		this.numOfHeaderLines = numOfHeaderLines;
	}

	@Override
	protected FileChannelReader getReader() {
		FileChannelLineReader fileChannelLineReader = new FileChannelLineReader(getFileResource(),getRecordLength(), separator);
		fileChannelLineReader.setNumOfHeaderLines(numOfHeaderLines);
		return fileChannelLineReader;
	}
	
	
	protected Collection<List<TupleRow>> call(List<Range<Long>> units, int threadCount) {
		RDCCaller<Range<Long>, List<TupleRow>, LineReaderCallable> rdcCaller = new RDCCaller<Range<Long>, List<TupleRow>, LineReaderCallable>(threadCount);
		return rdcCaller.call(units, LineReaderCallable.class, getFileResource(),getRecordLength(), recordLengths, fileResources,getColumnIndexMap(), getFilterColumnEvaluatorsMap(), getColumnEvaluatorsMap(), getColumnSelector(), separator);
	}


	public static class LineReaderCallable extends ReaderCallable{
		private byte separator = (byte) ',';

		@SuppressWarnings("unchecked")
		public LineReaderCallable(Object fileResource, Object recordLength, Object recordLengths, Object fileResources,
				Object columnIndexMap, Object filterColumnEvaluatorsMap, Object columnEvaluatorsMap, Object columnSelector, Object separator) {
			this.separator = (byte) separator;
			setup((FileResource)fileResource, (Integer)recordLength, (ArrayList<Integer>) recordLengths, (ArrayList<FileResource>) fileResources, (Map<String, Integer>)columnIndexMap, (Map<String, TupleRowBaseEvaluator<?>>)filterColumnEvaluatorsMap, (Map<String, TupleRowBaseEvaluator<?>>)columnEvaluatorsMap, (ColumnAndSelector<TupleRow>)columnSelector);
		}

		@Override
		protected FileChannelReader getFileChannelReader(FileResource fileResource, int recordLength,
				FileChannelReader... fileChannelReaders) {
				return new FileChannelLineReader(fileResource,recordLength, separator, fileChannelReaders);
		}
		
	}

}
