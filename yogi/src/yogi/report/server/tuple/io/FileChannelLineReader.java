package yogi.report.server.tuple.io;

import java.util.ArrayList;
import java.util.List;

import yogi.base.io.resource.FileResource;
import yogi.base.util.range.Range;


public class FileChannelLineReader extends FileChannelReader{

	private int numOfHeaderLines = 0;

	/*
	 *  recordLength should be  larger than the max line size, otherwise it will not work.
	 */
	public FileChannelLineReader(FileResource fileResource, int recordLength, byte separator, FileChannelReader... fileChannelReaders)
	{
		super(fileResource, recordLength, new LineRecordReader(separator), fileChannelReaders);
	}
	
	public int getNumOfHeaderLines() {
		return numOfHeaderLines;
	}

	public void setNumOfHeaderLines(int numOfHeaderLines) {
		this.numOfHeaderLines = numOfHeaderLines;
	}

	public List<Range<Long>> getOffsets(long part){
		long size = this.size();
		List<Range<Long>> rtnValue = new ArrayList<Range<Long>>();
		long start = 0;
		long end = part;
		for(int i = 0; i < numOfHeaderLines; i ++){
			long read = this.read(start);
			start = start + read;
		}
		while(end < size){
			long read = this.read(end);
			end = end+read;
			rtnValue.add(new Range<Long>(start, end));
			start = end+1;
			end = end + part;
		}
		rtnValue.add(new Range<Long>(start, size));
		return rtnValue;
	}

	public List<Long> getPartOffsets(int numberOfParts){
		List<Long> rtnValue = new ArrayList<Long>(numberOfParts);
		long part = this.size()/numberOfParts;
		long offset = part;
		for(int i = 0; i < numberOfParts-1; i++){
			long read = this.read(offset);
			offset = offset+read;
			rtnValue.add(offset);
			offset = offset+1;
		}
		rtnValue.add(size());
		return rtnValue;
	}
}
