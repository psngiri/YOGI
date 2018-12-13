package yogi.report.server.tuple.io;

import java.util.ArrayList;

public class LineRecordReader extends RecordReader{
	private static byte newLineByte = (byte) '\n';
	private static byte carrageReturnByte = (byte) '\r';
	private byte separator = (byte) ',';
	private ArrayList<Integer> separatorIndexes = new ArrayList<Integer>();
	
	public LineRecordReader(byte separator) {
		super();
		this.separator = separator;
	}

	public ArrayList<Integer> getSeparatorIndexes() {
		return separatorIndexes;
	}

	@Override
	public char[] getCharArray(int start, int end, boolean trim) {
		if(start < 0) start = 0;
		if(end >= separatorIndexes.size()) end = separatorIndexes.size()-1;
		start = separatorIndexes.get(start);
		end = separatorIndexes.get(end)-1;
		if (end < start) return new char[0];
		return super.getCharArray(start, end, trim);
	}
	
	@Override
	public byte[] getByteArray(int start, int end) {
		if(start < 0) start = 0;
		if(end >= separatorIndexes.size()) end = separatorIndexes.size()-1;
		start = separatorIndexes.get(start);
		end = separatorIndexes.get(end);
		if (end < start) return new byte[0];
		return super.getByteArray(start, end);
	}

	protected long extension(long read) {
		separatorIndexes.clear();
		separatorIndexes.add(0);
		for(int j = 0; j < read; j++){
			recordByteBuffer.position(j);
			byte b = recordByteBuffer.get();
			if(b==newLineByte){
				if(j !=0) {
					recordByteBuffer.position(j-1);
				}
				if(recordByteBuffer.get()==carrageReturnByte)
				{
					separatorIndexes.add(j);
					
				}else {
					separatorIndexes.add(j+1);
				}
				return j+1;
			}
			if(b == separator){
				separatorIndexes.add(j+1);
			}
		}

		return read;
	}

}
