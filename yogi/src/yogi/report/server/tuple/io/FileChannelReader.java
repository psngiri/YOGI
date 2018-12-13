package yogi.report.server.tuple.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import yogi.base.io.resource.FileResource;
import yogi.base.util.range.Range;

public class FileChannelReader{
	private FileChannel fileChannel;
	private FileResource fileResource;
	private FileInputStream fis;
	private int recordLength;
	private RecordReader recordReader;
	private List<RecordReader> recordReaders;
	private FileChannelReader[] fileChannelReaders = new FileChannelReader[0];
	private boolean fileError = false;
	
	public FileChannelReader(FileResource fileResource, int recordLength, FileChannelReader... fileChannelReaders)
	{
		this(fileResource, recordLength, new RecordReader(), fileChannelReaders);
	}
	
	public FileResource getFileResource() {
		return fileResource;
	}

	public boolean isFileError() {
		return fileError;
	}

	protected FileChannelReader(FileResource fileResource, int recordLength, RecordReader recordReader, FileChannelReader... fileChannelReaders)
	{
		super();
		this.fileResource = fileResource;
		this.recordLength = recordLength;
		this.recordReader = recordReader;
		recordReader.setFileChannelReader(this);
		this.fileChannelReaders = fileChannelReaders;
	}
	
	public FileChannelReader getFileChannelReader(int index){
		if(index >= fileChannelReaders.length) throw new RuntimeException("No FileChannel Reader attached at this index:"+index+" please conifigure the readers before using.");
		return fileChannelReaders[index];
	}

	protected FileChannel getFileChannel() {
		if(fileChannel == null) open();
		return fileChannel;
	}

	public RecordReader getRecordReader(){
		return recordReader;
	}
	
	public RecordReader getRecordReader(int index){
		if(recordReaders == null) recordReaders = new ArrayList<RecordReader>();
		while(index >= recordReaders.size()){
			RecordReader myRecordReader = new RecordReader();
			myRecordReader.setFileChannelReader(this);
			recordReaders.add(myRecordReader);
		}
		return recordReaders.get(index);
	}
	
	public int getRecordLength() {
		return recordLength;
	}

	public boolean open(){
		if(fileChannel != null) return true;
		try {
			if(!fileResource.canRead()){
				fileError  = true;
				throw new RuntimeException("Could not read from FileResource: "+ fileResource.getName());
			}
			fis = fileResource.getInputStream();
			fileChannel = fis.getChannel();
		} catch (Exception e) {
			fileError  = true;
			throw new RuntimeException("Could not read from FileResource: "+ fileResource.getName());
		}
//		for(FileChannelReader fileChannelReader: fileChannelReaders){
//			fileChannelReader.open();
//		}
		return true;
	}
	
	public long size(){
		try {
			return getFileChannel().size();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public long numberOfLines(){
		long size = size();
		return size/recordLength+1;
	}
	
	public boolean close() {
		if(fileChannel == null) return true;
		try {
			fileChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileChannel = null;
		for(FileChannelReader fileChannelReader: fileChannelReaders){
			fileChannelReader.close();
		}
		return true;
	}

	public char[] getCharArray(int start, int end, boolean trim) {
		return recordReader.getCharArray(start, end, trim);
	}
	public byte getByte(int position){
		return recordReader.getByte(position);
	}
	public byte[] getByteArray(int start, int end) {
		return recordReader.getByteArray(start, end);
	}

	public long read(long offset) {
		return recordReader.read(offset);
	}

	@Override
	protected void finalize() throws Throwable {
		close();
		super.finalize();
	}
	
	public List<Range<Long>> getOffsets(long part){
		part = (part/recordLength)*recordLength;
		if(part < 0) throw new RuntimeException("Part cant be less than recordLength, set it big enough usually in millions:" + part);
		long size = this.size();
		List<Range<Long>> rtnValue = new ArrayList<Range<Long>>();
		long start = 0;
		long end = part;
		while(end < size){
			rtnValue.add(new Range<Long>(start, end));
			start = end;
			end = end + part;
		}
		rtnValue.add(new Range<Long>(start, size));
		return rtnValue;
	}

}
