package yogi.base.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.io.CharArrayReader.Line;
import yogi.base.io.resource.ResourceAssistant;
import yogi.base.io.resource.SystemResource;
import yogi.base.util.logging.Logging;

public class CharArrayReader implements RecordReader<Line>{
	private static Logger logger = Logging.getLogger(CharArrayReader.class);
	private SystemResource resource;
	protected BufferedReader reader;
	private int recordLength;//this lenght includes newline characters also.
	private int numberOfLinesInBuffer = 10;
	private Boolean hasNext = null;
	private Line line;
	private int offset = 0;
	
	public CharArrayReader(String fileName, int recordLength)
	{
		this(ResourceAssistant.getResource(fileName), recordLength);
	}
	
	public CharArrayReader(SystemResource resource, int recordLength)
	{
		super();
		this.resource = resource;
		this.recordLength = recordLength;
		this.line =  new Line(recordLength*numberOfLinesInBuffer);
	}

	public int getNumberOfLinesInBuffer() {
		return numberOfLinesInBuffer;
	}

	public void setNumberOfLinesInBuffer(int numberOfLinesInBuffer) {
		if(line.read != 0) throw new RuntimeException("Cant change the buffer size after the reading has started");
		this.numberOfLinesInBuffer = numberOfLinesInBuffer;
		this.line =  new Line(recordLength*numberOfLinesInBuffer);		
	}

	public int getRecordLength() {
		return recordLength;
	}

	public SystemResource getResource() {
		return resource;
	}

	public boolean open(){
		if (!resource.canRead()) {
			throw new RuntimeException("Could not read from " + resource);
		}
		 reader = new BufferedReader(
				 resource.getReader(),numberOfLinesInBuffer*recordLength);
		 if(logger.isLoggable(Level.INFO))
		 {
			 logger.info("Reading from " + resource);
		 }
		return true;
	}
	
	public boolean close() {
		try {
			reader.close();
		} catch (IOException e) {
			throw new RuntimeException("Error in " + resource, e);
		}
		return true;
	}

	public static class Line{
		char[] buf;
		int lineStart = 0;
		int read = 0;
		
		public Line(int bufferSize) {
			super();
			buf = new char[bufferSize];
		}

		public String getTrim(int start, int end){
			int pstart = lineStart+start;
			int pend = lineStart+end;
			
			while ((pstart < pend) && (buf[pstart] <= ' ')) {
			    pstart++;
			}
			while ((pstart < pend) && (buf[pend - 1] <= ' ')) {
			    pend--;
			}
			return new String(Arrays.copyOfRange(buf, pstart, pend));
		}
	}

	@Override
	public boolean hasNext() {
		if(hasNext != null) return hasNext;
		if(line.lineStart + recordLength < line.read){
			line.lineStart = line.lineStart + recordLength;
			hasNext = true;
			return true;
		}else{
			try {
				line.read = reader.read(line.buf, offset, line.buf.length);
				line.lineStart = 0;
				if(line.lineStart + recordLength < line.read){
					hasNext = true;
					return true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		hasNext = false;
		return false;
	}

	@Override
	public Line next() {
		if(hasNext == null) throw new RuntimeException("Call hasNext() and check if a record exists before calling next(). Null record");
		hasNext = null;
		return line;
	}
}
