package yogi.report.server.tuple.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class RecordReader {
	public static int NumberOfLines = 100;
	private FileChannelReader fileChannelReader;
	protected RecordByteBuffer recordByteBuffer;
	private ByteBuffer byteBuffer;
	private long offset = -1;
	
	public RecordReader() {
		super();
	}
	
	public boolean isFileError() {
		return fileChannelReader.isFileError();
	}
	
	protected void setFileChannelReader(FileChannelReader fileChannelReader) {
		this.fileChannelReader = fileChannelReader;
		byteBuffer = ByteBuffer.allocate(fileChannelReader.getRecordLength()*NumberOfLines);
		recordByteBuffer = new RecordByteBuffer(byteBuffer);
	}

	public char[] getCharArray(int start, int end, boolean trim) {
		int pstart = start;
		int pend = end;
		recordByteBuffer.position(pstart);
		if(trim)
		{
			while ((pstart < pend) && (recordByteBuffer.get() <= ' ')) {
			    pstart++;
			}
			recordByteBuffer.position(pend-1);
			while ((pstart < pend) && (recordByteBuffer.get() <= ' ')) {
			    pend--;
				recordByteBuffer.position(pend-1);
			}
		}
		char[] charArray = new char[pend-pstart];
		recordByteBuffer.position(pstart);
		for(int i = 0; i < charArray.length; i++){
			charArray[i] = (char)recordByteBuffer.get();
		}
		return charArray;
	}
	public byte getByte(int position){
		recordByteBuffer.position(position);
		return recordByteBuffer.get();
	}
	public byte[] getByteArray(int start, int end) {
		int pstart = start;
		int pend = end;
		recordByteBuffer.position(pstart);
		byte[] byteArray = new byte[pend-pstart];
		for(int i = 0; i < byteArray.length; i++){
			byteArray[i] = recordByteBuffer.get();
		}
		return byteArray;
	}

	public long read(long offset) {
		if(this.offset == offset) return recordByteBuffer.bytesInRecord();
		if(isFileError()) return 0;
		this.offset = offset;
		if(recordByteBuffer.contains(offset)){
			recordByteBuffer.setLineStart(offset);
			return 	extension(recordByteBuffer.bytesInRecord());
		}
		long read = -1;
		try {
			byteBuffer.clear();
			getFileChannel().position(offset);
			read = getFileChannel().read(byteBuffer);
//			if(read < recordLength){
//				throw new RuntimeException("Number of records read "+ read + " is less than the specified recordLength "+ recordLength);
//			}
			byteBuffer.flip();
			recordByteBuffer.set(offset, read, fileChannelReader.getRecordLength());
			read = extension(recordByteBuffer.bytesInRecord());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return read;
	}

	private FileChannel getFileChannel() {
		return fileChannelReader.getFileChannel();
	}
	protected long extension(long read) {
		return read;
	}

	static class RecordByteBuffer
	{
		private long start = -1;
		private int lineStart = 0;
		private ByteBuffer byteBuffer;
		private long bytesRead = 0;
		private int recordLength;
		private long end = -1;
		
		RecordByteBuffer(ByteBuffer byteBuffer) {
			super();
			this.byteBuffer = byteBuffer;
		}

		void set(long start, long bytesRead, int recordLength) {
			this.lineStart = 0;
			this.start = start;
			this.recordLength = recordLength;
			this.bytesRead = bytesRead;
			this.end = start +bytesRead-recordLength;
			if(end < start) end = start;
		}

		public boolean contains(long offset){
			return offset <= end && offset >= start;
		}
		
		public long bytesInRecord(){
			return Math.min(recordLength, bytesRead-lineStart);
		}
		
		void setLineStart(long lineOffset) {
			this.lineStart = (int)(lineOffset-start);
		}

		public RecordByteBuffer position(int newPosition){
			byteBuffer.position(lineStart + newPosition);
			return this;
		}
		
		public byte get(){
			return byteBuffer.get();
		}
	}
}
